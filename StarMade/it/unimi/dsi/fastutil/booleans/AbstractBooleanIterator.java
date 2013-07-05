/*    */ package it.unimi.dsi.fastutil.booleans;
/*    */ 
/*    */ public abstract class AbstractBooleanIterator
/*    */   implements BooleanIterator
/*    */ {
/*    */   public boolean nextBoolean()
/*    */   {
/* 60 */     return next().booleanValue();
/*    */   }
/* 62 */   public Boolean next() { return Boolean.valueOf(nextBoolean()); } 
/*    */   public void remove() {
/* 64 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ 
/*    */   public int skip(int n) {
/* 68 */     int i = n;
/* 69 */     while ((i-- != 0) && (hasNext())) nextBoolean();
/* 70 */     return n - i - 1;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.AbstractBooleanIterator
 * JD-Core Version:    0.6.2
 */