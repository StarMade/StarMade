/*    */ package org.schema.game.common.data.physics;
/*    */ 
/*    */ import com.bulletphysics.linearmath.Transform;
/*    */ import javax.vecmath.Matrix3f;
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public class SequentialImpulseContraintSolverExtVariableSet
/*    */ {
/*  8 */   Transform centerOfMassTrans = new Transform();
/*  9 */   Vector3f tmp = new Vector3f();
/* 10 */   Vector3f tmp2 = new Vector3f();
/* 11 */   Vector3f tmp3 = new Vector3f();
/* 12 */   Vector3f ftorqueAxis1 = new Vector3f();
/* 13 */   Matrix3f tmpMat = new Matrix3f();
/* 14 */   Vector3f vec = new Vector3f();
/* 15 */   Transform tmpTrans = new Transform();
/*    */ 
/* 18 */   Vector3f rel_pos1A = new Vector3f();
/* 19 */   Vector3f rel_pos2A = new Vector3f();
/*    */ 
/* 21 */   Vector3f pos1A = new Vector3f();
/* 22 */   Vector3f pos2A = new Vector3f();
/* 23 */   Vector3f velA = new Vector3f();
/* 24 */   Vector3f torqueAxis0A = new Vector3f();
/* 25 */   Vector3f torqueAxis1A = new Vector3f();
/* 26 */   Vector3f vel1A = new Vector3f();
/* 27 */   Vector3f vel2A = new Vector3f();
/* 28 */   Vector3f frictionDir1A = new Vector3f();
/* 29 */   Vector3f frictionDir2A = new Vector3f();
/* 30 */   Vector3f vecA = new Vector3f();
/*    */ 
/* 32 */   Matrix3f tmpMatA = new Matrix3f();
/* 33 */   Vector3f tmp4 = new Vector3f();
/*    */ 
/* 36 */   Vector3f tmpVecB = new Vector3f();
/* 37 */   Matrix3f tmpMat3B = new Matrix3f();
/*    */ 
/* 39 */   Vector3f pos1B = new Vector3f();
/* 40 */   Vector3f pos2B = new Vector3f();
/* 41 */   Vector3f rel_pos1B = new Vector3f();
/* 42 */   Vector3f rel_pos2B = new Vector3f();
/* 43 */   Vector3f vel1B = new Vector3f();
/* 44 */   Vector3f vel2B = new Vector3f();
/* 45 */   Vector3f velB = new Vector3f();
/* 46 */   Vector3f totalImpulseB = new Vector3f();
/* 47 */   Vector3f torqueAxis0B = new Vector3f();
/* 48 */   Vector3f torqueAxis1B = new Vector3f();
/* 49 */   Vector3f ftorqueAxis0B = new Vector3f();
/* 50 */   Vector3f ftorqueAxis1B = new Vector3f();
/* 51 */   Transform com0 = new Transform();
/* 52 */   Transform com1 = new Transform();
/* 53 */   Vector3f in0 = new Vector3f();
/* 54 */   Vector3f in1 = new Vector3f();
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.SequentialImpulseContraintSolverExtVariableSet
 * JD-Core Version:    0.6.2
 */