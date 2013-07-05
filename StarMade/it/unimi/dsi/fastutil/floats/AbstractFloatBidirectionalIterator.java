/*    */ package it.unimi.dsi.fastutil.floats;
/*    */ 
/*    */ public abstract class AbstractFloatBidirectionalIterator extends AbstractFloatIterator
/*    */   implements FloatBidirectionalIterator
/*    */ {
/*    */   public float previousFloat()
/*    */   {
/* 58 */     return previous().floatValue();
/*    */   }
/* 60 */   public Float previous() { return Float.valueOf(previousFloat()); }
/*    */ 
/*    */ 
/*    */   public int back(int n)
/*    */   {
/* 65 */     int i = n;
/* 66 */     while ((i-- != 0) && (hasPrevious())) previousFloat();
/* 67 */     return n - i - 1;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloatBidirectionalIterator
 * JD-Core Version:    0.6.2
 */