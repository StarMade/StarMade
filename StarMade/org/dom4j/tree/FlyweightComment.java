/*    */ package org.dom4j.tree;
/*    */ 
/*    */ import org.dom4j.Comment;
/*    */ import org.dom4j.Element;
/*    */ import org.dom4j.Node;
/*    */ 
/*    */ public class FlyweightComment extends AbstractComment
/*    */   implements Comment
/*    */ {
/*    */   protected String text;
/*    */ 
/*    */   public FlyweightComment(String text)
/*    */   {
/* 39 */     this.text = text;
/*    */   }
/*    */ 
/*    */   public String getText() {
/* 43 */     return this.text;
/*    */   }
/*    */ 
/*    */   protected Node createXPathResult(Element parent) {
/* 47 */     return new DefaultComment(parent, getText());
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.FlyweightComment
 * JD-Core Version:    0.6.2
 */