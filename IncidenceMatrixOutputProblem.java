import java.io.*;

public class IncidenceMatrixOutputProblem extends TestProblem {
	private int getIncidenceValue(String agunod_vertex, String agunod_edge) {
		if (agunod_edge.equals(agunod_vertex + agunod_vertex)) return 2; // Loop
		if (agunod_edge.contains(agunod_vertex)) return 1; // Non-Loop
		return 0; // Not incident
	}
	
	public void run(String agunod_filename) throws IOException {
		var agunod_edgeList = GraphLoader.loadEdgeList(agunod_filename);
		if (agunod_edgeList.isDirected()) {
			System.out.println("The graph \"" + agunod_filename + "\" is not undirected.");
			return;
		}
		System.out.println("\r\nIncidence matrix");
		var agunod_adjacencyList = GraphLoader.loadAdjacencyList(agunod_edgeList);
		for (String agunod_vert : agunod_adjacencyList.keySet()) {
			for (String agunod_edge : agunod_edgeList.getEdges()) {
				System.out.print(getIncidenceValue(agunod_vert, agunod_edge) + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
