/*    */ package org.dom4j.util;
/*    */ 
/*    */ import org.dom4j.Namespace;
/*    */ import org.dom4j.QName;
/*    */ import org.dom4j.tree.BaseElement;
/*    */ 
/*    */ public class NonLazyElement extends BaseElement
/*    */ {
/*    */   public NonLazyElement(String name)
/*    */   {
/* 25 */     super(name);
/* 26 */     this.attributes = createAttributeList();
/* 27 */     this.content = createContentList();
/*    */   }
/*    */ 
/*    */   public NonLazyElement(QName qname) {
/* 31 */     super(qname);
/* 32 */     this.attributes = createAttributeList();
/* 33 */     this.content = createContentList();
/*    */   }
/*    */ 
/*    */   public NonLazyElement(String name, Namespace namespace) {
/* 37 */     super(name, namespace);
/* 38 */     this.attributes = createAttributeList();
/* 39 */     this.content = createContentList();
/*    */   }
/*    */ 
/*    */   public NonLazyElement(QName qname, int attributeCount) {
/* 43 */     super(qname);
/* 44 */     this.attributes = createAttributeList(attributeCount);
/* 45 */     this.content = createContentList();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.util.NonLazyElement
 * JD-Core Version:    0.6.2
 */