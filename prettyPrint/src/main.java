import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;

public class main {
	public static void main(String args[]) throws FileNotFoundException{
		String input = args[0];
		FileReader file = new FileReader(input);
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
		
		Scanner input1 = new Scanner(System.in);
		System.out.println("enter a thr_value: ");
		thr_value = input1.nextInt();
	    
		threshold myThreshold = new threshold(maxVal, numRows, numCols);
		myThreshold.loadImage(input);
	    
	    String array = args[0];
	    String substring = array.substring(0, array.lastIndexOf('.'));
	    String outputName = substring + "_PP";
	    File file1 = new File(outputName);
	    PrintStream file2 = new PrintStream(file1);
	    
	    myThreshold.prettyPrint(file2);
	}//main
}//class
