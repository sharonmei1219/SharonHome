package com.sharonhome.sudoku.ranking;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.sharonhome.sudoku.generator.Spot;

public class TestPossibleValueMatrix {
	ArrayList<Single> singleList = new ArrayList<Single>();
	PossibleValuesBuilder pvBuilder = new PossibleValuesBuilder();
	Puzzle puzzle = new Puzzle(new int [][]{
			{-1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1}
	});
	PossibleValueMatrix PV = new PossibleValueMatrix(puzzle);

	@Test
	public void testConstucting() {
		assertEquals(0, PV.findNewSingle().size());
	}
	
	@Test
	public void testUpdateSingleRowImpacted(){
		// row 0: {0, 1, 2, 3, 4, 5, 6, 7, -1}
		for(int i = 0; i < 8; i++){
			PV.update(new Single(i, new Spot(0,i)));
		}

		singleList = PV.findNewSingle();

		assertEquals(1, singleList.size());
		assertEquals(8, singleList.get(0).getValue());
		assertEquals(0, singleList.get(0).geti());
		assertEquals(8, singleList.get(0).getj());
	}
	
	@Test
	public void testUpdateSingleColumnImpacted(){
		// column 2: {0, 1, 2, 3, 4, 5, 6, 7, -1}
		for(int i = 0; i < 8; i++){
			PV.update(new Single(i, new Spot(i,2)));
		}

		singleList = PV.findNewSingle();

		assertEquals(1, singleList.size());
		assertEquals(8, singleList.get(0).getValue());
		assertEquals(8, singleList.get(0).geti());
		assertEquals(2, singleList.get(0).getj());
	}
	
	@Test
	public void testUpdateSingleBlockImpacted(){
		// block
		PV.update(new Single(0, new Spot(3,3)));
		PV.update(new Single(1, new Spot(3,4)));
		PV.update(new Single(2, new Spot(3,5)));
		PV.update(new Single(3, new Spot(4,3)));
		PV.update(new Single(4, new Spot(4,4)));
		PV.update(new Single(5, new Spot(5,3)));
		PV.update(new Single(6, new Spot(5,4)));
		PV.update(new Single(7, new Spot(5,5)));
		
		singleList = PV.findNewSingle();

		assertEquals(1, singleList.size());
		assertEquals(8, singleList.get(0).getValue());
		assertEquals(4, singleList.get(0).geti());
		assertEquals(5, singleList.get(0).getj());
	}
	
	@Test
	public void constructWithNonEmptyPuzzle(){
		Puzzle puzzle = new Puzzle(new int [][]{
				{ 0,  1,  2, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1,  6, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1,  7, -1, -1, -1},
				{-1, -1, -1,  3, -1, -1, -1, -1, -1},
				{-1, -1, -1,  4, -1, -1, -1, -1, -1},
				{-1, -1, -1,  5, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1}
		});
		PossibleValueMatrix PV = new PossibleValueMatrix(puzzle);
		singleList = PV.findNewSingle();
		assertEquals(1, singleList.size());
		assertEquals(8, singleList.get(0).getValue());
		assertEquals(0, singleList.get(0).geti());
		assertEquals(3, singleList.get(0).getj());
	}
	
	@Test
	public void findNewPairsInRow(){
		Puzzle puzzle = new Puzzle(new int [][]{
				{ 0,  1,  2,  3,  4, -1,  6,  7, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1}
		});
		PossibleValueMatrix PV = new PossibleValueMatrix(puzzle);
		singleList = PV.findNewSingle();
		assertEquals(0, singleList.size());
		
		ArrayList<Pair> pairList = new ArrayList<Pair>();
		pairList = PV.findNewNakedPair();
		assertEquals(1, pairList.size());
		
		pairList = PV.findNewNakedPair();
		assertEquals(0, pairList.size());
	}
	
	@Test
	public void findNewPairsInColumn(){
		Puzzle puzzle = new Puzzle(new int [][]{
				{ 0, -1, -1, -1, -1, -1, -1, -1, -1},
				{ 1, -1, -1, -1, -1, -1, -1, -1, -1},
				{ 2, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{ 4, -1, -1, -1, -1, -1, -1, -1, -1},
				{ 5, -1, -1, -1, -1, -1, -1, -1, -1},
				{ 6, -1, -1, -1, -1, -1, -1, -1, -1},
				{ 7, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1}
		});
		PossibleValueMatrix PV = new PossibleValueMatrix(puzzle);
		singleList = PV.findNewSingle();
		assertEquals(0, singleList.size());
		
		ArrayList<Pair> pairList = new ArrayList<Pair>();
		pairList = PV.findNewNakedPair();
		assertEquals(1, pairList.size());
		
		pairList = PV.findNewNakedPair();
		assertEquals(0, pairList.size());
	}
	
	@Test
	public void findNewPairsInBlock(){
		Puzzle puzzle = new Puzzle(new int [][]{
				{ 0,  1,  2, -1, -1, -1, -1, -1, -1},
				{-1,  4,  5, -1, -1, -1, -1, -1, -1},
				{ 6,  7, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1}
		});
		PossibleValueMatrix PV = new PossibleValueMatrix(puzzle);
		singleList = PV.findNewSingle();
		assertEquals(0, singleList.size());
		
		ArrayList<Pair> pairList = new ArrayList<Pair>();
		pairList = PV.findNewNakedPair();
		assertEquals(1, pairList.size());
	}
	
	@Test
	public void findNewPairsWontBeFindTwiceBlock(){

		PossibleValues pv = pvBuilder.onlyPossibleIn(new int [] {3, 8});
		Pair p = new Pair(pv, new Spot(1, 0), new Spot(2, 2));
		ArrayList<Pair> pairList = new ArrayList<Pair>();
		pairList.add(p);
		
		Puzzle puzzle = new Puzzle(new int [][]{
				{ 0,  1,  2, -1, -1, -1, -1, -1, -1},
				{-1,  4,  5, -1, -1, -1, -1, -1, -1},
				{ 6,  7, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1}
		});
		PossibleValueMatrix PV = new PossibleValueMatrix(puzzle);
		ArrayList<Pair> newPairList = PV.findNewNakedPair();
		assertEquals(1, newPairList.size());
		newPairList = PV.findNewNakedPair();
		assertEquals(0, newPairList.size());
	}
	
	@Test
	public void testUpdatePairsInRow(){
		PossibleValues pv = pvBuilder.onlyPossibleIn(new int [] {0, 1});
		Pair p = new Pair(pv, new Spot(0, 3), new Spot(0, 8));
	
		Puzzle puzzle = new Puzzle(new int [][]{
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1,  2},
				{-1, -1, -1,  2, -1, -1, -1, -1, -1},
				{ 3, -1, -1,  4, -1, -1, -1, -1,  5},
				{ 4, -1, -1,  5, -1, -1, -1, -1,  6},
				{ 5, -1, -1,  6, -1, -1, -1, -1,  7},
				{ 6, -1, -1,  7, -1, -1, -1, -1,  8},
				{ 7, -1, -1,  8, -1, -1, -1, -1,  3},
				{ 8, -1, -1,  3, -1, -1, -1, -1,  4}
		});
		
		PossibleValueMatrix PV = new PossibleValueMatrix(puzzle);
		ArrayList<Pair> newPairList = PV.findNewNakedPair();
		assertTrue(newPairList.contains(p));
		
		PV.update(p);
		ArrayList<Single> sList = PV.findNewSingle();
		assertEquals(1, sList.size());
		assertEquals(new Single(2, new Spot(0, 0)), sList.get(0));
	}
	
	@Test
	public void testUpdatePairsInColumn(){
		PossibleValues pv = pvBuilder.onlyPossibleIn(new int [] {3, 8});
		Pair p = new Pair(pv, new Spot(3, 3), new Spot(7, 3));
	
		Puzzle puzzle = new Puzzle(new int [][]{
				{ 2,  4, -1, -1,  5,  6,  7,  1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{ 0,  1,  2, -1,  4,  5,  6, -1,  7},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{ 1,  2,  4, -1,  5,  6,  7,  0, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1}
		});
		
		PossibleValueMatrix PV = new PossibleValueMatrix(puzzle);
		ArrayList<Pair> newPairList = PV.findNewNakedPair();
		assertTrue(newPairList.contains(p));
		
		PV.update(p);
		ArrayList<Single> sList = PV.findNewSingle();
		assertEquals(1, sList.size());
		assertEquals(new Single(0, new Spot(0, 3)), sList.get(0));
	}
	
	@Test
	public void testUpdatePairsInBlock(){
		PossibleValues pv = pvBuilder.onlyPossibleIn(new int [] {3, 8});
		Pair p = new Pair(pv, new Spot(3, 3), new Spot(5, 5));
	
		Puzzle puzzle = new Puzzle(new int [][]{
				{-1, -1, -1, -1,  6, -1, -1, -1, -1},
				{-1, -1, -1, -1,  7, -1, -1, -1, -1},
				{-1, -1, -1, -1,  0, -1, -1, -1, -1},
				{ 0,  1,  2, -1,  4,  7,  6, -1,  5},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{ 1,  2,  4,  6,  5, -1,  7,  0, -1},
				{-1, -1, -1, -1,  1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1}
		});
		
		PossibleValueMatrix PV = new PossibleValueMatrix(puzzle);
		ArrayList<Pair> newPairList = PV.findNewNakedPair();
		assertTrue(newPairList.contains(p));
		
		PV.update(p);
		ArrayList<Single> sList = PV.findNewSingle();
		assertEquals(1, sList.size());
		assertEquals(new Single(2, new Spot(4, 4)), sList.get(0));
	}
	
	@Test
	public void testTrippleInARow(){
		Triple expTriple = new Triple(new PossibleValues(new int[]{2, 3, 4}),
				                      new Spot[]{new Spot(0, 0),
				                                 new Spot(0, 3),
				                                 new Spot(0, 6)
			                                     
		});
		
		Puzzle puzzle = new Puzzle(new int [][]{
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1,  1, -1, -1, -1, -1, -1},
				{ 5, -1, -1, -1, -1, -1,  0, -1,  6},
				{-1,  3, -1, -1, -1, -1, -1,  2, -1},
				{-1,  2, -1, -1,  3, -1, -1,  4, -1},
				{-1, -1, -1, -1,  4, -1,  5,  3, -1},
				{-1, -1,  3, -1, -1, -1, -1, -1,  2},
				{-1, -1,  2, -1, -1,  3, -1, -1,  4},
				{-1, -1, -1, -1, -1,  4,  3,  5, -1}
		});
		
		
		PossibleValueMatrix PV = new PossibleValueMatrix(puzzle);
		PossiblePositionVector PP = new PossiblePositionVector(puzzle);
		ArrayList<Pair> pairList = PP.findNewHiddenPair();
		for(Pair p : pairList){
			PV.update(p);
		}
		
		ArrayList<Triple> tripleList = PV.findNewNakedTriple();	

		assertTrue(tripleList.contains(expTriple));

		PV.update(expTriple);
		
		ArrayList<Single> sList = PV.findNewSingle();
		assertEquals(1, sList.size());
		assertEquals(new Single(5, new Spot(0, 8)), sList.get(0));
	}
	
	@Test
	public void testTrippleInAColumn(){
		Triple expTriple = new Triple(new PossibleValues(new int[]{2, 3, 4}),
				                      new Spot[]{new Spot(2, 0),
				                                 new Spot(5, 0),
				                                 new Spot(8, 0)
			                                     
		});
		
		Puzzle puzzle = new Puzzle(new int [][]{
				{-1, -1,  6, -1, -1, -1,  2,  4, -1},
				{-1, -1, -1,  2,  4,  3, -1, -1,  5},
				{-1, -1,  0, -1, -1,  5, -1, -1,  3},
				{-1, -1, -1, -1, -1, -1, -1,  3,  4},
				{-1, -1, -1, -1,  3,  4, -1, -1, -1},
				{-1,  1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1,  3,  2, -1},
				{-1, -1, -1,  3,  2, -1, -1, -1, -1},
				{-1, -1,  5, -1, -1, -1, -1, -1, -1}
		});
		
		
		PossibleValueMatrix PV = new PossibleValueMatrix(puzzle);
		PossiblePositionVector PP = new PossiblePositionVector(puzzle);
		ArrayList<Pair> pairList = PP.findNewHiddenPair();
		for(Pair p : pairList){
			PV.update(p);
		}
		
		ArrayList<Triple> tripleList = PV.findNewNakedTriple();	

		assertTrue(tripleList.contains(expTriple));

		PV.update(expTriple);
		
		ArrayList<Single> sList = PV.findNewSingle();
		assertEquals(1, sList.size());
		assertEquals(new Single(5, new Spot(0, 0)), sList.get(0));
	}
	
	@Test
	public void testTrippleInABlock(){
		Triple expTriple = new Triple(new PossibleValues(new int[]{2, 3, 4}),
				                      new Spot[]{new Spot(0, 0),
				                                 new Spot(0, 2),
				                                 new Spot(1, 1)
			                                     
		});
		
		Puzzle puzzle = new Puzzle(new int [][]{
				{-1, -1, -1,  5, -1, -1,  7,  8, -1},
				{-1, -1, -1, -1,  2, -1, -1,  0, -1},
				{-1,  1,  6, -1, -1, -1, -1, -1, -1},
				{ 4,  5,  3, -1, -1, -1, -1, -1, -1},
				{ 0, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1,  7, -1, -1, -1, -1, -1, -1, -1},
				{-1,  8,  0, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1}
		});
		
		
		PossibleValueMatrix PV = new PossibleValueMatrix(puzzle);

		
		ArrayList<Triple> tripleList = PV.findNewNakedTriple();	
		assertTrue(tripleList.contains(expTriple));

		PV.update(expTriple);
		
		ArrayList<Single> sList = PV.findNewSingle();
		assertEquals(1, sList.size());
		assertEquals(new Single(0, new Spot(0, 1)), sList.get(0));
	}
	
	@Test
	public void testTrippleUpdatePPInRow(){
		Triple expTriple = new Triple(new PossibleValues(new int[]{2, 3, 4}),
				                      new Spot[]{new Spot(0, 0),
				                                 new Spot(0, 3),
				                                 new Spot(0, 6)
			                                     
		});
		
		Puzzle puzzle = new Puzzle(new int [][]{
				{-1, -1, -1, -1,  6, -1, -1, -1, -1},
				{-1, -1, -1,  1, -1, -1, -1, -1, -1},
				{ 5, -1, -1, -1, -1,  8,  0, -1,  6},
				{ 4, -1, -1, -1, -1, -1, -1,  2, -1},
				{ 0, -1, -1, -1,  3, -1, -1,  4, -1},
				{-1, -1, -1, -1,  4, -1, -1, -1, -1},
				{ 1, -1, -1, -1, -1, -1, -1, -1,  2},
				{ 7, -1, -1, -1, -1,  3, -1, -1,  4},
				{ 8, -1, -1, -1,  2,  4, -1, -1, -1}
		});
		
		
		PossibleValueMatrix PV = new PossibleValueMatrix(puzzle);
		PossiblePositionVector PP = new PossiblePositionVector(puzzle);
		ArrayList<Pair> pairList = PP.findNewHiddenPair();
		for(Pair p : pairList){
			PV.update(p);
			PP.update(p);
		}
		
		ArrayList<Triple> tripleList = PV.findNewNakedTriple();	
		assertTrue(tripleList.contains(expTriple));
		
		PP.update(expTriple);
		ArrayList<Single> sList = PP.findNewSingle();
		assertTrue(sList.contains(new Single(2, new Spot(1, 5))));
	}
	
	@Test
	public void testTrippleUpdatePPInColumn(){
		Triple expTriple = new Triple(new PossibleValues(new int[]{2, 3, 4}),
				                      new Spot[]{new Spot(0, 0),
				                                 new Spot(3, 0),
				                                 new Spot(6, 0)
			                                     
		});
		
		Puzzle puzzle = new Puzzle(new int [][]{
				{-1, -1,  5,  4,  0, -1,  1,  7,  8},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1,  1, -1, -1, -1, -1, -1, -1, -1},
				{ 6, -1, -1, -1,  3,  4, -1, -1,  2},
				{-1, -1,  8, -1, -1, -1, -1,  3,  4},
				{-1, -1,  0, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1,  2,  4, -1, -1, -1, -1},
				{-1, -1,  6, -1, -1, -1,  2,  4, -1}
		});
		
		
		PossibleValueMatrix PV = new PossibleValueMatrix(puzzle);
		PossiblePositionVector PP = new PossiblePositionVector(puzzle);
		ArrayList<Pair> pairList = PP.findNewHiddenPair();
		for(Pair p : pairList){
			PV.update(p);
			PP.update(p);
		}
		
		ArrayList<Triple> tripleList = PV.findNewNakedTriple();	
		assertTrue(tripleList.contains(expTriple));
		
		PP.update(expTriple);
		ArrayList<Single> sList = PP.findNewSingle();
		assertTrue(sList.contains(new Single(2, new Spot(5, 1))));
	}
	
	@Test
	public void testUpdatePPTrippleInABlock(){
		Triple expTriple = new Triple(new PossibleValues(new int[]{2, 3, 4}),
				                      new Spot[]{new Spot(0, 0),
				                                 new Spot(0, 2),
				                                 new Spot(1, 1)
			                                     
		});
		
		Puzzle puzzle = new Puzzle(new int [][]{
				{-1,  8, -1,  5, -1, -1,  7, -1, -1},
				{-1, -1, -1, -1,  2, -1, -1,  0, -1},
				{-1,  1,  6, -1, -1, -1, -1, -1, -1},
				{ 4,  5,  3, -1, -1, -1, -1, -1, -1},
				{ 0, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1,  2,  2, -1},
				{-1,  7,  0, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1}
		});
		
		
		PossibleValueMatrix PV = new PossibleValueMatrix(puzzle);
		PossiblePositionVector PP = new PossiblePositionVector(puzzle);

		
		ArrayList<Triple> tripleList = PV.findNewNakedTriple();	
		assertTrue(tripleList.contains(expTriple));

		PP.update(expTriple);
		
		ArrayList<Single> sList = PP.findNewSingle();
		assertEquals(1, sList.size());
		assertEquals(new Single(2, new Spot(2, 8)), sList.get(0));
	}
}
