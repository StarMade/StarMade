/*    */ package it.unimi.dsi.fastutil.floats;
/*    */ 
/*    */ public abstract class AbstractFloatBigListIterator extends AbstractFloatBidirectionalIterator
/*    */   implements FloatBigListIterator
/*    */ {
/*    */   public void set(Float ok)
/*    */   {
/* 58 */     set(ok.floatValue());
/*    */   }
/* 60 */   public void add(Float ok) { add(ok.floatValue()); } 
/*    */   public void set(float k) {
/* 62 */     throw new UnsupportedOperationException();
/*    */   }
/* 64 */   public void add(float k) { throw new UnsupportedOperationException(); }
/*    */ 
/*    */   public long skip(long n)
/*    */   {
/* 68 */     long i = n;
/* 69 */     while ((i-- != 0L) && (hasNext())) nextFloat();
/* 70 */     return n - i - 1L;
/*    */   }
/*    */ 
/*    */   public long back(long n)
/*    */   {
/* 76 */     long i = n;
/* 77 */     while ((i-- != 0L) && (hasPrevious())) previousFloat();
/* 78 */     return n - i - 1L;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloatBigListIterator
 * JD-Core Version:    0.6.2
 */