import java.io.*;
import java.util.*;

public class GraphLoader {
	public static EdgeList loadEdgeList(String agunod_filename) throws IOException {
		BufferedReader agunod_buf = new BufferedReader(
				new InputStreamReader(
						GraphLoader.class.getClassLoader().getResourceAsStream(agunod_filename)
					));
		
		var agunod_edgesStringList = new ArrayList<String>();
		boolean agunod_isDirected = false;
		String agunod_line;
		agunod_buf.mark(1);

		while ((agunod_line = agunod_buf.readLine()) != null) {
			// Skip comments or empty lines...
			if (agunod_line.startsWith("#") || agunod_line.isBlank())
				continue;
			else if (agunod_line.equals("directed")) {
				agunod_isDirected = true;
				continue;
			}
			agunod_edgesStringList.add(agunod_line);
		}
		agunod_buf.close();
		return new EdgeList(agunod_edgesStringList, agunod_isDirected);
	}
	public static HashMap<String, ArrayList<String>> loadAdjacencyList(EdgeList agunod_edgeList) throws IOException {
		ArrayList<String> agunod_edges = agunod_edgeList.getEdges();
		var agunod_adjacencyList = new HashMap<String, ArrayList<String>>();
		for (String agunod_edge : agunod_edges) {
			String agunod_startVertex = "" + agunod_edge.charAt(0);
			String agunod_endVertex = "" + agunod_edge.charAt(1);
			if (!agunod_adjacencyList.containsKey(agunod_startVertex))
				agunod_adjacencyList.put(agunod_startVertex, new ArrayList<String>());
			if (!agunod_adjacencyList.containsKey(agunod_endVertex))
				agunod_adjacencyList.put(agunod_endVertex, new ArrayList<String>());
			// Check if the edge is a loop.
			if (agunod_startVertex.equals(agunod_endVertex))
				agunod_adjacencyList.get(agunod_startVertex).add(agunod_endVertex);
			// It's not a loop, add to both vertices.
			else {
				agunod_adjacencyList.get(agunod_startVertex).add(agunod_endVertex);
				// Add adjacency to the end vertex only if the graph is undirected.
				if (!agunod_edgeList.isDirected())
					agunod_adjacencyList.get(agunod_endVertex).add(agunod_startVertex);
			}
		}
		return agunod_adjacencyList;
	}
	public static HashMap<String, ArrayList<String>> loadAdjacencyList(String agunod_filename) throws IOException {
		EdgeList agunod_edgeList = loadEdgeList(agunod_filename);
		return loadAdjacencyList(agunod_edgeList);
	}
}
