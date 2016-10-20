import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class HuffmanLinkedList {
	listBinTreeNode listHead;
	listBinTreeNode oldListHead;
	String chStr;
	int prob;
	PrintWriter outFile3;

	// create a dummylistNode and let listHead point to it
	public HuffmanLinkedList() {
		listHead = new listBinTreeNode("dummy", 0);
	}// HuffmanLinkedList constructor

	// inserting newNode into the list using inserting sort
	public void listInsert(listBinTreeNode newNode) {
		if (newNode.prob < listHead.prob) {
			newNode.next = listHead;
		} // if
		else {
			listBinTreeNode spot = findSpot(newNode.prob);
			newNode.next = spot.next;
			spot.next = newNode;
		} // else
	}// listInsert function

	public listBinTreeNode findSpot(int pro) {
		listBinTreeNode spot = listHead;
		while (spot.next != null && spot.next.prob < pro) {
			spot = spot.next;
		} // while
		return spot;
	}// findSpot function

	public Boolean isEmpty() {
		if (listHead == null) {
			return true;
		} // if
		else {
			return false;
		} // else
	}// isEmpty function

	// print the list from listHead to the end to the outFile3 (argv[4]) in the
	// following format:
	// listHead -->(“dummy”, 0, nextString)-->(nextString, prob, next)
	// -->(nextString, prob, nextString)--> ...... --> (nextString, prob,
	// NULL)--> NULL

	public void printList(PrintStream outFile3) throws IOException {
		listBinTreeNode spot = listHead;
		outFile3.print("ListHead -->(\"" + spot.chStr + "\"," + spot.prob + ",");
		outFile3.println();
		while (spot.next != null) {
			spot = spot.next;
			outFile3.print(spot.chStr + ")-->(" + spot.chStr + "," + spot.prob + ",");
		} // while
		outFile3.print("NULL)-->NULL");
		outFile3.println();
	}// printList

}// HuffmanLinkedList class
