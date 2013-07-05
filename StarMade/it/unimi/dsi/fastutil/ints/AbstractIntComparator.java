/*    */ package it.unimi.dsi.fastutil.ints;
/*    */ 
/*    */ public abstract class AbstractIntComparator
/*    */   implements IntComparator
/*    */ {
/*    */   public int compare(Integer ok1, Integer ok2)
/*    */   {
/* 57 */     return compare(ok1.intValue(), ok2.intValue());
/*    */   }
/*    */ 
/*    */   public abstract int compare(int paramInt1, int paramInt2);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractIntComparator
 * JD-Core Version:    0.6.2
 */