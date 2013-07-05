/*    */ package org.schema.game.common.data.physics;
/*    */ 
/*    */ import com.bulletphysics.collision.shapes.BoxShape;
/*    */ import com.bulletphysics.linearmath.Transform;
/*    */ import javax.vecmath.Matrix3f;
/*    */ import javax.vecmath.Vector3f;
/*    */ import o;
/*    */ import org.schema.game.common.data.physics.octree.IntersectionCallback;
/*    */ import org.schema.game.common.data.physics.octree.OctreeVariableSet;
/*    */ import q;
/*    */ import xO;
/*    */ 
/*    */ public class CubeCubeCollisionVariableSet
/*    */ {
/* 17 */   Transform tmpTrans1 = new Transform();
/* 18 */   Transform tmpTrans2 = new Transform();
/* 19 */   Transform tmpTrans3 = new Transform();
/* 20 */   Transform tmpTrans4 = new Transform();
/* 21 */   Vector3f localMinOut = new Vector3f();
/* 22 */   Vector3f localMaxOut = new Vector3f();
/* 23 */   Vector3f tmp = new Vector3f();
/* 24 */   Vector3f pos0 = new Vector3f();
/* 25 */   Vector3f pos1 = new Vector3f();
/* 26 */   Vector3f diff = new Vector3f();
/* 27 */   Vector3f normalOnSurfaceB = new Vector3f();
/*    */   OctreeVariableSet oSet;
/* 30 */   xO outer = new xO();
/* 31 */   xO inner = new xO();
/* 32 */   xO outBB = new xO();
/* 33 */   xO outBBCopy = new xO();
/* 34 */   q minIntA = new q();
/* 35 */   q maxIntA = new q();
/* 36 */   q minIntB = new q();
/* 37 */   q maxIntB = new q();
/*    */ 
/* 39 */   Vector3f min = new Vector3f();
/* 40 */   Vector3f max = new Vector3f();
/* 41 */   Vector3f bMinOut = new Vector3f();
/* 42 */   Vector3f bMaxOut = new Vector3f();
/* 43 */   Vector3f minOut = new Vector3f();
/* 44 */   Vector3f maxOut = new Vector3f();
/* 45 */   Vector3f othermin = new Vector3f();
/* 46 */   Vector3f othermax = new Vector3f();
/*    */ 
/* 48 */   Vector3f elemPosA = new Vector3f();
/* 49 */   Vector3f elemPosB = new Vector3f();
/* 50 */   Vector3f elemPosAAbs = new Vector3f();
/* 51 */   Vector3f elemPosBAbs = new Vector3f();
/*    */ 
/* 56 */   o startA = new o();
/* 57 */   o startB = new o();
/* 58 */   o endA = new o();
/* 59 */   o endB = new o();
/* 60 */   BoxShape box0 = new BoxShape(new Vector3f(0.5F, 0.5F, 0.5F));
/*    */ 
/* 62 */   BoxShape box1 = new BoxShape(new Vector3f(0.5F, 0.5F, 0.5F));
/*    */ 
/* 66 */   IntersectionCallback intersectionCallBackAwithB = new IntersectionCallback();
/* 67 */   IntersectionCallback intersectionCallBackBwithA = new IntersectionCallback();
/*    */ 
/* 69 */   Vector3f outInnerMin = new Vector3f();
/* 70 */   Vector3f outInnerMax = new Vector3f();
/* 71 */   Vector3f outOuterMin = new Vector3f();
/* 72 */   Vector3f outOuterMax = new Vector3f();
/*    */ 
/* 74 */   Vector3f nA = new Vector3f();
/* 75 */   Vector3f nB = new Vector3f();
/*    */ 
/* 77 */   Vector3f otherMinIn = new Vector3f();
/* 78 */   Vector3f otherMaxIn = new Vector3f();
/*    */ 
/* 80 */   xO bbOuterSeg = new xO();
/* 81 */   xO bbInnerSeg = new xO();
/* 82 */   xO bbSectorIntersection = new xO();
/* 83 */   xO bbSectorIntersectionTest = new xO();
/* 84 */   xO bbOuterOct = new xO();
/* 85 */   xO bbInnerOct = new xO();
/* 86 */   xO bbOctIntersection = new xO();
/* 87 */   public Matrix3f absolute1 = new Matrix3f();
/* 88 */   public Matrix3f absolute2 = new Matrix3f();
/* 89 */   public GjkPairDetectorVariables gjkVar = new GjkPairDetectorVariables();
/* 90 */   public Transform wtInv1 = new Transform();
/* 91 */   public Transform wtInv0 = new Transform();
/* 92 */   public AABBVarSet aabbVarSet = new AABBVarSet();
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CubeCubeCollisionVariableSet
 * JD-Core Version:    0.6.2
 */