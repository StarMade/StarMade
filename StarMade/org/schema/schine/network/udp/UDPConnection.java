/*    */ package org.schema.schine.network.udp;
/*    */ 
/*    */ public class UDPConnection
/*    */ {
/* 10 */   short sequenceNumber = -32768;
/*    */ 
/* 12 */   private final short[] ackBitField = new short[32];
/*    */ 
/*    */   public static boolean sequenceMoreRecent(int paramInt1, int paramInt2, int paramInt3)
/*    */   {
/*  6 */     return ((paramInt1 > paramInt2) && (paramInt1 - paramInt2 <= paramInt3 / 2)) || ((paramInt2 > paramInt1) && (paramInt2 - paramInt1 > paramInt3 / 2));
/*    */   }
/*    */ 
/*    */   public void receive()
/*    */   {
/*    */   }
/*    */ 
/*    */   public void send(byte[] paramArrayOfByte)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.udp.UDPConnection
 * JD-Core Version:    0.6.2
 */