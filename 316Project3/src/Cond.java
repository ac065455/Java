import java.util.*;

class Cond extends FunExp
{
	Exp exp1;
	Exp exp2;
	Exp exp3;

	Cond(Exp e1, Exp e2, Exp e3)
	{
		exp1 = e1;
		exp2 = e2;
		exp3 = e3;
	}

	@Override
	Val Eval(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		return null;
	}
}