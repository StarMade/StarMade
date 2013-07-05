/*    */ package it.unimi.dsi.fastutil.chars;
/*    */ 
/*    */ public abstract class AbstractCharSortedSet extends AbstractCharSet
/*    */   implements CharSortedSet
/*    */ {
/*    */   public CharSortedSet headSet(Character to)
/*    */   {
/* 50 */     return headSet(to.charValue());
/*    */   }
/*    */ 
/*    */   public CharSortedSet tailSet(Character from) {
/* 54 */     return tailSet(from.charValue());
/*    */   }
/*    */ 
/*    */   public CharSortedSet subSet(Character from, Character to) {
/* 58 */     return subSet(from.charValue(), to.charValue());
/*    */   }
/*    */ 
/*    */   public Character first() {
/* 62 */     return Character.valueOf(firstChar());
/*    */   }
/*    */ 
/*    */   public Character last() {
/* 66 */     return Character.valueOf(lastChar());
/*    */   }
/*    */ 
/*    */   @Deprecated
/*    */   public CharBidirectionalIterator charIterator() {
/* 71 */     return iterator();
/*    */   }
/*    */ 
/*    */   public abstract CharBidirectionalIterator iterator();
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractCharSortedSet
 * JD-Core Version:    0.6.2
 */