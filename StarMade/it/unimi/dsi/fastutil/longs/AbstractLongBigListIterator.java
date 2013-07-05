/*    */ package it.unimi.dsi.fastutil.longs;
/*    */ 
/*    */ public abstract class AbstractLongBigListIterator extends AbstractLongBidirectionalIterator
/*    */   implements LongBigListIterator
/*    */ {
/*    */   public void set(Long ok)
/*    */   {
/* 58 */     set(ok.longValue());
/*    */   }
/* 60 */   public void add(Long ok) { add(ok.longValue()); } 
/*    */   public void set(long k) {
/* 62 */     throw new UnsupportedOperationException();
/*    */   }
/* 64 */   public void add(long k) { throw new UnsupportedOperationException(); }
/*    */ 
/*    */   public long skip(long n)
/*    */   {
/* 68 */     long i = n;
/* 69 */     while ((i-- != 0L) && (hasNext())) nextLong();
/* 70 */     return n - i - 1L;
/*    */   }
/*    */ 
/*    */   public long back(long n)
/*    */   {
/* 76 */     long i = n;
/* 77 */     while ((i-- != 0L) && (hasPrevious())) previousLong();
/* 78 */     return n - i - 1L;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLongBigListIterator
 * JD-Core Version:    0.6.2
 */