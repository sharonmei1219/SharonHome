package com.sharonhome.sudoku.controller;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.NodeList;

import com.google.gson.Gson;
import com.sharonhome.sudoku.repository.PuzzleDao;

@Controller
public class SudokuController {
	private Gson gson = new Gson();
	@Autowired
	PuzzleDao puzzleDao;
	
	@RequestMapping(value = "/sudoku", method = RequestMethod.GET)
	public String sudokuPuzzle(ModelMap model) {
		model.addAttribute("tableSize", 9);
		model.addAttribute("blockSize", 3);
		return "sudoku";
 	}
	
	@RequestMapping(value = "/sudoku4Times4", method = RequestMethod.GET)
	public String sudokuPuzzle4Times4(ModelMap model){
		model.addAttribute("tableSize", 4);
		model.addAttribute("blockSize", 4);
		return "sudoku";
	}
	
	@RequestMapping(value = "/sudoku/new", 
			        method = RequestMethod.POST,
			        headers = {"Content-type=application/json"})
	@ResponseBody
	public String NewPuzzle(@RequestBody Level l){
		System.out.print("\n " + "getting puzzle \n");
		String puzzle = puzzleDao.getPuzzle(l.getLevel());
		System.out.print("\n " + l.getLevel() + "puzzle is : " + puzzle + "\n");
		return puzzle;
	}
	
	@RequestMapping(value = "/sudoku/help", 
	        method = RequestMethod.POST,
	        headers = {"Content-type=application/json"})
	@ResponseBody
	public String help(@RequestBody String puzzleString){
//	System.out.println("\n " + "help request received \n");
//	System.out.println(puzzleString);
//	return "help response";
		
		String url = "http://127.0.0.1:8008/";
		try {
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();
			SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(puzzleString), url);
			SOAPBody body = soapResponse.getSOAPBody();
			NodeList returnList = body.getElementsByTagName("hint");

			System.out.println(returnList.item(0).getTextContent());
			return returnList.item(0).getTextContent();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	
	private static SOAPMessage createSOAPRequest(String puzzle) throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();
		String serverURI = "http://127.0.0.1:8008/";
		
		SOAPEnvelope envelope = soapPart.getEnvelope();
		envelope.addNamespaceDeclaration("example", serverURI);
		
		SOAPBody soapBody = envelope.getBody();
		SOAPElement adder = soapBody.addChildElement("SudokuHelp");
		SOAPElement a = adder.addChildElement("puzzle");
		a.addTextNode(puzzle);
		
		soapMessage.saveChanges();
		return soapMessage;
	}
	
	@RequestMapping(value = "matchGame", method = RequestMethod.GET)
	public String showMatchGame(ModelMap model){
		return "redirect:/static";
	}
}