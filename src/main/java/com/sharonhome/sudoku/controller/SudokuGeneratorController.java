package com.sharonhome.sudoku.controller;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sharonhome.sudoku.generator.Permutations;
import com.sharonhome.sudoku.generator.PuzzleGenerator;
import com.sharonhome.sudoku.generator.SudokuSolver;
import com.sharonhome.sudoku.model.GeneratorStatus;
import com.sharonhome.sudoku.model.Progress;
import com.sharonhome.sudoku.model.StartParameter;
import com.sharonhome.sudoku.ranking.Puzzle;
import com.sharonhome.sudoku.ranking.PuzzleRanking;
import com.sharonhome.sudoku.repository.PuzzleDao;

@Controller
public class SudokuGeneratorController {

	@Autowired
	private PuzzleDao puzzleDao;
	public void setPuzzleDao(PuzzleDao puzzleDao){
		this.puzzleDao = puzzleDao;
	}
	
	private HelperGeneratorInterface helperGen = new HelperGenerator();
	public void setHelperGenerator(HelperGeneratorInterface helper){
		this.helperGen = helper;
	}

	@RequestMapping(value = "/sudokuGenerator", method = RequestMethod.GET)
	public String loadPage(ModelMap model) {
		newProgress.newProgress(0);
		newProgress.setStoredEasy(puzzleDao.numberOfPuzzle("easy"));
		newProgress.setStoredNormal(puzzleDao.numberOfPuzzle("normal"));
		newProgress.setStoredHard(puzzleDao.numberOfPuzzle("hard"));
		newProgress.setStoredEvil(puzzleDao.numberOfPuzzle("evil"));
		model.addAttribute("progress", newProgress);
		return "sudokuGenerator";
	}
	
	private Progress newProgress = new Progress();

	@RequestMapping(value = "/generationProgress", method = RequestMethod.GET)
	public @ResponseBody
	Progress getProgress() {

		newProgress.setWarning(warning);

		if (newProgress.progressEnd()) {
			newProgress.setInProgress(false);
		} else {
			newProgress.setInProgress(true);
		}
		return newProgress;
	}

	private String warning = "";
	
	@RequestMapping(value = "/startGeneration", method = RequestMethod.POST)
	public @ResponseBody String startGeneration(@RequestBody StartParameter sp) {
		int total = sp.getNumberOfPuzzleToGenerate();
		newProgress.newProgress(total);
		
		int numberOfHoles = sp.getNumberOfHolesInPuzzle();
		for(int i = 0; i < total; i++){
//			Puzzle originPuzzle = helperGen.generateOriginalPuzzle(numberOfHoles);
			Puzzle originPuzzle = helperGen.generatorPuzzle(numberOfHoles);
			if(originPuzzle == null) continue;

			String rank = helperGen.rank(originPuzzle);
			if(rank.equals("bruteForce")) continue;
			
			Puzzle permedPuzzle = helperGen.permutate(originPuzzle);

			if(!helperGen.validatePermedPuzzle(permedPuzzle)) {
				alert("invalid puzzle generated");
				continue;
			}
			try{
//				System.out.println(rank);
//				System.out.println(permedPuzzle.toString());
//				System.out.println();
				puzzleDao.insertPuzzle(rank, permedPuzzle.toString());
			}catch(Exception e){

				alert(e.toString());
			}

			newProgress.inc(rank);
		}
		return new String("Done!");
	}

	private void alert(String string) {
		warning += "\n" + string;
	}
}
