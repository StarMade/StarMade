/*  1:   */package org.schema.game.common.data.physics;
/*  2:   */
/*  3:   */import com.bulletphysics.linearmath.Transform;
/*  4:   */import javax.vecmath.Matrix3f;
/*  5:   */
/*  6:   */public class SequentialImpulseContraintSolverExtVariableSet
/*  7:   */{
/*  8: 8 */  Transform centerOfMassTrans = new Transform();
/*  9: 9 */  javax.vecmath.Vector3f tmp = new javax.vecmath.Vector3f();
/* 10:10 */  javax.vecmath.Vector3f tmp2 = new javax.vecmath.Vector3f();
/* 11:11 */  javax.vecmath.Vector3f tmp3 = new javax.vecmath.Vector3f();
/* 12:12 */  javax.vecmath.Vector3f ftorqueAxis1 = new javax.vecmath.Vector3f();
/* 13:13 */  Matrix3f tmpMat = new Matrix3f();
/* 14:14 */  javax.vecmath.Vector3f vec = new javax.vecmath.Vector3f();
/* 15:15 */  Transform tmpTrans = new Transform();
/* 16:   */  
/* 18:18 */  javax.vecmath.Vector3f rel_pos1A = new javax.vecmath.Vector3f();
/* 19:19 */  javax.vecmath.Vector3f rel_pos2A = new javax.vecmath.Vector3f();
/* 20:   */  
/* 21:21 */  javax.vecmath.Vector3f pos1A = new javax.vecmath.Vector3f();
/* 22:22 */  javax.vecmath.Vector3f pos2A = new javax.vecmath.Vector3f();
/* 23:23 */  javax.vecmath.Vector3f velA = new javax.vecmath.Vector3f();
/* 24:24 */  javax.vecmath.Vector3f torqueAxis0A = new javax.vecmath.Vector3f();
/* 25:25 */  javax.vecmath.Vector3f torqueAxis1A = new javax.vecmath.Vector3f();
/* 26:26 */  javax.vecmath.Vector3f vel1A = new javax.vecmath.Vector3f();
/* 27:27 */  javax.vecmath.Vector3f vel2A = new javax.vecmath.Vector3f();
/* 28:28 */  javax.vecmath.Vector3f frictionDir1A = new javax.vecmath.Vector3f();
/* 29:29 */  javax.vecmath.Vector3f frictionDir2A = new javax.vecmath.Vector3f();
/* 30:30 */  javax.vecmath.Vector3f vecA = new javax.vecmath.Vector3f();
/* 31:   */  
/* 32:32 */  Matrix3f tmpMatA = new Matrix3f();
/* 33:33 */  javax.vecmath.Vector3f tmp4 = new javax.vecmath.Vector3f();
/* 34:   */  
/* 36:36 */  javax.vecmath.Vector3f tmpVecB = new javax.vecmath.Vector3f();
/* 37:37 */  Matrix3f tmpMat3B = new Matrix3f();
/* 38:   */  
/* 39:39 */  javax.vecmath.Vector3f pos1B = new javax.vecmath.Vector3f();
/* 40:40 */  javax.vecmath.Vector3f pos2B = new javax.vecmath.Vector3f();
/* 41:41 */  javax.vecmath.Vector3f rel_pos1B = new javax.vecmath.Vector3f();
/* 42:42 */  javax.vecmath.Vector3f rel_pos2B = new javax.vecmath.Vector3f();
/* 43:43 */  javax.vecmath.Vector3f vel1B = new javax.vecmath.Vector3f();
/* 44:44 */  javax.vecmath.Vector3f vel2B = new javax.vecmath.Vector3f();
/* 45:45 */  javax.vecmath.Vector3f velB = new javax.vecmath.Vector3f();
/* 46:46 */  javax.vecmath.Vector3f totalImpulseB = new javax.vecmath.Vector3f();
/* 47:47 */  javax.vecmath.Vector3f torqueAxis0B = new javax.vecmath.Vector3f();
/* 48:48 */  javax.vecmath.Vector3f torqueAxis1B = new javax.vecmath.Vector3f();
/* 49:49 */  javax.vecmath.Vector3f ftorqueAxis0B = new javax.vecmath.Vector3f();
/* 50:50 */  javax.vecmath.Vector3f ftorqueAxis1B = new javax.vecmath.Vector3f();
/* 51:51 */  Transform com0 = new Transform();
/* 52:52 */  Transform com1 = new Transform();
/* 53:53 */  javax.vecmath.Vector3f in0 = new javax.vecmath.Vector3f();
/* 54:54 */  javax.vecmath.Vector3f in1 = new javax.vecmath.Vector3f();
/* 55:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.SequentialImpulseContraintSolverExtVariableSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */