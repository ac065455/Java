final class NullObj extends Val
{
	public String toString()
	{
		return "null";
	}

	Val cloneVal() // You modify the body code
	{
		return new NullObj(); //modified the return
	}

	float floatVal()
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

	//--------?? not sure if this is correct
	boolean equalVal(Val that) // You modify the body code
	{
		if ( that.getClass() == NullObj.class )
			return this.toString() == ((NullObj) that).toString();

		return false;
	}
}