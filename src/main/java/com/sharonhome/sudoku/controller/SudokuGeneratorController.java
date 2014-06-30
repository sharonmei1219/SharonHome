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
	PuzzleDao puzzleDao;

	@RequestMapping(value = "/sudokuGenerator", method = RequestMethod.GET)
	public String sudokuPuzzle(ModelMap model) {
		storedEasy = puzzleDao.numberOfPuzzle("easy");
		storedNormal = puzzleDao.numberOfPuzzle("normal");
		storedHard = puzzleDao.numberOfPuzzle("hard");
		storedEvil = puzzleDao.numberOfPuzzle("evil");
		Progress progress = new Progress();
		progress.setTotal(100);
		progress.setEasy(0);
		progress.setNormal(0);
		progress.setHard(0);
		progress.setEvil(0);
		progress.setStoredEasy(storedEasy);
		progress.setStoredNormal(storedNormal);
		progress.setStoredHard(storedHard);
		progress.setStoredEvil(storedEvil);
		model.addAttribute("progress", progress);
		return "sudokuGenerator";
	}

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
			Puzzle originPuzzle = generateOriginalPuzzle(numberOfHoles);
			if(originPuzzle == null) continue;
			String rank = rank(originPuzzle);
			Puzzle permedPuzzle = permutate(originPuzzle);
			if(!validatePermedPuzzle(permedPuzzle)) {
				alert("invalid puzzle generated");
				continue;
			}
			ApplicationContext context = new ClassPathXmlApplicationContext(
					"EasyPuzzleTempGen_context.xml");
			try{
				puzzleDao.insertPuzzle(rank, permedPuzzle.toString());
			}catch(Exception e){
				System.out.println(e);
				alert(e.toString());
			}
			System.out.println(i);
			updateProgress(rank);
		}
		return new String("Done!");
	}

	private void alert(String string) {
		warning += "\n" + string;
		
	}

	private boolean validatePermedPuzzle(Puzzle puzzle) {
		if(! puzzle.validate()) return false;
		SudokuSolver solver = new SudokuSolver(3);
		solver.setSolutionCandidates(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
		ArrayList<int[][]>solutions = new ArrayList<int[][]>();
		solver.solve(puzzle.puzzle, solutions);
		if(solutions.size() != 1) return false;
		return true;
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
	
	private Puzzle generateOriginalPuzzle(int numberOfHoles){
		Random rand = new Random();
		PuzzleGenerator gen = new PuzzleGenerator();
		int tableIndex = rand.nextInt(CSLCR_MAX);
		int [][] puzzle = gen.generatePuzzle(tableIndex, numberOfHoles);
		if(puzzle == null) return null;
		return  new Puzzle(puzzle);
	}
	
	private String rank(Puzzle puzzle){
		PuzzleRanking ranking = new PuzzleRanking();
		int rank =  ranking.ranking(puzzle);
		if(rank <= 7){
			return "easy";
		}else if(rank <= 13){
			return "normal";
		}else if(rank <= 20){
			return "hard";
		}else if(rank <= 500){
			return "evil";
		}
		return "bruteForce";
	}
	
	private Puzzle permutate(Puzzle inputPuzzle){
		Random rand = new Random();
		Permutations permutations = new Permutations(new int [] {1, 2, 3, 4, 5, 6, 7, 8, 9});
		int perIndex = rand.nextInt(permutations.total());
		int [] permutation = permutations.get(perIndex);
		return inputPuzzle.permutate(permutation);
	}
}
