/*    */ package org.dom4j.util;
/*    */ 
/*    */ import org.dom4j.Attribute;
/*    */ import org.dom4j.DocumentFactory;
/*    */ import org.dom4j.Element;
/*    */ import org.dom4j.QName;
/*    */ 
/*    */ public class UserDataDocumentFactory extends DocumentFactory
/*    */ {
/* 30 */   protected static transient UserDataDocumentFactory singleton = new UserDataDocumentFactory();
/*    */ 
/*    */   public static DocumentFactory getInstance()
/*    */   {
/* 41 */     return singleton;
/*    */   }
/*    */ 
/*    */   public Element createElement(QName qname)
/*    */   {
/* 47 */     return new UserDataElement(qname);
/*    */   }
/*    */ 
/*    */   public Attribute createAttribute(Element owner, QName qname, String value) {
/* 51 */     return new UserDataAttribute(qname, value);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.util.UserDataDocumentFactory
 * JD-Core Version:    0.6.2
 */