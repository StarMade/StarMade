/*  1:   */package org.dom4j.tree;
/*  2:   */
/*  3:   */import java.util.Iterator;
/*  4:   */import org.dom4j.Element;
/*  5:   */
/* 19:   *//**
/* 20:   */ * @deprecated
/* 21:   */ */
/* 22:   */public class ElementIterator
/* 23:   */  extends FilterIterator
/* 24:   */{
/* 25:   */  public ElementIterator(Iterator proxy)
/* 26:   */  {
/* 27:27 */    super(proxy);
/* 28:   */  }
/* 29:   */  
/* 38:   */  protected boolean matches(Object element)
/* 39:   */  {
/* 40:40 */    return element instanceof Element;
/* 41:   */  }
/* 42:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.ElementIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */