/*  1:   */package org.dom4j.rule.pattern;
/*  2:   */
/*  3:   */import org.dom4j.Node;
/*  4:   */import org.dom4j.rule.Pattern;
/*  5:   */
/* 21:   */public class NodeTypePattern
/* 22:   */  implements Pattern
/* 23:   */{
/* 24:24 */  public static final NodeTypePattern ANY_ATTRIBUTE = new NodeTypePattern((short)2);
/* 25:   */  
/* 28:28 */  public static final NodeTypePattern ANY_COMMENT = new NodeTypePattern((short)8);
/* 29:   */  
/* 32:32 */  public static final NodeTypePattern ANY_DOCUMENT = new NodeTypePattern((short)9);
/* 33:   */  
/* 36:36 */  public static final NodeTypePattern ANY_ELEMENT = new NodeTypePattern((short)1);
/* 37:   */  
/* 40:40 */  public static final NodeTypePattern ANY_PROCESSING_INSTRUCTION = new NodeTypePattern((short)7);
/* 41:   */  
/* 44:44 */  public static final NodeTypePattern ANY_TEXT = new NodeTypePattern((short)3);
/* 45:   */  
/* 46:   */  private short nodeType;
/* 47:   */  
/* 48:   */  public NodeTypePattern(short nodeType)
/* 49:   */  {
/* 50:50 */    this.nodeType = nodeType;
/* 51:   */  }
/* 52:   */  
/* 53:   */  public boolean matches(Node node) {
/* 54:54 */    return node.getNodeType() == this.nodeType;
/* 55:   */  }
/* 56:   */  
/* 57:   */  public double getPriority() {
/* 58:58 */    return 0.5D;
/* 59:   */  }
/* 60:   */  
/* 61:   */  public Pattern[] getUnionPatterns() {
/* 62:62 */    return null;
/* 63:   */  }
/* 64:   */  
/* 65:   */  public short getMatchType() {
/* 66:66 */    return this.nodeType;
/* 67:   */  }
/* 68:   */  
/* 69:   */  public String getMatchesNodeName() {
/* 70:70 */    return null;
/* 71:   */  }
/* 72:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.rule.pattern.NodeTypePattern
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */