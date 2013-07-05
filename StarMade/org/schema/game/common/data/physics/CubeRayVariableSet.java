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
/*    */ import org.schema.game.common.data.physics.octree.OctreeVariableSet;
/*    */ import q;
/*    */ import xO;
/*    */ 
/*    */ public class CubeRayVariableSet
/*    */ {
/* 25 */   BoxShape box0 = new BoxShape(new Vector3f(0.5F, 0.5F, 0.5F));
/*    */ 
/* 28 */   xO outer = new xO();
/* 29 */   xO inner = new xO();
/* 30 */   xO outBB = new xO();
/* 31 */   q minIntA = new q();
/* 32 */   q maxIntA = new q();
/* 33 */   q minIntB = new q();
/* 34 */   q maxIntB = new q();
/*    */   OctreeVariableSet oSet;
/*    */   SimplexSolverInterface simplexSolver;
/* 37 */   GjkEpaPenetrationDepthSolver gjkEpaPenetrationDepthSolver = new GjkEpaPenetrationDepthSolver();
/*    */   ConvexShape shapeA;
/*    */   CubeShape cubesB;
/* 40 */   Vector3f outMin = new Vector3f();
/* 41 */   Vector3f outMax = new Vector3f();
/* 42 */   Vector3f fromHelp = new Vector3f();
/* 43 */   Vector3f toHelp = new Vector3f();
/* 44 */   Vector3f fromToHelp = new Vector3f();
/* 45 */   Vector3f localMinOut = new Vector3f();
/* 46 */   Vector3f localMaxOut = new Vector3f();
/* 47 */   Vector3f normal = new Vector3f();
/* 48 */   float[] hitLambda = new float[1];
/* 49 */   IntersectionCallback intersectionCallBack = new IntersectionCallback();
/* 50 */   ChangableSphereShape sphereShape = new ChangableSphereShape(0.0F);
/*    */   CollisionObject cubesCollisionObject;
/*    */   static final float margin = 0.15F;
/* 53 */   Vector3f distToHit = new Vector3f();
/* 54 */   float lastMinDist = -1.0F;
/*    */ 
/* 56 */   Float2ObjectAVLTreeMap sortedAABB = new Float2ObjectAVLTreeMap();
/* 57 */   o elemA = new o();
/* 58 */   Vector3f elemPosA = new Vector3f();
/* 59 */   o startOut = new o();
/* 60 */   o endOut = new o();
/* 61 */   Vector3f minOut = new Vector3f();
/* 62 */   Vector3f maxOut = new Vector3f();
/* 63 */   Vector3f nA = new Vector3f();
/* 64 */   Transform tmpTrans3 = new Transform();
/* 65 */   Vector3f distTest = new Vector3f();
/*    */ 
/* 67 */   Float2ObjectAVLTreeMap sorted = new Float2ObjectAVLTreeMap();
/* 68 */   o[] posCache = new o[8];
/* 69 */   int posCachePointer = 0;
/* 70 */   Vector3f lastHitpointWorld = new Vector3f();
/*    */ 
/* 72 */   Vector3f lastDistHitpointWorld = new Vector3f();
/*    */ 
/* 74 */   Vector3f[] bbV = new Vector3f[9];
/*    */ 
/* 77 */   public Matrix3f absolute = new Matrix3f();
/*    */ 
/* 80 */   public RayCubeGridSolver solve = new RayCubeGridSolver();
/*    */ 
/* 83 */   public GjkPairDetectorVariables gjkVar = new GjkPairDetectorVariables();
/*    */ 
/* 86 */   public AABBVarSet aabbVarSet = new AABBVarSet();
/*    */ 
/*    */   public CubeRayVariableSet() {
/* 89 */     for (int i = 0; i < this.bbV.length; i++)
/* 90 */       this.bbV[i] = new Vector3f();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CubeRayVariableSet
 * JD-Core Version:    0.6.2
 */