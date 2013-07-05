/*     */ package org.schema.common.util.linAlg;
/*     */ 
/*     */ import com.bulletphysics.linearmath.MatrixUtil;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import java.io.PrintStream;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Quat4f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.common.FastMath;
/*     */ 
/*     */ public class TransformTools
/*     */ {
/*     */   public static void a(Transform paramTransform1, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat, Transform paramTransform2, Vector3f paramVector3f3, Quat4f paramQuat4f1, Quat4f paramQuat4f2, Quat4f paramQuat4f3, float[] paramArrayOfFloat)
/*     */   {
/*  21 */     paramTransform2.origin.scaleAdd(paramFloat, paramVector3f1, paramTransform1.origin);
/*     */ 
/*  35 */     if ((
/*  35 */       paramVector3f1 = paramVector3f2.length()) * 
/*  35 */       paramFloat > 0.7853982F) {
/*  36 */       paramVector3f1 = 0.7853982F / paramFloat;
/*     */     }
/*     */ 
/*  39 */     if (paramVector3f1 < 0.001F)
/*     */     {
/*  41 */       paramVector3f3.scale(0.5F * paramFloat - paramFloat * paramFloat * paramFloat * 0.02083333F * paramVector3f1 * paramVector3f1, paramVector3f2);
/*     */     }
/*     */     else
/*     */     {
/*  45 */       paramVector3f3.scale((float)Math.sin(0.5F * paramVector3f1 * paramFloat) / paramVector3f1, paramVector3f2);
/*     */     }
/*     */ 
/*  48 */     paramQuat4f1.set(paramVector3f3.x, paramVector3f3.y, paramVector3f3.z, (float)Math.cos(paramVector3f1 * paramFloat * 0.5F));
/*  49 */     paramVector3f2 = paramArrayOfFloat; paramVector3f1 = paramQuat4f2; if ((paramFloat = (paramTransform1 = paramTransform1.basis).m00 + paramTransform1.m11 + paramTransform1.m22) > 0.0F) { paramFloat = (float)Math.sqrt(paramFloat + 1.0F); paramVector3f2[3] = (paramFloat * 0.5F); paramFloat = 0.5F / paramFloat; paramVector3f2[0] = ((paramTransform1.m21 - paramTransform1.m12) * paramFloat); paramVector3f2[1] = ((paramTransform1.m02 - paramTransform1.m20) * paramFloat); paramVector3f2[2] = ((paramTransform1.m10 - paramTransform1.m01) * paramFloat); } else { paramVector3f3 = ((paramFloat = paramTransform1.m00 < paramTransform1.m22 ? 2 : paramTransform1.m00 < paramTransform1.m11 ? 1 : paramTransform1.m11 < paramTransform1.m22 ? 2 : 0) + 1) % 3; paramArrayOfFloat = (paramFloat + 2) % 3; float f = (float)Math.sqrt(paramTransform1.getElement(paramFloat, paramFloat) - paramTransform1.getElement(paramVector3f3, paramVector3f3) - paramTransform1.getElement(paramArrayOfFloat, paramArrayOfFloat) + 1.0F); paramVector3f2[paramFloat] = (f * 0.5F); f = 0.5F / f; paramVector3f2[3] = ((paramTransform1.getElement(paramArrayOfFloat, paramVector3f3) - paramTransform1.getElement(paramVector3f3, paramArrayOfFloat)) * f); paramVector3f2[paramVector3f3] = ((paramTransform1.getElement(paramVector3f3, paramFloat) + paramTransform1.getElement(paramFloat, paramVector3f3)) * f); paramVector3f2[paramArrayOfFloat] = ((paramTransform1.getElement(paramArrayOfFloat, paramFloat) + paramTransform1.getElement(paramFloat, paramArrayOfFloat)) * f); } paramVector3f1.set(paramVector3f2[0], paramVector3f2[1], paramVector3f2[2], paramVector3f2[3]);
/*     */ 
/*  52 */     paramQuat4f3.mul(paramQuat4f1, paramQuat4f2);
/*  53 */     paramQuat4f3.normalize();
/*     */ 
/*  55 */     paramTransform2.setRotation(paramQuat4f3);
/*     */   }
/*     */ 
/*     */   public static void a(Transform paramTransform1, Transform paramTransform2, float paramFloat, Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3, Matrix3f paramMatrix3f1, Matrix3f paramMatrix3f2, Quat4f paramQuat4f)
/*     */   {
/*  95 */     paramVector3f1.sub(paramTransform2.origin, paramTransform1.origin);
/*  96 */     paramVector3f1.scale(1.0F / paramFloat);
/*     */ 
/*  99 */     paramVector3f1 = paramVector3f3; paramTransform2 = paramTransform2; paramTransform1 = paramTransform1; paramMatrix3f1.set(paramTransform1.basis); float f1 = a(paramTransform1 = paramMatrix3f1, 1, 1, 2, 2); float f2 = a(paramTransform1, 1, 2, 2, 0); float f3 = a(paramTransform1, 1, 0, 2, 1); float f4 = paramTransform1.m00 * f1 + paramTransform1.m01 * f2 + paramTransform1.m02 * f3; if ((!a) && (f4 == 0.0F)) throw new AssertionError("\n" + paramTransform1); f4 = 1.0F / f4; f1 *= f4; float f5 = a(paramTransform1, 0, 2, 2, 1) * f4; float f6 = a(paramTransform1, 0, 1, 1, 2) * f4; f2 *= f4; float f7 = a(paramTransform1, 0, 0, 2, 2) * f4; float f8 = a(paramTransform1, 0, 2, 1, 0) * f4; f3 *= f4; float f9 = a(paramTransform1, 0, 1, 2, 0) * f4; f4 = a(paramTransform1, 0, 0, 1, 1) * f4; paramTransform1.m00 = f1; paramTransform1.m01 = f5; paramTransform1.m02 = f6; paramTransform1.m10 = f2; paramTransform1.m11 = f7; paramTransform1.m12 = f8; paramTransform1.m20 = f3; paramTransform1.m21 = f9; paramTransform1.m22 = f4; paramMatrix3f2.mul(paramTransform2.basis, paramMatrix3f1); MatrixUtil.getRotation(paramMatrix3f2, paramQuat4f); paramQuat4f.normalize(); paramTransform1 = paramQuat4f; paramTransform1 = 2.0F * (float)FastMath.a(paramTransform1.w); paramVector3f1.set(paramQuat4f.x, paramQuat4f.y, paramQuat4f.z); if ((paramTransform2 = paramVector3f1.lengthSquared()) < 1.421086E-014F) paramVector3f1.set(1.0F, 0.0F, 0.0F); else paramVector3f1.scale(1.0F / (float)Math.sqrt(paramTransform2));
/* 100 */     paramVector3f2.scale(paramTransform1 / paramFloat, paramVector3f3);
/*     */   }
/*     */   public static void main(String[] paramArrayOfString) {
/* 103 */     (
/* 105 */       paramArrayOfString = new Matrix3f())
/* 105 */       .setIdentity();
/* 106 */     System.err.println("MMMMMM\n" + paramArrayOfString);
/* 107 */     MatrixUtil.invert(paramArrayOfString);
/*     */   }
/*     */   private static float a(Matrix3f paramMatrix3f, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/* 110 */     return paramMatrix3f.getElement(paramInt1, paramInt2) * paramMatrix3f.getElement(paramInt3, paramInt4) - paramMatrix3f.getElement(paramInt1, paramInt4) * paramMatrix3f.getElement(paramInt3, paramInt2);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.common.util.linAlg.TransformTools
 * JD-Core Version:    0.6.2
 */