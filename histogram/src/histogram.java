import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;

public class histogram {
	int numRows, numCols, minVal, maxVal;
	int[] histogramAry;
	int[][] img;
	
	public histogram(int numRows, int numCols, int minVal, int maxVal){
		this.numRows = numRows;
		this.numCols = numCols;
		this.minVal = minVal;
		this.maxVal = maxVal;
		
		histogramAry = new int[maxVal];
		for(int i=0; i<maxVal; i++){
			histogramAry[i] = 0;
		}//for i
		
		img = new int[numRows][numCols];
		for(int i=0; i<numRows; i++){
			for(int j=0; j<numCols; j++){
				img[i][j] = 0;
			}//for j 
		}//for i
	}//constructor
	
	public void loadImage(String path) throws FileNotFoundException{
		FileReader file = new FileReader(path);
		Scanner inFile = new Scanner(file);
		numRows = inFile.nextInt();
		numCols = inFile.nextInt();
		minVal = inFile.nextInt();
		maxVal = inFile.nextInt();
		
		
	}//loadImage
}//histogram
