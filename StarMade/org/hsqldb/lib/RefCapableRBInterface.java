package org.hsqldb.lib;

public abstract interface RefCapableRBInterface
{
  public abstract String getString();
  
  public abstract String getString(String... paramVarArgs);
  
  public abstract String getExpandedString();
  
  public abstract String getExpandedString(String... paramVarArgs);
  
  public abstract String getString(int paramInt);
  
  public abstract String getString(int paramInt1, int paramInt2);
  
  public abstract String getString(int paramInt1, int paramInt2, int paramInt3);
  
  public abstract String getString(int paramInt, String paramString);
  
  public abstract String getString(String paramString, int paramInt);
  
  public abstract String getString(int paramInt1, int paramInt2, String paramString);
  
  public abstract String getString(int paramInt1, String paramString, int paramInt2);
  
  public abstract String getString(String paramString, int paramInt1, int paramInt2);
  
  public abstract String getString(int paramInt, String paramString1, String paramString2);
  
  public abstract String getString(String paramString1, String paramString2, int paramInt);
  
  public abstract String getString(String paramString1, int paramInt, String paramString2);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.lib.RefCapableRBInterface
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */