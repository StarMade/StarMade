/*    */ package org.dom4j.util;
/*    */ 
/*    */ import org.dom4j.DocumentFactory;
/*    */ import org.dom4j.Element;
/*    */ import org.dom4j.QName;
/*    */ 
/*    */ public class IndexedDocumentFactory extends DocumentFactory
/*    */ {
/* 27 */   protected static transient IndexedDocumentFactory singleton = new IndexedDocumentFactory();
/*    */ 
/*    */   public static DocumentFactory getInstance()
/*    */   {
/* 38 */     return singleton;
/*    */   }
/*    */ 
/*    */   public Element createElement(QName qname)
/*    */   {
/* 44 */     return new IndexedElement(qname);
/*    */   }
/*    */ 
/*    */   public Element createElement(QName qname, int attributeCount) {
/* 48 */     return new IndexedElement(qname, attributeCount);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.util.IndexedDocumentFactory
 * JD-Core Version:    0.6.2
 */