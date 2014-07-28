package com.sharonhome.sudoku.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AboutController {

	@RequestMapping(value = "/aboutSudoku", method = RequestMethod.GET)
	public String getAbout(){
		return "aboutSudoku";
	}
}
