/*    */ package it.unimi.dsi.fastutil.ints;
/*    */ 
/*    */ public abstract class AbstractIntBidirectionalIterator extends AbstractIntIterator
/*    */   implements IntBidirectionalIterator
/*    */ {
/*    */   public int previousInt()
/*    */   {
/* 58 */     return previous().intValue();
/*    */   }
/* 60 */   public Integer previous() { return Integer.valueOf(previousInt()); }
/*    */ 
/*    */ 
/*    */   public int back(int n)
/*    */   {
/* 65 */     int i = n;
/* 66 */     while ((i-- != 0) && (hasPrevious())) previousInt();
/* 67 */     return n - i - 1;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractIntBidirectionalIterator
 * JD-Core Version:    0.6.2
 */