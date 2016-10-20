import java.io.PrintStream;

public class DijkstraSSS {
	int numNodes; // number of nodes in G
	int sourceNode;
	int minNode;
	int currentNode;
	int newCost;
	int[][] costMatrix; // a 2-D cost matrix (integer array), size of N X N,
						// should be dynamically allocated.
	// Initially, costMatrix[i][i] set to zero and all others set to infinity,
	// 99999
	int[] fatherAry; // a 1-D integer array, size of N, should be dynamically
						// allocated.
	// initially set to itself, i.e., father[i] = i
	int[] markedAry; // a 1-D integer array, size of N, should be dynamically
						// allocated.
	// initially set to 0 (not marked)
	int[] bestCostAry; // a 1-D integer array, size of N, should be dynamically
						// allocated.
	// initially set to 9999 (infinity)

	public DijkstraSSS(int n) {
		numNodes = n;
		costMatrix = new int[n + 1][n + 1];

		fatherAry = new int[n + 1];
		markedAry = new int[n + 1];
		bestCostAry = new int[n + 1];

		// initialize diagonal to 0
		for (int i = 0; i < n + 1; i++) {
			for (int j = 0; j < n + 1; j++) {
				if (i == j) {
					costMatrix[i][j] = 0;
				} // if
				else {
					costMatrix[i][j] = 9999;
				} // else
			} // for j
		} // for i

		// initialize all 3 arrays
		for (int i = 0; i < n + 1; i++) {
			fatherAry[i] = i;
			markedAry[i] = 0;
			bestCostAry[i] = 9999;
		} // for i

		sourceNode = 1;
		bestCostAry[sourceNode] = 0;
	}// DijkstraSSS constructor

	public void loadCostMatrix(int Ni, int Nj, int cost) {
		costMatrix[Ni][Nj] = cost;
	}// read from input file and fill the costMatrix,

	public void loadBestCostAry(int s) {
		int n = numNodes;
		for (int i = 0; i < n; i++) {
			bestCostAry[i] = s;
		} // for i
	}// copy the row of source node from costMatrix,

	public void loadFatherAry(int s) {
		int n = numNodes;
		for (int i = 0; i < n; i++) {
			fatherAry[i] = s;
		} // for i
	}// set all to source

	// void loadMarkedAry(){
	// int n = numNodes;
	// for(int i = 0; i < n; i++){
	// markedAry[i] = 0;
	// }//for i
	// }// set all to 0,

	public int computeCost(int minNode, int currentNode) {
		return bestCostAry[minNode] + costMatrix[minNode][currentNode];
	}// it compute the currentNode’s cost ( bestCostArray[minNode] + cost from
		// minNode to currentNode, in costMatrix)

	public void markMinNode(int minNode) {
		markedAry[minNode] = 1;
	}// mark the minNode in markedAry

	public void changeFather(int node, int minNode) {
		fatherAry[currentNode] = minNode;
	}// set node’s father be minNode in fatherAry

	public void changeCost(int node, int newCost) {
		bestCostAry[node] = newCost;
	}// set node’s best cost to newCost in bestCostAry

	public void Dijkstra(PrintStream outFile1, PrintStream outFile2) {
		int n = numNodes;
		int count = 1;
		while (count < n + 1) {
			findMinNode(outFile2);
			for (int i = 1; i < n + 1; i++) {
				if (markedAry[i] == 0) {
					expandMinNode(i, outFile2);
				} // if
			} // for i
			count++;
		} // while
		debugPrint(outFile2);
		outFile1.println("the graph has " + numNodes + " nodes");
		outFile1.println("the source node is " + sourceNode);
		outFile1.println();
		int s = 1;
		while (s <= numNodes) {
			outFile1.println("the path from " + sourceNode + " to: " + s + ";");
			printShortestPath(s, outFile1);
			outFile1.print(" cost = " + bestCostAry[s]);
			outFile1.println();
			s++;
		} // while
	}// Dijkstra

	public void printShortestPath(int s, PrintStream outFile1) {
		if (fatherAry[s] == sourceNode) {
			outFile1.print(" " + sourceNode + " is greater than " + s + ";");
			return;
		} // if
		else {
			printShortestPath(fatherAry[s], outFile1);
			outFile1.print(" less than " + s + ";");
		} // else
	}// printShortestPath

	public void expandMinNode(int unmarkedNode, PrintStream outFile2) {
		currentNode = unmarkedNode;
		newCost = computeCost(minNode, currentNode);
		if (newCost < bestCostAry[currentNode]) {
			changeFather(currentNode, minNode);
			changeCost(currentNode, newCost);
			debugPrint(outFile2);
		} // if
	}// expandMinNode

	public void debugPrint(PrintStream outFile2) {
		int n = numNodes;
		outFile2.println("Debug Print ");
		outFile2.println();
		outFile2.println("sourceNode " + sourceNode);
		outFile2.println();
		outFile2.println("fatherAry ");
		for (int i = 1; i < n + 1; i++) {
			outFile2.print(fatherAry[i] + " ");
		} // for i
		outFile2.println();
		outFile2.println();
		outFile2.println("bestCostAry ");
		for (int i = 1; i < n + 1; i++) {
			outFile2.print(bestCostAry[i] + " ");
		} // for i
		outFile2.println();
		outFile2.println();
		outFile2.println("markedAry ");
		for (int i = 1; i < n + 1; i++) {
			outFile2.print(markedAry[i] + " ");
		} // for i
		outFile2.println();
		outFile2.println();
	}// debugPrint

	public void findMinNode(PrintStream outFile1) {
		int min = 0;
		int n = numNodes;
		for (int i = 0; i < n + 1; i++) {
			if (bestCostAry[i] < bestCostAry[min]) {
				if (markedAry[i] == 0) {
					min = i;
				} // if
			} // if
		} // for
		minNode = min;
		markMinNode(minNode);
		outFile1.println("minNode " + minNode);
	}// findMinNode

	public void nextSourceNode(int nextSourceNode) {
		int n = numNodes;
		fatherAry = new int[n + 1];
		markedAry = new int[n + 1];
		bestCostAry = new int[n + 1];
		for (int i = 0; i < n + 1; i++) {
			fatherAry[i] = i;
			markedAry[i] = 0;
			bestCostAry[i] = 9999;
		} // for i
		sourceNode = nextSourceNode;
		bestCostAry[sourceNode] = 0;
	}
}
