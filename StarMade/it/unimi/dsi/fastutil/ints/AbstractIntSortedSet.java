/*    */ package it.unimi.dsi.fastutil.ints;
/*    */ 
/*    */ public abstract class AbstractIntSortedSet extends AbstractIntSet
/*    */   implements IntSortedSet
/*    */ {
/*    */   public IntSortedSet headSet(Integer to)
/*    */   {
/* 50 */     return headSet(to.intValue());
/*    */   }
/*    */ 
/*    */   public IntSortedSet tailSet(Integer from) {
/* 54 */     return tailSet(from.intValue());
/*    */   }
/*    */ 
/*    */   public IntSortedSet subSet(Integer from, Integer to) {
/* 58 */     return subSet(from.intValue(), to.intValue());
/*    */   }
/*    */ 
/*    */   public Integer first() {
/* 62 */     return Integer.valueOf(firstInt());
/*    */   }
/*    */ 
/*    */   public Integer last() {
/* 66 */     return Integer.valueOf(lastInt());
/*    */   }
/*    */ 
/*    */   @Deprecated
/*    */   public IntBidirectionalIterator intIterator() {
/* 71 */     return iterator();
/*    */   }
/*    */ 
/*    */   public abstract IntBidirectionalIterator iterator();
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractIntSortedSet
 * JD-Core Version:    0.6.2
 */