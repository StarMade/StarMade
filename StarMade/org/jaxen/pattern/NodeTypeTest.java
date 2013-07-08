package org.jaxen.pattern;

import org.jaxen.Context;
import org.jaxen.Navigator;

public class NodeTypeTest
  extends NodeTest
{
  public static final NodeTypeTest DOCUMENT_TEST = new NodeTypeTest((short)9);
  public static final NodeTypeTest ELEMENT_TEST = new NodeTypeTest((short)1);
  public static final NodeTypeTest ATTRIBUTE_TEST = new NodeTypeTest((short)2);
  public static final NodeTypeTest COMMENT_TEST = new NodeTypeTest((short)8);
  public static final NodeTypeTest TEXT_TEST = new NodeTypeTest((short)3);
  public static final NodeTypeTest PROCESSING_INSTRUCTION_TEST = new NodeTypeTest((short)7);
  public static final NodeTypeTest NAMESPACE_TEST = new NodeTypeTest((short)13);
  private short nodeType;
  
  public NodeTypeTest(short nodeType)
  {
    this.nodeType = nodeType;
  }
  
  public boolean matches(Object node, Context context)
  {
    return this.nodeType == context.getNavigator().getNodeType(node);
  }
  
  public double getPriority()
  {
    return -0.5D;
  }
  
  public short getMatchType()
  {
    return this.nodeType;
  }
  
  public String getText()
  {
    switch (this.nodeType)
    {
    case 1: 
      return "child()";
    case 2: 
      return "@*";
    case 13: 
      return "namespace()";
    case 9: 
      return "/";
    case 8: 
      return "comment()";
    case 3: 
      return "text()";
    case 7: 
      return "processing-instruction()";
    }
    return "";
  }
  
  public String toString()
  {
    return super.toString() + "[ type: " + this.nodeType + " ]";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.pattern.NodeTypeTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */