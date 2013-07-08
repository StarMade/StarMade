/*   1:    */package org.jaxen.pattern;
/*   2:    */
/*   3:    */import org.jaxen.Context;
/*   4:    */import org.jaxen.JaxenException;
/*   5:    */
/* 100:    */public abstract class Pattern
/* 101:    */{
/* 102:    */  public static final short ELEMENT_NODE = 1;
/* 103:    */  public static final short ATTRIBUTE_NODE = 2;
/* 104:    */  public static final short TEXT_NODE = 3;
/* 105:    */  public static final short CDATA_SECTION_NODE = 4;
/* 106:    */  public static final short ENTITY_REFERENCE_NODE = 5;
/* 107:    */  public static final short PROCESSING_INSTRUCTION_NODE = 7;
/* 108:    */  public static final short COMMENT_NODE = 8;
/* 109:    */  public static final short DOCUMENT_NODE = 9;
/* 110:    */  public static final short DOCUMENT_TYPE_NODE = 10;
/* 111:    */  public static final short NAMESPACE_NODE = 13;
/* 112:    */  public static final short UNKNOWN_NODE = 14;
/* 113:    */  public static final short MAX_NODE_TYPE = 14;
/* 114:    */  public static final short ANY_NODE = 0;
/* 115:    */  public static final short NO_NODE = 14;
/* 116:    */  
/* 117:    */  public abstract boolean matches(Object paramObject, Context paramContext)
/* 118:    */    throws JaxenException;
/* 119:    */  
/* 120:    */  public double getPriority()
/* 121:    */  {
/* 122:122 */    return 0.5D;
/* 123:    */  }
/* 124:    */  
/* 133:    */  public Pattern[] getUnionPatterns()
/* 134:    */  {
/* 135:135 */    return null;
/* 136:    */  }
/* 137:    */  
/* 144:    */  public short getMatchType()
/* 145:    */  {
/* 146:146 */    return 0;
/* 147:    */  }
/* 148:    */  
/* 159:    */  public String getMatchesNodeName()
/* 160:    */  {
/* 161:161 */    return null;
/* 162:    */  }
/* 163:    */  
/* 165:    */  public Pattern simplify()
/* 166:    */  {
/* 167:167 */    return this;
/* 168:    */  }
/* 169:    */  
/* 170:    */  public abstract String getText();
/* 171:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.pattern.Pattern
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */