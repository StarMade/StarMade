package org.dom4j.rule;

import org.dom4j.Node;
import org.dom4j.NodeFilter;

public abstract interface Pattern
  extends NodeFilter
{
  public static final short ANY_NODE = 0;
  public static final short NONE = 9999;
  public static final short NUMBER_OF_TYPES = 14;
  public static final double DEFAULT_PRIORITY = 0.5D;
  
  public abstract boolean matches(Node paramNode);
  
  public abstract double getPriority();
  
  public abstract Pattern[] getUnionPatterns();
  
  public abstract short getMatchType();
  
  public abstract String getMatchesNodeName();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.rule.Pattern
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */