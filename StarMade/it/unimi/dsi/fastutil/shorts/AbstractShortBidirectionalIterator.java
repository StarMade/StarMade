/*    */ package it.unimi.dsi.fastutil.shorts;
/*    */ 
/*    */ public abstract class AbstractShortBidirectionalIterator extends AbstractShortIterator
/*    */   implements ShortBidirectionalIterator
/*    */ {
/*    */   public short previousShort()
/*    */   {
/* 58 */     return previous().shortValue();
/*    */   }
/* 60 */   public Short previous() { return Short.valueOf(previousShort()); }
/*    */ 
/*    */ 
/*    */   public int back(int n)
/*    */   {
/* 65 */     int i = n;
/* 66 */     while ((i-- != 0) && (hasPrevious())) previousShort();
/* 67 */     return n - i - 1;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShortBidirectionalIterator
 * JD-Core Version:    0.6.2
 */