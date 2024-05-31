import java.util.*;
import java.io.*;

public class ConnectedComponentsProblem extends TestProblem {
	public void run(String agunod_filename) throws IOException {
		var agunod_adjacencyList = GraphLoader.loadAdjacencyList(agunod_filename);
		int agunod_numConnected = 0;
		LinkedList<String> agunod_frontiers = new LinkedList<String>();
		ArrayList<String> agunod_explored = new ArrayList<String>();
		
		// Repeat the Breadth-first search until no vertex is left unexplored.
		while (agunod_explored.size() != agunod_adjacencyList.size()) {
			// Get a start vertex from the first found unexplored vertex.
			for (String agunod_startVertex : agunod_adjacencyList.keySet()) {
				if (!agunod_explored.contains(agunod_startVertex)) {
					agunod_explored.add(agunod_startVertex);
					agunod_frontiers.add(agunod_startVertex);
					break;
				}
			}
			// Perform the Breadth-first search.
			while (!agunod_frontiers.isEmpty()) {
				String currentVertex = agunod_frontiers.remove();
				for (String adjVertex : agunod_adjacencyList.get(currentVertex)) {
					if (!agunod_explored.contains(adjVertex)) {
						agunod_explored.add(adjVertex);
						agunod_frontiers.add(adjVertex);
					}
				}
			}
			agunod_numConnected++;	
		}
		if (agunod_numConnected == 1)
			System.out.println("The graph \"" + agunod_filename + "\" is connected.");
		else
			System.out.println("The graph \"" + agunod_filename + "\" is not connected, " +
								"the number of connected components are: " + agunod_numConnected);
	}
}
