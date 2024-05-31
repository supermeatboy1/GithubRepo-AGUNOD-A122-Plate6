import java.io.*;

public class VertexDegreeProblem extends TestProblem {
	public void run(String agunod_filename) throws IOException {
		var agunod_edgeList = GraphLoader.loadEdgeList(agunod_filename);
		if (agunod_edgeList.isDirected())
			throw new IllegalStateException("Graph should be undirected.");
		var agunod_adjacencyList = GraphLoader.loadAdjacencyList(agunod_edgeList);
		System.out.println("Degrees of \"" + agunod_filename + "\":");
		for (String agunod_vertex : agunod_adjacencyList.keySet()) {
			System.out.println("deg(" + agunod_vertex + ") = " +
				agunod_adjacencyList.get(agunod_vertex).size());
		}
	}
}
