package com.sonoma;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class MinSetOfZipcodeRange {
	private static final boolean DEBUG_ENABLED = false;

	public static void main(String[] args){
		String userInput = "[94133,94133] [00900,94299] [94600,94699]";
		String result = null;
		try {
			result = new MinSetOfZipcodeRange().mergeIntervals(userInput);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(result);	
	}
	
	//function to check the validity of User Input and passing the acceptable data
	//to mergeIntervals() as a List
	public List<Interval> processInput(String input) throws NullPointerException, Exception, NumberFormatException{
		List<Interval> inputList = new ArrayList<Interval>();
		if(input == null || input.length() == 0){
			throw new NullPointerException("Invalid Input");
		}
		String [] inputArray = input.split(" ");
		int lowRange =0, highRange=0;
		for(String s: inputArray){
			String temp[] = s.substring(1,s.length()-1).split(",");
			//check for number of digits in a zipcode
			if(temp[0].length()==5 && temp[1].length()== 5){
				try{
					//allow only integer values
					lowRange = Integer.parseInt(temp[0]);
					highRange = Integer.parseInt(temp[1]);
				}
				catch(NumberFormatException e){
					throw new NumberFormatException("Invalid format");
				}
			}else throw new Exception("Invalid number of digits");
			
			// first value has to be less than or equal to the preceding one in the given
			// zipcode range
			if(lowRange <= highRange)
				inputList.add(new Interval(lowRange, highRange));
			else throw new Exception("Range not in the specified format.");
		}
		return inputList;
	}
	
	//function to generate new reduced set of acceptable zipcodes using TreeSet 
	public String mergeIntervals(String userInput) throws NumberFormatException, NullPointerException, Exception {

		List<Interval> inputList = new MinSetOfZipcodeRange().processInput(userInput);
		TreeSet <Interval> treeSet = new TreeSet<Interval>(new Comparator<Interval>(){
			
			//make use of existing data structures than reinventing sorting logic
			//building a TreeSet based on first values of every provided zipcode
			@Override
			public int compare(Interval m1, Interval m2){
				if(m1.low < m2.low){
					return -1;
				}else{
					return 1;
				}
			}
		});
		
		List<Interval> result = new ArrayList<Interval>();
		treeSet.addAll(inputList);
		
		if (DEBUG_ENABLED){
			for (Interval temp : treeSet){
				System.out.println(temp.low + " " + temp.high);
			}
		}
		for (Interval temp : treeSet){
			if (result.isEmpty()){
				result.add(temp);
			}
			else{
				int currentIndex = result.size() - 1;
				if(result.get(currentIndex).low <= temp.low && temp.low <= result.get(currentIndex).high){
					if(result.get(currentIndex).high <= temp.high){
						result.set(currentIndex, new Interval(result.get(currentIndex).low, temp.high));
					}
					else{
						result.set(currentIndex, new Interval(result.get(currentIndex).low, result.get(currentIndex).high));
					}
				}
				else if(result.get(currentIndex).high < temp.low){
					result.add(temp);
				}
			}
		}
		StringBuilder ret = new StringBuilder();
		for (Interval temp : result) {
			ret.append(temp);
		}
		return ret.toString().trim();
	}
}

class Interval{
	
	int low = 0;
	int high = 0 ;
	
	public Interval(int low, int high){
		this.low = low;
		this.high = high;
	}

	@Override
	public String toString() {
		//format output (5 digits prepend with 0s) and return
		return "["+String.format("%05d", this.low)+","+String.format("%05d", this.high)+"] ";
	}
}
