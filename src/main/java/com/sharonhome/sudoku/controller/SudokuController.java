package com.sharonhome.sudoku.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

@Controller
@RequestMapping("/sudoku")
public class SudokuController {
 
	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		String [][] puzzle = {{"","","4","7","","3","","",""},
							  {"","5","","","","6","8","",""},
							  {"6","7","","8","4","","","2","3"},
							  {"","","","1","8","2","","",""},
							  {"","","7","","","","2","",""},
							  {"","","","6","7","5","","",""},
							  {"3","8","","","2","7","","4","9"},
							  {"","","5","4","","","","8",""},
							  {"","","","9","","8","3","",""}};
		Gson gson = new Gson();
		String jsonPuzzle = gson.toJson(puzzle);
 		model.addAttribute("puzzle", jsonPuzzle);
		return "sudoku";
 	}
}