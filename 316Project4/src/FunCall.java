import java.util.*;

class FunCall extends MultiArgumentExp
{
	FunName funName;
	ExpList eList = expList;

	// Inherits "ExpList expList" from MultiArgumentExp

	FunCall(FunName f, ExpList e)
	{
		funName = f;
		expList = e;
	}

	Val Eval(HashMap<String,Val> state)
	{
		String funNameId = funName.id.id;
		//System.out.println("3333");
		FunData funData = SemanticChecker.functionSymbolTable.get(funNameId);
		if ( funData != null ) // funName is a user-defined function
		{
			LinkedList<String> paramList = funData.parameterList;
			//ExpList eList = expList; made outside of method for easier access
			HashMap<String,Val> newState = new HashMap<String,Val>();

			for ( String parameter: paramList ) // form a new function-call state "newState"
			{
				//System.out.println("5555");
				if ( eList == null )
					newState.put(parameter, null);
				else
				{
					Val eVal = eList.firstExp().Eval(state);
					if ( eVal == null )
						return null;
					newState.put(parameter, eVal); // "parameter" is bound to eVal
					eList = eList.tailExpList();
				}
			}
			//System.out.println("2222");
			return funData.bodyExp.Eval(newState);
		}
		else if (SemanticChecker.classSymbolTable.containsKey(funNameId)){
			//System.out.println("1111");
			LinkedList<String>fieldList = SemanticChecker.classSymbolTable.get(funNameId);
			HashMap<String,Val> newState = new HashMap<String,Val>();
			
			for (String field: fieldList){ // form a new function-call state "newState"
				if(eList == null){
					newState.put(field, null);
				}//if
				else{
					if(eList.firstExp().Eval(state) == null)
						newState.put(field, new NullObj());
					newState.put(field, eList.firstExp().Eval(state)); // "field" is bound to eVal
					eList = eList.tailExpList();
				}//else
			}//for
			return new ClassObj(funNameId,newState);
		}//else if
		else if(SemanticChecker.classSymbolTable.containsKey(funNameId))
		System.out.println( "Error: undefined function: " + funNameId );
		return null;
	}
}