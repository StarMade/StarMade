/*    */ package it.unimi.dsi.fastutil.booleans;
/*    */ 
/*    */ public abstract class AbstractBooleanListIterator extends AbstractBooleanBidirectionalIterator
/*    */   implements BooleanListIterator
/*    */ {
/*    */   public void set(Boolean ok)
/*    */   {
/* 59 */     set(ok.booleanValue());
/*    */   }
/* 61 */   public void add(Boolean ok) { add(ok.booleanValue()); } 
/*    */   public void set(boolean k) {
/* 63 */     throw new UnsupportedOperationException();
/*    */   }
/* 65 */   public void add(boolean k) { throw new UnsupportedOperationException(); }
/*    */ 
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.AbstractBooleanListIterator
 * JD-Core Version:    0.6.2
 */