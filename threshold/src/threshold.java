import java.io.PrintStream;

public class threshold {
    int numRows;
    int numCols;
    int minVal;
    int maxVal;
    int[] thr_ary;
    int[][] img;
    
    public threshold(int maxVal, int numRows, int numCols){
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
    
    void computeThreshold(PrintStream file2, int data, int value, int numRows, int numCols){
        if(data < value){
            img[numRows][numCols] = 0;
        }//if
        else{
            img[numRows][numCols] = 1;
        }//else
    }//computeThreshold
    
    void printThreshold(PrintStream file2, int numRows, int numCols){
        for(int i=0; i<numRows; i++){
            for(int j=0; j<numCols; j++){
                file2.print(img[i][j]);
            }//for j
            file2.println();
        }//for i
    }//printThreshold
}//class
