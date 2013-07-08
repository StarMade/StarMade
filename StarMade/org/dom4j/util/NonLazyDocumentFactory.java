/*  1:   */package org.dom4j.util;
/*  2:   */
/*  3:   */import org.dom4j.DocumentFactory;
/*  4:   */import org.dom4j.Element;
/*  5:   */import org.dom4j.QName;
/*  6:   */
/* 24:   */public class NonLazyDocumentFactory
/* 25:   */  extends DocumentFactory
/* 26:   */{
/* 27:27 */  protected static transient NonLazyDocumentFactory singleton = new NonLazyDocumentFactory();
/* 28:   */  
/* 36:   */  public static DocumentFactory getInstance()
/* 37:   */  {
/* 38:38 */    return singleton;
/* 39:   */  }
/* 40:   */  
/* 42:   */  public Element createElement(QName qname)
/* 43:   */  {
/* 44:44 */    return new NonLazyElement(qname);
/* 45:   */  }
/* 46:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.util.NonLazyDocumentFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */