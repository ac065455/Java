import java.util.*;

class Eq extends CompExp
{
	// Inherits "ExpList expList" from CompExp

	Eq(ExpList e)
	{
		expList = e;
	}

	@Override
	Val Eval(HashMap<String, Val> state) {
		return orEval(expList, state);
	}

	Val orEval(ExpList expList, HashMap<String, Val> state) 
	{
		if ( expList == null )
			return new BoolVal(false);
		
		Val firstExpVal = expList.firstExp().Eval(state);
		Val tailExpListVal = orEval(expList.tailExpList(), state);
		if ( firstExpVal == null | tailExpListVal == null )
			return null;
//		else if ( ! firstExpVal.isBoolean() )
//		{
//			System.out.println( "Error: + operator cannot be applied to " + firstExpVal.toString() );
//			return null;
//		}
		else
		{
			((BoolVal)firstExpVal).val = ((BoolVal)firstExpVal).val == ((BoolVal)tailExpListVal).val;
			return firstExpVal;
		}
		}//orEval
}