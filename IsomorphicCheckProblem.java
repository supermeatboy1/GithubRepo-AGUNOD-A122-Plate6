import java.io.IOException;

public class IsomorphicCheckProblem extends TestProblem {
	@Override
	public void run(String filename) throws IOException {
		var edgeList = GraphLoader.loadEdgeList(filename);
		if (edgeList.isDirected())
			throw new IllegalStateException("Graph should be undirected.");
	}
}
