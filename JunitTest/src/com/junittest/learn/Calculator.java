package com.junittest.learn;

public class Calculator implements Adder, Subber {

	@Override
	public long subtract(long... apprands) {
		long ret = apprands[0];
		for (int i = 1; i< apprands.length; i++)
		{
			ret-= apprands[i];
		}
		return ret;
		
	}

	@Override
	public long add(long... apprands) {
		long ret = apprands[0];
		for (int i = 1; i< apprands.length; i++)
		{
			ret+= apprands[i];
		}
		return ret;
	}

}
