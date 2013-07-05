/*    */ package it.unimi.dsi.fastutil.bytes;
/*    */ 
/*    */ public abstract class AbstractByteSortedSet extends AbstractByteSet
/*    */   implements ByteSortedSet
/*    */ {
/*    */   public ByteSortedSet headSet(Byte to)
/*    */   {
/* 50 */     return headSet(to.byteValue());
/*    */   }
/*    */ 
/*    */   public ByteSortedSet tailSet(Byte from) {
/* 54 */     return tailSet(from.byteValue());
/*    */   }
/*    */ 
/*    */   public ByteSortedSet subSet(Byte from, Byte to) {
/* 58 */     return subSet(from.byteValue(), to.byteValue());
/*    */   }
/*    */ 
/*    */   public Byte first() {
/* 62 */     return Byte.valueOf(firstByte());
/*    */   }
/*    */ 
/*    */   public Byte last() {
/* 66 */     return Byte.valueOf(lastByte());
/*    */   }
/*    */ 
/*    */   @Deprecated
/*    */   public ByteBidirectionalIterator byteIterator() {
/* 71 */     return iterator();
/*    */   }
/*    */ 
/*    */   public abstract ByteBidirectionalIterator iterator();
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByteSortedSet
 * JD-Core Version:    0.6.2
 */