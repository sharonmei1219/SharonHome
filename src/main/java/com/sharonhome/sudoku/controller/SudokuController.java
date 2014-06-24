package com.sharonhome.sudoku.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
public class SudokuController {
	private Gson gson = new Gson();
 
	@RequestMapping(value = "/sudoku", method = RequestMethod.GET)
	public String sudokuPuzzle(ModelMap model) {
		String [][] puzzle = {{"","","4","7","","3","","",""},
							  {"","5","","","","6","8","",""},
							  {"6","7","","8","4","","","2","3"},
							  {"","","","1","8","2","","",""},
							  {"","","7","","","","2","",""},
							  {"","","","6","7","5","","",""},
							  {"3","8","","","2","7","","4","9"},
							  {"","","5","4","","","","8",""},
							  {"","","","9","","8","3","",""}
							  };
		String jsonPuzzle = gson.toJson(puzzle);
 		model.addAttribute("puzzle", jsonPuzzle);
		return "sudoku";
 	}
	
	@RequestMapping(value = "/sudoku/new", 
			        method = RequestMethod.POST,
			        headers = {"Content-type=application/json"})
	@ResponseBody
	public String NewPuzzle(@RequestBody Level l){
		String [][] puzzle = {{ "", "" , "" , "" , "5", "3", "" , "" , ""},
							  {"9", "5", "3",  "", "1",  "", "8",  "",  ""},
							  { "",  "", "1",  "", "4",  "",  "", "2", "3"},
							  { "", "" , "" , "", "" , "2", "7", "3",  ""},
							  { "", "",  "",  "", "9", "4", "2",  "", "8"},
							  { "", "",  "", "6", "7",  "",  "",  "",  ""},
							  {"3", "", "6", "",  "",  "", "1",  "",  ""},
							  {"7", "9", "5", "4", "3",  "",  "",  "",  ""},
							  { "", "4",  "", "9",  "", "8",  "",  "",  "" }};
		String jsonPuzzle = gson.toJson(puzzle);
		System.out.println("what is that" + l.toString());
		return jsonPuzzle;
	}
}