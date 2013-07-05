/*    */ package it.unimi.dsi.fastutil.doubles;
/*    */ 
/*    */ public abstract class AbstractDoubleBidirectionalIterator extends AbstractDoubleIterator
/*    */   implements DoubleBidirectionalIterator
/*    */ {
/*    */   public double previousDouble()
/*    */   {
/* 58 */     return previous().doubleValue();
/*    */   }
/* 60 */   public Double previous() { return Double.valueOf(previousDouble()); }
/*    */ 
/*    */ 
/*    */   public int back(int n)
/*    */   {
/* 65 */     int i = n;
/* 66 */     while ((i-- != 0) && (hasPrevious())) previousDouble();
/* 67 */     return n - i - 1;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDoubleBidirectionalIterator
 * JD-Core Version:    0.6.2
 */