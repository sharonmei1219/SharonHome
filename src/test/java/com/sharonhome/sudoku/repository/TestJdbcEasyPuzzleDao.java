package com.sharonhome.sudoku.repository;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.sharonhome.sudoku.generator.RandomNumberGen;

public class TestJdbcEasyPuzzleDao extends
		AbstractTransactionalDataSourceSpringContextTests {
	private JdbcPuzzleDao puzzleDao;
	Mockery context = new Mockery();
	
	public void setPuzzleDao(JdbcPuzzleDao puzzleDao){
		this.puzzleDao = puzzleDao;
	}
	
	@Override
	protected  String[] getConfigLocations(){
		return new String[] {"test_context.xml"};
	}
	
	public void testGetEasyPuzzle(){
		final RandomNumberGen rand = context.mock(RandomNumberGen.class);
		puzzleDao.setRand(rand);
		
		context.checking(new Expectations() {{
		    oneOf(rand).nextInt(2);will(returnValue(0));
		}}
		);
		
		String puzzle = puzzleDao.getPuzzle("easy");
		assertEquals("incorrect puzzle ?", "[[11],[11]]", puzzle);
	}
	
//	public void testInsertHardPuzzle(){
//		final RandomNumberGen rand = context.mock(RandomNumberGen.class);
//		puzzleDao.setRand(rand);
//		
//		context.checking(new Expectations() {{
//		    oneOf(rand).nextInt(2);will(returnValue(0));
//		}}
//		);
//		
//		String puzzle = puzzleDao.getPuzzle("easy");
//		assertEquals("incorrect puzzle ?", "[[11],[11]]", puzzle);
//	}
}
