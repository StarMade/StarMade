/*    */ package it.unimi.dsi.fastutil.doubles;
/*    */ 
/*    */ public abstract class AbstractDoubleListIterator extends AbstractDoubleBidirectionalIterator
/*    */   implements DoubleListIterator
/*    */ {
/*    */   public void set(Double ok)
/*    */   {
/* 59 */     set(ok.doubleValue());
/*    */   }
/* 61 */   public void add(Double ok) { add(ok.doubleValue()); } 
/*    */   public void set(double k) {
/* 63 */     throw new UnsupportedOperationException();
/*    */   }
/* 65 */   public void add(double k) { throw new UnsupportedOperationException(); }
/*    */ 
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDoubleListIterator
 * JD-Core Version:    0.6.2
 */