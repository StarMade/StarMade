/*    */ package it.unimi.dsi.fastutil.ints;
/*    */ 
/*    */ public abstract class AbstractIntBigListIterator extends AbstractIntBidirectionalIterator
/*    */   implements IntBigListIterator
/*    */ {
/*    */   public void set(Integer ok)
/*    */   {
/* 58 */     set(ok.intValue());
/*    */   }
/* 60 */   public void add(Integer ok) { add(ok.intValue()); } 
/*    */   public void set(int k) {
/* 62 */     throw new UnsupportedOperationException();
/*    */   }
/* 64 */   public void add(int k) { throw new UnsupportedOperationException(); }
/*    */ 
/*    */   public long skip(long n)
/*    */   {
/* 68 */     long i = n;
/* 69 */     while ((i-- != 0L) && (hasNext())) nextInt();
/* 70 */     return n - i - 1L;
/*    */   }
/*    */ 
/*    */   public long back(long n)
/*    */   {
/* 76 */     long i = n;
/* 77 */     while ((i-- != 0L) && (hasPrevious())) previousInt();
/* 78 */     return n - i - 1L;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractIntBigListIterator
 * JD-Core Version:    0.6.2
 */