package com.sharonhome.sudoku.generator;

import java.util.ArrayList;

public class NumberingSystem {

	private int[] cumilatedScales;
	private int topScale;

	public NumberingSystem(int[] scales) {
		this.cumilatedScales = new int[scales.length];
		this.cumilatedScales[scales.length - 1] = 1;
		for(int i = scales.length - 2; i >= 0; i --){
			this.cumilatedScales[i] = this.cumilatedScales[i+1] * scales[i + 1];
		}
		topScale = scales[0];
	}

	public ArrayList<Integer> getNthNumber(int nth) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int factor : cumilatedScales){
			result.add(nth/factor);
			nth %= factor;
		}
		return result;
	}

	public int total() {
		return cumilatedScales[0] * topScale;
	}

}
