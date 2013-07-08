package org.jaxen.pattern;

import org.jaxen.Context;

public class AnyNodeTest
  extends NodeTest
{
  private static AnyNodeTest instance = new AnyNodeTest();
  
  public static AnyNodeTest getInstance()
  {
    return instance;
  }
  
  public boolean matches(Object node, Context context)
  {
    return true;
  }
  
  public double getPriority()
  {
    return -0.5D;
  }
  
  public short getMatchType()
  {
    return 0;
  }
  
  public String getText()
  {
    return "*";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.pattern.AnyNodeTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */