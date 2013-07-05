package org.hsqldb.auth;

public abstract interface AuthFunctionBean
{
  public abstract String[] authenticate(String paramString1, String paramString2)
    throws Exception;
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.auth.AuthFunctionBean
 * JD-Core Version:    0.6.2
 */