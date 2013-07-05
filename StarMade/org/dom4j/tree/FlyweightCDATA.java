/*    */ package org.dom4j.tree;
/*    */ 
/*    */ import org.dom4j.CDATA;
/*    */ import org.dom4j.Element;
/*    */ import org.dom4j.Node;
/*    */ 
/*    */ public class FlyweightCDATA extends AbstractCDATA
/*    */   implements CDATA
/*    */ {
/*    */   protected String text;
/*    */ 
/*    */   public FlyweightCDATA(String text)
/*    */   {
/* 39 */     this.text = text;
/*    */   }
/*    */ 
/*    */   public String getText() {
/* 43 */     return this.text;
/*    */   }
/*    */ 
/*    */   protected Node createXPathResult(Element parent) {
/* 47 */     return new DefaultCDATA(parent, getText());
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.FlyweightCDATA
 * JD-Core Version:    0.6.2
 */