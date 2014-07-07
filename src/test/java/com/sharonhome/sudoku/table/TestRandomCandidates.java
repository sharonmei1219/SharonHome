package com.sharonhome.sudoku.table;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.sharonhome.sudoku.generator.Permutations;
import com.sharonhome.sudoku.generator.RandomNumberGen;

public class TestRandomCandidates {

	Mockery context = new Mockery();
	
	@Test
	public void test() {
		final RandomNumberGen rand = context.mock(RandomNumberGen.class);
		context.checking(new Expectations() {{
			exactly(8).of(rand).nextInt(362880); will(returnValue(0));
			oneOf(rand).nextInt(362880); will(returnValue(362879));
		    
		}}
		);
		
		Permutations permutations = new Permutations(new int []{1, 2, 3, 4, 5, 6, 7, 8, 9});
		RandomCandidates rc = new RandomCandidatesInDifferentSequence(rand, permutations);
		Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9},  rc.getCandidate(0, 1));
		Assert.assertArrayEquals(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1},  rc.getCandidate(8, 1));
		context.assertIsSatisfied();
	}

}
