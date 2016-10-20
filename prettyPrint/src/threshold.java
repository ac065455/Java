import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;

public class threshold {
	int numRows;
    int numCols;
    int minVal;
    int maxVal;
    int[] thr_ary;
    int[][] img;
    int thr_value;
    
    threshold(int maxVal, int numRow, int numCol){
        numRows = numRow;
        numCols = numCol;
        this.maxVal = maxVal;
        
        thr_ary = new int[maxVal];
        for(int i=0; i<maxVal; i++){
            thr_ary[i] = 0;
        }//for
        
        img = new int[numRows][numCols];
       
        for(int i=0; i<numRows; i++){
            for(int j=0; j<numCols; j++){
                img[i][j] = 0;
            }//for j
        }//for i
    }//constructor
    
    void loadImage(String path) throws FileNotFoundException{
    	FileReader file = new FileReader(path);
    	Scanner inFile = new Scanner(file);
        int data;
        numRows = inFile.nextInt();
        numCols = inFile.nextInt();
        minVal = inFile.nextInt();
        maxVal = inFile.nextInt();
        for(int i=0; i<numRows; i++){
            for(int j=0; j<numCols; j++){
                while(inFile.hasNextInt()){
                	data = inFile.nextInt();
                    img[i][j] = data;
                    break;
                }//while
            }//for j
        }//for i
    }//loadImage
    
    void prettyPrint(PrintStream outFile){
    	outFile.print(numRows + " " + numCols + " " + minVal + " " + maxVal);
    	outFile.println();
        for(int i=0; i<numRows; i++){
            for(int j=0; j<numCols; j++){
                if(img[i][j] > 0){
                    outFile.print(img[i][j]);
                }//if
                else{
                    outFile.print(" ");
                }//else
            }//for j
            outFile.println();
        }//for i
    }//prettyPrint
}//class
