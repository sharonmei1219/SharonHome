package com.sharonhome.sudoku.ranking;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPossibleValues {
	
	private PossibleValuesBuilder pvBuilder = new PossibleValuesBuilder();

	@Test
	public void testConstruction() {
		PossibleValues pvs = new PossibleValues();
		assertFalse(pvs.hasSingleValue());
		assertFalse(pvs.hasDeterminedValue());
	}
	
	@Test
	public void testClear() {
		PossibleValues pvs = new PossibleValues();
		pvs.clearAllPossibility();
		assertFalse(pvs.hasSingleValue());
		assertTrue(pvs.hasDeterminedValue());
	}
	
	@Test
	public void testRemove() {
		PossibleValues pvs = pvBuilder.onlyPossibleIn(new int [] {8});
		assertTrue(pvs.hasSingleValue());
		int value = pvs.getSingleValue();
		assertEquals(8, value);
	}
	
	@Test
	public void testIsNotTwinAsThereAreMoreThan2PossibleValues(){
		PossibleValues pvs = new PossibleValues();
		PossibleValues comparedPvs = new PossibleValues();
		assertFalse(pvs.isTwinOf(comparedPvs));
	}
	
	@Test
	public void testIsTwinIfHasSame2PossibleValues(){
		PossibleValues pvs = pvBuilder.onlyPossibleIn(new int []{7, 8});
		PossibleValues comparedPvs = pvBuilder.onlyPossibleIn(new int []{7, 8});
		assertTrue(pvs.isTwinOf(comparedPvs));
	}
	
	@Test
	public void testIsNotTwinIfHasDifferentNumberOfPossibleValues(){
		PossibleValues pvs = pvBuilder.onlyPossibleIn(new int []{7, 8});
		PossibleValues comparedPvs = pvBuilder.onlyPossibleIn(new int []{6, 7, 8});
		assertFalse(pvs.isTwinOf(comparedPvs));
	}

	@Test
	public void testIsNotTwinIfHasDifferentPossibleValues(){
		PossibleValues pvs = pvBuilder.onlyPossibleIn(new int []{7, 8});
		PossibleValues comparedPvs = pvBuilder.onlyPossibleIn(new int []{6, 7});
		assertFalse(pvs.isTwinOf(comparedPvs));
	}
	
	@Test
	public void testRemoveNoneExistPossibility(){
		PossibleValues pvs = pvBuilder.onlyPossibleIn(new int []{7, 8});
		pvs.remove(6);
		assertFalse(pvs.hasSingleValue());
		pvs.remove(7);
		assertTrue(pvs.hasSingleValue());
	}
	
	@Test
	public void testRemoveAnotherPossibleValues(){
		PossibleValues pvs = pvBuilder.onlyPossibleIn(new int []{6, 7, 8});
		PossibleValues pvsToRemove = pvBuilder.onlyPossibleIn(new int []{7, 8});
		pvs.remove(pvsToRemove);
		assertTrue(pvs.hasSingleValue());
		assertEquals(6, pvs.getSingleValue());
		
		pvs = pvBuilder.onlyPossibleIn(new int []{6, 7});
		pvs.remove(pvsToRemove);
		assertTrue(pvs.hasSingleValue());
		assertEquals(6, pvs.getSingleValue());
		
		pvs = pvBuilder.onlyPossibleIn(new int []{3, 5});
		pvs.remove(pvsToRemove);
		assertFalse(pvs.hasSingleValue());
	}
}
