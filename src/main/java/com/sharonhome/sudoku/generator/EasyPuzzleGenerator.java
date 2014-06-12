package com.sharonhome.sudoku.generator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sharonhome.sudoku.repository.PuzzleDao;

public class EasyPuzzleGenerator {
	public static void main( String[] args ){
		ApplicationContext context = new ClassPathXmlApplicationContext("EasyPuzzleTempGen_context.xml");
		PuzzleDao puzzleDao = (PuzzleDao) context.getBean("puzzleDao");
	}
}
