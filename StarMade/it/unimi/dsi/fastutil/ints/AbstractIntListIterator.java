/*    */ package it.unimi.dsi.fastutil.ints;
/*    */ 
/*    */ public abstract class AbstractIntListIterator extends AbstractIntBidirectionalIterator
/*    */   implements IntListIterator
/*    */ {
/*    */   public void set(Integer ok)
/*    */   {
/* 59 */     set(ok.intValue());
/*    */   }
/* 61 */   public void add(Integer ok) { add(ok.intValue()); } 
/*    */   public void set(int k) {
/* 63 */     throw new UnsupportedOperationException();
/*    */   }
/* 65 */   public void add(int k) { throw new UnsupportedOperationException(); }
/*    */ 
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractIntListIterator
 * JD-Core Version:    0.6.2
 */