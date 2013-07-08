package org.jaxen.pattern;

import org.jaxen.Context;
import org.jaxen.Navigator;

public class TextNodeTest
  extends NodeTest
{
  public static final TextNodeTest SINGLETON = new TextNodeTest();
  
  public boolean matches(Object node, Context context)
  {
    return context.getNavigator().isText(node);
  }
  
  public double getPriority()
  {
    return -0.5D;
  }
  
  public short getMatchType()
  {
    return 3;
  }
  
  public String getText()
  {
    return "text()";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.pattern.TextNodeTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */