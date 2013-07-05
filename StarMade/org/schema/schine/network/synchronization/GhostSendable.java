/*    */ package org.schema.schine.network.synchronization;
/*    */ 
/*    */ import org.schema.schine.network.objects.Sendable;
/*    */ 
/*    */ public class GhostSendable
/*    */ {
/*    */   public final long timeDeleted;
/*    */   public final Sendable sendable;
/*    */ 
/*    */   public GhostSendable(long paramLong, Sendable paramSendable)
/*    */   {
/* 15 */     this.timeDeleted = paramLong;
/* 16 */     this.sendable = paramSendable;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.synchronization.GhostSendable
 * JD-Core Version:    0.6.2
 */