/*  1:   */package org.schema.game.common.data.physics;
/*  2:   */
/*  3:   */import com.bulletphysics.collision.dispatch.CollisionObject;
/*  4:   */import com.bulletphysics.collision.narrowphase.GjkEpaPenetrationDepthSolver;
/*  5:   */import com.bulletphysics.collision.narrowphase.SimplexSolverInterface;
/*  6:   */import com.bulletphysics.collision.shapes.BoxShape;
/*  7:   */import com.bulletphysics.collision.shapes.ConvexShape;
/*  8:   */import com.bulletphysics.linearmath.Transform;
/*  9:   */import it.unimi.dsi.fastutil.floats.Float2ObjectAVLTreeMap;
/* 10:   */import javax.vecmath.Matrix3f;
/* 11:   */import javax.vecmath.Vector3f;
/* 12:   */import o;
/* 13:   */import org.schema.game.common.data.physics.octree.IntersectionCallback;
/* 14:   */import org.schema.game.common.data.physics.octree.OctreeVariableSet;
/* 15:   */import q;
/* 16:   */import xO;
/* 17:   */
/* 23:   */public class CubeRayVariableSet
/* 24:   */{
/* 25:25 */  BoxShape box0 = new BoxShape(new Vector3f(0.5F, 0.5F, 0.5F));
/* 26:   */  
/* 28:28 */  xO outer = new xO();
/* 29:29 */  xO inner = new xO();
/* 30:30 */  xO outBB = new xO();
/* 31:31 */  q minIntA = new q();
/* 32:32 */  q maxIntA = new q();
/* 33:33 */  q minIntB = new q();
/* 34:34 */  q maxIntB = new q();
/* 35:   */  OctreeVariableSet oSet;
/* 36:   */  SimplexSolverInterface simplexSolver;
/* 37:37 */  GjkEpaPenetrationDepthSolver gjkEpaPenetrationDepthSolver = new GjkEpaPenetrationDepthSolver();
/* 38:   */  ConvexShape shapeA;
/* 39:   */  CubeShape cubesB;
/* 40:40 */  Vector3f outMin = new Vector3f();
/* 41:41 */  Vector3f outMax = new Vector3f();
/* 42:42 */  Vector3f fromHelp = new Vector3f();
/* 43:43 */  Vector3f toHelp = new Vector3f();
/* 44:44 */  Vector3f fromToHelp = new Vector3f();
/* 45:45 */  Vector3f localMinOut = new Vector3f();
/* 46:46 */  Vector3f localMaxOut = new Vector3f();
/* 47:47 */  Vector3f normal = new Vector3f();
/* 48:48 */  float[] hitLambda = new float[1];
/* 49:49 */  IntersectionCallback intersectionCallBack = new IntersectionCallback();
/* 50:50 */  ChangableSphereShape sphereShape = new ChangableSphereShape(0.0F);
/* 51:   */  CollisionObject cubesCollisionObject;
/* 52:   */  static final float margin = 0.15F;
/* 53:53 */  Vector3f distToHit = new Vector3f();
/* 54:54 */  float lastMinDist = -1.0F;
/* 55:   */  
/* 56:56 */  Float2ObjectAVLTreeMap sortedAABB = new Float2ObjectAVLTreeMap();
/* 57:57 */  o elemA = new o();
/* 58:58 */  Vector3f elemPosA = new Vector3f();
/* 59:59 */  o startOut = new o();
/* 60:60 */  o endOut = new o();
/* 61:61 */  Vector3f minOut = new Vector3f();
/* 62:62 */  Vector3f maxOut = new Vector3f();
/* 63:63 */  Vector3f nA = new Vector3f();
/* 64:64 */  Transform tmpTrans3 = new Transform();
/* 65:65 */  Vector3f distTest = new Vector3f();
/* 66:   */  
/* 67:67 */  Float2ObjectAVLTreeMap sorted = new Float2ObjectAVLTreeMap();
/* 68:68 */  o[] posCache = new o[8];
/* 69:69 */  int posCachePointer = 0;
/* 70:70 */  Vector3f lastHitpointWorld = new Vector3f();
/* 71:   */  
/* 72:72 */  Vector3f lastDistHitpointWorld = new Vector3f();
/* 73:   */  
/* 74:74 */  Vector3f[] bbV = new Vector3f[9];
/* 75:   */  
/* 77:77 */  public Matrix3f absolute = new Matrix3f();
/* 78:   */  
/* 80:80 */  public RayCubeGridSolver solve = new RayCubeGridSolver();
/* 81:   */  
/* 83:83 */  public GjkPairDetectorVariables gjkVar = new GjkPairDetectorVariables();
/* 84:   */  
/* 86:86 */  public AABBVarSet aabbVarSet = new AABBVarSet();
/* 87:   */  
/* 88:   */  public CubeRayVariableSet() {
/* 89:89 */    for (int i = 0; i < this.bbV.length; i++) {
/* 90:90 */      this.bbV[i] = new Vector3f();
/* 91:   */    }
/* 92:   */  }
/* 93:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CubeRayVariableSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */