import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class listBinTreeNode {
	String chStr;
	int prob;
	listBinTreeNode next;
	listBinTreeNode left;
	listBinTreeNode right;

	public listBinTreeNode(){}
	
	public listBinTreeNode(String chStr, int prob)
	{
		this.chStr = chStr;
		this.prob = prob;
		next = null;
		left = null;
		right = null;
	}//constructor
	
	public void printNode(listBinTreeNode T, PrintStream outFile2) throws FileNotFoundException
	{
		//outFile2.print("hello");
		if(T.next == null && (T.left == null || T.right == null)){
            outFile2.println(T.chStr + " " + T.prob + " " + " " + "" + " " + "" + " ");
        }else if(T.next == null){
            outFile2.println(T.chStr + " " + T.prob + " " + " " + T.left.chStr + " " + T.right.chStr + " ");
        }else if(T.left == null || T.right == null){
            outFile2.println(T.chStr + " " + T.prob + " " + T.next.chStr + " " + " ");
        }//else if
        else{
            outFile2.println(T.chStr + " " + T.prob + " " + T.next.chStr + " " + T.left.chStr + " " + T.right.chStr + " ");
        }//else
	}//printNode
}
