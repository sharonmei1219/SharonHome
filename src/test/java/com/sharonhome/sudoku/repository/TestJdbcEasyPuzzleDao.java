package com.sharonhome.sudoku.repository;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

public class TestJdbcEasyPuzzleDao extends
		AbstractTransactionalDataSourceSpringContextTests {
	private PuzzleDao puzzleDao;
	
	public void setPuzzleDao(PuzzleDao puzzleDao){
		this.puzzleDao = puzzleDao;
	}
	
	@Override
	protected  String[] getConfigLocations(){
		return new String[] {"test_context.xml"};
	}
	
	public void testGetEasyPuzzle(){
		String puzzle = puzzleDao.getNewPuzzleByID("1");
		assertEquals("incorrect puzzle ?", "[[11],[11]]", puzzle);
	}
}
