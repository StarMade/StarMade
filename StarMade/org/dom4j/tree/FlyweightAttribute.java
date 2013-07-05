/*    */ package org.dom4j.tree;
/*    */ 
/*    */ import org.dom4j.DocumentFactory;
/*    */ import org.dom4j.Namespace;
/*    */ import org.dom4j.QName;
/*    */ 
/*    */ public class FlyweightAttribute extends AbstractAttribute
/*    */ {
/*    */   private QName qname;
/*    */   protected String value;
/*    */ 
/*    */   public FlyweightAttribute(QName qname)
/*    */   {
/* 35 */     this.qname = qname;
/*    */   }
/*    */ 
/*    */   public FlyweightAttribute(QName qname, String value) {
/* 39 */     this.qname = qname;
/* 40 */     this.value = value;
/*    */   }
/*    */ 
/*    */   public FlyweightAttribute(String name, String value)
/*    */   {
/* 53 */     this.qname = getDocumentFactory().createQName(name);
/* 54 */     this.value = value;
/*    */   }
/*    */ 
/*    */   public FlyweightAttribute(String name, String value, Namespace namespace)
/*    */   {
/* 69 */     this.qname = getDocumentFactory().createQName(name, namespace);
/* 70 */     this.value = value;
/*    */   }
/*    */ 
/*    */   public String getValue() {
/* 74 */     return this.value;
/*    */   }
/*    */ 
/*    */   public QName getQName() {
/* 78 */     return this.qname;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.FlyweightAttribute
 * JD-Core Version:    0.6.2
 */