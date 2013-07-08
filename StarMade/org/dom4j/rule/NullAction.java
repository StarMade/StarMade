package org.dom4j.rule;

import org.dom4j.Node;

public class NullAction
  implements Action
{
  public static final NullAction SINGLETON = new NullAction();
  
  public void run(Node node)
    throws Exception
  {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.rule.NullAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */