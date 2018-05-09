package com.cigniti.test;

import java.util.ArrayList;
import java.util.HashMap;

public class practise {

	public static void main(String[] args) {
		
		ArrayList<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map;
		
		String[] names = {"Satya","Aditya","Nitya"};
		String[] marks = {"300","200","500"};
		int tablesNeed = 3;
		
		for (int i = 0; i < tablesNeed; i++) {
			
			map = new HashMap<String, String>();
			map.put("name",names[i]);
			map.put("marks", marks[i]);
			
			aList.add(map);
		}
		
		System.out.println(aList.get(2).get("name"));
		
		System.out.println(aList.get(2).get("marks"));

		System.out.println(aList.get(1).get("name"));

		System.out.println(aList.get(0).get("marks"));
		
		System.out.println("=======");
		
		
		for (HashMap<String, String> all : aList) {
			System.out.println(all.get("name"));
			System.out.println(all.get("marks"));
		}

		
		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		HashMap<String, String> map2 = new HashMap<String, String>();
//		
//		map1.put("S.No", "6001");
//		map1.put("Name", "Aditya");
//		map1.put("Salary", "50000");
//		
//		map2.put("S.No", "6054");
//		map2.put("Name", "Satya,Nitya");
//		map2.put("Salary", "25000");
//		
//		
//		aList.add(map1);
//		aList.add(map2);
//		
//		System.out.println(aList.get(1).get("Name"));
//		
//		String names[] = aList.get(1).get("Name").split(",");
//		System.out.println(names[0]);
//		System.out.println(names[1]);
		
		
		
		
			
	}

}
