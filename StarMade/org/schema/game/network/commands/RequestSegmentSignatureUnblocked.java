/*    */ package org.schema.game.network.commands;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*    */ import java.io.PrintStream;
/*    */ import ka;
/*    */ import kc;
/*    */ import lf;
/*    */ import mw;
/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ import org.schema.game.network.objects.NetworkSegmentProvider;
/*    */ import org.schema.game.network.objects.remote.RemoteSegmentSignature;
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
/*    */ public class RequestSegmentSignatureUnblocked extends Command
/*    */ {
/*    */   public RequestSegmentSignatureUnblocked()
/*    */   {
/* 21 */     this.mode = 0;
/*    */   }
/*    */ 
/*    */   public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void serverProcess(ServerProcessor paramServerProcessor, Object[] arg2, ServerStateInterface paramServerStateInterface, short paramShort)
/*    */   {
/* 34 */     int i = ((Integer)???[0]).intValue();
/*    */ 
/* 38 */     int j = ((Integer)???[1]).intValue();
/* 39 */     int k = ((Integer)???[2]).intValue();
/* 40 */     ??? = ((Integer)???[3]).intValue();
/*    */     try
/*    */     {
/*    */       kc localkc;
/*    */       ka localka;
/* 51 */       if ((
/* 51 */         localka = (
/* 49 */         localkc = (kc)paramServerProcessor.getClient()
/* 48 */         .getLocalAndRemoteObjectContainer().getLocalObjects().get(i))
/* 49 */         .a()) == null)
/*    */       {
/* 52 */         throw new IllegalArgumentException("[SERVER] Could NOT find the segment controller ID " + i + ". This CAN happen, when the round gets restarted and there is still some segements in queue");
/*    */       }
/*    */       mw localmw;
/* 56 */       if ((
/* 56 */         localmw = (mw)localka.getSegmentFromCache(j, k, ???)) != null)
/*    */       {
/* 57 */         createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { Long.valueOf(localmw.a()), Boolean.valueOf(localmw.g()) });
/* 58 */         paramServerProcessor = localkc.a();
/* 59 */         System.currentTimeMillis(); mw.d();
/*    */ 
/* 61 */         synchronized (paramServerProcessor) {
/* 62 */           paramServerProcessor.signatureBuffer.add(new RemoteSegmentSignature(new lf(new q(localmw.a), localmw.a(), localmw.a().getId(), localmw.g()), paramServerProcessor));
/*    */ 
/* 65 */           return; } 
/*    */       }
/* 66 */       paramServerProcessor = localkc.a();
/*    */ 
/* 70 */       ((vg)paramServerStateInterface).a().b(localka, new q(j, k, ???), paramServerProcessor);
/*    */       return;
/*    */     }
/*    */     catch (Exception localException)
/*    */     {
/* 76 */       localException.printStackTrace();
/*    */ 
/* 75 */       System.err.println("Exception catched for ID: " + i);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.commands.RequestSegmentSignatureUnblocked
 * JD-Core Version:    0.6.2
 */