/*    */ package it.unimi.dsi.fastutil.doubles;
/*    */ 
/*    */ public abstract class AbstractDoubleBigListIterator extends AbstractDoubleBidirectionalIterator
/*    */   implements DoubleBigListIterator
/*    */ {
/*    */   public void set(Double ok)
/*    */   {
/* 58 */     set(ok.doubleValue());
/*    */   }
/* 60 */   public void add(Double ok) { add(ok.doubleValue()); } 
/*    */   public void set(double k) {
/* 62 */     throw new UnsupportedOperationException();
/*    */   }
/* 64 */   public void add(double k) { throw new UnsupportedOperationException(); }
/*    */ 
/*    */   public long skip(long n)
/*    */   {
/* 68 */     long i = n;
/* 69 */     while ((i-- != 0L) && (hasNext())) nextDouble();
/* 70 */     return n - i - 1L;
/*    */   }
/*    */ 
/*    */   public long back(long n)
/*    */   {
/* 76 */     long i = n;
/* 77 */     while ((i-- != 0L) && (hasPrevious())) previousDouble();
/* 78 */     return n - i - 1L;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDoubleBigListIterator
 * JD-Core Version:    0.6.2
 */