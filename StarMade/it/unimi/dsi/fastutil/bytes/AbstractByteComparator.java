/*    */ package it.unimi.dsi.fastutil.bytes;
/*    */ 
/*    */ public abstract class AbstractByteComparator
/*    */   implements ByteComparator
/*    */ {
/*    */   public int compare(Byte ok1, Byte ok2)
/*    */   {
/* 57 */     return compare(ok1.byteValue(), ok2.byteValue());
/*    */   }
/*    */ 
/*    */   public abstract int compare(byte paramByte1, byte paramByte2);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByteComparator
 * JD-Core Version:    0.6.2
 */