/*     */ package org.schema.schine.network.objects.remote;
/*     */ 
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import d;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Quat4f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.schine.network.objects.NetworkObject;
/*     */ import org.schema.schine.network.objects.NetworkTransformation;
/*     */ 
/*     */ public class RemotePhysicsTransform extends RemoteField
/*     */ {
/*     */   static final int len = 36;
/*     */ 
/*     */   public RemotePhysicsTransform(NetworkTransformation paramNetworkTransformation, NetworkObject paramNetworkObject)
/*     */   {
/*  26 */     super(paramNetworkTransformation, paramNetworkObject);
/*     */   }
/*     */   public RemotePhysicsTransform(NetworkTransformation paramNetworkTransformation, boolean paramBoolean) {
/*  29 */     super(paramNetworkTransformation, paramBoolean);
/*     */   }
/*     */ 
/*     */   public int byteLength() {
/*  33 */     return 36;
/*     */   }
/*     */ 
/*     */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*     */   {
/*  38 */     paramInt = new Quat4f(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat());
/*     */ 
/*  40 */     ((NetworkTransformation)get()).getTransformReceive().basis.set(paramInt);
/*     */ 
/*  53 */     ((NetworkTransformation)get()).getTransformReceive().origin.set(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat());
/*     */ 
/*  55 */     if (paramDataInputStream.readBoolean()) {
/*  56 */       ((NetworkTransformation)get()).receivedVil = true;
/*  57 */       ((NetworkTransformation)get()).getLinReceive().set(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat());
/*  58 */       ((NetworkTransformation)get()).getAngReceive().set(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat());
/*     */     }
/*  60 */     if (this.onServer) {
/*  61 */       ((NetworkTransformation)get()).setTimeStampReceive(paramDataInputStream.readLong());
/*     */     }
/*  63 */     ((NetworkTransformation)get()).received = true;
/*     */   }
/*     */ 
/*     */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*     */   {
/*  83 */     Quat4f localQuat4f = new Quat4f();
/*  84 */     d.a(((NetworkTransformation)get()).getTransform().basis, localQuat4f);
/*     */ 
/*  86 */     paramDataOutputStream.writeFloat(localQuat4f.x);
/*  87 */     paramDataOutputStream.writeFloat(localQuat4f.y);
/*  88 */     paramDataOutputStream.writeFloat(localQuat4f.z);
/*  89 */     paramDataOutputStream.writeFloat(localQuat4f.w);
/*     */ 
/* 101 */     paramDataOutputStream.writeFloat(((NetworkTransformation)get()).getTransform().origin.x);
/* 102 */     paramDataOutputStream.writeFloat(((NetworkTransformation)get()).getTransform().origin.y);
/* 103 */     paramDataOutputStream.writeFloat(((NetworkTransformation)get()).getTransform().origin.z);
/*     */ 
/* 105 */     if (((NetworkTransformation)get()).sendVil) {
/* 106 */       paramDataOutputStream.writeBoolean(true);
/* 107 */       paramDataOutputStream.writeFloat(((NetworkTransformation)get()).getLin().x);
/* 108 */       paramDataOutputStream.writeFloat(((NetworkTransformation)get()).getLin().y);
/* 109 */       paramDataOutputStream.writeFloat(((NetworkTransformation)get()).getLin().z);
/*     */ 
/* 111 */       paramDataOutputStream.writeFloat(((NetworkTransformation)get()).getAng().x);
/* 112 */       paramDataOutputStream.writeFloat(((NetworkTransformation)get()).getAng().y);
/* 113 */       paramDataOutputStream.writeFloat(((NetworkTransformation)get()).getAng().z);
/*     */     } else {
/* 115 */       paramDataOutputStream.writeBoolean(false);
/*     */     }
/*     */ 
/* 118 */     if (!this.onServer) {
/* 119 */       paramDataOutputStream.writeLong(((NetworkTransformation)get()).getTimeStamp());
/*     */     }
/*     */ 
/* 122 */     return byteLength();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemotePhysicsTransform
 * JD-Core Version:    0.6.2
 */