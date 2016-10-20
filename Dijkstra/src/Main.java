import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {

	public static void main(String args[]) throws FileNotFoundException {
		FileReader file = new FileReader(args[0]);
		PrintStream outFile1 = new PrintStream(args[1]);
		PrintStream outFile2 = new PrintStream(args[2]);

		int data = 0;
		int numNodes;
		int sourceNode;
		int Ni = 0;
		int Nj = 0;
		int cost = 0;
		int count = 1;

		Scanner inFile = new Scanner(file);
		numNodes = inFile.nextInt();
		DijkstraSSS Dijkstra = new DijkstraSSS(numNodes);

		while (inFile.hasNext()) {
			if (count == 1) {
				Ni = inFile.nextInt();
				count++;
			} // if
			else if (count == 2) {
				Nj = inFile.nextInt();
				count++;
			} // else if
			else if (count == 3) {
				cost = inFile.nextInt();
				sourceNode = Ni;
				Dijkstra.loadCostMatrix(Ni, Nj, cost);
				count = 1;
			} // else if
		} // while
		inFile.close();
		Dijkstra.debugPrint(outFile2);
		Dijkstra.Dijkstra(outFile1, outFile2);
		outFile1.println();

		for (int i = 2; i < numNodes + 1; i++) {
			sourceNode = 2;
			Dijkstra.nextSourceNode(i);
			Dijkstra.Dijkstra(outFile1, outFile2);
			outFile1.println();
		} // for i

		outFile1.close();
		outFile2.close();
	}// main

}//class
