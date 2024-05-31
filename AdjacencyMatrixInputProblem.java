import java.io.*;

public class AdjacencyMatrixInputProblem extends TestProblem {
	public void run(String agunod_filename) throws IOException {
		BufferedReader agunod_buf = new BufferedReader(
			new InputStreamReader(getClass().getClassLoader().getResourceAsStream(agunod_filename)
		));
		// Mark the first position to return to later.
		agunod_buf.mark(1);
		
		// Read the first line of the matrix and get the number of vertices based on the first line.
		String agunod_firstLine = agunod_buf.readLine();
		while (agunod_firstLine.startsWith("#")) { // Skip file comments.
			agunod_firstLine = agunod_buf.readLine();
			if (agunod_firstLine == null) {
				agunod_buf.close();
				throw new IllegalStateException("Empty matrix.");
			}
		}
		int agunod_numOfVertices = agunod_firstLine.split(" ").length;
		agunod_buf.reset(); // Go back to the first position.
		System.out.println("Edges in the adjacency matrix \"" + agunod_filename + "\"");

		for (int agunod_row = 0; agunod_row < agunod_numOfVertices; agunod_row++) {
			String agunod_matLine = null;
			while (agunod_matLine == null || agunod_matLine.startsWith("#")) {
				agunod_matLine = agunod_buf.readLine();
				/**
				 * The number of lines should be equal to the number of vertices.
				 * If the line is empty, there's not enough information in the adjacency matrix.
				 */
				if (agunod_matLine == null || agunod_matLine.trim().isEmpty()) {
					agunod_buf.close();
					throw new IllegalStateException("Invalid adjacency matrix.");
				}
			}
			String[] agunod_numberStrings = agunod_matLine.split(" ");
			// Do not accept incomplete rows or rows with extra numbers.
			if (agunod_numberStrings.length != agunod_numOfVertices) {
				agunod_buf.close();
				throw new IllegalStateException("Invalid adjacency matrix.");
			}
			for (int agunod_column = agunod_row; agunod_column < agunod_numOfVertices; agunod_column++) {
				if (Integer.parseInt(agunod_numberStrings[agunod_column]) == 0)
					continue;
				String agunod_edgeName = "" + Constants.ALPHABET.charAt(agunod_row) +
											Constants.ALPHABET.charAt(agunod_column);
				System.out.println(agunod_edgeName + ": " + agunod_numberStrings[agunod_column]);
			}
		}
		// Check if the matrix has too much rows.
		String agunod_checkLine = "";
		while (agunod_checkLine != null) {
			agunod_checkLine = agunod_buf.readLine();
			if (agunod_checkLine == null || agunod_checkLine.startsWith("#"))
				continue; // Skip comments or EOF.
			if (!agunod_checkLine.trim().isEmpty()) {
				agunod_buf.close();
				throw new IllegalStateException("Too many rows in the adjacency matrix.");
			}
		}
		agunod_buf.close();
	}
}
