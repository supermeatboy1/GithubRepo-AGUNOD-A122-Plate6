import java.io.*;
import java.util.*;

public class BipartiteCheckProblem extends TestProblem {
	private static class ColoredVertex {
		private String agunod_name;
		private int agunod_color;
		ColoredVertex(String agunod_name, int agunod_color) {
			this.agunod_name = agunod_name;
			this.agunod_color = agunod_color;
		}
	}
	private static boolean checkVertexExplored(ArrayList<ColoredVertex> agunod_explored, String agunod_name) {
		for (ColoredVertex agunod_v : agunod_explored)
			if (agunod_v.agunod_name.equals(agunod_name))
				return true;
		return false;
	}
	private static int getVertexColor(ArrayList<ColoredVertex> agunod_explored, String agunod_name) {
		if (agunod_name == null) return 0;
		for (ColoredVertex agunod_v : agunod_explored)
			if (agunod_v.agunod_name.equals(agunod_name))
				return 1 - agunod_v.agunod_color;
		return -1;
	}
	public void run(String agunod_filename) throws IOException {
		var agunod_adjacencyList = GraphLoader.loadAdjacencyList(agunod_filename);
		boolean agunod_isBipartite = true;
		LinkedList<String> agunod_frontiers = new LinkedList<String>();
		ArrayList<ColoredVertex> agunod_explored = new ArrayList<ColoredVertex>();
		// Repeat the Breadth-first search until no vertex is left unexplored.
		while (agunod_explored.size() != agunod_adjacencyList.size()) {
			// Get a start vertex from the first found unexplored vertex.
			for (String agunod_startVertex : agunod_adjacencyList.keySet()) {
				if (!checkVertexExplored(agunod_explored, agunod_startVertex)) {
					agunod_explored.add(new ColoredVertex(agunod_startVertex, 0));
					agunod_frontiers.add(agunod_startVertex);
					break;
				}
			}
			while (!agunod_frontiers.isEmpty()) {
				String agunod_currentVertex = agunod_frontiers.remove();
				int agunod_newColor = getVertexColor(agunod_explored, agunod_currentVertex);
				for (String agunod_adjVertex : agunod_adjacencyList.get(agunod_currentVertex)) {
					if (!checkVertexExplored(agunod_explored, agunod_adjVertex)) {
						agunod_explored.add(new ColoredVertex(agunod_adjVertex, agunod_newColor));
						agunod_frontiers.add(agunod_adjVertex);
					} else {
						int agunod_existingColor = getVertexColor(agunod_explored, agunod_adjVertex);
						if (agunod_existingColor == agunod_newColor)
							agunod_isBipartite = false;
					}
				}
			}		
		}
		System.out.println("The graph \"" + agunod_filename + "\" is "
					+ (!agunod_isBipartite ? "not " : "") + "bipartite.");
	}
}
