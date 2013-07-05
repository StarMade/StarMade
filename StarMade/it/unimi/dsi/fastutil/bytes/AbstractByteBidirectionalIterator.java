/*    */ package it.unimi.dsi.fastutil.bytes;
/*    */ 
/*    */ public abstract class AbstractByteBidirectionalIterator extends AbstractByteIterator
/*    */   implements ByteBidirectionalIterator
/*    */ {
/*    */   public byte previousByte()
/*    */   {
/* 58 */     return previous().byteValue();
/*    */   }
/* 60 */   public Byte previous() { return Byte.valueOf(previousByte()); }
/*    */ 
/*    */ 
/*    */   public int back(int n)
/*    */   {
/* 65 */     int i = n;
/* 66 */     while ((i-- != 0) && (hasPrevious())) previousByte();
/* 67 */     return n - i - 1;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByteBidirectionalIterator
 * JD-Core Version:    0.6.2
 */