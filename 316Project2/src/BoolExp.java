
public class BoolExp extends MultiArgumentExp{
	BoolOp boolOp;
	ExpList expList;
	
	public BoolExp(BoolOp bo, ExpList el)
	{
		boolOp = bo;
		expList = el;
	}//BoolExp constructor
}//BoolExp class
