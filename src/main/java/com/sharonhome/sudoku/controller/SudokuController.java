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
//		String puzzle = puzzleDao.getPuzzle("normal");
// 		model.addAttribute("puzzle", puzzle);
		return "sudoku";
 	}
	
	@RequestMapping(value = "/sudoku/new", 
			        method = RequestMethod.POST,
			        headers = {"Content-type=application/json"})
	@ResponseBody
	public String NewPuzzle(@RequestBody Level l){

		String puzzle = puzzleDao.getPuzzle(l.getLevel());
		System.out.print("\n " + l.getLevel() + "puzzle is : " + puzzle + "\n");
		return puzzle;
	}
}