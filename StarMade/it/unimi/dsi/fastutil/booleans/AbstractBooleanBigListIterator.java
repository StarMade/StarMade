/*    */ package it.unimi.dsi.fastutil.booleans;
/*    */ 
/*    */ public abstract class AbstractBooleanBigListIterator extends AbstractBooleanBidirectionalIterator
/*    */   implements BooleanBigListIterator
/*    */ {
/*    */   public void set(Boolean ok)
/*    */   {
/* 58 */     set(ok.booleanValue());
/*    */   }
/* 60 */   public void add(Boolean ok) { add(ok.booleanValue()); } 
/*    */   public void set(boolean k) {
/* 62 */     throw new UnsupportedOperationException();
/*    */   }
/* 64 */   public void add(boolean k) { throw new UnsupportedOperationException(); }
/*    */ 
/*    */   public long skip(long n)
/*    */   {
/* 68 */     long i = n;
/* 69 */     while ((i-- != 0L) && (hasNext())) nextBoolean();
/* 70 */     return n - i - 1L;
/*    */   }
/*    */ 
/*    */   public long back(long n)
/*    */   {
/* 76 */     long i = n;
/* 77 */     while ((i-- != 0L) && (hasPrevious())) previousBoolean();
/* 78 */     return n - i - 1L;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.AbstractBooleanBigListIterator
 * JD-Core Version:    0.6.2
 */