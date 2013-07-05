/*    */ package it.unimi.dsi.fastutil;
/*    */ 
/*    */ public abstract class AbstractStack<K>
/*    */   implements Stack<K>
/*    */ {
/*    */   public K top()
/*    */   {
/* 34 */     return peek(0);
/*    */   }
/*    */ 
/*    */   public K peek(int i) {
/* 38 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.AbstractStack
 * JD-Core Version:    0.6.2
 */