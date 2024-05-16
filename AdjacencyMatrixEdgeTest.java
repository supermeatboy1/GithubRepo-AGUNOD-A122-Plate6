import java.util.Scanner;

public class AdjacencyMatrixEdgeTest {

	public static void run() throws InterruptedException {
		Scanner scan = new Scanner(
			CycleTest.class.getClassLoader().getResourceAsStream("test_cases/p2_adjmat_0.txt")
		);
		while (scan.hasNextLine()) {
			System.out.println(scan.nextLine());
		}
		scan.close();
	}
}
