package com.sharonhome.sudoku.generator;

public class PuzzleGenerator {
	SetSet ss = new SetSet(new int[][] { { 0, 3, 6 }, { 1, 4, 7 },
			{ 2, 5, 8 } });
	CSLCRSetNumberingSystem setNs = new CSLCRSetNumberingSystem();
	CSLCRSetGenerator setGen = new CSLCRSetGenerator(ss, setNs);

	CSLCRPerGenerator perGen = new CSLCRPerGenerator(setGen);

	HolesCandidate candidates = new HolesCandidate(9, 9);
	RandomNumberGen rand = new RandomNumberGenbyRandom();
	Digger digger = new Digger();

	SudokuSolver solver = new SudokuSolver(3);
	SudokuTableTemplateGenerator tableGen = new SudokuTableTemplateGenerator();
	
	public PuzzleGenerator(){
		solver.setSolutionCandidates(new int []{0, 1, 2, 3, 4, 5, 6, 7, 8});
	}
	
	public int [][] generatePuzzle(int cslcrNum, int holeCount){
		int [][] result = null;
		
		int[] cslcr = perGen.getNthPerCSLCR(cslcrNum);
		int[][] table = tableGen.genTable(cslcr);
		RandomSpotSeq randomSpotSeq = new RandomSpotSeq(candidates,
						rand);
		Spot[] holes = digger.dig(table, solver, randomSpotSeq, holeCount);
		if(holes == null) return result;
		result = digger.eraseHoles(table, holes);
		
		return result;
	}
}
