import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws IOException{
		try{
		
		FileReader file = new FileReader(args[0]);
		PrintStream outFile1 = new PrintStream(args[1]);
		PrintStream outFile2 = new PrintStream(args[2]);
		PrintStream outFile3 = new PrintStream(args[3]);
		
		Scanner inFile = new Scanner(file);
	    String data;
	    String chStr;
	    int prob;
	    HuffmanLinkedList LL = new HuffmanLinkedList();
	    
		    while(inFile.hasNext()){
		        chStr = inFile.next();
		        prob = inFile.nextInt();
//		        System.out.println(chStr);
//		        System.out.println(prob);
		        listBinTreeNode newNode = new listBinTreeNode(chStr,prob);
		        LL.listInsert(newNode);
		        LL.printList(outFile3);
		    }//while
		    inFile.close();

	    HuffmanBinaryTree BT = new HuffmanBinaryTree(LL,outFile3);
	    BT.preOrderTraversal(BT.root, outFile2);
	    BT.constructCharCode(BT.root, "", outFile1);
	    
	    outFile1.close();
	    outFile2.close();
	    outFile3.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}//catch
	}//main

}//main class
