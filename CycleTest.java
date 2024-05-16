package ave.maria;

import java.util.*;

public class CycleTest {
	// String = vertex
	// ArrayList<String> = adjacent vertices
	static HashMap<String, ArrayList<String>> graph = new HashMap<String, ArrayList<String>>();
	static HashMap<String, Boolean> visitedVertices = new HashMap<String, Boolean>();
	
	public static void run() throws InterruptedException {
		Scanner scan = new Scanner(
			CycleTest.class.getClassLoader().getResourceAsStream("test_cases/p3_cycle_1.txt")
		);
		while (scan.hasNextLine()) {
			String[] tokens = scan.nextLine().split(" ");
			String vertex_name = tokens[1];
			ArrayList<String> adjacent_vertices = new ArrayList<String>();
			for (int i = 3; i < tokens.length; i++) {
				adjacent_vertices.add(tokens[i]);
			}
			graph.put(vertex_name, adjacent_vertices);
		}
		scan.close();
		System.out.println("List of vertices and adjacent vertices: ");
		String first_vertex = null;
		for (String key : graph.keySet()) {
			if (first_vertex == null) {
				first_vertex = key;
			}
		
			System.out.print(key + " -> ");
			for (String adj : graph.get(key)) {
				System.out.print(adj + ", ");
			}
			System.out.println();
		}
		System.out.println("==========================================\r\nPress Enter to continue.");
		Stack<String> stack = new Stack<String>();
		//Stack<String> previous_vertices = new Stack<String>();
		String previous_vertex = null;
		String current_vertex = first_vertex;
		stack.push(first_vertex);
		System.out.println("Current vertex on the stack: " + first_vertex);
		
		while (!stack.isEmpty()) {
			previous_vertex = current_vertex;
			current_vertex = stack.pop();
			if (!visitedVertices.containsKey(current_vertex)) {
				System.out.println(current_vertex + " is newly marked as visited.");
				visitedVertices.put(current_vertex, true);
				System.out.print("Adding the neighbors of " + current_vertex + ": ");
				for (String neighbor : graph.get(current_vertex)) {
					stack.push(neighbor);
					System.out.print(neighbor + ", ");
				}
				//previous_vertices.add(current_vertex);
				System.out.println();
			} else {
				/*
				if (previous_vertices.size() > 2 &&
					//current_vertex.equals(previous_vertices.get(previous_vertices.size() - 2))) {
					//System.out.println(current_vertex + " is a previous vertex. Skipping...");
					previous_vertices.pop();
				} else {
					//System.out.println(current_vertex + " is already visited." + " Top of the previous vertices stack is: " + previous_vertices.get(previous_vertices.size() - 2));
					System.out.println(current_vertex + " is already visited.");
				//}
				 */
				boolean is_previous_neighbor = false;
				for (String prev_adj : graph.get(previous_vertex)) {
					if (prev_adj.equals(current_vertex)) {
						is_previous_neighbor = true;
					}
				}
				if (is_previous_neighbor) {
					System.out.println(current_vertex + " is a previous vertex. Skipping...");
				} else {
					System.out.println(current_vertex + " is already visited.");
				}
				//System.out.println(current_vertex + " is already visited, previous_vertex is: " + previous_vertex);
			}
			System.out.println("==============");
		}
		System.out.println("End of process.");
		
		//scan = new Scanner(System.in);
		/*
		String current_vertex = null;
		boolean looping = true;
		while (looping) {
			if (current_vertex == null) {
				System.out.print("Type the vertex you want to start in: ");
				current_vertex = scan.nextLine().toUpperCase();
				System.out.println("Current vertex: " + current_vertex);
				System.out.print("Adjacent vertices: ");
				for (String adj : graph.get(current_vertex)) {
					System.out.print(adj + ", ");
				}
				System.out.println();
			} else {
				String input = scan.nextLine().toUpperCase();
				if (input.equals("exit")) {
					looping = false;
					break;
				}
				if (graph.containsKey(input)) {
					if (!graph.get(current_vertex).contains(input)) {
						System.out.println(input + " is not a neighbor of " + current_vertex);
					} else {
						current_vertex = input;
						System.out.println("Current vertex: " + current_vertex);
						System.out.print("Adjacent vertices: ");
						for (String adj : graph.get(current_vertex)) {
							System.out.print(adj + ", ");
						}
						System.out.println();
					}
				} else {
					System.out.println("Invalid vertex.");
				}
			}
		}
		*/
		//scan.close();
	}
}
