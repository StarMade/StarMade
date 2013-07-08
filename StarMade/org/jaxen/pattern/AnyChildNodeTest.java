package org.jaxen.pattern;

import org.jaxen.Context;
import org.jaxen.Navigator;

public class AnyChildNodeTest
  extends NodeTest
{
  private static AnyChildNodeTest instance = new AnyChildNodeTest();
  
  public static AnyChildNodeTest getInstance()
  {
    return instance;
  }
  
  public boolean matches(Object node, Context context)
  {
    short type = context.getNavigator().getNodeType(node);
    return (type == 1) || (type == 3) || (type == 8) || (type == 7);
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
 * Qualified Name:     org.jaxen.pattern.AnyChildNodeTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */