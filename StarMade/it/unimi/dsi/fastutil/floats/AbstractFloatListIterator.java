/*    */ package it.unimi.dsi.fastutil.floats;
/*    */ 
/*    */ public abstract class AbstractFloatListIterator extends AbstractFloatBidirectionalIterator
/*    */   implements FloatListIterator
/*    */ {
/*    */   public void set(Float ok)
/*    */   {
/* 59 */     set(ok.floatValue());
/*    */   }
/* 61 */   public void add(Float ok) { add(ok.floatValue()); } 
/*    */   public void set(float k) {
/* 63 */     throw new UnsupportedOperationException();
/*    */   }
/* 65 */   public void add(float k) { throw new UnsupportedOperationException(); }
/*    */ 
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloatListIterator
 * JD-Core Version:    0.6.2
 */