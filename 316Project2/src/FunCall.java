
public class FunCall extends MultiArgumentExp{
	FunName funName;
	ExpList expList;
	
	public FunCall(FunName fn, ExpList el)
	{
		funName = fn;
		expList = el;
	}//FunCall constructor
}//FunCall class
