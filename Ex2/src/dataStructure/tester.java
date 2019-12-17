package dataStructure;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class tester {

	public static void main(String[] args) {
		LinkedHashMap<Integer, ArrayList<Integer>> li = 
				new LinkedHashMap<Integer, ArrayList<Integer>>();
		
		
		// add codcod
		li.put(1, new ArrayList<Integer>());
		
		// connect 1 -- > 3
		
		
		li.get(1).add(1);
		li.get(1).add(2);
		li.get(1).add(3);
		li.get(1).remove(1);
		System.out.println(li.get(1).get(1));
		
	}

}
