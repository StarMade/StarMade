/*    */ package it.unimi.dsi.fastutil.longs;
/*    */ 
/*    */ public abstract class AbstractLongSortedSet extends AbstractLongSet
/*    */   implements LongSortedSet
/*    */ {
/*    */   public LongSortedSet headSet(Long to)
/*    */   {
/* 50 */     return headSet(to.longValue());
/*    */   }
/*    */ 
/*    */   public LongSortedSet tailSet(Long from) {
/* 54 */     return tailSet(from.longValue());
/*    */   }
/*    */ 
/*    */   public LongSortedSet subSet(Long from, Long to) {
/* 58 */     return subSet(from.longValue(), to.longValue());
/*    */   }
/*    */ 
/*    */   public Long first() {
/* 62 */     return Long.valueOf(firstLong());
/*    */   }
/*    */ 
/*    */   public Long last() {
/* 66 */     return Long.valueOf(lastLong());
/*    */   }
/*    */ 
/*    */   @Deprecated
/*    */   public LongBidirectionalIterator longIterator() {
/* 71 */     return iterator();
/*    */   }
/*    */ 
/*    */   public abstract LongBidirectionalIterator iterator();
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLongSortedSet
 * JD-Core Version:    0.6.2
 */