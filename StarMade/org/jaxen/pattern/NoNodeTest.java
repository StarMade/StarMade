/*  1:   */package org.jaxen.pattern;
/*  2:   */
/*  3:   */import org.jaxen.Context;
/*  4:   */
/* 56:   */public class NoNodeTest
/* 57:   */  extends NodeTest
/* 58:   */{
/* 59:59 */  private static NoNodeTest instance = new NoNodeTest();
/* 60:   */  
/* 61:   */  public static NoNodeTest getInstance()
/* 62:   */  {
/* 63:63 */    return instance;
/* 64:   */  }
/* 65:   */  
/* 72:   */  public boolean matches(Object node, Context context)
/* 73:   */  {
/* 74:74 */    return false;
/* 75:   */  }
/* 76:   */  
/* 77:   */  public double getPriority()
/* 78:   */  {
/* 79:79 */    return -0.5D;
/* 80:   */  }
/* 81:   */  
/* 82:   */  public short getMatchType()
/* 83:   */  {
/* 84:84 */    return 14;
/* 85:   */  }
/* 86:   */  
/* 87:   */  public String getText()
/* 88:   */  {
/* 89:89 */    return "";
/* 90:   */  }
/* 91:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.pattern.NoNodeTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */