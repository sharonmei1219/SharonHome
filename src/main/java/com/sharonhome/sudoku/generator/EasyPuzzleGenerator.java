package com.sharonhome.sudoku.generator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;
import com.sharonhome.sudoku.repository.PuzzleDao;

public class EasyPuzzleGenerator {
	public static void main(String[] args) {
		Gson gson = new Gson();
		
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"EasyPuzzleTempGen_context.xml");
		PuzzleDao puzzleDao = (PuzzleDao) context.getBean("puzzleDao");
		SetSet ss = new SetSet(new int[][] { { 0, 3, 6 }, { 1, 4, 7 },
				{ 2, 5, 8 } });
		CSLCRSetNumberingSystem setNs = new CSLCRSetNumberingSystem();
		CSLCRSetGenerator setGen = new CSLCRSetGenerator(ss, setNs);

		CSLCRPerGenerator perGen = new CSLCRPerGenerator(setGen);

		HolesCandidate candidates = new HolesCandidate(9, 9);
		RandomNumberGen rand = new RandomNumberGenbyRandom();
		Digger digger = new Digger();

		SudokuSolver solver = new SudokuSolver(3);
		solver.setSolutionCandidates(new int []{0, 1, 2, 3, 4, 5, 6, 7, 8});

		SudokuTableTemplateGenerator tableGen = new SudokuTableTemplateGenerator();

		int totalCslcrPermutations = perGen.total();
		for (int i = 0; i < totalCslcrPermutations; i++) {
			int[] cslcr = perGen.getNthPerCSLCR(i);

			int[][] table = tableGen.genTable(cslcr);
			for (int count = 0; count < 2; count++) {
				RandomSpotSeq randomSpotSeq = new RandomSpotSeq(candidates,
						rand);
				Spot[] holes = digger.dig(table, solver, randomSpotSeq, 58);
				if(holes == null) continue;
				int[][] puzzle = digger.eraseHoles(table, holes);
				String puzzleInString = gson.toJson(puzzle);
				System.out.println(puzzleInString);
				System.out.println();
			}
		}
	}

	private static void printPuzzle(int[][] puzzle) {
		System.out.println();
		for (int i = 0; i < 9; i++) {
			System.out.println();
			for (int j = 0; j < 9; j++) {
				if (puzzle[i][j] < 0) {
					System.out.print(" ");
				} else {
					System.out.print(puzzle[i][j]);
				}
				System.out.print(", ");
			}
		}
	}
}
