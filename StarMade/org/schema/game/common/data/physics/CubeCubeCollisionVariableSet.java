/*  1:   */package org.schema.game.common.data.physics;
/*  2:   */
/*  3:   */import com.bulletphysics.collision.shapes.BoxShape;
/*  4:   */import com.bulletphysics.linearmath.Transform;
/*  5:   */import javax.vecmath.Matrix3f;
/*  6:   */import javax.vecmath.Vector3f;
/*  7:   */import le;
/*  8:   */import o;
/*  9:   */import org.schema.game.common.data.physics.octree.IntersectionCallback;
/* 10:   */import org.schema.game.common.data.physics.octree.OctreeVariableSet;
/* 11:   */import q;
/* 12:   */import xO;
/* 13:   */
/* 16:   */public class CubeCubeCollisionVariableSet
/* 17:   */{
/* 18:18 */  Transform tmpTrans1 = new Transform();
/* 19:19 */  Transform tmpTrans2 = new Transform();
/* 20:20 */  Transform tmpTrans3 = new Transform();
/* 21:21 */  Transform tmpTrans4 = new Transform();
/* 22:22 */  Vector3f localMinOut = new Vector3f();
/* 23:23 */  Vector3f localMaxOut = new Vector3f();
/* 24:24 */  Vector3f tmp = new Vector3f();
/* 25:25 */  Vector3f pos0 = new Vector3f();
/* 26:26 */  Vector3f pos1 = new Vector3f();
/* 27:27 */  Vector3f diff = new Vector3f();
/* 28:28 */  Vector3f normalOnSurfaceB = new Vector3f();
/* 29:   */  
/* 30:   */  OctreeVariableSet oSet;
/* 31:31 */  xO outer = new xO();
/* 32:32 */  xO inner = new xO();
/* 33:33 */  xO outBB = new xO();
/* 34:34 */  xO outBBCopy = new xO();
/* 35:35 */  q minIntA = new q();
/* 36:36 */  q maxIntA = new q();
/* 37:37 */  q minIntB = new q();
/* 38:38 */  q maxIntB = new q();
/* 39:   */  
/* 40:40 */  Vector3f min = new Vector3f();
/* 41:41 */  Vector3f max = new Vector3f();
/* 42:42 */  Vector3f bMinOut = new Vector3f();
/* 43:43 */  Vector3f bMaxOut = new Vector3f();
/* 44:44 */  Vector3f minOut = new Vector3f();
/* 45:45 */  Vector3f maxOut = new Vector3f();
/* 46:46 */  Vector3f othermin = new Vector3f();
/* 47:47 */  Vector3f othermax = new Vector3f();
/* 48:   */  
/* 49:49 */  Vector3f elemPosA = new Vector3f();
/* 50:50 */  Vector3f elemPosB = new Vector3f();
/* 51:51 */  Vector3f elemPosAAbs = new Vector3f();
/* 52:52 */  Vector3f elemPosBAbs = new Vector3f();
/* 53:   */  
/* 57:57 */  o startA = new o();
/* 58:58 */  o startB = new o();
/* 59:59 */  o endA = new o();
/* 60:60 */  o endB = new o();
/* 61:61 */  BoxShape box0 = new BoxShape(new Vector3f(0.5F, 0.5F, 0.5F));
/* 62:   */  
/* 63:63 */  BoxShape box1 = new BoxShape(new Vector3f(0.5F, 0.5F, 0.5F));
/* 64:   */  
/* 67:67 */  IntersectionCallback intersectionCallBackAwithB = new IntersectionCallback();
/* 68:68 */  IntersectionCallback intersectionCallBackBwithA = new IntersectionCallback();
/* 69:   */  
/* 70:70 */  Vector3f outInnerMin = new Vector3f();
/* 71:71 */  Vector3f outInnerMax = new Vector3f();
/* 72:72 */  Vector3f outOuterMin = new Vector3f();
/* 73:73 */  Vector3f outOuterMax = new Vector3f();
/* 74:   */  
/* 75:75 */  Vector3f nA = new Vector3f();
/* 76:76 */  Vector3f nB = new Vector3f();
/* 77:   */  
/* 78:78 */  Vector3f otherMinIn = new Vector3f();
/* 79:79 */  Vector3f otherMaxIn = new Vector3f();
/* 80:   */  
/* 81:81 */  xO bbOuterSeg = new xO();
/* 82:82 */  xO bbInnerSeg = new xO();
/* 83:83 */  xO bbSectorIntersection = new xO();
/* 84:84 */  xO bbSectorIntersectionTest = new xO();
/* 85:85 */  xO bbOuterOct = new xO();
/* 86:86 */  xO bbInnerOct = new xO();
/* 87:87 */  xO bbOctIntersection = new xO();
/* 88:88 */  public Matrix3f absolute1 = new Matrix3f();
/* 89:89 */  public Matrix3f absolute2 = new Matrix3f();
/* 90:90 */  public GjkPairDetectorVariables gjkVar = new GjkPairDetectorVariables();
/* 91:91 */  public Transform wtInv1 = new Transform();
/* 92:92 */  public Transform wtInv0 = new Transform();
/* 93:93 */  public AABBVarSet aabbVarSet = new AABBVarSet();
/* 94:94 */  public Vector3f elemPosTest = new Vector3f();
/* 95:95 */  public Vector3f elemPosTestTmp = new Vector3f();
/* 96:96 */  public q elemPosCheck = new q();
/* 97:97 */  public q elemPosCheckD = new q();
/* 98:98 */  public le pieceTmp = new le();
/* 99:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CubeCubeCollisionVariableSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */