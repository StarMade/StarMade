/*    */ package it.unimi.dsi.fastutil.longs;
/*    */ 
/*    */ public abstract class AbstractLongIterator
/*    */   implements LongIterator
/*    */ {
/*    */   public long nextLong()
/*    */   {
/* 60 */     return next().longValue();
/*    */   }
/* 62 */   public Long next() { return Long.valueOf(nextLong()); } 
/*    */   public void remove() {
/* 64 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ 
/*    */   public int skip(int n) {
/* 68 */     int i = n;
/* 69 */     while ((i-- != 0) && (hasNext())) nextLong();
/* 70 */     return n - i - 1;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLongIterator
 * JD-Core Version:    0.6.2
 */