/*    */ package it.unimi.dsi.fastutil;
/*    */ 
/*    */ public abstract class AbstractPriorityQueue<K>
/*    */   implements PriorityQueue<K>
/*    */ {
/*    */   public void changed()
/*    */   {
/* 29 */     throw new UnsupportedOperationException();
/*    */   }
/* 31 */   public K last() { throw new UnsupportedOperationException(); } 
/*    */   public boolean isEmpty() {
/* 33 */     return size() == 0;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.AbstractPriorityQueue
 * JD-Core Version:    0.6.2
 */