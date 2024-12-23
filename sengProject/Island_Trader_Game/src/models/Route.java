package models;

import java.util.*;

/**
 * Class that models a all possible routes from point A to point B.
 * 
 * @author Zahid Khan
 */

public class Route {
	// A string variable to hold the graph in textual from.
	private String inputData;
	// An Array of Arrays of Integers which will hold the converted graph from Textual form to Adjacency-List.
	private ArrayList<ArrayList<ArrayList<Integer>>> adjList;
	// An array of Arrays of Integers which holds all the possible combinations to go from point a to point b.
	private ArrayList<ArrayList<Integer>> outputData;
	
	
	/**
	 * Creates an object, and textual representation of fully connected graph.
	 */
	public Route() {
		inputData = ("U 5 W\n"
				   + "0 1 1\n"
				   + "0 2 2\n"
				   + "0 3 3\n"
				   + "0 4 4\n"
				   + "1 2 1\n"
				   + "1 3 2\n"
				   + "1 4 3\n"
				   + "2 3 1\n"
				   + "2 4 2\n"
				   + "3 4 1\n");
	}
	
	
	/**
	 * This method creates and returns an adjacency-list of the graph of Type ArrayList of type ArrayList of type ArrayList of type Integer.
	 * @return returns an adjacency-list
	 */
	public ArrayList<ArrayList<ArrayList<Integer>>> createAdjList() {
		String[] data = inputData.split("\n");
		String[] firstLine = data[0].split(" ");
		int totalVertices = Integer.parseInt(firstLine[1]);
		
		for (int i = 0; i < totalVertices; i++) {
			ArrayList<ArrayList<Integer>> newList = new ArrayList<ArrayList<Integer>>();
			
			adjList.add(newList);
		}
		
		for (int j = 1; j < data.length; j++) {
			String[] splitLine = data[j].split(" ");
			
			int start = Integer.parseInt(splitLine[0]);
			int end = Integer.parseInt(splitLine[1]);
			int weight = Integer.parseInt(splitLine[2]);
			
			ArrayList<Integer> startToEnd = new ArrayList<Integer>();
			ArrayList<Integer> endToStart = new ArrayList<Integer>();
			
			startToEnd.add(end);
			startToEnd.add(weight);
			
			endToStart.add(start);
			endToStart.add(weight);
			
			adjList.get(start).add(startToEnd);
			adjList.get(end).add(endToStart);
		}
		return adjList;
	}
	
	
	/**
	 * This method calculates all the paths from point A to point B using the back-tracking algorithm
	 * @param source This is the start vertex/point
	 * @param destination This is the end vertex/point
	 * @return returns an array of arrays with all the possible and valid combination
	 */
	public ArrayList<ArrayList<Integer>> calculateAllPaths(int source, int destination) {
		outputData = new ArrayList<ArrayList<Integer>>();
		adjList = new ArrayList<ArrayList<ArrayList<Integer>>>();
		ArrayList<ArrayList<ArrayList<Integer>>> inputAdjList = createAdjList();
		ArrayList<Integer> candidate = new ArrayList<Integer>();
		candidate.add(source);
		
		dfsBackTracker(candidate, inputAdjList, destination, outputData);
		
		return outputData;
	}
	
	
	/**
	 * This is a helper function to @link calculateAllPaths, it performs depth-first-search recursively,
	 * and create candidates, if the candidate is valid it is added to output else voided 
	 * @param candidate the starting vertex/point
	 * @param inputAdjList adjacency list created using textual form of graph
	 * @param destination the end vertex/point
	 * @param outputData an Array of all possible combinations
	 */
	@SuppressWarnings("unchecked")
	private void dfsBackTracker(ArrayList<Integer> candidate, 
								ArrayList<ArrayList<ArrayList<Integer>>> inputAdjList, 
								int destination, 
								ArrayList<ArrayList<Integer>> outputData) {
		if (shouldPrune(candidate)) {
			return;
		}
		if (isSolution(candidate, destination)) {
			outputData.add(candidate);
		}else {
			ArrayList<ArrayList<Integer>> subChild = children(candidate, inputAdjList);
			for (int i = 0; i < subChild.size(); i++) {
				Object childCandidate = subChild.get(i);
				dfsBackTracker((ArrayList<Integer>) childCandidate, inputAdjList, destination, outputData);
			}
		}
	}
	
	
	/**
	 * this methods checks the validity of candidates that no vertex is more than once in the list 
	 * and last vertex is the destination island
	 * @param candidate takes an Array of Candidates
	 * @return if candidate is valid it returns false, true otherwise
	 */
	private boolean shouldPrune(ArrayList<Integer> candidate) {
		int lastElement = (int) candidate.get(candidate.size()-1);
		
		int counter = 0;
		for (Object num: candidate) {
			if ((int) num == lastElement) {
				counter += 1;
			}
		}
		if (counter == 1 && candidate.size() <= 5) {
			return false;
		}else {
			return true;
		}
	}
	
	
	/**
	 * This method checks if the candidate fulfill our requirements by check the last vertex in the list
	 * @param candidate takes the valid candidate
	 * @param destination takes the final/end vertex/point
	 * @return return true if candidate list valid, false otherwise
	 */
	private boolean isSolution(ArrayList<Integer> candidate, int destination) {
		//TODO
		int lastElement = (int) candidate.get(candidate.size()-1);
		return (lastElement == destination);
	}
	
	/**
	 * This method create all the candidates starting from start vertex/point.
	 * @param candidate Array of candidate is our starting vertex
	 * @param inputAdjList Adjacency List of textual graph
	 * @return an Array of all the sub-candidates
	 */
	private ArrayList<ArrayList<Integer>> children(ArrayList<Integer> candidate,
												   ArrayList<ArrayList<ArrayList<Integer>>> inputAdjList) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		
		for (int i = 0; i < inputAdjList.size(); i++) {
			@SuppressWarnings("unchecked")
			ArrayList<Integer> newCandidate = (ArrayList<Integer>) candidate.clone();
			newCandidate.add(i);
			result.add(newCandidate);
		}
		return result;
	}
	
	
	/**
	 * this method take a selected route as a parameter and calculates the travel time in days 
	 * and returns type double for all the possible routes
	 * @param route
	 * @return type Double
	 */
	public double travelTimeIndays(ArrayList<Integer> route) {
		double totalDuration = 0;
		for (int i = 0; i < route.size()-1; i++) {
			ArrayList<ArrayList<Integer>> weightList = adjList.get(route.get(i));
			for (int j = 0; j < weightList.size(); j++) {
				if (weightList.get(j).get(0) == route.get(i+1)){
					totalDuration += weightList.get(j).get(1);
					break;
				}
			}
		}
		return totalDuration;
	}
}
