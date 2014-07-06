package com.sharonhome.sudoku.table;

import java.util.ArrayList;

import com.sharonhome.sudoku.table.CanTable;

public class RandomTable implements CanTable, SudokuTable{

	private RandomCandidates candidates;
	private int [][] table = new int[9][9];
	private TableValidator validator;

	public RandomTable(RandomCandidates rc, TableValidator v) {
		this.candidates = rc;
		this.validator = v;
		all(new EmptyCell());
	}

	private void all(Handling handling) {
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++){
				handling.handle(i, j);
				if(handling.done()) return;
			}
	}

	private void emptyCell(int i, int j) {
		table[i][j] = -1;
	}
	private boolean isEmpty(int i, int j){
		return table[i][j] == -1;
	}
	
	public boolean full() {
		Tupple t = findNextEmptyCell();
		return t.i == -1;
	}

	public int[] candidatesForNextEmptyCell() {
		Tupple t = findNextEmptyCell();
		return candidates.getCandidate(t.i, t.j);
	}

	private Tupple findNextEmptyCell() {
		EmptyCellSeeker seeker = new EmptyCellSeeker();
		all(seeker);
		return seeker.t;
	}

	public CanTable fillEmptyCell(int num) {
		RandomTable result = new RandomTable(candidates, validator);
		result.table = cloneTable();
		Tupple t = findNextEmptyCell();
		result.setNum(t.i, t.j, num);
		return result;
	}

	private int[][] cloneTable() {
		Copier copier = new Copier();
		all(copier);
		return copier.copiedTable;
	}

	public boolean validate() {
		return validator.validate(this);
	}
	
	private interface Handling{
		void handle(int i, int j);
		boolean done();
	};
	private class EmptyCell implements Handling{
		public void handle(int i, int j){
			emptyCell(i, j);
		}
		public boolean done(){return false;}
	};
	
	private class Copier implements Handling{
		public int[][] copiedTable = new int[9][9];
		public boolean done(){return false;}
		public void handle(int i, int j) {
			this.copiedTable[i][j] = table[i][j];
		}
	}
	private class EmptyCellSeeker implements Handling{
		boolean done = false;
		public Tupple t = new Tupple();

		public void handle(int i, int j) {
			if(!isEmpty(i, j)) return;
			t.i = i;
			t.j = j;
			done = true;
		}
		public boolean done() {
			return done;
		}
		
	}
	
	private class Tupple{
		public int i = -1;
		public int j = -1;
	}

	public ArrayList<int[]> getAllRows() {
		ArrayList <int[]> result = new ArrayList<int[]>();
		for(int row = 0; row < 9; row++)
			result.add(getRow(row));
		return result;
	}

	private int[] getRow(int rowIndex) {
		NonEmptyCounter counter = new NonEmptyCounter();
		rowOperation(rowIndex, counter);
		
		NonEmptyCellCollector collector = new NonEmptyCellCollector(counter.count);
		rowOperation(rowIndex, collector);
		return collector.cells;
	}
	
	private void rowOperation(int rows, Handling handling) {
		for(int j = 0; j < 9; j++)
			handling.handle(rows, j);
	}
	
	private class NonEmptyCellCollector implements Handling{

		public int[] cells;
		private int index = 0;

		public NonEmptyCellCollector(int count) {
			cells = new int[count];
		}

		public void handle(int i, int j) {
			if(isEmpty(i, j)) return;
			this.cells[index ++] = table[i][j];
		}

		public boolean done() {return false;}
		
	}

	private class NonEmptyCounter implements Handling{
		public int count = 0;
		public void handle(int i, int j) {
			if(isEmpty(i, j)) return;
			count  ++;
		}
		public boolean done() {return false;}
		
	}
	public ArrayList<int[]>  getAllColumns() {
		ArrayList<int[]> result = new ArrayList<int[]>();
		for(int column = 0; column < 9; column ++)
			result.add(getColumn(column));
		return result;
	}

	private int[] getColumn(int column) {
		NonEmptyCounter counter = new NonEmptyCounter();
		columnOperation(column, counter);
		
		NonEmptyCellCollector collector = new NonEmptyCellCollector(counter.count);
		columnOperation(column, collector);
		return collector.cells;
	}

	private void columnOperation(int column, Handling handling) {
		for(int i = 0; i < 9; i ++)
			handling.handle(i, column);
		
	}

	public ArrayList<int[]> getAllBlocks() {
		ArrayList<int[]> result = new ArrayList<int[]>();
		for(int b = 0; b < 9; b++)
			result.add(getBlock(b));
		return result;
	}

	private int[] getBlock(int block) {
		NonEmptyCounter counter = new NonEmptyCounter();
		blockOperation(block, counter);
		
		NonEmptyCellCollector collector = new NonEmptyCellCollector(counter.count);
		blockOperation(block, collector);
		return collector.cells;
	}

	private void blockOperation(int block, Handling handling) {
		int br = block/3;
		int bc = block%3;
		for(int i = br * 3; i < br*3 + 3; i++)
			for(int j = bc*3; j < bc*3 + 3; j++)
				handling.handle(i, j);
		
	}

	public void setNum(int i, int j, int num) {
		table[i][j] = num;
		
	}

}
