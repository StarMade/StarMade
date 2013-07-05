/*    */ package org.schema.game.common.data.physics;
/*    */ 
/*    */ import com.bulletphysics.linearmath.Transform;
/*    */ import javax.vecmath.Matrix3f;
/*    */ import javax.vecmath.Quat4f;
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public class GjkPairDetectorVariables
/*    */ {
/* 11 */   public final Vector3f tmp = new Vector3f();
/* 12 */   public final Vector3f normalInB = new Vector3f();
/* 13 */   public final Vector3f tmpNormalInB = new Vector3f();
/* 14 */   public final Vector3f tmpPointOnA = new Vector3f();
/* 15 */   public final Vector3f w = new Vector3f();
/* 16 */   public final Vector3f qWorld = new Vector3f();
/* 17 */   public final Vector3f pWorld = new Vector3f();
/* 18 */   public final Vector3f qInB = new Vector3f();
/* 19 */   public final Vector3f pInA = new Vector3f();
/* 20 */   public final Vector3f seperatingAxisInA = new Vector3f();
/* 21 */   public final Vector3f seperatingAxisInB = new Vector3f();
/* 22 */   public final Vector3f positionOffset = new Vector3f();
/*    */ 
/* 24 */   public final Vector3f pointOnB = new Vector3f();
/* 25 */   public final Vector3f pointOnA = new Vector3f();
/* 26 */   public final Vector3f tmpPointOnB = new Vector3f();
/*    */ 
/* 29 */   public final Transform localTransB = new Transform();
/* 30 */   public final Transform localTransA = new Transform();
/* 31 */   public final Vector3f axis = new Vector3f();
/* 32 */   public final Matrix3f tmp2 = new Matrix3f();
/* 33 */   public final Matrix3f dmat = new Matrix3f();
/* 34 */   public final Quat4f dorn = new Quat4f();
/* 35 */   public final Vector3f iAxis = new Vector3f();
/* 36 */   public final Quat4f iDorn = new Quat4f();
/* 37 */   public final Quat4f iorn0 = new Quat4f();
/* 38 */   public final Quat4f iPredictOrn = new Quat4f();
/* 39 */   public final float[] float4Temp = new float[4];
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.GjkPairDetectorVariables
 * JD-Core Version:    0.6.2
 */