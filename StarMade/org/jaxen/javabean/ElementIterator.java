/*  1:   */package org.jaxen.javabean;
/*  2:   */
/*  3:   */import java.util.Iterator;
/*  4:   */
/*  7:   */public class ElementIterator
/*  8:   */  implements Iterator
/*  9:   */{
/* 10:   */  private Element parent;
/* 11:   */  private String name;
/* 12:   */  private Iterator iterator;
/* 13:   */  
/* 14:   */  public ElementIterator(Element parent, String name, Iterator iterator)
/* 15:   */  {
/* 16:16 */    this.parent = parent;
/* 17:17 */    this.name = name;
/* 18:18 */    this.iterator = iterator;
/* 19:   */  }
/* 20:   */  
/* 21:   */  public boolean hasNext()
/* 22:   */  {
/* 23:23 */    return this.iterator.hasNext();
/* 24:   */  }
/* 25:   */  
/* 26:   */  public Object next()
/* 27:   */  {
/* 28:28 */    return new Element(this.parent, this.name, this.iterator.next());
/* 29:   */  }
/* 30:   */  
/* 33:   */  public void remove()
/* 34:   */  {
/* 35:35 */    throw new UnsupportedOperationException();
/* 36:   */  }
/* 37:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.javabean.ElementIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */