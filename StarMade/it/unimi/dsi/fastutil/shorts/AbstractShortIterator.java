/*    */ package it.unimi.dsi.fastutil.shorts;
/*    */ 
/*    */ public abstract class AbstractShortIterator
/*    */   implements ShortIterator
/*    */ {
/*    */   public short nextShort()
/*    */   {
/* 60 */     return next().shortValue();
/*    */   }
/* 62 */   public Short next() { return Short.valueOf(nextShort()); } 
/*    */   public void remove() {
/* 64 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ 
/*    */   public int skip(int n) {
/* 68 */     int i = n;
/* 69 */     while ((i-- != 0) && (hasNext())) nextShort();
/* 70 */     return n - i - 1;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShortIterator
 * JD-Core Version:    0.6.2
 */