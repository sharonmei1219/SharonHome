package com.sharonhome.sudoku.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sharonhome.sudoku.generator.PuzzleGenerator;
import com.sharonhome.sudoku.model.GeneratorStatus;
import com.sharonhome.sudoku.model.Progress;
import com.sharonhome.sudoku.model.StartParameter;
import com.sharonhome.sudoku.ranking.Puzzle;
import com.sharonhome.sudoku.ranking.PuzzleRanking;

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
	private int normal;
	private int evil;
	private int total;

	@RequestMapping(value = "/generationProgress", method = RequestMethod.GET)
	public @ResponseBody
	Progress getProgress() {
		easy += inc;
		hard += inc;
		Progress progress = new Progress();
		if (easy + hard + normal + evil > total) {
			progress.setInProgress(false);
		} else {
			progress.setInProgress(true);
		}
		progress.setTotal(total);
		progress.setEasy(easy);
		progress.setNormal(normal);
		progress.setHard(hard);
		progress.setEvil(evil);
		return progress;
	}
	private boolean notStop = true;
	private int inc = 0;
	
	@RequestMapping(value = "/startGeneration", method = RequestMethod.POST)
	public @ResponseBody String startGeneration(@RequestBody StartParameter sp) {
		System.out.println("sp received " + sp.toString());
		total = sp.getNumberOfPuzzleToGenerate();
		easy = 0;
		normal = 0;
		hard = 0;
		evil = 0;
		int numberOfHoles = sp.getNumberOfHolesInPuzzle();
		Random rand = new Random();
		PuzzleRanking ranking = new PuzzleRanking();
		PuzzleGenerator gen = new PuzzleGenerator();
		for(int i = 0; i < total; i++){
			int tableIndex = rand.nextInt(46656);
			int [][] puzzle = gen.generatePuzzle(tableIndex, numberOfHoles);
			if(puzzle == null) continue;;
			Puzzle inputPuzzle = new Puzzle(puzzle);
			int rank = ranking.ranking(inputPuzzle);
			if(rank <= 7){
				easy ++;
			}else if(rank <= 13){
				normal ++;
			}else if(rank <= 20){
				hard ++;
			}else{
				evil ++;
			}
		}
		return new String("Done!");
	}
}
