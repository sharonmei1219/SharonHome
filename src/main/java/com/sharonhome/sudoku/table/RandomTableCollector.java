package com.sharonhome.sudoku.table;

public class RandomTableCollector implements ResultCollector {

	private int[][] table;
	private boolean done = false;

	public void collect(CanTable table) {
		this.table = table.getTable();
		done = true;
	}

	public boolean done() {
		return done;
	}

	public int[][] result() {
		return this.table;
	}

}
