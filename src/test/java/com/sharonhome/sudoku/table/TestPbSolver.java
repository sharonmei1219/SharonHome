package com.sharonhome.sudoku.table;

import static org.junit.Assert.*;

import org.hsqldb.Table;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import com.sharonhome.sudoku.table.CanTable;
import com.sharonhome.sudoku.table.PbSolver;
import com.sharonhome.sudoku.table.ResultCollector;

public class TestPbSolver {
	Mockery context = new Mockery();
	final CanTable table = context.mock(CanTable.class);

	final ResultCollector result = context.mock(ResultCollector.class);

	@Test
	public void testSolverFullTable() {
		
		context.checking(new Expectations() {{
		    oneOf(table).full(); will(returnValue(true));
		    oneOf(result).collect(table);
		}}
		);
		
		PbSolver solver = new PbSolver();
		solver.solve(table, result);
		context.assertIsSatisfied();
	}
	
	@Test
	public void testSolverWithOneEmptyCellButNoFitCandidate() {
		
		context.checking(new Expectations() {{
		    oneOf(table).full(); will(returnValue(false));
		    oneOf(table).candidatesForNextEmptyCell(); will(returnValue(new int[]{1, 2}));
		    oneOf(table).fillEmptyCell(1); will(returnValue(table));
		    oneOf(table).fillEmptyCell(2); will(returnValue(table));
		    oneOf(table).validate(); will(returnValue(false));
		    oneOf(table).validate(); will(returnValue(false));
		}}
		);
		
		PbSolver solver = new PbSolver();
		solver.solve(table, result);
		context.assertIsSatisfied();
	}
	
	@Test
	public void testSolverWithOneEmptyCellWithFitCandidate() {
		
		context.checking(new Expectations() {{
		    oneOf(table).full(); will(returnValue(false));
		    oneOf(table).candidatesForNextEmptyCell(); will(returnValue(new int[]{1}));
		    oneOf(table).fillEmptyCell(1); will(returnValue(table));
		    oneOf(table).validate(); will(returnValue(true));
		    oneOf(table).full(); will(returnValue(true));
		    oneOf(result).collect(table);
		    oneOf(result).done(); will(returnValue(false));
		}}
		);
		
		PbSolver solver = new PbSolver();
		solver.solve(table, result);
		context.assertIsSatisfied();
	}
	
	@Test
	public void testSolverStopSolve() {
		
		context.checking(new Expectations() {{
		    oneOf(table).full(); will(returnValue(false));
		    oneOf(table).candidatesForNextEmptyCell(); will(returnValue(new int[]{1, 2, 3}));
		    oneOf(table).fillEmptyCell(1); will(returnValue(table));
		    oneOf(table).validate(); will(returnValue(true));
		    oneOf(table).full(); will(returnValue(true));
		    oneOf(result).collect(table);
		    oneOf(result).done(); will(returnValue(false));
		    
		    oneOf(table).fillEmptyCell(2); will(returnValue(table));
		    oneOf(table).validate(); will(returnValue(true));
		    oneOf(table).full(); will(returnValue(true));
		    oneOf(result).collect(table);
		    oneOf(result).done(); will(returnValue(true));
		}}
		);
		
		PbSolver solver = new PbSolver();
		solver.solve(table, result);
		context.assertIsSatisfied();
	}

}
