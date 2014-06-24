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
		Random rand = new Random();
		int tableIndex = rand.nextInt(46656);
		PuzzleGenerator gen = new PuzzleGenerator();
		int [][] puzzle = gen.generatePuzzle(tableIndex, 57);
		if(puzzle == null) return;
		Puzzle inputPuzzle = new Puzzle(puzzle);
		int rank = ranking.ranking(inputPuzzle);

		System.out.println(inputPuzzle);
		System.out.println();
		System.out.println("rank is " + rank);
		System.out.println();

	}
	
	@Test
	public void testRankIs4(){
		Puzzle inputPuzzle = new Puzzle(new int [][]{
				{-1, -1, -1, -1, 3, -1, -1, -1, -1}, 
				{4, 3, -1, 8, -1, -1, -1, -1, -1}, 
				{-1, -1, -1, -1, 0, 1, 5, -1, -1}, 
				{2, -1, -1, -1, -1, 5, 0, 7, -1}, 
				{5, 4, -1, -1, -1, 8, -1, -1, -1}, 
				{-1, -1, -1, 3, -1, -1, -1, 4, -1}, 
				{-1, 2, -1, -1, 5, -1, -1, -1, -1}, 
				{-1, -1, 7, -1, -1, -1, -1, -1, 3}, 
				{0, -1, 1, -1, 2, 3, 7, -1, -1}});
		System.out.println("Solved: rank is 4 " + ranking.ranking(inputPuzzle));
	}
	
	@Test
	public void testRankIs7(){
		Puzzle inputPuzzle = new Puzzle(new int [][]{
		{-1, -1, -1, -1, -1, -1, -1, -1, 5}, 
		{-1, -1, -1, -1, 3, -1, 1, 6, -1}, 
		{-1, -1, -1, 7, -1, 5, -1, 0, 2}, 
		{-1, 3, -1, 2, -1, -1, 8, 4, 6}, 
		{8, -1, -1, -1, 4, 3, -1, 7, 0}, 
		{2, -1, -1, -1, -1, -1, -1, -1, -1}, 
		{-1, -1, 8, -1, -1, -1, 0, -1, 7}, 
		{-1, 7, -1, 6, -1, 4, -1, -1, -1}, 
		{-1, -1, 5, -1, -1, -1, -1, 2, -1}});
		System.out.println("Solved: rank is 7 " + ranking.ranking(inputPuzzle)); 
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
	System.out.println("Solved: Naked Pair " + ranking.ranking(inputPuzzle));
	}
	
	@Test
	public void testPuzzleCanBeSolvedByHiddenPair(){
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
		System.out.println("Solved: Naked Pair " + ranking.ranking(inputPuzzle));
	}
	@Test
	public void testPuzzleRank12(){
		Puzzle inputPuzzle = new Puzzle(new int [][] {
				{1, -1, -1, -1, -1, -1, 8, -1, -1}, 
				{-1, 3, 5, -1, -1, 7, 2, -1, -1}, 
				{-1, -1, -1, -1, 0, -1, 5, -1, -1}, 
				{-1, -1, -1, -1, -1, 5, -1, 7, -1}, 
				{-1, -1, 6, -1, -1, -1, 3, -1, -1}, 
				{8, 7, -1, -1, -1, -1, 6, 4, -1}, 
				{3, -1, -1, 7, -1, -1, -1, -1, -1}, 
				{-1, 5, -1, -1, -1, 0, -1, 2, -1}, 
				{-1, -1, 1, 4, 2, -1, -1, -1, 6}, 
		});

		System.out.println("Solved: rank = 12 " + ranking.ranking(inputPuzzle));
	}

	
	@Test
	public void test2ndPuzzleHasHiddenPairHaveLockedButCantBeSolved(){
		Puzzle inputPuzzle = new Puzzle(new int [][] {
				{1, -1, -1, -1, 3, -1, -1, 6, -1}, 
				{-1, 3, 5, -1, -1, 7, -1, -1, -1}, 
				{-1, -1, -1, 2, -1, -1, 5, -1, -1}, 
				{2, -1, 3, -1, 4, -1, -1, 7, -1}, 
				{-1, 4, 6, -1, -1, 8, -1, -1, -1}, 
				{-1, 7, -1, -1, 1, -1, 6, -1, -1}, 
				{-1, -1, -1, -1, -1, -1, -1, -1, 0}, 
				{6, 5, -1, 1, 8, 0, -1, -1, 3}, 
				{-1, 8, -1, -1, -1, -1, 7, -1, -1} 
		});
		System.out.println();
		System.out.println("Solved: Naked Pair + Hidden Pair + Locked " + ranking.ranking(inputPuzzle));
	}
	
	@Test
	public void testSolvedRank6No2(){
		Puzzle inputPuzzle = new Puzzle(new int [][] {
				{-1, 0, -1, -1, -1, -1, 8, -1, -1}, 
				{4, -1, -1, 8, 6, 7, -1, -1, -1}, 
				{-1, -1, -1, -1, -1, 1, 5, -1, -1}, 
				{-1, 1, -1, 6, -1, -1, -1, -1, 8}, 
				{5, 4, -1, 0, -1, -1, -1, 1, -1}, 
				{-1, -1, -1, -1, -1, -1, 6, 4, -1}, 
				{3, 2, 4, -1, -1, -1, -1, -1, 0}, 
				{-1, 5, -1, -1, -1, -1, -1, -1, -1}, 
				{-1, -1, -1, -1, 2, 3, 7, -1, -1}, 
		});
		System.out.println("Solved: Naked Pair + Hidden Pair + Locked " + ranking.ranking(inputPuzzle));
	}
	
	@Test
	public void test1stPuzzleHasHiddenPairAndBlockButCantBeSolved(){
		Puzzle inputPuzzle = new Puzzle(new int [][] {
				{-1, 0, -1, -1, -1, 4, 8, -1, 7}, 
				{-1, 3, -1, -1, 6, -1, -1, -1, -1}, 
				{-1, -1, -1, 2, -1, -1, -1, -1, 4}, 
				{2, 1, -1, -1, 4, -1, 0, -1, -1}, 
				{-1, -1, 6, -1, 7, -1, -1, -1, -1}, 
				{8, -1, -1, -1, -1, -1, -1, -1, 5}, 
				{-1, -1, -1, 7, 5, 6, -1, 8, 0}, 
				{-1, -1, 7, 1, 8, -1, -1, -1, -1}, 
				{-1, -1, 1, -1, -1, -1, -1, 5, 6} 
		});
		System.out.println();
		int rank = ranking.ranking(inputPuzzle);
		System.out.println("Not Solved #1 " + rank);
	}
	
	@Test
	public void puzzleCanBeSolvedByNakedPairHiddenPairAndLockedCell(){
		Puzzle inputPuzzle = new Puzzle(new int [][] {
								{-1, 0, -1, -1, -1, -1, -1, -1, -1}, 
								{4, -1, -1, -1, 6, 7, 2, -1, -1}, 
								{-1, -1, 8, 2, -1, -1, -1, -1, 4}, 
								{2, -1, 3, -1, -1, -1, 0, -1, -1}, 
								{5, 4, -1, -1, -1, -1, -1, -1, -1}, 
								{-1, -1, -1, 3, 1, 2, -1, -1, -1}, 
								{-1, -1, -1, -1, 5, 6, -1, 8, 0}, 
								{-1, -1, -1, 1, -1, -1, -1, -1, -1}, 
								{-1, 8, 1, 4, -1, -1, -1, -1, 6}}); 

		System.out.println("Solved: pair or tripple " + ranking.ranking(inputPuzzle));

	}
	
	@Test
	public void puzzleNotSolved3(){
		Puzzle inputPuzzle = new Puzzle(new int [][] {
		{1, -1, -1, -1, -1, -1, 8, 6, -1}, 
		{-1, 3, -1, -1, -1, -1, -1, -1, -1}, 
		{-1, -1, -1, 2, -1, -1, 5, -1, 4}, 
		{2, -1, 3, -1, -1, -1, -1, 7, -1}, 
		{-1, -1, -1, 0, -1, 8, 3, 1, -1}, 
		{-1, 7, -1, -1, 1, -1, -1, -1, -1}, 
		{-1, -1, 4, -1, -1, 6, -1, 8, -1}, 
		{-1, 5, 7, -1, -1, 0, -1, -1, -1}, 
		{-1, -1, -1, 4, -1, -1, -1, -1, 6}}); 

		System.out.println("Not Solved #3 " + ranking.ranking(inputPuzzle));
	}
	
	@Test
	public void puzzleNotSolved4(){
		Puzzle inputPuzzle = new Puzzle(new int [][] {
		{-1, -1, -1, 5, -1, -1, -1, -1, -1}, 
		{-1, -1, 5, -1, -1, 7, -1, 0, 1}, 
		{-1, 6, -1, 2, -1, 1, -1, -1, -1}, 
		{-1, -1, -1, -1, -1, -1, 0, 7, -1}, 
		{-1, -1, 6, 0, -1, -1, -1, -1, 2}, 
		{8, -1, -1, -1, 1, -1, -1, -1, -1}, 
		{3, -1, -1, -1, 5, -1, 1, 8, -1}, 
		{-1, -1, 7, -1, -1, -1, -1, -1, 3}, 
		{0, -1, -1, -1, -1, 3, -1, 5, -1}}); 

		System.out.println("Not Solved #4 " + ranking.ranking(inputPuzzle)); 
	}
	
	@Test
	public void puzzleNotSolved5(){
		Puzzle inputPuzzle = new Puzzle(new int [][] {
				{-1, 0, -1, -1, 3, -1, -1, -1, 7}, 
				{4, -1, -1, 8, -1, -1, -1, 0, -1}, 
				{-1, -1, -1, -1, -1, -1, 5, -1, -1}, 
				{-1, -1, -1, 6, -1, 5, -1, 7, 8}, 
				{-1, -1, -1, -1, 7, -1, 3, 1, 2}, 
				{-1, -1, -1, -1, -1, -1, -1, -1, -1}, 
				{-1, 2, 4, -1, 5, -1, -1, -1, -1}, 
				{6, -1, -1, -1, -1, -1, -1, -1, 3}, 
				{-1, -1, 1, -1, 2, 3, 7, -1, -1}}); 

		System.out.println("Not Solved #5 " + ranking.ranking(inputPuzzle)); 
	}
	
	@Test
	public void puzzleNotSolved6(){
		Puzzle inputPuzzle = new Puzzle(new int [][] {
				{-1, -1, -1, -1, -1, -1, -1, 6, -1}, 
				{-1, 3, 5, -1, -1, 7, 2, -1, -1}, 
				{7, -1, -1, -1, -1, -1, 5, -1, 4}, 
				{-1, -1, -1, 6, -1, -1, -1, 7, 8}, 
				{5, 4, -1, -1, -1, -1, -1, -1, -1}, 
				{-1, -1, 0, -1, 1, -1, -1, -1, -1}, 
				{-1, -1, -1, -1, -1, -1, 1, -1, -1}, 
				{6, -1, -1, 1, -1, -1, 4, 2, -1}, 
				{0, 8, -1, -1, 2, -1, -1, -1, 6}}); 
		System.out.println("Solved: triple in a block  " + ranking.ranking(inputPuzzle)); 
	}
	

	@Test
	public void puzzleNotSolved7(){
		Puzzle inputPuzzle = new Puzzle(new int [][] {
				{-1, -1, 2, -1, 3, -1, -1, -1, -1}, 
				{-1, -1, 5, 8, -1, 7, -1, -1, 1}, 
				{-1, -1, -1, -1, 0, 1, -1, 3, -1}, 
				{-1, 1, -1, -1, -1, -1, 0, 7, 8}, 
				{-1, 4, 6, -1, -1, -1, -1, -1, -1}, 
				{8, -1, -1, -1, -1, 2, -1, -1, -1}, 
				{-1, -1, -1, 7, -1, -1, 1, -1, -1}, 
				{-1, -1, -1, 1, -1, 0, -1, 2, -1}, 
				{-1, -1, -1, -1, 2, -1, -1, 5, 6}}); 
		System.out.println("Not Solved #7 " + ranking.ranking(inputPuzzle)); 
	}
	
	@Test
	public void puzzleNotSolved8(){
		Puzzle inputPuzzle = new Puzzle(new int [][] {
				{1, -1, -1, 5, -1, -1, 8, -1, -1}, 
				{-1, -1, -1, -1, -1, -1, -1, 0, -1}, 
				{7, -1, 8, -1, 0, 1, -1, 3, -1}, 
				{-1, 1, -1, 6, -1, 5, 0, -1, -1}, 
				{5, -1, -1, -1, -1, -1, -1, -1, 2}, 
				{-1, -1, -1, -1, -1, -1, 6, 4, -1}, 
				{3, -1, -1, -1, -1, -1, -1, -1, -1}, 
				{-1, 5, 7, 1, -1, -1, -1, -1, 3}, 
				{0, -1, -1, 4, 2, -1, -1, -1, -1} 
				}); 
		System.out.println("Not Solved #8 " + ranking.ranking(inputPuzzle)); 
	}
	
	@Test
	public void puzzleNotSolved9(){
		Puzzle inputPuzzle = new Puzzle(new int [][] {
				{-1, -1, 2, 5, 3, -1, -1, 6, 7}, 
				{4, -1, -1, -1, -1, -1, -1, -1, -1}, 
				{-1, -1, 8, -1, -1, -1, 5, 3, -1}, 
				{-1, -1, -1, 6, 4, 5, -1, 7, -1}, 
				{-1, -1, -1, -1, -1, -1, -1, -1, 2}, 
				{-1, -1, -1, 3, 1, -1, 6, -1, -1}, 
				{-1, -1, -1, -1, -1, -1, 1, -1, -1}, 
				{-1, 5, 7, -1, 8, 0, -1, 2, -1}, 
				{-1, -1, -1, -1, -1, -1, -1, 5, 6}, 
				}); 
		System.out.println("Not Solved #9 " + ranking.ranking(inputPuzzle)); 
	}
	
	@Test
	public void whyRankIs9(){
		Puzzle inputPuzzle = new Puzzle(new int[][]{
				{1, 0, -1, -1, 3, -1, -1, -1, -1}, 
				{-1, -1, 5, -1, -1, 7, -1, 0, 1}, 
				{-1, -1, -1, 2, -1, -1, 5, -1, -1}, 
				{-1, 1, -1, -1, -1, -1, -1, -1, -1}, 
				{-1, 4, -1, -1, 7, 8, 3, -1, -1}, 
				{-1, 7, -1, 3, -1, -1, 6, -1, 5}, 
				{-1, -1, 4, -1, 5, 6, -1, -1, -1}, 
				{-1, -1, -1, -1, -1, -1, 4, -1, -1}, 
				{0, 8, 1, -1, -1, -1, -1, -1, -1}
		});
		int rank = ranking.ranking(inputPuzzle);
		System.out.println("Solved: Naked Pair + Hidden Pair + Locked * 2 " + rank); 
	}
	
	@Test
	public void rankIs9No2(){
		Puzzle inputPuzzle = new Puzzle(new int[][]{
				{1, 0, -1, -1, -1, -1, 8, -1, -1}, 
				{-1, -1, -1, 8, -1, -1, -1, 0, -1}, 
				{7, 6, -1, -1, -1, -1, 5, -1, 4}, 
				{-1, -1, -1, -1, -1, -1, -1, -1, -1}, 
				{-1, -1, -1, 0, -1, -1, -1, 1, 2}, 
				{8, 7, -1, -1, -1, -1, 6, -1, -1}, 
				{-1, -1, -1, -1, 5, -1, -1, -1, 0}, 
				{-1, 5, 7, -1, 8, 0, 4, -1, -1}, 
				{-1, -1, 1, -1, 2, -1, -1, -1, 6} 
		});
		int rank = ranking.ranking(inputPuzzle);
		System.out.println("Solved: rand 9 #2 " + rank); 
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
