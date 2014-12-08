package com.sharonhome.sudoku.controller;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;
import org.python.util.PythonInterpreter;
import org.w3c.dom.NodeList;

import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

public class TestCallingPython {

	@Test
	public void test() throws UnsupportedOperationException, SOAPException {
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		
		String url = "http://127.0.0.1:8008/";
		try {
			SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(), url);
			SOAPBody body = soapResponse.getSOAPBody();
			NodeList returnList = body.getElementsByTagName("hint");

			System.out.println(returnList.item(0).getTextContent());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private static SOAPMessage createSOAPRequest() throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();
		String serverURI = "http://127.0.0.1:8008/";
		
		SOAPEnvelope envelope = soapPart.getEnvelope();
		envelope.addNamespaceDeclaration("example", serverURI);
		
		SOAPBody soapBody = envelope.getBody();
		SOAPElement adder = soapBody.addChildElement("SudokuHelp");
		SOAPElement a = adder.addChildElement("puzzle");
		a.addTextNode("please help me");
		
		soapMessage.saveChanges();
		return soapMessage;
	}
}
