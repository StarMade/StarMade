/*    */ package org.schema.game.network.commands;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*    */ import ka;
/*    */ import kc;
/*    */ import lf;
/*    */ import mw;
/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ import org.schema.game.network.objects.NetworkSegmentProvider;
/*    */ import org.schema.game.network.objects.remote.RemoteSegmentRemoteObj;
/*    */ import org.schema.game.server.controller.GameServerController;
/*    */ import org.schema.schine.network.Command;
/*    */ import org.schema.schine.network.NetworkStateContainer;
/*    */ import org.schema.schine.network.RegisteredClientOnServer;
/*    */ import org.schema.schine.network.client.ClientStateInterface;
/*    */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*    */ import org.schema.schine.network.server.ServerProcessor;
/*    */ import org.schema.schine.network.server.ServerStateInterface;
/*    */ import q;
/*    */ import vg;
/*    */ 
/*    */ public class RequestSegmentDataUnblocked extends Command
/*    */ {
/*    */   public RequestSegmentDataUnblocked()
/*    */   {
/* 21 */     this.mode = 0;
/*    */   }
/*    */ 
/*    */   public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface arg3, short paramShort)
/*    */   {
/* 33 */     paramShort = ((Integer)paramArrayOfObject[0]).intValue();
/*    */ 
/* 36 */     int i = ((Integer)paramArrayOfObject[1]).intValue();
/* 37 */     int j = ((Integer)paramArrayOfObject[2]).intValue();
/* 38 */     paramArrayOfObject = ((Integer)paramArrayOfObject[3]).intValue();
/*    */     try
/*    */     {
/* 44 */       paramServerProcessor = null;
/*    */       ka localka;
/* 48 */       if ((
/* 48 */         localka = (
/* 45 */         paramServerProcessor = (kc)paramServerProcessor.getClient()
/* 44 */         .getLocalAndRemoteObjectContainer().getLocalObjects().get(paramShort))
/* 45 */         .a()) == null)
/*    */       {
/* 49 */         throw new IllegalArgumentException("[SERVER] Could NOT find the segment controller ID " + paramShort + ". This CAN happen, when the round gets restarted and there is still some segements in queue");
/*    */       }
/*    */ 
/* 54 */       if ((
/* 54 */         paramShort = (mw)localka.getSegmentFromCache(i, j, paramArrayOfObject)) != null)
/*    */       {
/* 56 */         paramServerProcessor = paramServerProcessor.a();
/*    */ 
/* 58 */         (
/* 59 */           paramArrayOfObject = new lf(new q(paramShort.a), paramShort.a(), paramShort.a().getId(), paramShort.g())).a = 
/* 59 */           localka;
/* 60 */         synchronized (paramServerProcessor) {
/* 61 */           paramServerProcessor.segmentBuffer.add(new RemoteSegmentRemoteObj(paramArrayOfObject, paramServerProcessor));
/* 62 */           return; } 
/* 63 */       }paramServerProcessor = paramServerProcessor.a();
/*    */ 
/* 67 */       ((vg)???).a().a(localka, new q(i, j, paramArrayOfObject), paramServerProcessor);
/*    */       return;
/*    */     }
/*    */     catch (Exception localException)
/*    */     {
/* 72 */       localException.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.commands.RequestSegmentDataUnblocked
 * JD-Core Version:    0.6.2
 */