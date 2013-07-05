/*    */ package it.unimi.dsi.fastutil.floats;
/*    */ 
/*    */ public abstract class AbstractFloatSortedSet extends AbstractFloatSet
/*    */   implements FloatSortedSet
/*    */ {
/*    */   public FloatSortedSet headSet(Float to)
/*    */   {
/* 50 */     return headSet(to.floatValue());
/*    */   }
/*    */ 
/*    */   public FloatSortedSet tailSet(Float from) {
/* 54 */     return tailSet(from.floatValue());
/*    */   }
/*    */ 
/*    */   public FloatSortedSet subSet(Float from, Float to) {
/* 58 */     return subSet(from.floatValue(), to.floatValue());
/*    */   }
/*    */ 
/*    */   public Float first() {
/* 62 */     return Float.valueOf(firstFloat());
/*    */   }
/*    */ 
/*    */   public Float last() {
/* 66 */     return Float.valueOf(lastFloat());
/*    */   }
/*    */ 
/*    */   @Deprecated
/*    */   public FloatBidirectionalIterator floatIterator() {
/* 71 */     return iterator();
/*    */   }
/*    */ 
/*    */   public abstract FloatBidirectionalIterator iterator();
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloatSortedSet
 * JD-Core Version:    0.6.2
 */