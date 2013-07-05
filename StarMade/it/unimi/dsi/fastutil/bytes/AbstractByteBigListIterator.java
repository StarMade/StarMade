/*    */ package it.unimi.dsi.fastutil.bytes;
/*    */ 
/*    */ public abstract class AbstractByteBigListIterator extends AbstractByteBidirectionalIterator
/*    */   implements ByteBigListIterator
/*    */ {
/*    */   public void set(Byte ok)
/*    */   {
/* 58 */     set(ok.byteValue());
/*    */   }
/* 60 */   public void add(Byte ok) { add(ok.byteValue()); } 
/*    */   public void set(byte k) {
/* 62 */     throw new UnsupportedOperationException();
/*    */   }
/* 64 */   public void add(byte k) { throw new UnsupportedOperationException(); }
/*    */ 
/*    */   public long skip(long n)
/*    */   {
/* 68 */     long i = n;
/* 69 */     while ((i-- != 0L) && (hasNext())) nextByte();
/* 70 */     return n - i - 1L;
/*    */   }
/*    */ 
/*    */   public long back(long n)
/*    */   {
/* 76 */     long i = n;
/* 77 */     while ((i-- != 0L) && (hasPrevious())) previousByte();
/* 78 */     return n - i - 1L;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByteBigListIterator
 * JD-Core Version:    0.6.2
 */