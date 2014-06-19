package com.sharonhome.sudoku.ranking;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

import com.sharonhome.sudoku.generator.CSLCRPerGenerator;
import com.sharonhome.sudoku.generator.CSLCRSetGenerator;
import com.sharonhome.sudoku.generator.CSLCRSetNumberingSystem;
import com.sharonhome.sudoku.generator.Digger;
import com.sharonhome.sudoku.generator.HolesCandidate;
import com.sharonhome.sudoku.generator.PuzzleGenerator;
import com.sharonhome.sudoku.generator.RandomNumberGen;
import com.sharonhome.sudoku.generator.RandomNumberGenbyRandom;
import com.sharonhome.sudoku.generator.RandomSpotSeq;
import com.sharonhome.sudoku.generator.SetSet;
import com.sharonhome.sudoku.generator.Spot;
import com.sharonhome.sudoku.generator.SudokuSolver;
import com.sharonhome.sudoku.generator.SudokuTableTemplateGenerator;

public class TestRanking {
	PuzzleRanking ranking = new PuzzleRanking();

	@Test
	public void test() {
		PuzzleGenerator gen = new PuzzleGenerator();
		int [][] puzzle = gen.generatePuzzle(100, 44);
		printPuzzle(puzzle);
		Puzzle inputPuzzle = new Puzzle(puzzle);
		int rank = ranking.ranking(inputPuzzle);
		System.out.println();
		System.out.println("rank is " + rank);
	}
	
	@Test
	public void testCanBeSolvedByNakedPair(){
	Puzzle inputPuzzle = new Puzzle(new int [][]{
							{-1, -1,  2, -1, -1, -1, -1,  6, -1}, 
							{-1,  3, -1,  8, -1,  7, -1, -1,  1}, 
							{ 7, -1, -1, -1,  0, -1, -1, -1, -1}, 
							{-1,  1, -1, -1, -1, -1, -1, -1,  8}, 
							{-1, -1,  6, -1, -1, -1,  3, -1,  2}, 
							{-1, -1,  0, -1, -1,  2, -1,  4, -1}, 
							{-1, -1,  4, -1,  5, -1, -1, -1, -1}, 
							{ 6, -1,  7, -1, -1, -1,  4, -1,  3}, 
							{-1,  8,  1, -1, -1,  3,  7,  5, -1}});
	System.out.println();
	System.out.println("solved by naked rank is " + ranking.ranking(inputPuzzle));
	}
	
	@Test
	public void testSolveAComplicatedPuzzle(){
		Puzzle inputPuzzle = new Puzzle(new int [][] {
				{-1,  0,  2, -1, -1, -1,  8, -1,  7}, 
				{-1, -1, -1, -1,  6, -1, -1, -1, -1}, 
				{-1, -1, -1,  2, -1,  1, -1, -1, -1}, 
				{-1, -1, -1,  6,  4, -1,  0, -1, -1}, 
				{-1, -1, -1, -1,  7,  8, -1, -1, -1}, 
				{-1, -1, -1,  3, -1,  2, -1,  4,  5}, 
				{ 3, -1, -1, -1, -1,  6,  1, -1, -1}, 
				{-1,  5,  7, -1,  8,  0, -1,  2,  3}, 
				{-1,  8, -1, -1, -1, -1,  7, -1, -1} 
		});
		System.out.println();
		System.out.println("notsolved rank is " + ranking.ranking(inputPuzzle));
	}
	
	void printPuzzle(int [][] puzzle){
		for(int i = 0; i < 9; i ++){
			System.out.println();
			for(int j = 0; j < 9; j++){
				System.out.print(puzzle[i][j]);
				System.out.print(", ");
			}
		}
	}
}
