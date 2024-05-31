import java.io.*;

public class DSGraphsMain {
	private static void testProblem(TestProblem p, String filePrefix) {
		try {
			// Five test cases per problem
			for (int i = 1; i <= 5; i++) {
				System.out.print("Test case " + i + ": ");
				p.run(filePrefix + i + ".txt");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		System.out.println("Problem 1:\r\n");
		testProblem(new ConnectedComponentsProblem(), "test_cases/p1_simple_");
		System.out.println("\r\nProblem 2:\r\n");
		testProblem(new AdjacencyMatrixInputProblem(), "test_cases/p2_adjmat_");
		System.out.println("\r\nProblem 3:\r\n");
		testProblem(new CycleCheckProblem(), "test_cases/p3_cycle_");
		System.out.println("\r\nProblem 4:\r\n");
		testProblem(new VertexDegreeProblem(), "test_cases/p4_degree_");
		System.out.println("\r\nProblem 5:\r\n");
		testProblem(new BipartiteCheckProblem(), "test_cases/p5_bipartite_");
		System.out.println("\r\nProblem 6:\r\n");
		testProblem(new AdjacencyMatrixOutputProblem(), "test_cases/p6_vertpairs_");
		System.out.println("\r\nProblem 7:\r\n");
		testProblem(new IncidenceMatrixOutputProblem(), "test_cases/p7_incidence_");
	}
}
