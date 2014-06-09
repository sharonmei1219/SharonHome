package com.sharonhome.sudoku.generator;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class TestTableDigger {

	@Test
	public void testShufflePos() {
		Permutations pers = new Permutations(new int[]{0, 1, 2, 3});
		int perNr = 23;
		assertEquals(24, pers.total());
		int[] per = pers.get(perNr);
		Assert.assertArrayEquals(new int []{3,  2, 1, 0}, per);
		Spot[] pos = mapNumberToSpot(per, 2);
		assertEquals(new Spot(1, 1), pos[0]);
		assertEquals(new Spot(1, 0), pos[1]);
		assertEquals(new Spot(0, 1), pos[2]);
		assertEquals(new Spot(0, 0), pos[3]);
	}
	
	@Test
	public void testDig1Hole(){
		int [][] puzzle = {{0, 0}, {0, 0}};
		SudokuSolver solver = new SudokuSolver(1);
		solver.setSolutionCandidates(new int []{1});
		Spot[] pos = new Spot[]{new Spot(0,0), new Spot(0, 1), new Spot(1, 0), new Spot(1, 1)};
		TestingRandomSeq candidateHoles = new TestingRandomSeq();
		candidateHoles.setSeq(pos);
		Spot[] holes = dig(puzzle, solver, candidateHoles, 1);
		assertEquals(1, holes.length);
		assertEquals(new Spot(0, 0), holes[0]);
		candidateHoles = new TestingRandomSeq();
		candidateHoles.setSeq(pos);
		Spot[] holes2 = dig(puzzle, solver, candidateHoles, 2);
		assertEquals(2, holes2.length);
		assertEquals(new Spot(0, 0), holes2[0]);
		assertEquals(new Spot(1, 1), holes2[1]);
	}
	
	@Test
	public void testDig12Holes(){
		int [][] puzzle = {{0, 0}, {0, 0}};
		SudokuSolver solver = new SudokuSolver(1);
		solver.setSolutionCandidates(new int []{1});
		Spot[] pos = new Spot[]{new Spot(0,0), new Spot(0, 1), new Spot(1, 0), new Spot(1, 1)};
		TestingRandomSeq candidateHoles = new TestingRandomSeq();
		candidateHoles.setSeq(pos);
		Spot[] holes2 = dig(puzzle, solver, candidateHoles, 2);
		assertEquals(2, holes2.length);
		assertEquals(new Spot(0, 0), holes2[0]);
		assertEquals(new Spot(1, 1), holes2[1]);
	}
	
	@Test
	public void testDigReturnsNull(){
		int [][] puzzle = {{0, 1}, {0, 1}};
		SudokuSolver solver = new SudokuSolver(1);
		solver.setSolutionCandidates(new int []{1});
		Spot[] pos = new Spot[]{new Spot(0,0), new Spot(0, 1), new Spot(1, 0), new Spot(1, 1)};
		TestingRandomSeq candidateHoles = new TestingRandomSeq();
		candidateHoles.setSeq(pos);
		Spot[] holes2 = dig(puzzle, solver, candidateHoles, 2);
		assertNull(holes2);
	}
	
	@Test
	public void testDigARealOne(){
		int [][] table = {{8,2,4,7,5,3,9,1,6},
						   {9,5,3,2,1,6,8,7,4},
						   {6,7,1,8,4,9,5,2,3},
						   {4,6,9,1,8,2,7,3,5},
						   {5,1,7,3,9,4,2,6,8},
						   {2,3,8,6,7,5,4,9,1},
						   {3,8,6,5,2,7,1,4,9},
						   {7,9,5,4,3,1,6,8,2},
						   {1,4,2,9,6,8,3,5,7}};
		SudokuSolver solver = new SudokuSolver(1);
		solver.setSolutionCandidates(new int []{1, 2, 3, 4, 5, 6, 7, 8, 9});
		HolesInRandomSeq candidateHoles = new HolesInRandomSeqWithMathRandom(81, 9);
		Spot[] holes2 = dig(table, solver, candidateHoles, 32);
		System.out.print(holes2.length);
		int [][] puzzle = erase(table, holes2);
		for(int i = 0; i < 9; i++){
			System.out.println();
			for(int j = 0; j < 9; j++){
				if(puzzle[i][j] == -1){
					System.out.print(" ");
				}else{
				System.out.print(puzzle[i][j]);}
				System.out.print(", ");
			}
		}
	}
	
	private int[][] erase(int[][] table, Spot[] holes2) {
		int [][] result = deepcopy(table);
		for(Spot s : holes2)
			result[s.geti()][s.getj()] = -1;
		return result;
	}

	private Spot[] dig(int[][] puzzle, SudokuSolver solver, HolesInRandomSeq candidateHoles, int requiredHoles) {
		int [][] localPuzzle = deepcopy(puzzle);
		int nrOfHoles = 0;
		Spot[] result = new Spot[requiredHoles];
		while(nrOfHoles < requiredHoles && !candidateHoles.noMoreCandidate()){
			Spot next = candidateHoles.next();
			int x = next.geti();
			int y = next.getj();
			int temp = localPuzzle[x][y];
			localPuzzle[x][y] = -1;
			ArrayList<int[][]> solutions = new ArrayList<int[][]>();
			solver.solve(localPuzzle, solutions);
			if(solutions.size() == 1){
				result[nrOfHoles++] = next;
			}else{
				localPuzzle[x][y]= temp;
			}
		}
		if (nrOfHoles == requiredHoles)
			return result;
		return null;
	}

	private int[][] deepcopy(int[][] puzzle) {
		int result[][] = new int [puzzle.length][puzzle[0].length];
		for(int i = 0; i < puzzle.length; i ++){
			for(int j = 0; j < puzzle[i].length; j++){
				result[i][j] = puzzle[i][j];
			}
		}
		return result;
	}

	private Spot[] mapNumberToSpot(int[] per, int nrColumn) {
		Spot [] result = new Spot[per.length];
		for(int i = 0; i < per.length; i++){
			int x = per[i]/nrColumn;
			int y = per[i]%nrColumn;
			result[i] = new Spot(x, y);
		}
		return result;
	}
	class TestingRandomSeq implements HolesInRandomSeq{
		private Spot[] seq;
		private int index;

		public void setSeq(Spot[] seq){
			this.seq = seq;
			this.index = 0;
		}

		public boolean noMoreCandidate() {
			return index == seq.length;
		}

		public Spot next() {
			return seq[index++];
		}
	};
	
	class HolesInRandomSeqWithMathRandom implements HolesInRandomSeq{
		private int[] reminningPosIndex;
		private int columnWidth;
		private Random rand;

		public HolesInRandomSeqWithMathRandom(int total, int columnWidth){
			this.reminningPosIndex = new int [total];
			for(int i = total - 1; i >= 0; i--)
				this.reminningPosIndex[i] = i;
			this.columnWidth = columnWidth;
			this.rand = new Random();
		}
		public boolean noMoreCandidate() {
			return reminningPosIndex.length == 0;
		}

		public Spot next() {
			int index = rand.nextInt(reminningPosIndex.length);
			Spot result = mapIntToPos(reminningPosIndex[index]);
			reminningPosIndex = remove(reminningPosIndex, index);
			return result;
		}
		private int[] remove(int[] reminningPosIndex2, int index) {
			int []result = new int[reminningPosIndex2.length - 1];
			int resultIndex = 0;
			for(int i = 0; i < reminningPosIndex2.length; i++){
				if(i == index) continue;
				result[resultIndex++] = reminningPosIndex2[i];
			}
			return result;
		}
		private Spot mapIntToPos(int index) {
			int x = index/columnWidth;
			int y = index%columnWidth;
			return new Spot(x, y);
		}
	}
}
