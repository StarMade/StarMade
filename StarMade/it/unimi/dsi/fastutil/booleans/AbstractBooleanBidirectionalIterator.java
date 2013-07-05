/*    */ package it.unimi.dsi.fastutil.booleans;
/*    */ 
/*    */ public abstract class AbstractBooleanBidirectionalIterator extends AbstractBooleanIterator
/*    */   implements BooleanBidirectionalIterator
/*    */ {
/*    */   public boolean previousBoolean()
/*    */   {
/* 58 */     return previous().booleanValue();
/*    */   }
/* 60 */   public Boolean previous() { return Boolean.valueOf(previousBoolean()); }
/*    */ 
/*    */ 
/*    */   public int back(int n)
/*    */   {
/* 65 */     int i = n;
/* 66 */     while ((i-- != 0) && (hasPrevious())) previousBoolean();
/* 67 */     return n - i - 1;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.AbstractBooleanBidirectionalIterator
 * JD-Core Version:    0.6.2
 */