/*    */ package it.unimi.dsi.fastutil.shorts;
/*    */ 
/*    */ public abstract class AbstractShortSortedSet extends AbstractShortSet
/*    */   implements ShortSortedSet
/*    */ {
/*    */   public ShortSortedSet headSet(Short to)
/*    */   {
/* 50 */     return headSet(to.shortValue());
/*    */   }
/*    */ 
/*    */   public ShortSortedSet tailSet(Short from) {
/* 54 */     return tailSet(from.shortValue());
/*    */   }
/*    */ 
/*    */   public ShortSortedSet subSet(Short from, Short to) {
/* 58 */     return subSet(from.shortValue(), to.shortValue());
/*    */   }
/*    */ 
/*    */   public Short first() {
/* 62 */     return Short.valueOf(firstShort());
/*    */   }
/*    */ 
/*    */   public Short last() {
/* 66 */     return Short.valueOf(lastShort());
/*    */   }
/*    */ 
/*    */   @Deprecated
/*    */   public ShortBidirectionalIterator shortIterator() {
/* 71 */     return iterator();
/*    */   }
/*    */ 
/*    */   public abstract ShortBidirectionalIterator iterator();
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShortSortedSet
 * JD-Core Version:    0.6.2
 */