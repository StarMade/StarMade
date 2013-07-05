/*     */ package org.schema.game.network.objects.remote;
/*     */ 
/*     */ import jL;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import le;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.schine.network.objects.NetworkObject;
/*     */ import org.schema.schine.network.objects.remote.RemoteField;
/*     */ import org.schema.schine.network.server.ServerState;
/*     */ import q;
/*     */ 
/*     */ public class RemoteSegmentPiece extends RemoteField
/*     */ {
/*     */   private SegmentController segmentController;
/*     */ 
/*     */   public RemoteSegmentPiece(le paramle, SegmentController paramSegmentController, NetworkObject paramNetworkObject)
/*     */   {
/*  23 */     super(paramle, paramNetworkObject);
/*  24 */     this.segmentController = paramSegmentController;
/*     */   }
/*     */   public RemoteSegmentPiece(le paramle, SegmentController paramSegmentController, boolean paramBoolean) {
/*  27 */     super(paramle, paramBoolean);
/*  28 */     this.segmentController = paramSegmentController;
/*     */   }
/*     */ 
/*     */   public int byteLength() {
/*  32 */     return 22;
/*     */   }
/*     */ 
/*     */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*     */   {
/*     */     int i;
/*     */     int j;
/*  48 */     if ((
/*  48 */       paramInt = paramDataInputStream.readByte()) == 0)
/*     */     {
/*  49 */       paramInt = paramDataInputStream.readByte();
/*  50 */       i = paramDataInputStream.readByte();
/*  51 */       j = paramDataInputStream.readByte();
/*  52 */     } else if (paramInt == 1) {
/*  53 */       paramInt = paramDataInputStream.readShort();
/*  54 */       i = paramDataInputStream.readShort();
/*  55 */       j = paramDataInputStream.readShort();
/*     */     } else {
/*  57 */       paramInt = paramDataInputStream.readInt();
/*  58 */       i = paramDataInputStream.readInt();
/*  59 */       j = paramDataInputStream.readInt();
/*     */     }
/*     */ 
/*  63 */     byte[] arrayOfByte = new byte[3];
/*  64 */     paramDataInputStream.readFully(arrayOfByte);
/*     */ 
/*  69 */     if ((
/*  69 */       paramDataInputStream = this.segmentController.getSegmentBuffer().a(new q(paramInt, i, j), this.segmentController.getState() instanceof ServerState)) != null)
/*     */     {
/*  70 */       paramDataInputStream.a(arrayOfByte);
/*  71 */       set(paramDataInputStream);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void writeDynamicPosition(int paramInt1, int paramInt2, int paramInt3, DataOutputStream paramDataOutputStream)
/*     */   {
/*  78 */     if ((paramInt1 >= -128) && (paramInt2 >= -128) && (paramInt3 >= -128) && (paramInt1 <= -128) && (paramInt2 <= -128) && (paramInt3 <= 127))
/*     */     {
/*  80 */       paramDataOutputStream.writeByte(0);
/*  81 */       paramDataOutputStream.writeByte(paramInt1);
/*  82 */       paramDataOutputStream.writeByte(paramInt2);
/*  83 */       paramDataOutputStream.writeByte(paramInt3); return;
/*  84 */     }if ((paramInt1 >= -32768) && (paramInt2 >= -32768) && (paramInt3 >= -32768) && (paramInt1 <= -32768) && (paramInt2 <= -32768) && (paramInt3 <= 32767))
/*     */     {
/*  86 */       paramDataOutputStream.writeByte(1);
/*  87 */       paramDataOutputStream.writeShort(paramInt1);
/*  88 */       paramDataOutputStream.writeShort(paramInt2);
/*  89 */       paramDataOutputStream.writeShort(paramInt3); return;
/*     */     }
/*  91 */     paramDataOutputStream.writeByte(2);
/*  92 */     paramDataOutputStream.writeInt(paramInt1);
/*  93 */     paramDataOutputStream.writeInt(paramInt2);
/*  94 */     paramDataOutputStream.writeInt(paramInt3);
/*     */   }
/*     */ 
/*     */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*     */   {
/* 102 */     assert (get() != null);
/* 103 */     writeDynamicPosition(((le)get()).a + ((le)get()).a().a.a, ((le)get()).b + ((le)get()).a().a.b, ((le)get()).c + ((le)get()).a().a.c, paramDataOutputStream);
/*     */ 
/* 109 */     paramDataOutputStream.write(((le)get()).a());
/*     */ 
/* 111 */     return 1;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteSegmentPiece
 * JD-Core Version:    0.6.2
 */