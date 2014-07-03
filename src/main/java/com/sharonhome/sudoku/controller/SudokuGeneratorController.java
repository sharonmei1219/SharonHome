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

	private static final int CSLCR_MAX = 46656;
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
		newProgress.setTotal(100);
		newProgress.setEasy(0);
		newProgress.setNormal(0);
		newProgress.setHard(0);
		newProgress.setEvil(0);
		newProgress.setStoredEasy(puzzleDao.numberOfPuzzle("easy"));
		newProgress.setStoredNormal(puzzleDao.numberOfPuzzle("normal"));
		newProgress.setStoredHard(puzzleDao.numberOfPuzzle("hard"));
		newProgress.setStoredEvil(puzzleDao.numberOfPuzzle("evil"));
		storedEasy = newProgress.getStoredEasy();
		storedNormal = newProgress.getStoredNormal();
		storedHard =  newProgress.getStoredHard();
		storedEvil = newProgress.getStoredEvil();
		model.addAttribute("progress", newProgress);
		return "sudokuGenerator";
	}
	
	private Progress newProgress = new Progress();

	private int storedEasy;
	private int storedNormal;
	private int storedHard;
	private int storedEvil;
	private int easy;
	private int hard;
	private int normal;
	private int evil;
	private int total;

	@RequestMapping(value = "/generationProgress", method = RequestMethod.GET)
	public @ResponseBody
	Progress getProgress() {
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
		progress.setStoredEasy(storedEasy + easy);
		progress.setStoredNormal(storedNormal + normal);
		progress.setStoredHard(storedHard + hard);
		progress.setStoredEvil(storedEvil + evil);
		progress.setWarning(warning);

		return progress;
	}

	private String warning = "";
	
	@RequestMapping(value = "/startGeneration", method = RequestMethod.POST)
	public @ResponseBody String startGeneration(@RequestBody StartParameter sp) {
		initProgress(sp);
		
		int numberOfHoles = sp.getNumberOfHolesInPuzzle();
		for(int i = 0; i < total; i++){
			Puzzle originPuzzle = helperGen.generateOriginalPuzzle(numberOfHoles);
			if(originPuzzle == null) continue;
			String rank = helperGen.rank(originPuzzle);
			Puzzle permedPuzzle = helperGen.permutate(originPuzzle);
			if(!helperGen.validatePermedPuzzle(permedPuzzle)) {
				alert("invalid puzzle generated");
				continue;
			}
			try{
				System.out.println(permedPuzzle.toString());
				puzzleDao.insertPuzzle(rank, permedPuzzle.toString());
			}catch(Exception e){
				System.out.println(e);
				alert(e.toString());
			}

			updateProgress(rank);
		}
		return new String("Done!");
	}

	private void alert(String string) {
		warning += "\n" + string;
		
	}

	private void initProgress(StartParameter sp) {
		total = sp.getNumberOfPuzzleToGenerate();
		easy = 0;
		normal = 0;
		hard = 0;
		evil = 0;
		warning = "";
	}

	private void updateProgress(String rank) {
		if(rank.equals("easy")){
			easy ++;
		}else if(rank.equals("normal")){
			normal ++;
		}else if(rank.equals("hard")){
			hard ++;
		}else if(rank.equals("evil")){
			evil ++;
		}
	}
}
