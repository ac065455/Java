import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.text.NumberFormat;
import java.util.Scanner;

public class eucleanDistanceTransform {
	int numRows;
    int numCols;
    int minVal;
    int maxVal;
    int newMin;
    int newMax;
    double [][] zeroFramedAry;
    double[] neighborAry;
    
    eucleanDistanceTransform(int numRows, int numCols, int minVal, int maxVal){
        this.numRows = numRows;
        this.numCols = numCols;
        this.minVal = minVal;
        this.maxVal = maxVal;
        
        zeroFramedAry = new double[numRows+2][numCols+2];      
        for(int i=0; i<numRows+2; i++){
            for(int j=0; j<numCols+2; j++){
                zeroFramedAry[i][j] = 0;
            }//for j
        }//for i
        
        neighborAry = new double[5];
        for(int i=0; i<5; i++){
            neighborAry[i] = 0;
        }//for i
    }//constructor
    
    //load image into 2d array
    void loadImage(String path) throws FileNotFoundException{
    	FileReader file = new FileReader(path);
    	Scanner inFile = new Scanner(file);
    	numRows = inFile.nextInt();
    	numCols = inFile.nextInt();
    	minVal = inFile.nextInt();
    	maxVal = inFile.nextInt();
        for(int i=1; i<numRows+1; i++){
            for(int j=1; j<numCols+1; j++){
                while(inFile.hasNextInt()){
                    zeroFramedAry[i][j] = inFile.nextInt();
                    break;
                }//while
            }//for j
        }//for i
        inFile.close();
    }//loadImage
    
    void firstPassEuclean(){
        double topLeft, top, topRight, left, newMin = 999, newMax = 1;
        double euclean = Math.sqrt(2);
        for(int i=1; i<numRows+1; i++){
            for(int j=1; j<numCols+1; j++){
                if(zeroFramedAry[i][j] > 0){
                    neighborAry[0] = topLeft = zeroFramedAry[i-1][j-1] + euclean;
                    neighborAry[1] = top = zeroFramedAry[i-1][j] + 1;
                    neighborAry[2] = topRight = zeroFramedAry[i-1][j+1] + euclean;
                    neighborAry[3] = left = zeroFramedAry[i][j-1] + 1;
                    double max = findMaxDistance(neighborAry);
                    double min = findMinDistance(max,neighborAry);
                    zeroFramedAry[i][j] = min;
                }//if
            }//for j
        }//for i
        for(int i=1; i<numRows+1; i++){
            for(int j=1; j<numCols+1; j++){
                if(zeroFramedAry[i][j] > 0){
                    if(zeroFramedAry[i][j] < newMin){
                        newMin = zeroFramedAry[i][j];
                    }//if
                    if(zeroFramedAry[i][j] > newMax){
                        newMax = zeroFramedAry[i][j];
                    }//else if
                }//if
            }//for j
        }//for i
        this.newMax = (int)newMax;
        this.newMin = (int)newMin;
    }//firstPassEuclean
    
    void secondPassEuclean(){
        double right, botLeft, bot, botRight, self, newMin = 999, newMax = 1;
        double euclean = Math.sqrt(2);
        for(int i=numRows; i>0; i--){
            for(int j=numCols ;j>0; j--){
                if(zeroFramedAry[i][j] > 0){
                    neighborAry[0] = right = zeroFramedAry[i][j+1] + 1;
                    neighborAry[1] = botLeft = zeroFramedAry[i+1][j-1] + euclean;
                    neighborAry[2] = bot = zeroFramedAry[i+1][j] + 1;
                    neighborAry[3] = botRight = zeroFramedAry[i+1][j+1] + euclean;
                    neighborAry[4] = self = zeroFramedAry[i][j];
                    double max = findMaxDistance(neighborAry[4],findMaxDistance(neighborAry));
                    double min = findMinDistance(neighborAry[4],findMinDistance(max,neighborAry));
                    zeroFramedAry[i][j] = min;
                }//if
            }//for j
        }//for i
        for(int i=1; i<numRows+1; i++){
            for(int j=1; j<numCols+1; j++){
                if(zeroFramedAry[i][j] > 0){
                    if(zeroFramedAry[i][j] < newMin){
                        newMin = zeroFramedAry[i][j];
                    }//if
                    if(zeroFramedAry[i][j] > newMax){
                        newMax = zeroFramedAry[i][j];
                    }//else if
                }//if
            }//for j
        }//for i
        this.newMax = (int)newMax;
        this.newMin = (int)newMin;
    }//secondPassEuclean
    
    double findMaxDistance(double neighborAry[]){
        double max=0;
        for(int i=0; i<4; i++){
            if(neighborAry[i] > max){
                max = neighborAry[i];
            }//if
        }//for i
        return max;
    }//findMax
    
    double findMaxDistance(double self, double neighbors){
        if(self > neighbors){
            return self;
        }//if
        else{
            return neighbors;
        }//else
    }//findMinDistance
    
    double findMinDistance(double self, double neighbors){
        if(self < neighbors){
            return self;
        }//if
        else{
            return neighbors;
        }//else
    }//findMinDistance
    
    double findMinDistance(double max, double neighborAry[]){
        double min = max;
        for(int i=0; i<4; i++){
            if(neighborAry[i] < min){
                min = neighborAry[i];
            }//if
        }//for i
        return min;
    }//findMin
    
    void prettyPrint(PrintStream outFile){
        outFile.println(numRows + " " + numCols + " " + newMin + " " + newMax);
        for(int i=0; i<numRows+2; i++){
            for(int j=0; j<numCols+2; j++){
                if(zeroFramedAry[i][j] > 0){
                    outFile.print((int)(zeroFramedAry[i][j] + .5));
                }//if
                else{
                    outFile.print(" ");
                }//else
            }//for j
            outFile.println();
        }//for i
    }//prettyPrint
    
    void prettyPrintDistance(PrintStream outFile){
        outFile.println(numRows + " " + numCols + " " + newMin + " " + newMax);
        for(int i=0; i<numRows+2; i++){
            for(int j=0; j<numCols+2; j++){
            	if(zeroFramedAry[i][j] < 2){
            		outFile.print("    " + (int)zeroFramedAry[i][j]);
            	}//if
            	else{
            	outFile.printf("%.2f" + " ", zeroFramedAry[i][j]);
            	}//else
            }//for j
            outFile.println();
        }//for i
    }//prettyPrint

}//class
