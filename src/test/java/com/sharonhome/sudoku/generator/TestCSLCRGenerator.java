package com.sharonhome.sudoku.generator;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestCSLCRGenerator {
	SetSet cst = new SetSet(new int [][]{{11, 12},{21, 22}});
	@Test
	public void test() {
		ArrayList<NumberingSystem> nrSystem = new ArrayList<NumberingSystem>();
		nrSystem.add(new NumberingSystem(new int [] {3, 3, 3}));
		nrSystem.add(new NumberingSystem(new int [] {2, 2, 2}));
		nrSystem.add(new NumberingSystem(new int [] {1, 1, 1}));
		NumberingSystem highOrderNrSystem = new NumberingSystem(new int[]{
				nrSystem.get(0).total(),
				nrSystem.get(1).total(),
				nrSystem.get(2).total()
		});
		assertEquals(27, nrSystem.get(0).total());
		assertEquals(8, nrSystem.get(1).total());
		assertEquals(1, nrSystem.get(2).total());
		assertEquals(216, highOrderNrSystem.total());
	}
	
//	@Test
//	public void testGenerateAllSelections(){
//		CSLCRNumberingSystem CSLCRns = new CSLCRNumberingSystem();
//		SetSet ss = new SetSet(new int[][]{{11, 12, 13}, {21, 22, 23}, {31, 32, 33}});
//		AllSelectionGenerator allGen = new AllSelectionGenerator(ss, CSLCRns);
//		
//		for(int i = 0; i < CSLCRns.total(); i++)
//			printAllSelection(allGen.getAllSelection(i));
//
//	}
	
	void printAllSelection(ArrayList<int[]> allSelection){
		System.out.println();
		printArray(allSelection.get(0));
		printArray(allSelection.get(1));
		printArray(allSelection.get(2));
	}
	
	void printArray(int [] array){
		System.out.print("{");
		for(int num:array){
			System.out.print(num);
			System.out.print(", ");
		}
		System.out.print("}, ");
	}
}
