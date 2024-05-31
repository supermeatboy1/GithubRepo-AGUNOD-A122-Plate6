import java.util.ArrayList;

public class EdgeList {
	private ArrayList<String> edges = new ArrayList<String>();
	private boolean isDirected = false;
	
	public EdgeList(ArrayList<String> edges, boolean isDirected) {
		this.edges = edges;
		this.isDirected = isDirected;
	}

	public ArrayList<String> getEdges() { return edges; }
	public boolean isDirected() { return isDirected; }
}
