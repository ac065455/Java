import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;

public class main {
	public static void main(String args[]) throws FileNotFoundException{
		FileReader file = new FileReader(args[0]);
		PrintStream outFile1 = new PrintStream(args[1]);
		
		int data;
		int numRows;
		int numCols;
		int minVal;
		int maxVal;
		int thr_value;
		
		Scanner inFile = new Scanner(file);
		
		numRows = inFile.nextInt();
		System.out.println("numRows: " + numRows);
		numCols = inFile.nextInt();
		System.out.println("numCols: " + numCols);
		minVal = inFile.nextInt();
		System.out.println("minVal: " + minVal);
		maxVal = inFile.nextInt();
		System.out.println("maxVal: " + maxVal);
		
		Scanner input = new Scanner(System.in);
		System.out.println("enter a thr_value: ");
		thr_value = input.nextInt();
		
		threshold myThreshold = new threshold(maxVal, numRows, numCols);
		
	    int num = thr_value;
	    String outName = Integer.toString(num);
	    String outputName = outName + ".txt";
	    File file1 = new File(outputName);
	    PrintStream file2 = new PrintStream(file1);
		
		int[][] img;
		img = new int[numRows][numCols];
		
		for(int i=0; i<numRows; i++){
			for(int j=0; j<numCols; j++){
				while(inFile.hasNext()){
					img[i][j] = inFile.nextInt();
					myThreshold.computeThreshold(file2,img[i][j], thr_value, i, j);
					break;
				}//while
			}//for j
		}//for i
		myThreshold.printThreshold(file2, numRows, numCols);
	}//main
}//class
