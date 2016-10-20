import java.util.*;

final class ClassObj extends Val
{
	String className; // class name of this object
	HashMap<String, Val> fields; // field name mapped to its value

	ClassObj(String cn, HashMap<String, Val> fs)
	{
		className = cn;
		fields = fs;
	}

	public String toString()
	{
		return className+" object: "+fields.toString();
	}

	Val cloneVal() // You modify the body code
	{
		return new ClassObj(className,fields); //modified the return
	}

	float floatVal()

	// This is not used by the interpreter. For other purposes, this might return some code value of this object.

	{
		return 0.0f;
	}

	boolean isNumber()
	{
		return false;
	}

	boolean isZero()
	{
		return false;
	}

	//modified equalVal
	boolean equalVal(Val that) // You modify the body code
	{
		if ( that.getClass() == ClassObj.class )
			return this.className == ((ClassObj) that).className;

		return false;
	}
}