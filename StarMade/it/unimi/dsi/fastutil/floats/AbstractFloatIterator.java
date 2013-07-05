/*    */ package it.unimi.dsi.fastutil.floats;
/*    */ 
/*    */ public abstract class AbstractFloatIterator
/*    */   implements FloatIterator
/*    */ {
/*    */   public float nextFloat()
/*    */   {
/* 60 */     return next().floatValue();
/*    */   }
/* 62 */   public Float next() { return Float.valueOf(nextFloat()); } 
/*    */   public void remove() {
/* 64 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ 
/*    */   public int skip(int n) {
/* 68 */     int i = n;
/* 69 */     while ((i-- != 0) && (hasNext())) nextFloat();
/* 70 */     return n - i - 1;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloatIterator
 * JD-Core Version:    0.6.2
 */