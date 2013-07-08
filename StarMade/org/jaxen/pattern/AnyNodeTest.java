/*  1:   */package org.jaxen.pattern;
/*  2:   */
/*  3:   */import org.jaxen.Context;
/*  4:   */
/* 56:   */public class AnyNodeTest
/* 57:   */  extends NodeTest
/* 58:   */{
/* 59:59 */  private static AnyNodeTest instance = new AnyNodeTest();
/* 60:   */  
/* 61:   */  public static AnyNodeTest getInstance()
/* 62:   */  {
/* 63:63 */    return instance;
/* 64:   */  }
/* 65:   */  
/* 70:   */  public boolean matches(Object node, Context context)
/* 71:   */  {
/* 72:72 */    return true;
/* 73:   */  }
/* 74:   */  
/* 75:   */  public double getPriority()
/* 76:   */  {
/* 77:77 */    return -0.5D;
/* 78:   */  }
/* 79:   */  
/* 80:   */  public short getMatchType()
/* 81:   */  {
/* 82:82 */    return 0;
/* 83:   */  }
/* 84:   */  
/* 85:   */  public String getText()
/* 86:   */  {
/* 87:87 */    return "*";
/* 88:   */  }
/* 89:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.pattern.AnyNodeTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */