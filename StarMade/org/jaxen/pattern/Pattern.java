package org.jaxen.pattern;

import org.jaxen.Context;
import org.jaxen.JaxenException;

public abstract class Pattern
{
  public static final short ELEMENT_NODE = 1;
  public static final short ATTRIBUTE_NODE = 2;
  public static final short TEXT_NODE = 3;
  public static final short CDATA_SECTION_NODE = 4;
  public static final short ENTITY_REFERENCE_NODE = 5;
  public static final short PROCESSING_INSTRUCTION_NODE = 7;
  public static final short COMMENT_NODE = 8;
  public static final short DOCUMENT_NODE = 9;
  public static final short DOCUMENT_TYPE_NODE = 10;
  public static final short NAMESPACE_NODE = 13;
  public static final short UNKNOWN_NODE = 14;
  public static final short MAX_NODE_TYPE = 14;
  public static final short ANY_NODE = 0;
  public static final short NO_NODE = 14;
  
  public abstract boolean matches(Object paramObject, Context paramContext)
    throws JaxenException;
  
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
    return 0;
  }
  
  public String getMatchesNodeName()
  {
    return null;
  }
  
  public Pattern simplify()
  {
    return this;
  }
  
  public abstract String getText();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.pattern.Pattern
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */