package org.dom4j.util;

public abstract interface SingletonStrategy
{
  public abstract Object instance();
  
  public abstract void reset();
  
  public abstract void setSingletonClassName(String paramString);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.util.SingletonStrategy
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */