/*  1:   */package org.dom4j.rule;
/*  2:   */
/*  3:   */import org.dom4j.Node;
/*  4:   */
/* 19:   */public class NullAction
/* 20:   */  implements Action
/* 21:   */{
/* 22:22 */  public static final NullAction SINGLETON = new NullAction();
/* 23:   */  
/* 24:   */  public void run(Node node)
/* 25:   */    throws Exception
/* 26:   */  {}
/* 27:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.rule.NullAction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */