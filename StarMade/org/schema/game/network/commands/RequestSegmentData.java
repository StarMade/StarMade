/*     */ package org.schema.game.network.commands;
/*     */ 
/*     */ import ct;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import jL;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Map;
/*     */ import mw;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.world.DeserializationException;
/*     */ import org.schema.schine.network.Command;
/*     */ import org.schema.schine.network.Header;
/*     */ import org.schema.schine.network.NetworkProcessor;
/*     */ import org.schema.schine.network.NetworkStateContainer;
/*     */ import org.schema.schine.network.RegisteredClientOnServer;
/*     */ import org.schema.schine.network.client.ClientStateInterface;
/*     */ import org.schema.schine.network.server.ServerProcessor;
/*     */ import org.schema.schine.network.server.ServerStateInterface;
/*     */ import q;
/*     */ import tR;
/*     */ import vg;
/*     */ 
/*     */ public class RequestSegmentData extends Command
/*     */ {
/*     */   protected static final boolean USE_IMMEDIATE_REQUEST = true;
/*     */ 
/*     */   public RequestSegmentData()
/*     */   {
/*  22 */     this.mode = 1;
/*     */   }
/*     */ 
/*     */   public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
/*     */   {
/*  33 */     paramArrayOfObject = ((ct)paramClientStateInterface).a(paramShort);
/*     */ 
/*  36 */     assert (paramArrayOfObject != null) : ("not found: " + paramShort + ": " + ((ct)paramClientStateInterface).a());
/*     */ 
/*  38 */     assert ((paramArrayOfObject.g()) || (paramArrayOfObject.a() != null));
/*     */     try
/*     */     {
/*  43 */       paramArrayOfObject.a(paramClientStateInterface.getProcessor().getIn(), -1, false);
/*  44 */       System.currentTimeMillis(); mw.d();
/*     */     }
/*     */     catch (DeserializationException localDeserializationException)
/*     */     {
/*  48 */       localDeserializationException.printStackTrace();
/*     */ 
/*  47 */       System.err.println("CRITICAL: SERVER PROVIDED CORRUPT SEGEMNT");
/*     */     }
/*     */ 
/*  50 */     if ((paramClientStateInterface.getProcessor().getIn().available() > 0) && 
/*  51 */       (!$assertionsDisabled)) throw new AssertionError(" Failed to fully deserialize " + paramArrayOfObject.a + "; still available: " + paramClientStateInterface.getProcessor().getIn().available());
/*     */   }
/*     */ 
/*     */   public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface arg3, short arg4)
/*     */   {
/*  68 */     paramArrayOfObject = new Header(RequestSegmentData.class, ???.getId(), paramServerProcessor.getClient().getId(), ???, (byte)123);
/*     */ 
/*  72 */     ??? = (vg)???;
/*     */ 
/*  74 */     ??? = paramServerProcessor.getIn().readInt();
/*  75 */     int i = paramServerProcessor.getIn().readInt();
/*  76 */     int j = paramServerProcessor.getIn().readInt();
/*  77 */     int k = paramServerProcessor.getIn().readInt();
/*     */ 
/*  84 */     if (( = (SegmentController)???.getLocalAndRemoteObjectContainer().getLocalObjects().get(???)) != null)
/*     */     {
/*  85 */       if (???.getSegmentBuffer().a(i, j, k)) {
/*  86 */         ??? = (mw)???.getSegmentBuffer().a(i, j, k);
/*     */ 
/*  89 */         synchronized (paramServerProcessor.getLock()) {
/*  90 */           assert ((???.a.a == i) && (???.a.b == j) && (???.a.c == k)) : (" serializing " + i + ", " + j + ", " + k + "; toSerialize " + ???.a);
/*     */ 
/*  92 */           paramArrayOfObject.write(paramServerProcessor.getOut());
/*     */ 
/*  94 */           ???.a(paramServerProcessor.getOut());
/*  95 */           paramServerProcessor.flushDoubleOutBuffer();
/*  96 */           return;
/*     */         }
/*     */       }
/*  98 */       ??? = (mw)((tR)???.getSegmentProvider()).a(i, j, i);
/*     */ 
/* 104 */       synchronized (paramServerProcessor.getLock()) {
/* 105 */         assert ((???.a.a == i) && (???.a.b == j) && (???.a.c == k)) : (" serializing " + i + ", " + j + ", " + k + "; toSerialize " + ???.a);
/*     */ 
/* 107 */         paramArrayOfObject.write(paramServerProcessor.getOut());
/* 108 */         ???.a(paramServerProcessor.getOut());
/* 109 */         paramServerProcessor.flushDoubleOutBuffer();
/* 110 */         return;
/*     */       }
/*     */     }
/* 113 */     System.err.println("[SERVER][ERROR] Exception: Requested SegmentController not found " + ??? + ". sending back dummy");
/*     */ 
/* 117 */     synchronized (paramServerProcessor.getLock()) {
/* 118 */       ??? = new mw(null);
/* 119 */       paramArrayOfObject.write(paramServerProcessor.getOut());
/* 120 */       ???.a(paramServerProcessor.getOut());
/* 121 */       paramServerProcessor.flushDoubleOutBuffer();
/*     */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void writeAndCommitParametriziedCommand(Object[] paramArrayOfObject, int paramInt1, int arg3, short paramShort, NetworkProcessor paramNetworkProcessor)
/*     */   {
/* 133 */     assert ((paramNetworkProcessor.getState() instanceof ct));
/*     */ 
/* 135 */     paramArrayOfObject = new Header(getId(), paramShort, (byte)123);
/* 136 */     paramInt1 = null;
/*     */ 
/* 138 */     synchronized (((ct)paramNetworkProcessor.getState()).a())
/*     */     {
/* 140 */       paramInt1 = (mw)((ct)paramNetworkProcessor.getState()).a().get(Short.valueOf(paramShort));
/*     */     }
/*     */ 
/* 144 */     assert (paramInt1 != null);
/*     */ 
/* 147 */     synchronized (paramNetworkProcessor.getLock())
/*     */     {
/* 149 */       paramArrayOfObject.write(paramNetworkProcessor.getOut());
/*     */ 
/* 151 */       paramNetworkProcessor.getOut().writeInt(paramInt1.a().getId());
/*     */ 
/* 153 */       System.currentTimeMillis(); mw.d();
/* 154 */       paramNetworkProcessor.getOut().writeInt(paramInt1.a.a);
/* 155 */       paramNetworkProcessor.getOut().writeInt(paramInt1.a.b);
/* 156 */       paramNetworkProcessor.getOut().writeInt(paramInt1.a.c);
/* 157 */       paramNetworkProcessor.flushDoubleOutBuffer();
/*     */       return;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.commands.RequestSegmentData
 * JD-Core Version:    0.6.2
 */