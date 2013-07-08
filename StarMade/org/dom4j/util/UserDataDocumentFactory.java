/*  1:   */package org.dom4j.util;
/*  2:   */
/*  3:   */import org.dom4j.Attribute;
/*  4:   */import org.dom4j.DocumentFactory;
/*  5:   */import org.dom4j.Element;
/*  6:   */import org.dom4j.QName;
/*  7:   */
/* 27:   */public class UserDataDocumentFactory
/* 28:   */  extends DocumentFactory
/* 29:   */{
/* 30:30 */  protected static transient UserDataDocumentFactory singleton = new UserDataDocumentFactory();
/* 31:   */  
/* 39:   */  public static DocumentFactory getInstance()
/* 40:   */  {
/* 41:41 */    return singleton;
/* 42:   */  }
/* 43:   */  
/* 45:   */  public Element createElement(QName qname)
/* 46:   */  {
/* 47:47 */    return new UserDataElement(qname);
/* 48:   */  }
/* 49:   */  
/* 50:   */  public Attribute createAttribute(Element owner, QName qname, String value) {
/* 51:51 */    return new UserDataAttribute(qname, value);
/* 52:   */  }
/* 53:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.util.UserDataDocumentFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */