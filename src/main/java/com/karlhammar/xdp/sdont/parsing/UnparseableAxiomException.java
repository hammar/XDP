package com.karlhammar.xdp.sdont.parsing;

@SuppressWarnings("serial")
public class UnparseableAxiomException extends Exception
{
	public UnparseableAxiomException()
	{
		super();
	}
	
	public UnparseableAxiomException(String msg)
	{
		super(msg);
	}
}
