package org.dom4j.rule.pattern;

import org.dom4j.Node;
import org.dom4j.rule.Pattern;

public class NodeTypePattern
  implements Pattern
{
  public static final NodeTypePattern ANY_ATTRIBUTE = new NodeTypePattern((short)2);
  public static final NodeTypePattern ANY_COMMENT = new NodeTypePattern((short)8);
  public static final NodeTypePattern ANY_DOCUMENT = new NodeTypePattern((short)9);
  public static final NodeTypePattern ANY_ELEMENT = new NodeTypePattern((short)1);
  public static final NodeTypePattern ANY_PROCESSING_INSTRUCTION = new NodeTypePattern((short)7);
  public static final NodeTypePattern ANY_TEXT = new NodeTypePattern((short)3);
  private short nodeType;
  
  public NodeTypePattern(short nodeType)
  {
    this.nodeType = nodeType;
  }
  
  public boolean matches(Node node)
  {
    return node.getNodeType() == this.nodeType;
  }
  
  public double getPriority()
  {
    return 0.5D;
  }
  
  public Pattern[] getUnionPatterns()
  {
    return null;
  }
  
  public short getMatchType()
  {
    return this.nodeType;
  }
  
  public String getMatchesNodeName()
  {
    return null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.rule.pattern.NodeTypePattern
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */