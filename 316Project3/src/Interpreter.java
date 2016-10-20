import java.util.*;

public class Interpreter extends Parser{
	public static HashMap<String, Val> state = new HashMap<String, Val>();
	
	public static void main(String[] args){
		setIO(args[0], args[1]);
		setLex();
		
		getToken();
		Program program = program();
		if(!t.isEmpty())
			displayln(t + " : Syntax Error, unexpected symbol where id expected");
		else if(!syntaxErrorFound){
			program.semanticCheck();
//			assignmentList.printParseTree("");       // print the parse tree in linearly indented form in argv[1] file
//			Program.M(state);              // interpret the assignment list
			System.out.println(state.toString()); // print the program state on the terminal
		}
		closeIO();
	}//main
}//interpreter class
