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
/* 14:   */import q;
/* 15:   */import xO;
/* 16:   */
/* 26:   */public class CubeConvexCastVariableSet
/* 27:   */{
/* 28:28 */  Vector3f outMin = new Vector3f();
/* 29:29 */  Vector3f outMax = new Vector3f();
/* 30:30 */  Vector3f fromHelp = new Vector3f();
/* 31:31 */  Vector3f toHelp = new Vector3f();
/* 32:32 */  Vector3f fromToHelp = new Vector3f();
/* 33:33 */  Vector3f normal = new Vector3f();
/* 34:34 */  Vector3f localMinOut = new Vector3f();
/* 35:35 */  Vector3f localMaxOut = new Vector3f();
/* 36:36 */  float[] hitLambda = new float[1];
/* 37:37 */  IntersectionCallback intersectionCallBack = new IntersectionCallback();
/* 38:   */  
/* 39:39 */  xO outer = new xO();
/* 40:40 */  xO inner = new xO();
/* 41:41 */  xO outBB = new xO();
/* 42:42 */  q minIntA = new q();
/* 43:43 */  q maxIntA = new q();
/* 44:44 */  q minIntB = new q();
/* 45:45 */  q maxIntB = new q();
/* 46:   */  
/* 47:47 */  ChangableSphereShape sphereShape = new ChangableSphereShape(0.0F);
/* 48:   */  CollisionObject cubesObject;
/* 49:49 */  Transform f = new Transform();
/* 50:50 */  Transform t = new Transform();
/* 51:51 */  Float2ObjectAVLTreeMap sortedAABB = new Float2ObjectAVLTreeMap();
/* 52:52 */  o elemA = new o();
/* 53:53 */  Vector3f elemPosA = new Vector3f();
/* 54:54 */  o startOut = new o();
/* 55:55 */  o endOut = new o();
/* 56:56 */  Vector3f minOut = new Vector3f();
/* 57:57 */  Vector3f maxOut = new Vector3f();
/* 58:58 */  Vector3f nA = new Vector3f();
/* 59:59 */  Transform tmpTrans = new Transform();
/* 60:60 */  Vector3f distTest = new Vector3f();
/* 61:61 */  Float2ObjectAVLTreeMap sorted = new Float2ObjectAVLTreeMap();
/* 62:62 */  o[] posCache = new o[8];
/* 63:63 */  int posCachePointer = 0;
/* 64:   */  
/* 65:65 */  Vector3f castedAABBMin = new Vector3f();
/* 66:66 */  Vector3f castedAABBMax = new Vector3f();
/* 67:67 */  Vector3f convexFromAABBMin = new Vector3f();
/* 68:68 */  Vector3f convexFromAABBMax = new Vector3f();
/* 69:69 */  Vector3f convexToAABBMin = new Vector3f();
/* 70:70 */  Vector3f convexToAABBMax = new Vector3f();
/* 71:   */  
/* 72:72 */  Vector3f tmp = new Vector3f();
/* 73:   */  
/* 74:74 */  Transform tmpTrans1 = new Transform();
/* 75:75 */  Transform tmpTrans2 = new Transform();
/* 76:76 */  Transform tmpTrans3 = new Transform();
/* 77:77 */  Transform tmpTrans4 = new Transform();
/* 78:78 */  boolean disableCcd = false;
/* 79:   */  
/* 80:80 */  BoxShape box0 = new BoxShape(new Vector3f(0.501F, 0.501F, 0.501F));
/* 81:   */  
/* 82:   */  SimplexSolverInterface simplexSolver;
/* 83:   */  
/* 84:   */  ConvexShape shapeA;
/* 85:   */  CubeShape cubesB;
/* 86:86 */  public Matrix3f absolute1 = new Matrix3f();
/* 87:87 */  public Vector3f dist = new Vector3f();
/* 88:88 */  public GjkPairDetectorVariables gjkVar = new GjkPairDetectorVariables();
/* 89:89 */  public GjkEpaPenetrationDepthSolver gjkEpaPenetrationDepthSolver = new GjkEpaPenetrationDepthSolver();
/* 90:90 */  public Transform inv = new Transform();
/* 91:91 */  public AABBVarSet aabbVarSet = new AABBVarSet();
/* 92:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CubeConvexCastVariableSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */