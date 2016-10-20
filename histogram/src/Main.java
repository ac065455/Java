import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
	public static void main(String args[]) throws FileNotFoundException{
		FileReader file = new FileReader(args[0]);
		Scanner inFile = new Scanner(file);
		
		int numRows = inFile.nextInt();
		int numCols = inFile.nextInt();
		int minVal = inFile.nextInt();
		int maxVal = inFile.nextInt();
		
		histogram hist = new histogram(numRows, numCols, minVal, maxVal);
	}//main
}//class
