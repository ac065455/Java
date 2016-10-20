import java.util.*;

class Null extends Exp
{	
	static final Null nullVal = new Null();
	
	//----------??not sure if this is correct
	Val Eval(HashMap<String,Val> state) // You modify the body code
	{
		Val nVal = state.get(nullVal);
		if ( nVal != null )
			return nVal.cloneVal();
		System.out.println( "Error: variable "+nVal+" does not have a value" );
		return null;
	}
}