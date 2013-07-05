/*     */ package org.schema.game.network.objects.remote;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.lang.reflect.Constructor;
/*     */ import le;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.schine.network.objects.NetworkObject;
/*     */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*     */ 
/*     */ public class RemoteSegmentPieceBuffer extends RemoteBuffer
/*     */ {
/*     */   private static final int SEGMENT_BUFF_MAX_BATCH = 128;
/*     */   private SegmentController segmentController;
/*     */   private static Constructor staticConstructor;
/*     */ 
/*     */   public RemoteSegmentPieceBuffer(SegmentController paramSegmentController, NetworkObject paramNetworkObject)
/*     */   {
/*  36 */     super(RemoteSegmentPiece.class, paramNetworkObject);
/*  37 */     this.segmentController = paramSegmentController;
/*     */   }
/*     */   public RemoteSegmentPieceBuffer(SegmentController paramSegmentController, boolean paramBoolean) {
/*  40 */     super(RemoteSegmentPiece.class, paramBoolean);
/*  41 */     this.segmentController = paramSegmentController;
/*     */   }
/*     */ 
/*     */   public void cacheConstructor()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void clearReceiveBuffer()
/*     */   {
/*  50 */     getReceiveBuffer().clear();
/*     */   }
/*     */ 
/*     */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*     */   {
/*  55 */     int i = paramDataInputStream.readInt();
/*     */ 
/*  57 */     for (int j = 0; j < i; j++)
/*     */     {
/*     */       RemoteSegmentPiece localRemoteSegmentPiece;
/*  58 */       (
/*  59 */         localRemoteSegmentPiece = (RemoteSegmentPiece)staticConstructor.newInstance(new Object[] { null, this.segmentController, Boolean.valueOf(this.onServer) }))
/*  59 */         .fromByteStream(paramDataInputStream, paramInt);
/*  60 */       getReceiveBuffer().add(localRemoteSegmentPiece);
/*     */     }
/*     */   }
/*     */ 
/*     */   public int toByteStream2(DataOutputStream paramDataOutputStream)
/*     */   {
/*  73 */     int i = 0;
/*  74 */     synchronized ((ObjectArrayList)get())
/*     */     {
/*  76 */       paramDataOutputStream.writeInt(((ObjectArrayList)get()).size());
/*  77 */       i += 4;
/*     */ 
/*  80 */       for (RemoteSegmentPiece localRemoteSegmentPiece : (ObjectArrayList)get()) {
/*  81 */         i += localRemoteSegmentPiece.toByteStream(paramDataOutputStream);
/*     */       }
/*     */ 
/*  84 */       ((ObjectArrayList)get()).clear();
/*     */     }
/*     */ 
/*  87 */     return i;
/*     */   }
/*     */ 
/*     */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*     */   {
/*  93 */     int i = Math.min(128, ((ObjectArrayList)get()).size());
/*     */ 
/*  95 */     int j = 4;
/*     */ 
/*  97 */     paramDataOutputStream.writeInt(i);
/*     */ 
/*  99 */     for (int k = 0; k < i; k++) {
/* 100 */       RemoteSegmentPiece localRemoteSegmentPiece = (RemoteSegmentPiece)((ObjectArrayList)get()).remove(0);
/* 101 */       j += localRemoteSegmentPiece.toByteStream(paramDataOutputStream);
/* 102 */       localRemoteSegmentPiece.setChanged(false);
/*     */     }
/*     */ 
/* 107 */     this.keepChanged = (!((ObjectArrayList)get()).isEmpty());
/*     */ 
/* 115 */     return j;
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*     */     try
/*     */     {
/*  21 */       if (staticConstructor == null) {
/*  22 */         staticConstructor = RemoteSegmentPiece.class.getConstructor(new Class[] { le.class, SegmentController.class, Boolean.TYPE });
/*     */       }
/*     */ 
/*     */       return;
/*     */     }
/*     */     catch (SecurityException localSecurityException)
/*     */     {
/*  31 */       localSecurityException.printStackTrace();
/*     */ 
/*  27 */       if (!$assertionsDisabled) throw new AssertionError(); return;
/*     */     }
/*     */     catch (NoSuchMethodException localNoSuchMethodException)
/*     */     {
/*  31 */       localNoSuchMethodException.printStackTrace();
/*     */ 
/*  30 */       if (!$assertionsDisabled) throw new AssertionError();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteSegmentPieceBuffer
 * JD-Core Version:    0.6.2
 */