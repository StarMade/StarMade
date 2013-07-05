/*     */ package com.bulletphysics.collision.shapes;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public abstract class CollisionShape
/*     */ {
/*     */   protected Object userPointer;
/*     */ 
/*     */   public abstract void getAabb(Transform paramTransform, Vector3f paramVector3f1, Vector3f paramVector3f2);
/*     */ 
/*     */   public void getBoundingSphere(Vector3f arg1, float[] arg2)
/*     */   {
/*  48 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/*  50 */       Transform tr = localStack.get$com$bulletphysics$linearmath$Transform();
/*  51 */       tr.setIdentity();
/*  52 */       Vector3f aabbMin = localStack.get$javax$vecmath$Vector3f(); Vector3f aabbMax = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/*  54 */       getAabb(tr, aabbMin, aabbMax);
/*     */ 
/*  56 */       tmp.sub(aabbMax, aabbMin);
/*  57 */       radius[0] = (tmp.length() * 0.5F);
/*     */ 
/*  59 */       tmp.add(aabbMin, aabbMax);
/*  60 */       center.scale(0.5F, tmp);
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/*  61 */       .Stack tmp101_99 = localStack; tmp101_99.pop$com$bulletphysics$linearmath$Transform(); tmp101_99.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public float getAngularMotionDisc() {
/*  65 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f center = localStack.get$javax$vecmath$Vector3f();
/*  66 */       float[] disc = new float[1];
/*  67 */       getBoundingSphere(center, disc);
/*  68 */       disc[0] += center.length();
/*  69 */       return disc[0]; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void calculateTemporalAabb(Transform arg1, Vector3f arg2, Vector3f arg3, float arg4, Vector3f arg5, Vector3f arg6)
/*     */   {
/*  76 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); getAabb(curTrans, temporalAabbMin, temporalAabbMax);
/*     */ 
/*  78 */       float temporalAabbMaxx = temporalAabbMax.x;
/*  79 */       float temporalAabbMaxy = temporalAabbMax.y;
/*  80 */       float temporalAabbMaxz = temporalAabbMax.z;
/*  81 */       float temporalAabbMinx = temporalAabbMin.x;
/*  82 */       float temporalAabbMiny = temporalAabbMin.y;
/*  83 */       float temporalAabbMinz = temporalAabbMin.z;
/*     */ 
/*  86 */       Vector3f linMotion = localStack.get$javax$vecmath$Vector3f(linvel);
/*  87 */       linMotion.scale(timeStep);
/*     */ 
/*  90 */       if (linMotion.x > 0.0F) {
/*  91 */         temporalAabbMaxx += linMotion.x;
/*     */       }
/*     */       else {
/*  94 */         temporalAabbMinx += linMotion.x;
/*     */       }
/*  96 */       if (linMotion.y > 0.0F) {
/*  97 */         temporalAabbMaxy += linMotion.y;
/*     */       }
/*     */       else {
/* 100 */         temporalAabbMiny += linMotion.y;
/*     */       }
/* 102 */       if (linMotion.z > 0.0F) {
/* 103 */         temporalAabbMaxz += linMotion.z;
/*     */       }
/*     */       else {
/* 106 */         temporalAabbMinz += linMotion.z; } 
/*     */ float angularMotion = angvel.length() * getAngularMotionDisc() * timeStep;
/* 111 */       Vector3f angularMotion3d = localStack.get$javax$vecmath$Vector3f();
/* 112 */       angularMotion3d.set(angularMotion, angularMotion, angularMotion);
/* 113 */       temporalAabbMin.set(temporalAabbMinx, temporalAabbMiny, temporalAabbMinz);
/* 114 */       temporalAabbMax.set(temporalAabbMaxx, temporalAabbMaxy, temporalAabbMaxz);
/*     */ 
/* 116 */       temporalAabbMin.sub(angularMotion3d);
/* 117 */       temporalAabbMax.add(angularMotion3d);
/*     */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public boolean isPolyhedral() {
/* 122 */     return getShapeType().isPolyhedral();
/*     */   }
/*     */ 
/*     */   public boolean isConvex() {
/* 126 */     return getShapeType().isConvex();
/*     */   }
/*     */ 
/*     */   public boolean isConcave() {
/* 130 */     return getShapeType().isConcave();
/*     */   }
/*     */ 
/*     */   public boolean isCompound() {
/* 134 */     return getShapeType().isCompound();
/*     */   }
/*     */ 
/*     */   public boolean isInfinite()
/*     */   {
/* 139 */     return getShapeType().isInfinite();
/*     */   }
/*     */ 
/*     */   public abstract BroadphaseNativeType getShapeType();
/*     */ 
/*     */   public abstract void setLocalScaling(Vector3f paramVector3f);
/*     */ 
/*     */   public abstract Vector3f getLocalScaling(Vector3f paramVector3f);
/*     */ 
/*     */   public abstract void calculateLocalInertia(float paramFloat, Vector3f paramVector3f);
/*     */ 
/*     */   public abstract String getName();
/*     */ 
/*     */   public abstract void setMargin(float paramFloat);
/*     */ 
/*     */   public abstract float getMargin();
/*     */ 
/*     */   public void setUserPointer(Object userPtr)
/*     */   {
/* 161 */     this.userPointer = userPtr;
/*     */   }
/*     */ 
/*     */   public Object getUserPointer() {
/* 165 */     return this.userPointer;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.CollisionShape
 * JD-Core Version:    0.6.2
 */