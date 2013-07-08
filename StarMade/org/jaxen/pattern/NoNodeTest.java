package org.jaxen.pattern;

import org.jaxen.Context;

public class NoNodeTest
  extends NodeTest
{
  private static NoNodeTest instance = new NoNodeTest();
  
  public static NoNodeTest getInstance()
  {
    return instance;
  }
  
  public boolean matches(Object node, Context context)
  {
    return false;
  }
  
  public double getPriority()
  {
    return -0.5D;
  }
  
  public short getMatchType()
  {
    return 14;
  }
  
  public String getText()
  {
    return "";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.pattern.NoNodeTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */