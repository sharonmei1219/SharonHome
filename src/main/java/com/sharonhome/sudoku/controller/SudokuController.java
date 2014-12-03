package com.sharonhome.sudoku.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sharonhome.sudoku.repository.PuzzleDao;

@Controller
public class SudokuController {
	private Gson gson = new Gson();
	@Autowired
	PuzzleDao puzzleDao;
	
	@RequestMapping(value = "/sudoku", method = RequestMethod.GET)
	public String sudokuPuzzle(ModelMap model) {
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
	public String help(@RequestBody Level l){
	System.out.print("\n " + "help request received \n");
	return "help response";
}
}