/*     */ package org.schema.schine.network.objects;
/*     */ 
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class NetworkTransformation
/*     */ {
/*     */   private Transform transform;
/*     */   private Transform transformReceive;
/*  11 */   private Vector3f lin = new Vector3f();
/*  12 */   private Vector3f ang = new Vector3f();
/*  13 */   private Vector3f linReceive = new Vector3f();
/*  14 */   private Vector3f angReceive = new Vector3f();
/*     */   private long timeStamp;
/*     */   private long timeStampReceive;
/*     */   public boolean received;
/*     */   public boolean sendVil;
/*     */   public boolean receivedVil;
/*     */ 
/*     */   public NetworkTransformation()
/*     */   {
/*  22 */     this.transform = new Transform();
/*  23 */     this.transformReceive = new Transform();
/*     */   }
/*     */ 
/*     */   public NetworkTransformation(Transform paramTransform, long paramLong)
/*     */   {
/*  29 */     this.transform = paramTransform;
/*  30 */     this.transformReceive = new Transform(paramTransform);
/*  31 */     this.timeStamp = paramLong;
/*  32 */     this.timeStampReceive = paramLong;
/*     */   }
/*     */ 
/*     */   public long getTimeStamp()
/*     */   {
/*  39 */     return this.timeStamp;
/*     */   }
/*     */ 
/*     */   public Transform getTransform()
/*     */   {
/*  45 */     return this.transform;
/*     */   }
/*     */ 
/*     */   public void setTimeStamp(long paramLong)
/*     */   {
/*  51 */     this.timeStamp = paramLong;
/*     */   }
/*     */ 
/*     */   public void setTransform(Transform paramTransform)
/*     */   {
/*  57 */     this.transform = paramTransform;
/*     */   }
/*     */ 
/*     */   public Transform getTransformReceive()
/*     */   {
/*  64 */     return this.transformReceive;
/*     */   }
/*     */ 
/*     */   public void setTransformReceive(Transform paramTransform)
/*     */   {
/*  71 */     this.transformReceive = paramTransform;
/*     */   }
/*     */ 
/*     */   public long getTimeStampReceive()
/*     */   {
/*  78 */     return this.timeStampReceive;
/*     */   }
/*     */ 
/*     */   public void setTimeStampReceive(long paramLong)
/*     */   {
/*  85 */     this.timeStampReceive = paramLong;
/*     */   }
/*     */ 
/*     */   public Vector3f getLin()
/*     */   {
/*  92 */     return this.lin;
/*     */   }
/*     */ 
/*     */   public void setLin(Vector3f paramVector3f)
/*     */   {
/*  99 */     this.lin = paramVector3f;
/*     */   }
/*     */ 
/*     */   public Vector3f getAng()
/*     */   {
/* 106 */     return this.ang;
/*     */   }
/*     */ 
/*     */   public void setAng(Vector3f paramVector3f)
/*     */   {
/* 113 */     this.ang = paramVector3f;
/*     */   }
/*     */ 
/*     */   public Vector3f getLinReceive()
/*     */   {
/* 120 */     return this.linReceive;
/*     */   }
/*     */ 
/*     */   public void setLinReceive(Vector3f paramVector3f)
/*     */   {
/* 127 */     this.linReceive = paramVector3f;
/*     */   }
/*     */ 
/*     */   public Vector3f getAngReceive()
/*     */   {
/* 134 */     return this.angReceive;
/*     */   }
/*     */ 
/*     */   public void setAngReceive(Vector3f paramVector3f)
/*     */   {
/* 141 */     this.angReceive = paramVector3f;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.NetworkTransformation
 * JD-Core Version:    0.6.2
 */