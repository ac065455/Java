import java.util.*;

class FunCall extends MultiArgumentExp
{
	FunName funName;

	// Inherits "ExpList expList" from MultiArgumentExp

	FunCall(FunName f, ExpList e)
	{
		funName = f;
		expList = e;
	}

	@Override
	Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		return null;
	}
}