/*    */ package it.unimi.dsi.fastutil;
/*    */ 
/*    */ public abstract class AbstractIndirectPriorityQueue<K>
/*    */   implements IndirectPriorityQueue<K>
/*    */ {
/*    */   public int last()
/*    */   {
/* 27 */     throw new UnsupportedOperationException();
/*    */   }
/* 29 */   public void changed() { changed(first()); } 
/*    */   public void changed(int index) {
/* 31 */     throw new UnsupportedOperationException();
/*    */   }
/* 33 */   public void allChanged() { throw new UnsupportedOperationException(); } 
/*    */   public boolean remove(int index) {
/* 35 */     throw new UnsupportedOperationException();
/*    */   }
/* 37 */   public boolean contains(int index) { throw new UnsupportedOperationException(); } 
/*    */   public boolean isEmpty() {
/* 39 */     return size() == 0;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.AbstractIndirectPriorityQueue
 * JD-Core Version:    0.6.2
 */