/*    */ package it.unimi.dsi.fastutil.bytes;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.AbstractStack;
/*    */ 
/*    */ public abstract class AbstractByteStack extends AbstractStack<Byte>
/*    */   implements ByteStack
/*    */ {
/*    */   public void push(Byte o)
/*    */   {
/* 56 */     push(o.byteValue());
/*    */   }
/*    */ 
/*    */   public Byte pop() {
/* 60 */     return Byte.valueOf(popByte());
/*    */   }
/*    */ 
/*    */   public Byte top() {
/* 64 */     return Byte.valueOf(topByte());
/*    */   }
/*    */ 
/*    */   public Byte peek(int i) {
/* 68 */     return Byte.valueOf(peekByte(i));
/*    */   }
/*    */ 
/*    */   public void push(byte k) {
/* 72 */     push(Byte.valueOf(k));
/*    */   }
/*    */ 
/*    */   public byte popByte() {
/* 76 */     return pop().byteValue();
/*    */   }
/*    */ 
/*    */   public byte topByte() {
/* 80 */     return top().byteValue();
/*    */   }
/*    */ 
/*    */   public byte peekByte(int i) {
/* 84 */     return peek(i).byteValue();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByteStack
 * JD-Core Version:    0.6.2
 */