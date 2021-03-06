import java.util.*;

class Lt extends CompExp
{
	// Inherits "ExpList expList" from CompExp

	Lt(ExpList e)
	{
		expList = e;
	}

	@Override
	Val Eval(HashMap<String, Val> state) {
		return LtEval(expList, state);
	}
	//single expression
	Val LtEval(ExpList expList, HashMap<String,Val> state)
	{
		if ( expList == null )
			return new BoolVal(false);
		
		Val firstExpVal = expList.firstExp().Eval(state);
		Val secondExpVal = expList.secondExp().Eval(state);
		Val tailExpListVal = LtEval(expList.tailExpList(), state);
		
		if ( firstExpVal == null)
			return null;
		if ( ! firstExpVal.isNumber() )
		{
			System.out.println( "Error: + operator cannot be applied to " + firstExpVal.toString() );
			return null;
		}
		return firstExpVal;
		
	}
	
	Val LtEval(Exp exp, ExpList expList, HashMap<String,Val> state)
	{
		if ( expList == null )
			return new BoolVal(false);
		
		Val firstExpVal = expList.firstExp().Eval(state);
		Val secondExpVal = expList.secondExp().Eval(state);
		Val tailExpListVal = LtEval(expList.tailExpList(), state);
		if ( firstExpVal == null | tailExpListVal == null )
			return null;
		if ( ! firstExpVal.isNumber() )
		{
			System.out.println( "Error: + operator cannot be applied to " + firstExpVal.toString() );
			return null;
		}
		else
		{
			((BoolVal)firstExpVal).val = ((IntVal)secondExpVal).val < ((IntVal)tailExpListVal).val;
		}
		return firstExpVal;
	}


}