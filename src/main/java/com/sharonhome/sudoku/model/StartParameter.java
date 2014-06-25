package com.sharonhome.sudoku.model;

public class StartParameter {
	private int numberOfPuzzleToGenerate;
	private int numberOfHolesInPuzzle;
	public void setNumberOfPuzzleToGenerate(int numberOfPuzzleToGenerate){
		this.numberOfPuzzleToGenerate = numberOfPuzzleToGenerate;
	}
	public int getNumberOfPuzzleToGenerate(){
		return numberOfPuzzleToGenerate;
	}
	
	public void setNumberOfHolesInPuzzle(int numberOfHolesInPuzzle){
		this.numberOfHolesInPuzzle = numberOfHolesInPuzzle;
	}
	public int getNumberOfHolesInPuzzle(){
		return numberOfHolesInPuzzle;
	}
	
	@Override
	public String toString(){
		return "numberOfPuzzleToGenerate: " + numberOfPuzzleToGenerate + "\nnumberOfHolesInPuzzle: " + numberOfHolesInPuzzle;
	}

}
