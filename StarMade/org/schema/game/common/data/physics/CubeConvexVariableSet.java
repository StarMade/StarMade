/*  1:   */package org.schema.game.common.data.physics;
/*  2:   */
/*  3:   */import com.bulletphysics.collision.shapes.BoxShape;
/*  4:   */import com.bulletphysics.linearmath.Transform;
/*  5:   */import java.util.ArrayList;
/*  6:   */import javax.vecmath.Matrix3f;
/*  7:   */import javax.vecmath.Vector3f;
/*  8:   */import o;
/*  9:   */import org.schema.game.common.data.physics.octree.IntersectionCallback;
/* 10:   */import org.schema.game.common.data.physics.octree.OctreeVariableSet;
/* 11:   */import q;
/* 12:   */import xO;
/* 13:   */
/* 19:   */public class CubeConvexVariableSet
/* 20:   */{
/* 21:21 */  Vector3f nA = new Vector3f();
/* 22:22 */  Transform cubeMeshTransform = new Transform();
/* 23:23 */  Transform convexShapeTransform = new Transform();
/* 24:24 */  Transform boxTransformation = new Transform();
/* 25:25 */  Vector3f tmp = new Vector3f();
/* 26:26 */  Vector3f pos0 = new Vector3f();
/* 27:27 */  Vector3f pos1 = new Vector3f();
/* 28:28 */  Vector3f diff = new Vector3f();
/* 29:29 */  Vector3f normalOnSurfaceB = new Vector3f();
/* 30:   */  
/* 32:32 */  xO outer = new xO();
/* 33:33 */  xO inner = new xO();
/* 34:34 */  xO outBB = new xO();
/* 35:35 */  q minIntA = new q();
/* 36:36 */  q maxIntA = new q();
/* 37:37 */  q minIntB = new q();
/* 38:38 */  q maxIntB = new q();
/* 39:   */  
/* 40:40 */  Vector3f min = new Vector3f();
/* 41:41 */  Vector3f max = new Vector3f();
/* 42:42 */  Vector3f othermin = new Vector3f();
/* 43:43 */  Vector3f othermax = new Vector3f();
/* 44:44 */  Vector3f minOut = new Vector3f();
/* 45:45 */  Vector3f maxOut = new Vector3f();
/* 46:46 */  Vector3f localMinOut = new Vector3f();
/* 47:47 */  Vector3f localMaxOut = new Vector3f();
/* 48:48 */  Vector3f otherminOut = new Vector3f();
/* 49:49 */  Vector3f othermaxOut = new Vector3f();
/* 50:   */  
/* 51:51 */  Vector3f elemPosA = new Vector3f();
/* 52:52 */  Vector3f elemPosB = new Vector3f();
/* 53:53 */  Vector3f hitMin = new Vector3f();
/* 54:54 */  Vector3f hitMax = new Vector3f();
/* 55:55 */  o startA = new o();
/* 56:56 */  o endA = new o();
/* 57:   */  
/* 61:61 */  BoxShape box0 = new BoxShape(new Vector3f(0.56F, 0.56F, 0.56F));
/* 62:   */  
/* 66:66 */  ArrayList positionCache = new ArrayList();
/* 67:67 */  ArrayList blockInfoCache = new ArrayList();
/* 68:   */  
/* 69:69 */  IntersectionCallback intersectionCallBackAwithB = new IntersectionCallback();
/* 70:   */  
/* 71:71 */  Vector3f shapeMin = new Vector3f();
/* 72:72 */  Vector3f shapeMax = new Vector3f();
/* 73:73 */  Vector3f outMin = new Vector3f();
/* 74:74 */  Vector3f outMax = new Vector3f();
/* 75:75 */  public Matrix3f absolute = new Matrix3f();
/* 76:   */  public OctreeVariableSet oSet;
/* 77:77 */  public GjkPairDetectorVariables gjkVars = new GjkPairDetectorVariables();
/* 78:78 */  public Transform inv = new Transform();
/* 79:79 */  public AABBVarSet aabbVarSet = new AABBVarSet();
/* 80:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CubeConvexVariableSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */