/*    */ package org.schema.schine.network;
/*    */ 
/*    */ import java.util.Observable;
/*    */ 
/*    */ public class Pinger extends Observable
/*    */ {
/*    */   private static byte[] pingBytes;
/*    */   private static byte[] pongBytes;
/*    */ 
/*    */   public byte[] createPing()
/*    */   {
/* 17 */     return pingBytes;
/*    */   }
/*    */ 
/*    */   public byte[] createPong()
/*    */   {
/* 23 */     return pongBytes;
/*    */   }
/*    */ 
/*    */   static
/*    */   {
/*  6 */     (
/*  8 */       Pinger.pingBytes = new byte[2])[
/*  8 */       0] = 23;
/*  9 */     pingBytes[1] = -1;
/*    */ 
/* 11 */     (
/* 13 */       Pinger.pongBytes = new byte[2])[
/* 13 */       0] = 23;
/* 14 */     pongBytes[1] = -2;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.Pinger
 * JD-Core Version:    0.6.2
 */