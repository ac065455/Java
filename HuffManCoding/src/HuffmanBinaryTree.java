import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class HuffmanBinaryTree {
	String chStr;
	int prob;
	String code;
	listBinTreeNode root;
	PrintWriter outFile1;
	PrintWriter outFile2;
	PrintWriter outFile3;

	public HuffmanBinaryTree(HuffmanLinkedList LL, PrintStream outFile3) throws IOException {
		listBinTreeNode oldListHead = LL.listHead;
		oldListHead.next = LL.listHead.next;
		// cout<<LL.listHead->next->prob;
		listBinTreeNode first = LL.listHead.next;
		while (first.next != null) {
			listBinTreeNode newNode = new listBinTreeNode();
			newNode.prob = first.prob + first.next.prob; // first is the node
															// after dummy
			newNode.chStr = first.chStr + first.next.chStr;
			System.out.print(newNode.prob + " " + newNode.chStr);
			newNode.left = first;
			newNode.right = first.next;
			LL.listHead = first.next.next;
			first = LL.listHead;

			if (LL.listHead != null)
				LL.listInsert(newNode);

			if (LL.listHead == null || newNode.prob < LL.listHead.prob) {
				first = newNode;
				LL.listHead = first;
			} // if
			newNode.printNode(newNode, outFile3);
		} // while
		root = first;

	}// HuffmanBinaryTree constructor

	public void preOrderTraversal(listBinTreeNode T, PrintStream outFile2) throws IOException {

		if (T == null) {
			return;
		} // if
		else {
			T.printNode(T, outFile2);
			preOrderTraversal(T.left, outFile2);
			preOrderTraversal(T.right, outFile2);
		} // else
	}// preOrderTraversal function

	public void constructCharCode(listBinTreeNode T, String code, PrintStream outFile1) throws IOException {
		if (T == null) {
			return;
		} // if
		else if (T.left == null && T.right == null) {
			outFile1.print(T.chStr); // argv[2]
			outFile1.print(code + "\n"); // to outfile2
		} // else if
		else {
			constructCharCode(T.left, code + "0", outFile1);
			constructCharCode(T.right, code + "1", outFile1);
		} // else
	}// constructCharCode function
	

}// HuffmanBinaryTree class
