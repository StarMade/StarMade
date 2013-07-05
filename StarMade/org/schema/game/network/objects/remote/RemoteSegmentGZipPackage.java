/*     */ package org.schema.game.network.objects.remote;
/*     */ 
/*     */ import jL;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PipedInputStream;
/*     */ import java.io.PipedOutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.util.zip.GZIPInputStream;
/*     */ import java.util.zip.GZIPOutputStream;
/*     */ import mw;
/*     */ import org.schema.common.util.ByteUtil;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.game.common.data.world.SegmentData;
/*     */ import org.schema.schine.network.objects.NetworkObject;
/*     */ import org.schema.schine.network.objects.remote.RemoteField;
/*     */ 
/*     */ public class RemoteSegmentGZipPackage extends RemoteField
/*     */ {
/*  26 */   byte[] bufferBytes = new byte[5];
/*  27 */   byte[] compressedBuffer = new byte[16384];
/*     */ 
/*  29 */   public RemoteSegmentGZipPackage(mw parammw, NetworkObject paramNetworkObject) { super(parammw, paramNetworkObject); }
/*     */ 
/*     */   public int byteLength()
/*     */   {
/*  33 */     throw new IllegalArgumentException("Size unknown for zipped Stream");
/*     */   }
/*     */ 
/*     */   public void fromByteStream(DataInputStream paramDataInputStream, int arg2)
/*     */   {
/*  39 */     ??? = ByteUtil.a(paramDataInputStream);
/*     */ 
/*  43 */     assert (get() != null);
/*  44 */     assert (((mw)get()).a() != null);
/*  45 */     assert (((mw)get()).a() != null);
/*  46 */     if (??? <= 0) {
/*  47 */       System.err.println("Sent empty Segment " + ((mw)get()).a);
/*  48 */       return;
/*     */     }
/*  50 */     assert (??? <= this.compressedBuffer.length) : ???;
/*  51 */     assert (((mw)get()).a() != null);
/*  52 */     for (int i = 0; i < ???; i++) {
/*  53 */       this.compressedBuffer[i] = ((byte)paramDataInputStream.read());
/*     */     }
/*     */ 
/*  56 */     ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(this.compressedBuffer, 0, ???);
/*     */ 
/*  58 */     ((mw)get()).a().reset();
/*     */     try
/*     */     {
/*  66 */       long l = ByteUtil.a(paramDataInputStream = new GZIPInputStream(localByteArrayInputStream));
/*     */ 
/*  68 */       synchronized (((mw)get()).a()) {
/*  69 */         while (paramDataInputStream.read(this.bufferBytes) > 0) {
/*  70 */           byte b1 = this.bufferBytes[0];
/*  71 */           byte b2 = this.bufferBytes[1];
/*  72 */           byte b3 = this.bufferBytes[2];
/*  73 */           short s = ByteUtil.a(this.bufferBytes, 3);
/*     */ 
/*  75 */           ((mw)get()).a().setInfoElementUnsynched(b1, b2, b3, s, false);
/*     */         }
/*  79 */       }
/*     */ ((mw)get()).a().getSegmentBuffer().b((Segment)get());
/*  80 */       ((mw)get()).a(l);
/*     */ 
/*  82 */       paramDataInputStream.close();
/*     */       return; } catch (IOException localIOException) { localIOException.printStackTrace(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*     */   {
/*  95 */     assert (get() != null);
/*     */ 
/*  97 */     Object localObject1 = new PipedOutputStream();
/*  98 */     Object localObject3 = new PipedInputStream((PipedOutputStream)localObject1);
/*  99 */     localObject3 = new BufferedInputStream((InputStream)localObject3);
/*     */ 
/* 106 */     synchronized ((mw)get()) {
/*     */       try { localObject1 = new GZIPOutputStream((OutputStream)localObject1);
/*     */ 
/* 110 */         SegmentData localSegmentData = ((mw)get()).a();
/* 111 */         assert (localSegmentData != null);
/* 112 */         ((GZIPOutputStream)localObject1).write(ByteUtil.a(((mw)get()).a()));
/* 113 */         for (byte b1 = 0; b1 < 16; 
/* 114 */           b1 = (byte)(b1 + 1)) {
/* 115 */           for (byte b2 = 0; b2 < 16; b2 = (byte)(b2 + 1)) {
/* 116 */             for (byte b3 = 0; b3 < 16; b3 = (byte)(b3 + 1)) {
/* 117 */               int j = SegmentData.getInfoIndex(b3, b2, b1);
/* 118 */               if (localSegmentData.contains(j)) {
/* 119 */                 this.bufferBytes[0] = b3;
/* 120 */                 this.bufferBytes[1] = b2;
/* 121 */                 this.bufferBytes[2] = b1;
/* 122 */                 ByteUtil.a(localSegmentData.getType(j), this.bufferBytes, 3);
/* 123 */                 ((GZIPOutputStream)localObject1).write(this.bufferBytes);
/*     */               }
/*     */             }
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 130 */         ((GZIPOutputStream)localObject1).finish();
/* 131 */         ((GZIPOutputStream)localObject1).close();
/*     */       } catch (IOException localIOException)
/*     */       {
/* 134 */         localIOException.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/* 136 */     ??? = 0;
/* 137 */     Object localObject2 = 0;
/*     */ 
/* 139 */     ByteUtil.a(((BufferedInputStream)localObject3).available(), paramDataOutputStream);
/*     */     int i;
/* 141 */     while (( = ((BufferedInputStream)localObject3).read(this.bufferBytes)) > 0) {
/* 142 */       paramDataOutputStream.write(this.bufferBytes, 0, ???);
/* 143 */       localObject2 += ???;
/*     */     }
/*     */ 
/* 146 */     return i + 4;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteSegmentGZipPackage
 * JD-Core Version:    0.6.2
 */