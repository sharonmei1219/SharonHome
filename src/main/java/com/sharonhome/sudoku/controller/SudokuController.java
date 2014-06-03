package com.sharonhome.sudoku.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@Controller
//@RequestMapping(value = "/sudoku")
//public class SudokuController {
//	@RequestMapping(method = RequestMethod.GET)
//	public String printWelcome(ModelMap model) {
// 		model.addAttribute("message", "Spring 3 MVC Hello World");
//		return "hello";
// 	}
//}

@Controller
@RequestMapping("/sudoku")
public class SudokuController {
 
	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
 
		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "hello";
 
	}
 
}