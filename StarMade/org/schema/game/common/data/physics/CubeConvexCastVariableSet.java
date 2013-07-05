/*    */ package org.schema.game.common.data.physics;
/*    */ 
/*    */ import com.bulletphysics.collision.dispatch.CollisionObject;
/*    */ import com.bulletphysics.collision.narrowphase.GjkEpaPenetrationDepthSolver;
/*    */ import com.bulletphysics.collision.narrowphase.SimplexSolverInterface;
/*    */ import com.bulletphysics.collision.shapes.BoxShape;
/*    */ import com.bulletphysics.collision.shapes.ConvexShape;
/*    */ import com.bulletphysics.linearmath.Transform;
/*    */ import it.unimi.dsi.fastutil.floats.Float2ObjectAVLTreeMap;
/*    */ import javax.vecmath.Matrix3f;
/*    */ import javax.vecmath.Vector3f;
/*    */ import o;
/*    */ import org.schema.game.common.data.physics.octree.IntersectionCallback;
/*    */ import q;
/*    */ import xO;
/*    */ 
/*    */ public class CubeConvexCastVariableSet
/*    */ {
/* 28 */   Vector3f outMin = new Vector3f();
/* 29 */   Vector3f outMax = new Vector3f();
/* 30 */   Vector3f fromHelp = new Vector3f();
/* 31 */   Vector3f toHelp = new Vector3f();
/* 32 */   Vector3f fromToHelp = new Vector3f();
/* 33 */   Vector3f normal = new Vector3f();
/* 34 */   Vector3f localMinOut = new Vector3f();
/* 35 */   Vector3f localMaxOut = new Vector3f();
/* 36 */   float[] hitLambda = new float[1];
/* 37 */   IntersectionCallback intersectionCallBack = new IntersectionCallback();
/*    */ 
/* 39 */   xO outer = new xO();
/* 40 */   xO inner = new xO();
/* 41 */   xO outBB = new xO();
/* 42 */   q minIntA = new q();
/* 43 */   q maxIntA = new q();
/* 44 */   q minIntB = new q();
/* 45 */   q maxIntB = new q();
/*    */ 
/* 47 */   ChangableSphereShape sphereShape = new ChangableSphereShape(0.0F);
/*    */   CollisionObject cubesObject;
/* 49 */   Transform f = new Transform();
/* 50 */   Transform t = new Transform();
/* 51 */   Float2ObjectAVLTreeMap sortedAABB = new Float2ObjectAVLTreeMap();
/* 52 */   o elemA = new o();
/* 53 */   Vector3f elemPosA = new Vector3f();
/* 54 */   o startOut = new o();
/* 55 */   o endOut = new o();
/* 56 */   Vector3f minOut = new Vector3f();
/* 57 */   Vector3f maxOut = new Vector3f();
/* 58 */   Vector3f nA = new Vector3f();
/* 59 */   Transform tmpTrans = new Transform();
/* 60 */   Vector3f distTest = new Vector3f();
/* 61 */   Float2ObjectAVLTreeMap sorted = new Float2ObjectAVLTreeMap();
/* 62 */   o[] posCache = new o[8];
/* 63 */   int posCachePointer = 0;
/*    */ 
/* 65 */   Vector3f castedAABBMin = new Vector3f();
/* 66 */   Vector3f castedAABBMax = new Vector3f();
/* 67 */   Vector3f convexFromAABBMin = new Vector3f();
/* 68 */   Vector3f convexFromAABBMax = new Vector3f();
/* 69 */   Vector3f convexToAABBMin = new Vector3f();
/* 70 */   Vector3f convexToAABBMax = new Vector3f();
/*    */ 
/* 72 */   Vector3f tmp = new Vector3f();
/*    */ 
/* 74 */   Transform tmpTrans1 = new Transform();
/* 75 */   Transform tmpTrans2 = new Transform();
/* 76 */   Transform tmpTrans3 = new Transform();
/* 77 */   Transform tmpTrans4 = new Transform();
/* 78 */   boolean disableCcd = false;
/*    */ 
/* 80 */   BoxShape box0 = new BoxShape(new Vector3f(0.501F, 0.501F, 0.501F));
/*    */   SimplexSolverInterface simplexSolver;
/*    */   ConvexShape shapeA;
/*    */   CubeShape cubesB;
/* 86 */   public Matrix3f absolute1 = new Matrix3f();
/* 87 */   public Vector3f dist = new Vector3f();
/* 88 */   public GjkPairDetectorVariables gjkVar = new GjkPairDetectorVariables();
/* 89 */   public GjkEpaPenetrationDepthSolver gjkEpaPenetrationDepthSolver = new GjkEpaPenetrationDepthSolver();
/* 90 */   public Transform inv = new Transform();
/* 91 */   public AABBVarSet aabbVarSet = new AABBVarSet();
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CubeConvexCastVariableSet
 * JD-Core Version:    0.6.2
 */