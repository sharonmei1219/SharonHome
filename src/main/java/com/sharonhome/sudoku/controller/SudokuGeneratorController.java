package com.sharonhome.sudoku.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sharonhome.sudoku.model.GeneratorStatus;
import com.sharonhome.sudoku.model.Progress;
import com.sharonhome.sudoku.model.StartParameter;

@Controller
public class SudokuGeneratorController {

	@RequestMapping(value = "/sudokuGenerator", method = RequestMethod.GET)
	public String sudokuPuzzle(ModelMap model) {

		Progress progress = new Progress();
		progress.setTotal(100);
		progress.setEasy(0);
		progress.setNormal(0);
		progress.setHard(0);
		progress.setEvil(0);
		model.addAttribute("progress", progress);
		return "sudokuGenerator";
	}

	private int easy = 10;
	private int hard = 20;

	@RequestMapping(value = "/generationProgress", method = RequestMethod.GET)
	public @ResponseBody
	Progress getProgress() {
		easy += inc;
		hard += inc;
		Progress progress = new Progress();
		if (easy + hard + 5 + 7 >= 100) {
			progress.setInProgress(false);
		} else {
			progress.setInProgress(true);
		}
		progress.setTotal(100);
		progress.setEasy(easy);
		progress.setNormal(5);
		progress.setHard(hard);
		progress.setEvil(7);
		return progress;
	}
	private boolean notStop = true;
	private int inc = 0;
	
	@RequestMapping(value = "/startGeneration", method = RequestMethod.POST)
	public @ResponseBody String startGeneration(@RequestBody StartParameter sp) {
		inc = 1;
		System.out.println("sp received " + sp.toString());

		return new String("get it");
	}
}
