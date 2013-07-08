/*  1:   */package org.jaxen.javabean;
/*  2:   */
/*  4:   */public class Element
/*  5:   */{
/*  6:   */  private Element parent;
/*  7:   */  
/*  8:   */  private String name;
/*  9:   */  private Object object;
/* 10:   */  
/* 11:   */  public Element(Element parent, String name, Object object)
/* 12:   */  {
/* 13:13 */    this.parent = parent;
/* 14:14 */    this.name = name;
/* 15:15 */    this.object = object;
/* 16:   */  }
/* 17:   */  
/* 18:   */  public Element getParent()
/* 19:   */  {
/* 20:20 */    return this.parent;
/* 21:   */  }
/* 22:   */  
/* 23:   */  public String getName()
/* 24:   */  {
/* 25:25 */    return this.name;
/* 26:   */  }
/* 27:   */  
/* 28:   */  public Object getObject()
/* 29:   */  {
/* 30:30 */    return this.object;
/* 31:   */  }
/* 32:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.javabean.Element
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */