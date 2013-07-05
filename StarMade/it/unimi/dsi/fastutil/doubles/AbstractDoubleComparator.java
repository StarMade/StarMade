/*    */ package it.unimi.dsi.fastutil.doubles;
/*    */ 
/*    */ public abstract class AbstractDoubleComparator
/*    */   implements DoubleComparator
/*    */ {
/*    */   public int compare(Double ok1, Double ok2)
/*    */   {
/* 57 */     return compare(ok1.doubleValue(), ok2.doubleValue());
/*    */   }
/*    */ 
/*    */   public abstract int compare(double paramDouble1, double paramDouble2);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDoubleComparator
 * JD-Core Version:    0.6.2
 */