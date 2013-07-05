/*    */ package it.unimi.dsi.fastutil.bytes;
/*    */ 
/*    */ public abstract class AbstractByteIterator
/*    */   implements ByteIterator
/*    */ {
/*    */   public byte nextByte()
/*    */   {
/* 60 */     return next().byteValue();
/*    */   }
/* 62 */   public Byte next() { return Byte.valueOf(nextByte()); } 
/*    */   public void remove() {
/* 64 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ 
/*    */   public int skip(int n) {
/* 68 */     int i = n;
/* 69 */     while ((i-- != 0) && (hasNext())) nextByte();
/* 70 */     return n - i - 1;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByteIterator
 * JD-Core Version:    0.6.2
 */