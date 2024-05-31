import java.io.*;
import java.util.*;

public class CycleCheckProblem extends TestProblem {
	private ArrayList<String> agunod_explored = new ArrayList<String>();
	private HashMap<String, ArrayList<String>> agunod_adjacencyList;
	private boolean agunod_cycleDetected = false;
	
	private boolean recursiveCycleCheck(String agunod_vertex, String agunod_parent) {
		agunod_explored.add(agunod_vertex);
		for (String agunod_adjacent : agunod_adjacencyList.get(agunod_vertex)) {
			// Recursively check every unexplored vertex...
			if (!agunod_explored.contains(agunod_adjacent)) {
				if (recursiveCycleCheck(agunod_adjacent, agunod_vertex)) {
					return true;
				}
			// Check if the explored vertex is not a parent.
			// If it is not a parent, then the program has detected a cycle.
			} else if (agunod_parent != null && !agunod_adjacent.equals(agunod_parent)) {
				agunod_cycleDetected = true;
				return true;
			}
 		}
		return false;
	}
	public void run(String agunod_filename) throws IOException {
		agunod_adjacencyList = GraphLoader.loadAdjacencyList(agunod_filename);
		agunod_cycleDetected = false;
		agunod_explored.clear();
		// Repeat the Depth-first search while there's still vertices unexplored and if there's no detected cycle yet.
		while (agunod_explored.size() != agunod_adjacencyList.size() && !agunod_cycleDetected) {
			// Get a start vertex from the first found unexplored vertex.
			for (String startVertex : agunod_adjacencyList.keySet()) {
				if (!agunod_explored.contains(startVertex)) {
					recursiveCycleCheck(startVertex, null);
					break;
				}
			}
		}
		System.out.println("The graph \"" + agunod_filename + "\" " +
					(agunod_cycleDetected ? "has a cycle..." : "doesn't have a cycle..."));
	}
}
