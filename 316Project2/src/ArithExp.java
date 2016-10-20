
public class ArithExp extends MultiArgumentExp{
	ArithOp arithOp;
	ExpList expList;
	
	public ArithExp(ArithOp ao, ExpList el)
	{
		arithOp = ao;
		expList = el;
	}//ArithExp constructor
}//ArithExp class
