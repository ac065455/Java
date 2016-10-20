import java.util.*;

class Not extends FunExp
{
	Exp exp;

	Not(Exp e)
	{
		exp = e;
	}

	@Override
	Val Eval(HashMap<String, Val> state) {
		if(exp == null){
			return null;
		}
		Val expVal = exp.Eval(state);
		
		if(!expVal.isBoolean()){
			System.out.println("Error: + operator cannot be applied to " + expVal.toString() );
			return null;
		}
		((BoolVal)expVal).val = !((BoolVal)expVal).val;
		return expVal;
	}



}