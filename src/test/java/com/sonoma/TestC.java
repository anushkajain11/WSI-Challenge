package com.sonoma;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;

public class TestC{

	MinSetOfZipcodeRange obj;
	
	@Before
	public void setUp() throws Exception{
		obj = new MinSetOfZipcodeRange();
	}
	
	@Test(expected = NullPointerException.class)
	public void testIfUserInputIsNull() throws NullPointerException, NumberFormatException, Exception{
		String input = null;
		obj.processInput(input);
	}
	
	@Test(expected = NullPointerException.class)
	public void testIfUserInputIsEmpty() throws NullPointerException, NumberFormatException, Exception{
		String input = "";
		obj.processInput(input);
	}
	
	@Test(expected = NumberFormatException.class)
	public void testIfUserInputIsNotDigits() throws NullPointerException, NumberFormatException, Exception{
		String input = "[ab234,45619]";
		obj.processInput(input);
	}
	
	@Test(expected = Exception.class)
	public void testIfRangeInUserInputIsNotFiveDigits() throws NullPointerException, NumberFormatException, Exception{
		String input = "[001234,45619]";
		obj.processInput(input);
	}
	
	@Test(expected = Exception.class)
	public void testIfLowRangeIsGreaterThanHighRange() throws NullPointerException, NumberFormatException, Exception{
		String input = "[91234,45619]";
		obj.processInput(input);
	}
	
	
	@Test
	public void testMergeIntervalFlow() throws NumberFormatException, NullPointerException, Exception {
		String input = "[90023,94033] [00900,94299] [94600,94699]";
		String expected = "[00900,94299] [94600,94699]";
		String result = obj.mergeIntervals(input);
		//System.out.println(result);
		Assert.assertTrue(result.equals(expected));
	}
	
	@Test
	public void testWithDuplicateRangeInUserInput() throws NullPointerException, NumberFormatException, Exception{
		String input = "[21234,45619] [21234,45619]";
		String expected = "[21234,45619]";
		String result = obj.mergeIntervals(input);
		Assert.assertTrue(result.equals(expected));
	}
	
	
	

}
