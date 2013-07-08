/*  1:   */package org.dom4j.tree;
/*  2:   */
/*  3:   */import java.util.Iterator;
/*  4:   */import org.dom4j.Element;
/*  5:   */import org.dom4j.QName;
/*  6:   */
/* 21:   *//**
/* 22:   */ * @deprecated
/* 23:   */ */
/* 24:   */public class ElementQNameIterator
/* 25:   */  extends FilterIterator
/* 26:   */{
/* 27:   */  private QName qName;
/* 28:   */  
/* 29:   */  public ElementQNameIterator(Iterator proxy, QName qName)
/* 30:   */  {
/* 31:31 */    super(proxy);
/* 32:32 */    this.qName = qName;
/* 33:   */  }
/* 34:   */  
/* 43:   */  protected boolean matches(Object object)
/* 44:   */  {
/* 45:45 */    if ((object instanceof Element)) {
/* 46:46 */      Element element = (Element)object;
/* 47:   */      
/* 48:48 */      return this.qName.equals(element.getQName());
/* 49:   */    }
/* 50:   */    
/* 51:51 */    return false;
/* 52:   */  }
/* 53:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.ElementQNameIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */