/*     */ package com.bulletphysics.linearmath;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Quat4f;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class TransformUtil
/*     */ {
/*     */   public static final float SIMDSQRT12 = 0.7071068F;
/*     */   public static final float ANGULAR_MOTION_THRESHOLD = 0.7853982F;
/*     */ 
/*     */   public static float recipSqrt(float x)
/*     */   {
/*  44 */     return 1.0F / (float)Math.sqrt(x);
/*     */   }
/*     */ 
/*     */   public static void planeSpace1(Vector3f n, Vector3f p, Vector3f q) {
/*  48 */     if (Math.abs(n.z) > 0.7071068F)
/*     */     {
/*  50 */       float a = n.y * n.y + n.z * n.z;
/*  51 */       float k = recipSqrt(a);
/*  52 */       p.set(0.0F, -n.z * k, n.y * k);
/*     */ 
/*  54 */       q.set(a * k, -n.x * p.z, n.x * p.y);
/*     */     }
/*     */     else
/*     */     {
/*  58 */       float a = n.x * n.x + n.y * n.y;
/*  59 */       float k = recipSqrt(a);
/*  60 */       p.set(-n.y * k, n.x * k, 0.0F);
/*     */ 
/*  62 */       q.set(-n.z * p.y, n.z * p.x, a * k);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void integrateTransform(Transform arg0, Vector3f arg1, Vector3f arg2, float arg3, Transform arg4)
/*     */   {
/*  68 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$javax$vecmath$Vector3f(); tmp7_5.push$javax$vecmath$Quat4f(); predictedTransform.origin.scaleAdd(timeStep, linvel, curTrans.origin);
/*     */ 
/*  78 */       Vector3f axis = localStack.get$javax$vecmath$Vector3f();
/*  79 */       float fAngle = angvel.length();
/*     */ 
/*  82 */       if (fAngle * timeStep > 0.7853982F) {
/*  83 */         fAngle = 0.7853982F / timeStep;
/*     */       }
/*     */ 
/*  86 */       if (fAngle < 0.001F)
/*     */       {
/*  88 */         axis.scale(0.5F * timeStep - timeStep * timeStep * timeStep * 0.02083333F * fAngle * fAngle, angvel);
/*     */       }
/*     */       else
/*     */       {
/*  92 */         axis.scale((float)Math.sin(0.5F * fAngle * timeStep) / fAngle, angvel);
/*     */       }
/*  94 */       Quat4f dorn = localStack.get$javax$vecmath$Quat4f();
/*  95 */       dorn.set(axis.x, axis.y, axis.z, (float)Math.cos(fAngle * timeStep * 0.5F));
/*  96 */       Quat4f orn0 = curTrans.getRotation(localStack.get$javax$vecmath$Quat4f());
/*     */ 
/*  98 */       Quat4f predictedOrn = localStack.get$javax$vecmath$Quat4f();
/*  99 */       predictedOrn.mul(dorn, orn0);
/* 100 */       predictedOrn.normalize();
/*     */ 
/* 102 */       predictedTransform.setRotation(predictedOrn);
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 103 */       .Stack tmp204_202 = localStack; tmp204_202.pop$javax$vecmath$Vector3f(); tmp204_202.pop$javax$vecmath$Quat4f(); } throw finally;
/*     */   }
/*     */   public static void calculateVelocity(Transform arg0, Transform arg1, float arg2, Vector3f arg3, Vector3f arg4) {
/* 106 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); linVel.sub(transform1.origin, transform0.origin);
/* 107 */       linVel.scale(1.0F / timeStep);
/*     */ 
/* 109 */       Vector3f axis = localStack.get$javax$vecmath$Vector3f();
/* 110 */       float[] angle = new float[1];
/* 111 */       calculateDiffAxisAngle(transform0, transform1, axis, angle);
/* 112 */       angVel.scale(angle[0] / timeStep, axis);
/*     */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public static void calculateDiffAxisAngle(Transform arg0, Transform arg1, Vector3f arg2, float[] arg3)
/*     */   {
/* 122 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$javax$vecmath$Quat4f(); tmp7_5.push$javax$vecmath$Matrix3f(); Matrix3f tmp = localStack.get$javax$vecmath$Matrix3f();
/* 123 */       tmp.set(transform0.basis);
/* 124 */       MatrixUtil.invert(tmp);
/*     */ 
/* 126 */       Matrix3f dmat = localStack.get$javax$vecmath$Matrix3f();
/* 127 */       dmat.mul(transform1.basis, tmp);
/*     */ 
/* 129 */       Quat4f dorn = localStack.get$javax$vecmath$Quat4f();
/* 130 */       MatrixUtil.getRotation(dmat, dorn);
/*     */ 
/* 135 */       dorn.normalize();
/*     */ 
/* 137 */       angle[0] = QuaternionUtil.getAngle(dorn);
/* 138 */       axis.set(dorn.x, dorn.y, dorn.z);
/*     */ 
/* 143 */       float len = axis.lengthSquared();
/* 144 */       if (len < 1.421086E-014F) {
/* 145 */         axis.set(1.0F, 0.0F, 0.0F);
/*     */       }
/*     */       else
/* 148 */         axis.scale(1.0F / (float)Math.sqrt(len));
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 150 */       .Stack tmp148_146 = localStack; tmp148_146.pop$javax$vecmath$Quat4f(); tmp148_146.pop$javax$vecmath$Matrix3f(); } throw finally;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.TransformUtil
 * JD-Core Version:    0.6.2
 */