
public class CompExp extends MultiArgumentExp{
	CompOp compOp;
	ExpList expList;
	
	public CompExp(CompOp co, ExpList el)
	{
		compOp = co;
		expList = el;
	}//CompExp constructor
}//CompExp class
