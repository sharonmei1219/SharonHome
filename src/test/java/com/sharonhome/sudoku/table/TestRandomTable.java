package com.sharonhome.sudoku.table;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.sharonhome.sudoku.table.CanTable;

public class TestRandomTable {
	Mockery context = new Mockery();
	final RandomCandidates rc = context.mock(RandomCandidates.class);
	TableValidator v = context.mock(TableValidator.class);
	RandomTable rt = new RandomTable( rc, v);
	CanTable ct = (CanTable) rt;
	SudokuTable st = (SudokuTable) rt;

	@Test
	public void testFirstEmptyCellIs00InAEmptyTable() {
		context.checking(new Expectations() {{
		    oneOf(rc).getCandidate(0,0); will(returnValue(new int []{1}));
		}}
		);
		Assert.assertArrayEquals(new int[]{1}, rt.candidatesForNextEmptyCell());
		context.assertIsSatisfied();
	}
	
	@Test
	public void testSecondEmptyCellIs01InAEmptyTable(){

		ct = ct.fillEmptyCell(1);
		context.checking(new Expectations() {{
		    oneOf(rc).getCandidate(0,1); will(returnValue(new int []{1}));
		}}
		);
		Assert.assertArrayEquals(new int[]{1}, ct.candidatesForNextEmptyCell());
		context.assertIsSatisfied();
	}
	
	@Test
	public void testTableIsFull(){
		for(int i = 0; i < 81; i++)
			ct = ct.fillEmptyCell(1);
		assertTrue(ct.full());
	}
	
	@Test
	public void testValidation(){
		context.checking(new Expectations() {{
		    oneOf(v).validate(st); will(returnValue(true));
		}}
		);
		assertTrue(ct.validate());
		context.assertIsSatisfied();
	}
	
	@Test
	public void testGetAllRows(){
		rt.setNum(0, 0, 1);
		rt.setNum(0, 1, 2);
		rt.setNum(2, 3, 4);
		rt.setNum(2, 6, 4);
		ArrayList<int[]> rows = rt.getAllRows();
		Assert.assertArrayEquals(new int[]{1, 2}, rows.get(0));
		Assert.assertArrayEquals(new int[]{}, rows.get(1));
		Assert.assertArrayEquals(new int[]{4, 4}, rows.get(2));
	}
	
	@Test
	public void testGetAllColumns(){
		rt.setNum(0, 0, 1);
		rt.setNum(1, 0, 2);
		rt.setNum(3, 2, 4);
		rt.setNum(6, 2, 4);
		ArrayList<int[]> columns = rt.getAllColumns();
		Assert.assertArrayEquals(new int[]{1, 2}, columns.get(0));
		Assert.assertArrayEquals(new int[]{}, columns.get(1));
		Assert.assertArrayEquals(new int[]{4, 4}, columns.get(2));
	}
	
	@Test
	public void testGetAllBlocks(){
		rt.setNum(0, 0, 1);
		rt.setNum(1, 1, 2);
		rt.setNum(3, 3, 4);
		rt.setNum(4, 4, 4);
		ArrayList<int[]> blocks = rt.getAllBlocks();
		Assert.assertArrayEquals(new int[]{1, 2}, blocks.get(0));
		Assert.assertArrayEquals(new int[]{}, blocks.get(1));
		Assert.assertArrayEquals(new int[]{4, 4}, blocks.get(4));
	}

}
