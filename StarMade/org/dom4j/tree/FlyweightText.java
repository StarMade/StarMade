/*    */ package org.dom4j.tree;
/*    */ 
/*    */ import org.dom4j.Element;
/*    */ import org.dom4j.Node;
/*    */ import org.dom4j.Text;
/*    */ 
/*    */ public class FlyweightText extends AbstractText
/*    */   implements Text
/*    */ {
/*    */   protected String text;
/*    */ 
/*    */   public FlyweightText(String text)
/*    */   {
/* 39 */     this.text = text;
/*    */   }
/*    */ 
/*    */   public String getText() {
/* 43 */     return this.text;
/*    */   }
/*    */ 
/*    */   protected Node createXPathResult(Element parent) {
/* 47 */     return new DefaultText(parent, getText());
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.FlyweightText
 * JD-Core Version:    0.6.2
 */