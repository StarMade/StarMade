/*    */ package it.unimi.dsi.fastutil.doubles;
/*    */ 
/*    */ public abstract class AbstractDoubleSortedSet extends AbstractDoubleSet
/*    */   implements DoubleSortedSet
/*    */ {
/*    */   public DoubleSortedSet headSet(Double to)
/*    */   {
/* 50 */     return headSet(to.doubleValue());
/*    */   }
/*    */ 
/*    */   public DoubleSortedSet tailSet(Double from) {
/* 54 */     return tailSet(from.doubleValue());
/*    */   }
/*    */ 
/*    */   public DoubleSortedSet subSet(Double from, Double to) {
/* 58 */     return subSet(from.doubleValue(), to.doubleValue());
/*    */   }
/*    */ 
/*    */   public Double first() {
/* 62 */     return Double.valueOf(firstDouble());
/*    */   }
/*    */ 
/*    */   public Double last() {
/* 66 */     return Double.valueOf(lastDouble());
/*    */   }
/*    */ 
/*    */   @Deprecated
/*    */   public DoubleBidirectionalIterator doubleIterator() {
/* 71 */     return iterator();
/*    */   }
/*    */ 
/*    */   public abstract DoubleBidirectionalIterator iterator();
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDoubleSortedSet
 * JD-Core Version:    0.6.2
 */