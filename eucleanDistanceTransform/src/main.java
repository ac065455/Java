import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;

public class main {
	public static void main(String args[]) throws FileNotFoundException{
		FileReader file = new FileReader(args[0]);
	    Scanner inFile = new Scanner(file);
	    
	    PrintStream outFile1 = new PrintStream(args[1]);
	    PrintStream outFile2 = new PrintStream(args[2]);
	    
	    int numRows;
	    int numCols;
	    int minVal;
	    int maxVal;
	    
	    numRows = inFile.nextInt();
	    numCols = inFile.nextInt();
	    minVal = inFile.nextInt();
	    maxVal = inFile.nextInt();
	    System.out.println("numRows: " + numRows);
	    System.out.println("numCols: " + numCols);
	    System.out.println("minVal: " + minVal);
	    System.out.println("maxVal: " + maxVal);
	    
	    eucleanDistanceTransform EDT = new eucleanDistanceTransform(numRows, numCols, minVal, maxVal);
	    EDT.loadImage(args[0]);
	    outFile2.println("result after pass 1: ");
	    EDT.firstPassEuclean();
	    EDT.prettyPrint(outFile2);
	    
	    outFile2.println("result after pass 2: ");
	    outFile1.println("result after pass 2: ");
	    EDT.secondPassEuclean();
	    EDT.prettyPrintDistance(outFile1);
	    EDT.prettyPrint(outFile2);
	    outFile2.close();
	    outFile1.close();
	}//main
}//class
