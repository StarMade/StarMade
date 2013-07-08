/*  1:   */package org.jaxen.pattern;
/*  2:   */
/*  3:   */import org.jaxen.Context;
/*  4:   */import org.jaxen.Navigator;
/*  5:   */
/* 56:   */public class TextNodeTest
/* 57:   */  extends NodeTest
/* 58:   */{
/* 59:59 */  public static final TextNodeTest SINGLETON = new TextNodeTest();
/* 60:   */  
/* 67:   */  public boolean matches(Object node, Context context)
/* 68:   */  {
/* 69:69 */    return context.getNavigator().isText(node);
/* 70:   */  }
/* 71:   */  
/* 72:   */  public double getPriority()
/* 73:   */  {
/* 74:74 */    return -0.5D;
/* 75:   */  }
/* 76:   */  
/* 77:   */  public short getMatchType()
/* 78:   */  {
/* 79:79 */    return 3;
/* 80:   */  }
/* 81:   */  
/* 82:   */  public String getText()
/* 83:   */  {
/* 84:84 */    return "text()";
/* 85:   */  }
/* 86:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.pattern.TextNodeTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */