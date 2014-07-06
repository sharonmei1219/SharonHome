package com.sharonhome.sudoku.table;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

public class TestValidator {
	
	TableValidator v = new SudokuValidator();
	Mockery context = new Mockery();
	SudokuTable table = context.mock(SudokuTable.class);
	@Test
	public void testWithRowVialation() {
		final ArrayList <int[]> rows = new ArrayList<int[]>();
		rows.add(new int[]{1, 1});
		context.checking(new Expectations() {{
		    oneOf(table).getAllRows(); will(returnValue(rows));
		}}
		);
		assertFalse(v.validate(table));
		context.assertIsSatisfied();
	}
	
	@Test
	public void testWithColumnVialation() {
		final ArrayList <int[]> rows = new ArrayList<int[]>();
		rows.add(new int[]{1, 0});
		final ArrayList <int[]> columns = new ArrayList<int[]>();
		columns.add(new int []{1, 1});
		context.checking(new Expectations() {{
		    oneOf(table).getAllRows(); will(returnValue(rows));
		    oneOf(table).getAllColumns(); will(returnValue(columns));
		}}
		);
		assertFalse(v.validate(table));
		context.assertIsSatisfied();
	}
	
	@Test
	public void testWithBlockVialation() {
		final ArrayList <int[]> rows = new ArrayList<int[]>();
		rows.add(new int[]{1, 0});
		final ArrayList <int[]> columns = new ArrayList<int[]>();
		columns.add(new int []{1, 0});
		final ArrayList <int[]> blocks = new ArrayList<int[]>();
		blocks.add(new int[]{1, 1});
		context.checking(new Expectations() {{
		    oneOf(table).getAllRows(); will(returnValue(rows));
		    oneOf(table).getAllColumns(); will(returnValue(columns));
		    oneOf(table).getAllBlocks(); will(returnValue(blocks));
		}}
		);
		assertFalse(v.validate(table));
		context.assertIsSatisfied();
	}
	
	@Test
	public void testTableValidate() {
		final ArrayList <int[]> rows = new ArrayList<int[]>();
		rows.add(new int[]{1, 0});
		final ArrayList <int[]> columns = new ArrayList<int[]>();
		columns.add(new int []{1, 0});
		final ArrayList <int[]> blocks = new ArrayList<int[]>();
		blocks.add(new int[]{1, 0});
		context.checking(new Expectations() {{
		    oneOf(table).getAllRows(); will(returnValue(rows));
		    oneOf(table).getAllColumns(); will(returnValue(columns));
		    oneOf(table).getAllBlocks(); will(returnValue(blocks));
		}}
		);
		assertTrue(v.validate(table));
		context.assertIsSatisfied();
	}

}
