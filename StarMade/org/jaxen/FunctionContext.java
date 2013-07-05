package org.jaxen;

public abstract interface FunctionContext
{
  public abstract Function getFunction(String paramString1, String paramString2, String paramString3)
    throws UnresolvableException;
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.FunctionContext
 * JD-Core Version:    0.6.2
 */