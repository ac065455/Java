import java.util.HashMap;
import java.util.LinkedList;

public class Parser extends LexAnalyzer
{
    private static HashMap<String, LinkedList<String>> classSymbol = new HashMap<>();
    private static HashMap<String, LinkedList<String>> functionSymbol = new HashMap<>();
    
    static String className;
    static String funName;
    
	static boolean errorFound = false;
	
	public static program program()
	{
		ClassDefList classDefList = classDefList();
		FunDefList funDefList = funDefList();
		return new program(classDefList,funDefList);
	}
	
	public static ClassDefList classDefList()
	{
		if (state == State.Keyword_class)
		{
			getToken();
			ClassDef classDef = classDef();
			ClassDefList classDefList = classDefList();
			return new multipleClassDef(classDef,classDefList);
		}
		else
			return new emptyClassDefList();
	}
	
	public static ClassDef classDef()
	{
		ClassName className = className();
		ClassBody classBody = classBody();
		return new ClassDef(className,classBody);
	}
	
	public static ClassName className()
	{
		if (state == State.Id)
		{
			className = t;
			getToken();
			//System.out.println(id);
			classSymbol.put(className, new LinkedList<String>());
			return new ClassName(className);
		}//if
		else
			errorMsg(5);
		return null;
	}//ClassName
	
	public static ClassBody classBody()
	{
		if (state == State.LBrace)
		{
			getToken();
			FieldVarList fieldVarList = fieldVarList();
			if (state == State.RBrace)
			{
			getToken();
			return new ClassBody(fieldVarList);
			}//if	
		}//if
		return null;
	}//ClassBody method
	
	public static FieldVarList fieldVarList()
	{
		if (state == State.Id)
		{
			FieldVar fieldVar = fieldVar();
			FieldVarList fieldVarList = fieldVarList();
			return new multipleFieldVarList(fieldVar,fieldVarList);
		}
		else
			return new emptyFieldVarList();
	}//FieldVarList method
	
	public static FieldVar fieldVar()
	{
		if (state == State.Id)
		{
			String id = t;
			classSymbol.get(className).add(id);
			getToken();
			//System.out.println(id);
			return new FieldVar(id);
		}
		else
			errorMsg(5);
		return null;
	}//FieldVar method
	
	public static FunDefList funDefList()
	{
		if (state == State.LParen)
		{
			FunDef funDef = funDef();
			FunDefList funDefList = funDefList();
			return new multipleFunDefList(funDef,funDefList);
		}
		else
			return new emptyFunDefList();
	}//FunDefList
	
	public static FunDef funDef()
	{
		if (state == State.LParen)
		{
			getToken();
			Header header = header();
			Exp exp = exp();
			if (state == State.RParen)
			{
				getToken();
				return new FunDef(header,exp);
			}//if
			else{
				errorMsg(1);
			}//else
		}//if
		return null;
	}//FunDef method
	
	public static Header header()
	{
		if (state == State.LParen)
		{
			getToken();
			FunName funName = funName();
			ParameterList parameterList = parameterList();
			if (state == State.RParen)
			{
				getToken();
				return new Header(funName,parameterList);
			}//if
		}//if
		return null;
	}//Header method
	
	public static FunName funName()
	{
		if (state == State.Id)
		{
			funName = t;
			getToken();
			functionSymbol.put(funName, new LinkedList<String>());
			return new FunName(funName);
		//	System.out.println(id);
		}
		else 
			errorMsg(5);
		return null;
	}//FunName method
	
	public static ParameterList parameterList()
	{
		if (state == State.Id)
		{
			Parameter parameter = parameter();
			ParameterList parameterList = parameterList();
			return new multipleParameterList(parameter,parameterList);
		}
		else
			return new emptyParameterList();
	}//ParameterList method
	
	public static Parameter parameter()
	{
		if (state == State.Id)
		{
			String id = t;
			functionSymbol.get(funName).add(id);
			getToken();
			
			//System.out.println(id);
		}
		else
			errorMsg(5);
		return null;
	}//Parameter method
	
	public static Exp exp()
	{
		if (state == State.Id)
		{
			String id = t;
			getToken();
			//System.out.println(id);
			return new ExpId(id);
		}
		else if (state == State.Int)
		{
			String value = t;
			getToken();
			//System.out.println(value);
			return new ExpInt(value);
		}
		else if (state == State.Float)
		{
			String valueF = t;
			getToken();
			//System.out.println(valueF);
			return new ExpFloat(valueF);
		}
		else if (state == State.FloatE)
		{
			String valueFE = t;
			getToken();
			//System.out.println(valueFE);
			return new ExpFloatE(valueFE);
		}
		else if (state == State.Keyword_null)
		{
			String n = t;
			getToken();
			//System.out.println(n);
			return new ExpNull();
		}
		else if (state == State.LParen)
		{
			getToken();
			FunExp funExp = funExp();
			if (state == State.RParen)
			{
				getToken();
				return new ExpParen(funExp);
			}
		}
		else
			return null;
		return null;
	}
	
	public static FunExp funExp()
	{
		if (state == State.Keyword_if)
		{
		Cond cond = cond();
		return cond;
		}
		else if (state == State.Not)
		{
			Not not = not();
			return not;
		}
		else
		{
			MultiArgumentExp multiArgumentExp = multiArgumentExp();
			return multiArgumentExp;
		}
	}
	
	public static MultiArgumentExp multiArgumentExp()
	{
		if (state == State.Id)
		{
			FunCall funCall = funCall();
			return funCall;
		}
		else if (state == State.Add || state == State.Sub || state == State.Mul || state ==State.Div)
		{
			ArithExp arithExp = arithExp();
			return arithExp;
		}
		else if (state == State.Or || state == State.And)
		{
			BoolExp boolExp = boolExp();
			return boolExp;
		}
		else if (state == State.Lt || state == State.Le || state == State.Gt || state == State.Ge || state == State.Eq)
		{
			CompExp compExp = compExp();
			return compExp;
		}
		else
			return null;
	}
	
	public static FunCall funCall()
	{
		FunName funName = funName();
		ExpList expList = expList();
		return new FunCall(funName,expList);
	}
	
	public static ArithExp arithExp()
	{
		ArithOp arithOp = arithOp();
		ExpList expList = expList();
		return new ArithExp(arithOp,expList);
	}
	
	public static BoolExp boolExp()
	{
		BoolOp boolOp = boolOp();
		ExpList expList = expList();
		return new BoolExp(boolOp,expList);
	}
	
	public static CompExp compExp()
	{
		CompOp compOp = compOp();
		ExpList expList = expList();
		return new CompExp(compOp,expList);
	}
	
	public static ExpList expList()
	{
		if (state == State.Id || state == State.Int || state == State.Float || state == State.FloatE || state == State.Keyword_null || state == State.LParen)
		{
			Exp exp = exp();
			ExpList expList = expList();
			return new multipleExpList(exp,expList);
		}
		else 
			return new emptyExpList();
	}
	
	public static Cond cond()
	{
		if (state == State.Keyword_if)
		{
			getToken();
			Exp exp1 = exp();
			Exp exp2 = exp();
			Exp exp3 = exp();
			return new Cond(exp1,exp2,exp3);
		}
		else 
			errorMsg(5);
		return null;
	}
	
	public static Not not()
	{
		if (state == State.Not)
		{
			getToken();
			Exp exp = exp();
			return new Not(exp);
		}
		else
			errorMsg(5);
		return null;
	}
	
	public static ArithOp arithOp()
	{
		if (state == State.Add)
		{
			String id = t;
			getToken();
			//System.out.println(id);
			return new Add();
		}
		else if (state == State.Sub)
		{
			String id = t;
			getToken();
			//System.out.println(id);
			return new Sub();
		}
		else if (state == State.Mul)
		{
			String id = t;
			getToken();
		//	System.out.println(id);
			return new Mult();
		}
		else if (state == State.Div)
		{
			String id = t;
			getToken();
		//	System.out.println(id);
			return new Div();
		}
		else
			return null;
	}
	
	public static BoolOp boolOp()
	{
		if (state == State.Or)
		{
			String id = t;
			getToken();
			//System.out.println(id);
			return new Or();
		}
		else if (state == State.And)
		{
			String id = t;
			getToken();
			//System.out.println(id);
			return new And();
		}
		else
			return null;
	}
	
	public static CompOp compOp()
	{
		if (state == State.Lt)
		{
			String id = t;
			getToken();
			//System.out.println(id);
			return new Lt();
		}
		else if (state == State.Le)
		{
			String id = t;
			getToken();
			//System.out.println(id);
			return new Lte();
		}
		else if (state == State.Gt)
		{
			String id = t;
			getToken();
			//System.out.println(id);
			return new Gt();
		}
		else if (state == State.Ge)
		{
			String id = t;
			getToken();
			//System.out.println(id);
			return new Gte();
		}
		else if (state == State.Eq)
		{
			String id = t;
			getToken();
			//System.out.println(id);
			return new Equals();
		}
		else
			return null;
	}
	
	
	public static void errorMsg(int i)
	{
		errorFound = true;
		
		display(t + " : Syntax Error, unexpected symbol where");

		switch( i )
		{
		case 1:	displayln(" arith op or ) expected"); return;
		case 2: displayln(" id, int, float, or ( expected"); return;
		case 3:	displayln(" = expected"); return;
		case 4:	displayln(" ; expected"); return;
		case 5:	displayln(" id expected"); return;
		}
	}
	
	public static void print(HashMap<String,LinkedList<String>> hm)
	{
		for(String key: hm.keySet())
		{
			System.out.print(key);
			System.out.println(hm.get(key).toString());
		}
		System.out.println();
		
	}
	
	public static void main(String argv[])
	{
		// argv[0]: input file containing an assignment list
		// argv[1]: output file displaying the parse tree
		
		setIO( argv[0], argv[1] );
		setLex();

		getToken();

		program program = program(); // build a parse tree
		if ( ! t.isEmpty() )
			errorMsg(5);
		print(classSymbol);
		print(functionSymbol);

		closeIO();
	}
}//Parser class
