/*    */ package it.unimi.dsi.fastutil.longs;
/*    */ 
/*    */ public abstract class AbstractLongListIterator extends AbstractLongBidirectionalIterator
/*    */   implements LongListIterator
/*    */ {
/*    */   public void set(Long ok)
/*    */   {
/* 59 */     set(ok.longValue());
/*    */   }
/* 61 */   public void add(Long ok) { add(ok.longValue()); } 
/*    */   public void set(long k) {
/* 63 */     throw new UnsupportedOperationException();
/*    */   }
/* 65 */   public void add(long k) { throw new UnsupportedOperationException(); }
/*    */ 
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLongListIterator
 * JD-Core Version:    0.6.2
 */