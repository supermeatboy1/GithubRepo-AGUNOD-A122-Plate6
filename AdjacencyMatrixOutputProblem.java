import java.io.*;
import java.util.*;

public class AdjacencyMatrixOutputProblem extends TestProblem {
	public void run(String agunod_filename) throws IOException {
		var agunod_edgeList = GraphLoader.loadEdgeList(agunod_filename);
		System.out.println("The adjacency matrix for the " +
				(agunod_edgeList.isDirected() ? "directed" : "undirected") + " graph is");
		var agunod_adjacencyList = GraphLoader.loadAdjacencyList(agunod_edgeList);
		for (String agunod_rowVertex : agunod_adjacencyList.keySet()) {
			ArrayList<String> agunod_adjacentVertices = agunod_adjacencyList.get(agunod_rowVertex);
			Collections.sort(agunod_adjacentVertices);
			for (String agunod_columnVertex : agunod_adjacencyList.keySet()) {
				int agunod_numOfEdges = 0;
				while (agunod_adjacentVertices.contains(agunod_columnVertex)) {
					agunod_numOfEdges++;
					agunod_adjacentVertices.remove(agunod_columnVertex);
				}
				System.out.print(agunod_numOfEdges + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
