package com.sharonhome.sudoku.table;

import static org.junit.Assert.*;

import org.hsqldb.Table;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import com.sharonhome.sudoku.generator.Permutations;
import com.sharonhome.sudoku.generator.RandomNumberGen;
import com.sharonhome.sudoku.generator.RandomNumberGenbyRandom;
import com.sharonhome.sudoku.table.CanTable;
import com.sharonhome.sudoku.table.PbSolver;
import com.sharonhome.sudoku.table.ResultCollector;

public class TestPbSolver {
	Mockery context = new Mockery();
	final CanTable table = context.mock(CanTable.class);

	final ResultCollector result = context.mock(ResultCollector.class);
	PbSolver solver = new PbSolver();

	@Test
	public void testSolverFullTable() {
		
		context.checking(new Expectations() {{
		    oneOf(table).full(); will(returnValue(true));
		    oneOf(result).collect(table);
		}}
		);
		
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
	
	@Test
	public void testWithoutMock(){
		Permutations pers = new Permutations(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
		RandomNumberGen rand = new RandomNumberGenbyRandom();
		RandomCandidates candidates = new RandomCandidatesInDifferentSequence(rand, pers);
		TableValidator v = new SudokuValidator();
		CanTable ct = new RandomTable(candidates, v);
		
	}

}
