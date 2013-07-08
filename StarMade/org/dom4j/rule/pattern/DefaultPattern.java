/*  1:   */package org.dom4j.rule.pattern;
/*  2:   */
/*  3:   */import org.dom4j.Node;
/*  4:   */import org.dom4j.NodeFilter;
/*  5:   */import org.dom4j.rule.Pattern;
/*  6:   */
/* 24:   */public class DefaultPattern
/* 25:   */  implements Pattern
/* 26:   */{
/* 27:   */  private NodeFilter filter;
/* 28:   */  
/* 29:   */  public DefaultPattern(NodeFilter filter)
/* 30:   */  {
/* 31:31 */    this.filter = filter;
/* 32:   */  }
/* 33:   */  
/* 34:   */  public boolean matches(Node node) {
/* 35:35 */    return this.filter.matches(node);
/* 36:   */  }
/* 37:   */  
/* 38:   */  public double getPriority() {
/* 39:39 */    return 0.5D;
/* 40:   */  }
/* 41:   */  
/* 42:   */  public Pattern[] getUnionPatterns() {
/* 43:43 */    return null;
/* 44:   */  }
/* 45:   */  
/* 46:   */  public short getMatchType() {
/* 47:47 */    return 0;
/* 48:   */  }
/* 49:   */  
/* 50:   */  public String getMatchesNodeName() {
/* 51:51 */    return null;
/* 52:   */  }
/* 53:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.rule.pattern.DefaultPattern
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */