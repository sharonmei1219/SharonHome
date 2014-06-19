package com.sharonhome.sudoku.generator;

import java.util.ArrayList;

public class PossiblePositions {
	private boolean [][] map;
	public PossiblePositions(int i, int j) {
		map = new boolean[i][j];
		for(int x = 0; x < i; x++){
			for(int y = 0; y < j; y++)
				map[x][y] = true;
		}
	}
	
	public PossiblePositions(int[][] puzzle) {
		map = new boolean[puzzle.length][puzzle[0].length];
		for(int x = 0; x < puzzle.length; x++){
			for(int y = 0; y < map[0].length; y++)
				if(puzzle[x][y] == -1)
				map[x][y] = true;
		}
	}

	public void removeRow(int i) {
		int len = map[i].length;
		for(int j = 0; j < len; j++)
			map[i][j] = false;
	}
	public ArrayList<Spot> remaining() {
		ArrayList<Spot> result = new ArrayList<Spot>();
		for (int i = 0; i < map.length; i++){
			for(int j = 0; j < map[i].length; j++)
				if (map[i][j]) result.add(new Spot(i,j));
		}
		return result;
	}
	public void removeColumn(int j) {
		for (int i = 0; i < map.length; i++)
			map[i][j] = false;
	}

	public void removeBlock(int i, int j, int blockSize) {
		int minX = i - i%blockSize;
		int minY = j - j%blockSize;
		
		for(int x = minX; x < minX + blockSize; x++){
			for(int y = minY; y < minY + blockSize; y++){
				map[x][y] = false;
			}
		}
		
	}

	public boolean onlyOneInARow(Spot spot) {
		int i = spot.geti();
		for (int j = 0; j < map[i].length; j++){
			if(j == spot.getj()) continue;
			if(map[i][j]) return false;
		}
		return true;
	}

	public boolean onlyOneInAColumn(Spot spot) {
		int j = spot.getj();
		for(int i = 0; i < map.length; i++){
			if(i == spot.geti()) continue;
			if(map[i][j]) return false;
		}
		return true;
	}

	public boolean onlyOneInABlock(Spot spot, int blockSize) {
		int minI = spot.geti() - spot.geti()%blockSize;
		int minJ = spot.getj() - spot.getj()%blockSize;
		for(int i = minI; i< minI + blockSize; i ++){
			for(int j =  minJ; j < minJ + blockSize; j++){
				if(i == spot.geti() && j == spot.getj()) continue;
				if(map[i][j]) return false;
			}
		}
		return true;
	}

}
