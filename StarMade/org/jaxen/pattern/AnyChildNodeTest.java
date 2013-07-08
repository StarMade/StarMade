/*  1:   */package org.jaxen.pattern;
/*  2:   */
/*  3:   */import org.jaxen.Context;
/*  4:   */import org.jaxen.Navigator;
/*  5:   */
/* 56:   */public class AnyChildNodeTest
/* 57:   */  extends NodeTest
/* 58:   */{
/* 59:59 */  private static AnyChildNodeTest instance = new AnyChildNodeTest();
/* 60:   */  
/* 61:   */  public static AnyChildNodeTest getInstance()
/* 62:   */  {
/* 63:63 */    return instance;
/* 64:   */  }
/* 65:   */  
/* 72:   */  public boolean matches(Object node, Context context)
/* 73:   */  {
/* 74:74 */    short type = context.getNavigator().getNodeType(node);
/* 75:75 */    return (type == 1) || (type == 3) || (type == 8) || (type == 7);
/* 76:   */  }
/* 77:   */  
/* 79:   */  public double getPriority()
/* 80:   */  {
/* 81:81 */    return -0.5D;
/* 82:   */  }
/* 83:   */  
/* 84:   */  public short getMatchType()
/* 85:   */  {
/* 86:86 */    return 0;
/* 87:   */  }
/* 88:   */  
/* 89:   */  public String getText()
/* 90:   */  {
/* 91:91 */    return "*";
/* 92:   */  }
/* 93:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.pattern.AnyChildNodeTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */