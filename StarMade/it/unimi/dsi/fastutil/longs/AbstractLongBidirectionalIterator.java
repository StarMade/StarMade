/*    */ package it.unimi.dsi.fastutil.longs;
/*    */ 
/*    */ public abstract class AbstractLongBidirectionalIterator extends AbstractLongIterator
/*    */   implements LongBidirectionalIterator
/*    */ {
/*    */   public long previousLong()
/*    */   {
/* 58 */     return previous().longValue();
/*    */   }
/* 60 */   public Long previous() { return Long.valueOf(previousLong()); }
/*    */ 
/*    */ 
/*    */   public int back(int n)
/*    */   {
/* 65 */     int i = n;
/* 66 */     while ((i-- != 0) && (hasPrevious())) previousLong();
/* 67 */     return n - i - 1;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLongBidirectionalIterator
 * JD-Core Version:    0.6.2
 */