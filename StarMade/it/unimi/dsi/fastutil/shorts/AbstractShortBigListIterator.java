/*    */ package it.unimi.dsi.fastutil.shorts;
/*    */ 
/*    */ public abstract class AbstractShortBigListIterator extends AbstractShortBidirectionalIterator
/*    */   implements ShortBigListIterator
/*    */ {
/*    */   public void set(Short ok)
/*    */   {
/* 58 */     set(ok.shortValue());
/*    */   }
/* 60 */   public void add(Short ok) { add(ok.shortValue()); } 
/*    */   public void set(short k) {
/* 62 */     throw new UnsupportedOperationException();
/*    */   }
/* 64 */   public void add(short k) { throw new UnsupportedOperationException(); }
/*    */ 
/*    */   public long skip(long n)
/*    */   {
/* 68 */     long i = n;
/* 69 */     while ((i-- != 0L) && (hasNext())) nextShort();
/* 70 */     return n - i - 1L;
/*    */   }
/*    */ 
/*    */   public long back(long n)
/*    */   {
/* 76 */     long i = n;
/* 77 */     while ((i-- != 0L) && (hasPrevious())) previousShort();
/* 78 */     return n - i - 1L;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShortBigListIterator
 * JD-Core Version:    0.6.2
 */