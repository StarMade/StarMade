/*    */ package it.unimi.dsi.fastutil.doubles;
/*    */ 
/*    */ public abstract class AbstractDoubleIterator
/*    */   implements DoubleIterator
/*    */ {
/*    */   public double nextDouble()
/*    */   {
/* 60 */     return next().doubleValue();
/*    */   }
/* 62 */   public Double next() { return Double.valueOf(nextDouble()); } 
/*    */   public void remove() {
/* 64 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ 
/*    */   public int skip(int n) {
/* 68 */     int i = n;
/* 69 */     while ((i-- != 0) && (hasNext())) nextDouble();
/* 70 */     return n - i - 1;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDoubleIterator
 * JD-Core Version:    0.6.2
 */