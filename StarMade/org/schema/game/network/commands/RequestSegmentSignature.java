/*    */ package org.schema.game.network.commands;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*    */ import mw;
/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ import org.schema.schine.network.Command;
/*    */ import org.schema.schine.network.NetworkStateContainer;
/*    */ import org.schema.schine.network.client.ClientStateInterface;
/*    */ import org.schema.schine.network.server.ServerProcessor;
/*    */ import org.schema.schine.network.server.ServerStateInterface;
/*    */ import tR;
/*    */ import vg;
/*    */ 
/*    */ public class RequestSegmentSignature extends Command
/*    */ {
/*    */   public RequestSegmentSignature()
/*    */   {
/* 16 */     this.mode = 1;
/*    */   }
/*    */ 
/*    */   public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
/*    */   {
/* 23 */     paramClientStateInterface.arrivedReturn(paramShort, paramArrayOfObject);
/*    */   }
/*    */ 
/*    */   public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
/*    */   {
/* 31 */     Object localObject = (vg)paramServerStateInterface;
/*    */ 
/* 33 */     int i = ((Integer)paramArrayOfObject[0]).intValue();
/*    */ 
/* 35 */     int j = ((Integer)paramArrayOfObject[1]).intValue();
/* 36 */     int k = ((Integer)paramArrayOfObject[2]).intValue();
/* 37 */     paramArrayOfObject = ((Integer)paramArrayOfObject[3]).intValue();
/*    */     try
/*    */     {
/* 41 */       if ((
/* 41 */         localObject = (SegmentController)((vg)localObject).getLocalAndRemoteObjectContainer().getLocalObjects().get(i)) == null)
/*    */       {
/* 42 */         throw new IllegalArgumentException("[SERVER] Could NOT find the segment controller ID " + i + ". This CAN happen, when the round gets restarted and there is still some segements in queue");
/*    */       }
/*    */       mw localmw;
/* 47 */       if ((
/* 47 */         localmw = (mw)((SegmentController)localObject).getSegmentFromCache(j, k, paramArrayOfObject)) != null)
/*    */       {
/* 48 */         System.currentTimeMillis(); mw.d();
/*    */ 
/* 50 */         createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { Long.valueOf(localmw.a()), Boolean.valueOf(localmw.g()) }); return;
/* 55 */       }
/*    */ paramArrayOfObject = (mw)((tR)((SegmentController)localObject).getSegmentProvider()).a(j, k, paramArrayOfObject);
/* 55 */       System.currentTimeMillis(); mw.d();
/*    */ 
/* 57 */       createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { Long.valueOf(paramArrayOfObject.a()), Boolean.valueOf(paramArrayOfObject.g()) });
/*    */       return; } catch (Exception localException) {
/* 61 */       createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { Integer.valueOf(-1), Boolean.valueOf(true) });
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.commands.RequestSegmentSignature
 * JD-Core Version:    0.6.2
 */