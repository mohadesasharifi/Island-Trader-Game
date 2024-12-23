package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import models.Route;

class RouteTest {

	@Test
	void test() {
		Route data = new Route();
		ArrayList<Integer> source = new ArrayList<Integer>();
		source.add(0);
		ArrayList<ArrayList<Integer>> test0 = data.calculateAllPaths(0, 1);
		ArrayList<ArrayList<Integer>> test1 = data.calculateAllPaths(1, 2);
		ArrayList<ArrayList<Integer>> test2 = data.calculateAllPaths(2, 3);
		ArrayList<ArrayList<Integer>> test3 = data.calculateAllPaths(3, 4);
		ArrayList<ArrayList<Integer>> test4 = data.calculateAllPaths(4, 0);
		ArrayList<ArrayList<Integer>> test5 = data.calculateAllPaths(4, 3);
		ArrayList<ArrayList<Integer>> test6 = data.calculateAllPaths(4, 2);
		ArrayList<ArrayList<Integer>> test7 = data.calculateAllPaths(4, 1);
		ArrayList<ArrayList<Integer>> test8 = data.calculateAllPaths(3, 2);
		ArrayList<ArrayList<Integer>> test9 = data.calculateAllPaths(3, 1);
		ArrayList<ArrayList<Integer>> test10 = data.calculateAllPaths(3, 0);
//		for (int count=0; count<test3.size(); count++) {
//			
//		}
		Integer[] route0 = new Integer[] {0, 4, 3, 2, 1};
		ArrayList<Integer> myRoute0 = new ArrayList<Integer>();
		for (int i: route0) {
			myRoute0.add(i);
		}
		assertEquals(test0.get(15), myRoute0);
		
		Integer[] route1 = new Integer[] {1, 0, 2};
		ArrayList<Integer> myRoute1 = new ArrayList<Integer>();
		for (int i: route1) {
			myRoute1.add(i);
		}
		assertEquals(test1.get(0), myRoute1);
		
		Integer[] route2 = new Integer[] {2, 0, 1, 4, 3};
		ArrayList<Integer> myRoute2 = new ArrayList<Integer>();
		for (int i: route2) {
			myRoute2.add(i);
		}
		assertEquals(test2.get(1), myRoute2);
		
		
		Integer[] route3 = new Integer[] {3, 1, 0, 4};
		ArrayList<Integer> myRoute3 = new ArrayList<Integer>();
		for (int i: route3) {
			myRoute3.add(i);
		}
		assertEquals(test3.get(6), myRoute3);
		
		
		Integer[] route4 = new Integer[] {4, 3, 1, 2, 0};
		ArrayList<Integer> myRoute4 = new ArrayList<Integer>();
		for (int i: route4) {
			myRoute4.add(i);
		}
		assertEquals(test4.get(13), myRoute4);
		
		
		Integer[] route5 = new Integer[] {4, 2, 0, 1, 3};
		ArrayList<Integer> myRoute5 = new ArrayList<Integer>();
		for (int i: route5) {
			myRoute5.add(i);
		}
		assertEquals(test5.get(10), myRoute5);
		
		
		Integer[] route6 = new Integer[] {4, 1, 2};
		ArrayList<Integer> myRoute6 = new ArrayList<Integer>();
		for (int i: route6) {
			myRoute6.add(i);
		}
		assertEquals(test6.get(7), myRoute6);
		
		
		Integer[] route7 = new Integer[] {4, 2, 3, 0, 1};
		ArrayList<Integer> myRoute7 = new ArrayList<Integer>();
		for (int i: route7) {
			myRoute7.add(i);
		}
		assertEquals(test7.get(9), myRoute7);
		
		
		Integer[] route8 = new Integer[] {3, 4, 0, 1, 2};
		ArrayList<Integer> myRoute8 = new ArrayList<Integer>();
		for (int i: route8) {
			myRoute8.add(i);
		}
		assertEquals(test8.get(11), myRoute8);
		
		
		Integer[] route9 = new Integer[] {3, 0, 4, 2, 1};
		ArrayList<Integer> myRoute9 = new ArrayList<Integer>();
		for (int i: route9) {
			myRoute9.add(i);
		}
		assertEquals(test9.get(4), myRoute9);
		
		
		Integer[] route10 = new Integer[] {3, 1, 2, 4, 0};
		ArrayList<Integer> myRoute10 = new ArrayList<Integer>();
		for (int i: route10) {
			myRoute10.add(i);
		}
		assertEquals(test10.get(3), myRoute10);
		
		assertEquals(9, (int) data.travelTimeIndays(myRoute10));
	}
}
