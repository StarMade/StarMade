/*    */ package org.dom4j.util;
/*    */ 
/*    */ import org.dom4j.DocumentFactory;
/*    */ import org.dom4j.Element;
/*    */ import org.dom4j.QName;
/*    */ 
/*    */ public class NonLazyDocumentFactory extends DocumentFactory
/*    */ {
/* 27 */   protected static transient NonLazyDocumentFactory singleton = new NonLazyDocumentFactory();
/*    */ 
/*    */   public static DocumentFactory getInstance()
/*    */   {
/* 38 */     return singleton;
/*    */   }
/*    */ 
/*    */   public Element createElement(QName qname)
/*    */   {
/* 44 */     return new NonLazyElement(qname);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.util.NonLazyDocumentFactory
 * JD-Core Version:    0.6.2
 */