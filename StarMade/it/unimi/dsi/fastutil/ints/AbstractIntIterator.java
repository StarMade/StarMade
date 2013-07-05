/*    */ package it.unimi.dsi.fastutil.ints;
/*    */ 
/*    */ public abstract class AbstractIntIterator
/*    */   implements IntIterator
/*    */ {
/*    */   public int nextInt()
/*    */   {
/* 60 */     return next().intValue();
/*    */   }
/* 62 */   public Integer next() { return Integer.valueOf(nextInt()); } 
/*    */   public void remove() {
/* 64 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ 
/*    */   public int skip(int n) {
/* 68 */     int i = n;
/* 69 */     while ((i-- != 0) && (hasNext())) nextInt();
/* 70 */     return n - i - 1;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractIntIterator
 * JD-Core Version:    0.6.2
 */