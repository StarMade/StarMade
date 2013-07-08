/*  1:   */package org.dom4j;
/*  2:   */
/* 15:   */public class IllegalAddException
/* 16:   */  extends IllegalArgumentException
/* 17:   */{
/* 18:   */  public IllegalAddException(String reason)
/* 19:   */  {
/* 20:20 */    super(reason);
/* 21:   */  }
/* 22:   */  
/* 23:   */  public IllegalAddException(Element parent, Node node, String reason) {
/* 24:24 */    super("The node \"" + node.toString() + "\" could not be added to the element \"" + parent.getQualifiedName() + "\" because: " + reason);
/* 25:   */  }
/* 26:   */  
/* 28:   */  public IllegalAddException(Branch parent, Node node, String reason)
/* 29:   */  {
/* 30:30 */    super("The node \"" + node.toString() + "\" could not be added to the branch \"" + parent.getName() + "\" because: " + reason);
/* 31:   */  }
/* 32:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.IllegalAddException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */