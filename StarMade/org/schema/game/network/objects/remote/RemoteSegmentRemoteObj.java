/*     */ package org.schema.game.network.objects.remote;
/*     */ 
/*     */ import Q;
/*     */ import ct;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
/*     */ import jL;
/*     */ import jY;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import lf;
/*     */ import mw;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.controller.SegmentOutOfBoundsException;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.game.common.data.world.SegmentData;
/*     */ import org.schema.schine.network.NetworkStateContainer;
/*     */ import org.schema.schine.network.objects.NetworkObject;
/*     */ import org.schema.schine.network.objects.Sendable;
/*     */ import org.schema.schine.network.objects.remote.RemoteField;
/*     */ import q;
/*     */ import tR;
/*     */ import vg;
/*     */ 
/*     */ public class RemoteSegmentRemoteObj extends RemoteField
/*     */ {
/*  28 */   private static ByteArrayOutputStream tmpArrayBuffer = new ByteArrayOutputStream(102400);
/*  29 */   private static DataOutputStream tmpBuffer = new DataOutputStream(tmpArrayBuffer);
/*     */ 
/*  31 */   private static ThreadLocal threadLocal = new RemoteSegmentRemoteObj.1();
/*     */ 
/*     */   public RemoteSegmentRemoteObj(NetworkObject paramNetworkObject)
/*     */   {
/*  42 */     super(new lf(), paramNetworkObject);
/*     */   }
/*     */ 
/*     */   public RemoteSegmentRemoteObj(lf paramlf, NetworkObject paramNetworkObject) {
/*  46 */     super(paramlf, paramNetworkObject);
/*     */   }
/*     */   public RemoteSegmentRemoteObj(boolean paramBoolean) {
/*  49 */     super(new lf(), paramBoolean);
/*     */   }
/*     */ 
/*     */   public RemoteSegmentRemoteObj(lf paramlf, boolean paramBoolean) {
/*  53 */     super(paramlf, paramBoolean);
/*     */   }
/*     */ 
/*     */   public int byteLength()
/*     */   {
/*  58 */     return 0;
/*     */   }
/*     */ 
/*     */   public void fromByteStream(DataInputStream paramDataInputStream, int arg2)
/*     */   {
/*  65 */     ??? = paramDataInputStream.readInt();
/*  66 */     int i = paramDataInputStream.readInt();
/*  67 */     int j = paramDataInputStream.readInt();
/*  68 */     int k = paramDataInputStream.readInt();
/*  69 */     int m = paramDataInputStream.readInt();
/*  70 */     Object localObject1 = (byte[])threadLocal.get();
/*  71 */     paramDataInputStream.readFully((byte[])localObject1, 0, m);
/*  72 */     paramDataInputStream = new ByteArrayInputStream((byte[])localObject1);
/*  73 */     paramDataInputStream = new DataInputStream(paramDataInputStream);
/*     */ 
/*  77 */     if (this.onServer)
/*  78 */       localObject1 = (Sendable)vg.a.getLocalAndRemoteObjectContainer().getLocalObjects().get(???);
/*     */     else {
/*  80 */       localObject1 = (Sendable)ct.a.getLocalAndRemoteObjectContainer().getLocalObjects().get(???);
/*     */     }
/*     */ 
/*  83 */     if ((localObject1 == null) || (!(localObject1 instanceof SegmentController))) {
/*  84 */       System.err.println("[ERROR][RemoteSegmantRemoteObj] could not find segmentController: " + ??? + ": " + localObject1 + " -> dumping stream");
/*  85 */       Q.a.a(paramDataInputStream, m, true);
/*  86 */       return;
/*     */     }
/*     */ 
/*  90 */     localObject1 = (Q)( = (SegmentController)localObject1)
/*  90 */       .getSegmentProvider();
/*     */ 
/*  92 */     ((lf)get()).jdField_a_of_type_Q = new q(i, j, k);
/*     */ 
/*  95 */     int n = 0;
/*  96 */     synchronized (((Q)localObject1).a()) {
/*  97 */       for (int i1 = 0; i1 < ((Q)localObject1).a().size(); i1++)
/*     */       {
/*  99 */         if (((Segment)((Q)localObject1).a().get(i1)).jdField_a_of_type_Q.a(i, j, k))
/*     */         {
/*     */           mw localmw;
/* 102 */           if ((
/* 102 */             localmw = (mw)((Q)localObject1).a().remove(i1))
/* 102 */             .a() == null) {
/* 103 */             ???.getSegmentProvider().a()
/* 105 */               .assignData(localmw);
/*     */           }
/*     */ 
/* 115 */           localmw.a(paramDataInputStream, m, true);
/*     */ 
/* 120 */           synchronized (((Q)localObject1).a()) {
/* 121 */             assert (localmw != null);
/* 122 */             ((Q)localObject1).a().add(localmw);
/*     */           }
/* 124 */           n = 1;
/* 125 */           break;
/*     */         }
/*     */       }
/*     */     }
/* 129 */     if (n == 0)
/*     */     {
/* 135 */       Q.a.a(paramDataInputStream, m, true);
/*     */     }
/*     */   }
/*     */ 
/*     */   public int toByteStream(DataOutputStream paramDataOutputStream) {
/* 143 */     SegmentController localSegmentController = ((lf)get()).jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController;
/*     */ 
/* 148 */     int i = 0;
/* 149 */     assert (tmpArrayBuffer.size() == 0);
/*     */ 
/* 151 */     int j = 0;
/*     */     Object localObject1;
/*     */     Object localObject2;
/*     */     try {
/* 154 */       if ((
/* 154 */         localObject1 = localSegmentController.getSegmentBuffer().a(((lf)get()).jdField_a_of_type_Q)) != null)
/*     */       {
/* 156 */         localObject2 = (mw)localObject1;
/* 156 */         System.currentTimeMillis(); mw.d();
/* 157 */         assert (((mw)localObject2).jdField_a_of_type_Q.equals(((lf)get()).jdField_a_of_type_Q)) : (" serializing " + ((lf)get()).jdField_a_of_type_Q + "; toSerialize " + ((mw)localObject2).jdField_a_of_type_Q);
/* 158 */         i = ((mw)localObject2).a(tmpBuffer);
/* 159 */         j = 1;
/*     */       }
/*     */     } catch (Exception localException) { localObject1 = null;
/*     */ 
/* 163 */       localException.printStackTrace();
/*     */     }
/*     */ 
/* 165 */     if (j == 0)
/*     */     {
/*     */       try
/*     */       {
/* 169 */         localObject2 = new q(((lf)get()).jdField_a_of_type_Q);
/* 170 */         localObject1 = (mw)((tR)localSegmentController.getSegmentProvider()).a(((lf)get()).jdField_a_of_type_Q.a, ((lf)get()).jdField_a_of_type_Q.b, ((lf)get()).jdField_a_of_type_Q.c);
/*     */ 
/* 173 */         assert (((mw)localObject1).jdField_a_of_type_Q.equals(((lf)get()).jdField_a_of_type_Q)) : (" REQUESTED " + ((lf)get()).jdField_a_of_type_Q + "; BUT GOT " + ((mw)localObject1).jdField_a_of_type_Q + "; ORIGINAL: " + localObject2);
/* 174 */         System.currentTimeMillis(); mw.d();
/* 175 */         i = ((mw)localObject1).a(tmpBuffer);
/*     */       }
/*     */       catch (InterruptedException localInterruptedException)
/*     */       {
/* 180 */         localInterruptedException.printStackTrace();
/*     */       }
/*     */       catch (SegmentOutOfBoundsException localSegmentOutOfBoundsException)
/*     */       {
/* 180 */         localSegmentOutOfBoundsException.printStackTrace();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 184 */     assert (i == tmpArrayBuffer.size()) : ("LEN " + i + "; written: " + tmpBuffer.size());
/* 185 */     assert (i > 0);
/* 186 */     paramDataOutputStream.writeInt(localSegmentController.getId());
/* 187 */     paramDataOutputStream.writeInt(((lf)get()).jdField_a_of_type_Q.a);
/* 188 */     paramDataOutputStream.writeInt(((lf)get()).jdField_a_of_type_Q.b);
/* 189 */     paramDataOutputStream.writeInt(((lf)get()).jdField_a_of_type_Q.c);
/* 190 */     paramDataOutputStream.writeInt(i);
/* 191 */     tmpArrayBuffer.writeTo(paramDataOutputStream);
/* 192 */     tmpArrayBuffer.reset();
/*     */ 
/* 197 */     return i + 20;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteSegmentRemoteObj
 * JD-Core Version:    0.6.2
 */