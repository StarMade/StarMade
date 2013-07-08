/*  1:   */package org.schema.game.common.data.physics;
/*  2:   */
/*  3:   */import com.bulletphysics.linearmath.Transform;
/*  4:   */import javax.vecmath.Matrix3f;
/*  5:   */import javax.vecmath.Quat4f;
/*  6:   */import javax.vecmath.Vector3f;
/*  7:   */
/*  9:   */public class GjkPairDetectorVariables
/* 10:   */{
/* 11:11 */  public final Vector3f tmp = new Vector3f();
/* 12:12 */  public final Vector3f normalInB = new Vector3f();
/* 13:13 */  public final Vector3f tmpNormalInB = new Vector3f();
/* 14:14 */  public final Vector3f tmpPointOnA = new Vector3f();
/* 15:15 */  public final Vector3f w = new Vector3f();
/* 16:16 */  public final Vector3f qWorld = new Vector3f();
/* 17:17 */  public final Vector3f pWorld = new Vector3f();
/* 18:18 */  public final Vector3f qInB = new Vector3f();
/* 19:19 */  public final Vector3f pInA = new Vector3f();
/* 20:20 */  public final Vector3f seperatingAxisInA = new Vector3f();
/* 21:21 */  public final Vector3f seperatingAxisInB = new Vector3f();
/* 22:22 */  public final Vector3f positionOffset = new Vector3f();
/* 23:   */  
/* 24:24 */  public final Vector3f pointOnB = new Vector3f();
/* 25:25 */  public final Vector3f pointOnA = new Vector3f();
/* 26:26 */  public final Vector3f tmpPointOnB = new Vector3f();
/* 27:   */  
/* 29:29 */  public final Transform localTransB = new Transform();
/* 30:30 */  public final Transform localTransA = new Transform();
/* 31:31 */  public final Vector3f axis = new Vector3f();
/* 32:32 */  public final Matrix3f tmp2 = new Matrix3f();
/* 33:33 */  public final Matrix3f dmat = new Matrix3f();
/* 34:34 */  public final Quat4f dorn = new Quat4f();
/* 35:35 */  public final Vector3f iAxis = new Vector3f();
/* 36:36 */  public final Quat4f iDorn = new Quat4f();
/* 37:37 */  public final Quat4f iorn0 = new Quat4f();
/* 38:38 */  public final Quat4f iPredictOrn = new Quat4f();
/* 39:39 */  public final float[] float4Temp = new float[4];
/* 40:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.GjkPairDetectorVariables
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */