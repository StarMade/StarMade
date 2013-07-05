/*    */ package it.unimi.dsi.fastutil.bytes;
/*    */ 
/*    */ public abstract class AbstractByteListIterator extends AbstractByteBidirectionalIterator
/*    */   implements ByteListIterator
/*    */ {
/*    */   public void set(Byte ok)
/*    */   {
/* 59 */     set(ok.byteValue());
/*    */   }
/* 61 */   public void add(Byte ok) { add(ok.byteValue()); } 
/*    */   public void set(byte k) {
/* 63 */     throw new UnsupportedOperationException();
/*    */   }
/* 65 */   public void add(byte k) { throw new UnsupportedOperationException(); }
/*    */ 
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByteListIterator
 * JD-Core Version:    0.6.2
 */