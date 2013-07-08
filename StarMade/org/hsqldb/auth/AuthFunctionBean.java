package org.hsqldb.auth;

public abstract interface AuthFunctionBean
{
  public abstract String[] authenticate(String paramString1, String paramString2)
    throws Exception;
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.auth.AuthFunctionBean
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */