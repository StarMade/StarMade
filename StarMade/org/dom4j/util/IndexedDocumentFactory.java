/*  1:   */package org.dom4j.util;
/*  2:   */
/*  3:   */import org.dom4j.DocumentFactory;
/*  4:   */import org.dom4j.Element;
/*  5:   */import org.dom4j.QName;
/*  6:   */
/* 24:   */public class IndexedDocumentFactory
/* 25:   */  extends DocumentFactory
/* 26:   */{
/* 27:27 */  protected static transient IndexedDocumentFactory singleton = new IndexedDocumentFactory();
/* 28:   */  
/* 36:   */  public static DocumentFactory getInstance()
/* 37:   */  {
/* 38:38 */    return singleton;
/* 39:   */  }
/* 40:   */  
/* 42:   */  public Element createElement(QName qname)
/* 43:   */  {
/* 44:44 */    return new IndexedElement(qname);
/* 45:   */  }
/* 46:   */  
/* 47:   */  public Element createElement(QName qname, int attributeCount) {
/* 48:48 */    return new IndexedElement(qname, attributeCount);
/* 49:   */  }
/* 50:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.util.IndexedDocumentFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */