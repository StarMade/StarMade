package org.hsqldb.util;

abstract interface Traceable
{
  public static final boolean TRACE = Boolean.getBoolean("hsqldb.util.trace");
  
  public abstract void trace(String paramString);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.util.Traceable
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */