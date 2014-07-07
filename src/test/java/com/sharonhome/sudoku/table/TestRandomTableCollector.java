package com.sharonhome.sudoku.table;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

public class TestRandomTableCollector {
	Mockery context = new Mockery();
	CanTable table = context.mock(CanTable.class);
	RandomTableCollector collector = new RandomTableCollector();
	@Test
	public void testCollectingDone() {
		context.checking(new Expectations(){{
			oneOf(table).getTable(); will(returnValue(new int[][]{{1, 1},
					                                              {2, 2}}));
		}});
		collector.collect(table);
		Assert.assertArrayEquals(new int[] {1, 1}, collector.result()[0]);
		Assert.assertArrayEquals(new int[] {2, 2}, collector.result()[1]);
		assertTrue(collector.done());
	}
	
	@Test
	public void testCollectingNotDone() {
		assertFalse(collector.done());
	}

}
