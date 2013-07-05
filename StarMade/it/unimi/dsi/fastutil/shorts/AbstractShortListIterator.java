/*    */ package it.unimi.dsi.fastutil.shorts;
/*    */ 
/*    */ public abstract class AbstractShortListIterator extends AbstractShortBidirectionalIterator
/*    */   implements ShortListIterator
/*    */ {
/*    */   public void set(Short ok)
/*    */   {
/* 59 */     set(ok.shortValue());
/*    */   }
/* 61 */   public void add(Short ok) { add(ok.shortValue()); } 
/*    */   public void set(short k) {
/* 63 */     throw new UnsupportedOperationException();
/*    */   }
/* 65 */   public void add(short k) { throw new UnsupportedOperationException(); }
/*    */ 
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShortListIterator
 * JD-Core Version:    0.6.2
 */