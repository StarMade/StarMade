/*    */ package org.dom4j.tree;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class SingleIterator
/*    */   implements Iterator
/*    */ {
/* 22 */   private boolean first = true;
/*    */   private Object object;
/*    */ 
/*    */   public SingleIterator(Object object)
/*    */   {
/* 27 */     this.object = object;
/*    */   }
/*    */ 
/*    */   public boolean hasNext() {
/* 31 */     return this.first;
/*    */   }
/*    */ 
/*    */   public Object next() {
/* 35 */     Object answer = this.object;
/* 36 */     this.object = null;
/* 37 */     this.first = false;
/*    */ 
/* 39 */     return answer;
/*    */   }
/*    */ 
/*    */   public void remove() {
/* 43 */     throw new UnsupportedOperationException("remove() is not supported by this iterator");
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.SingleIterator
 * JD-Core Version:    0.6.2
 */