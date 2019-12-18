package dataStructure;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class tester {

	public static void main(String[] args) {
		LinkedHashMap<Integer, ArrayList<Integer>> li = 
				new LinkedHashMap<Integer, ArrayList<Integer>>();
		int x = 1;
		li.put(x++, new ArrayList<Integer>());
		
		// connect 1 -- > 3
		
		
		li.get(2).add(3);
		System.out.println(x);
		
	}

}
