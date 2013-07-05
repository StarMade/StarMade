/*     */ package com.bulletphysics.collision.narrowphase;
/*     */ 
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class ManifoldPoint
/*     */ {
/*  36 */   public final Vector3f localPointA = new Vector3f();
/*  37 */   public final Vector3f localPointB = new Vector3f();
/*  38 */   public final Vector3f positionWorldOnB = new Vector3f();
/*     */ 
/*  40 */   public final Vector3f positionWorldOnA = new Vector3f();
/*  41 */   public final Vector3f normalWorldOnB = new Vector3f();
/*     */   public float distance1;
/*     */   public float combinedFriction;
/*     */   public float combinedRestitution;
/*     */   public int partId0;
/*     */   public int partId1;
/*     */   public int index0;
/*     */   public int index1;
/*     */   public Object userPersistentData;
/*     */   public float appliedImpulse;
/*     */   public boolean lateralFrictionInitialized;
/*     */   public float appliedImpulseLateral1;
/*     */   public float appliedImpulseLateral2;
/*     */   public int lifeTime;
/*  61 */   public final Vector3f lateralFrictionDir1 = new Vector3f();
/*  62 */   public final Vector3f lateralFrictionDir2 = new Vector3f();
/*     */ 
/*     */   public ManifoldPoint() {
/*  65 */     this.userPersistentData = null;
/*  66 */     this.appliedImpulse = 0.0F;
/*  67 */     this.lateralFrictionInitialized = false;
/*  68 */     this.lifeTime = 0;
/*     */   }
/*     */ 
/*     */   public ManifoldPoint(Vector3f pointA, Vector3f pointB, Vector3f normal, float distance) {
/*  72 */     init(pointA, pointB, normal, distance);
/*     */   }
/*     */ 
/*     */   public void init(Vector3f pointA, Vector3f pointB, Vector3f normal, float distance) {
/*  76 */     this.localPointA.set(pointA);
/*  77 */     this.localPointB.set(pointB);
/*  78 */     this.normalWorldOnB.set(normal);
/*  79 */     this.distance1 = distance;
/*  80 */     this.combinedFriction = 0.0F;
/*  81 */     this.combinedRestitution = 0.0F;
/*  82 */     this.userPersistentData = null;
/*  83 */     this.appliedImpulse = 0.0F;
/*  84 */     this.lateralFrictionInitialized = false;
/*  85 */     this.appliedImpulseLateral1 = 0.0F;
/*  86 */     this.appliedImpulseLateral2 = 0.0F;
/*  87 */     this.lifeTime = 0;
/*     */   }
/*     */ 
/*     */   public float getDistance() {
/*  91 */     return this.distance1;
/*     */   }
/*     */ 
/*     */   public int getLifeTime() {
/*  95 */     return this.lifeTime;
/*     */   }
/*     */ 
/*     */   public void set(ManifoldPoint p) {
/*  99 */     this.localPointA.set(p.localPointA);
/* 100 */     this.localPointB.set(p.localPointB);
/* 101 */     this.positionWorldOnA.set(p.positionWorldOnA);
/* 102 */     this.positionWorldOnB.set(p.positionWorldOnB);
/* 103 */     this.normalWorldOnB.set(p.normalWorldOnB);
/* 104 */     this.distance1 = p.distance1;
/* 105 */     this.combinedFriction = p.combinedFriction;
/* 106 */     this.combinedRestitution = p.combinedRestitution;
/* 107 */     this.partId0 = p.partId0;
/* 108 */     this.partId1 = p.partId1;
/* 109 */     this.index0 = p.index0;
/* 110 */     this.index1 = p.index1;
/* 111 */     this.userPersistentData = p.userPersistentData;
/* 112 */     this.appliedImpulse = p.appliedImpulse;
/* 113 */     this.lateralFrictionInitialized = p.lateralFrictionInitialized;
/* 114 */     this.appliedImpulseLateral1 = p.appliedImpulseLateral1;
/* 115 */     this.appliedImpulseLateral2 = p.appliedImpulseLateral2;
/* 116 */     this.lifeTime = p.lifeTime;
/* 117 */     this.lateralFrictionDir1.set(p.lateralFrictionDir1);
/* 118 */     this.lateralFrictionDir2.set(p.lateralFrictionDir2);
/*     */   }
/*     */ 
/*     */   public Vector3f getPositionWorldOnA(Vector3f out) {
/* 122 */     out.set(this.positionWorldOnA);
/* 123 */     return out;
/*     */   }
/*     */ 
/*     */   public Vector3f getPositionWorldOnB(Vector3f out)
/*     */   {
/* 128 */     out.set(this.positionWorldOnB);
/* 129 */     return out;
/*     */   }
/*     */ 
/*     */   public void setDistance(float dist) {
/* 133 */     this.distance1 = dist;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.narrowphase.ManifoldPoint
 * JD-Core Version:    0.6.2
 */