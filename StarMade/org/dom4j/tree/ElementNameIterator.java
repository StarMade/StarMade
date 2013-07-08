/*  1:   */package org.dom4j.tree;
/*  2:   */
/*  3:   */import java.util.Iterator;
/*  4:   */import org.dom4j.Element;
/*  5:   */
/* 20:   *//**
/* 21:   */ * @deprecated
/* 22:   */ */
/* 23:   */public class ElementNameIterator
/* 24:   */  extends FilterIterator
/* 25:   */{
/* 26:   */  private String name;
/* 27:   */  
/* 28:   */  public ElementNameIterator(Iterator proxy, String name)
/* 29:   */  {
/* 30:30 */    super(proxy);
/* 31:31 */    this.name = name;
/* 32:   */  }
/* 33:   */  
/* 42:   */  protected boolean matches(Object object)
/* 43:   */  {
/* 44:44 */    if ((object instanceof Element)) {
/* 45:45 */      Element element = (Element)object;
/* 46:   */      
/* 47:47 */      return this.name.equals(element.getName());
/* 48:   */    }
/* 49:   */    
/* 50:50 */    return false;
/* 51:   */  }
/* 52:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.ElementNameIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */