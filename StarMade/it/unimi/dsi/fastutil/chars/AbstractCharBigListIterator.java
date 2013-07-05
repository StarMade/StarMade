/*    */ package it.unimi.dsi.fastutil.chars;
/*    */ 
/*    */ public abstract class AbstractCharBigListIterator extends AbstractCharBidirectionalIterator
/*    */   implements CharBigListIterator
/*    */ {
/*    */   public void set(Character ok)
/*    */   {
/* 58 */     set(ok.charValue());
/*    */   }
/* 60 */   public void add(Character ok) { add(ok.charValue()); } 
/*    */   public void set(char k) {
/* 62 */     throw new UnsupportedOperationException();
/*    */   }
/* 64 */   public void add(char k) { throw new UnsupportedOperationException(); }
/*    */ 
/*    */   public long skip(long n)
/*    */   {
/* 68 */     long i = n;
/* 69 */     while ((i-- != 0L) && (hasNext())) nextChar();
/* 70 */     return n - i - 1L;
/*    */   }
/*    */ 
/*    */   public long back(long n)
/*    */   {
/* 76 */     long i = n;
/* 77 */     while ((i-- != 0L) && (hasPrevious())) previousChar();
/* 78 */     return n - i - 1L;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractCharBigListIterator
 * JD-Core Version:    0.6.2
 */