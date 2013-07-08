package org.schema.game.common.data.physics;

import class_35;
import class_48;
import class_988;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.narrowphase.GjkEpaPenetrationDepthSolver;
import com.bulletphysics.collision.narrowphase.SimplexSolverInterface;
import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.collision.shapes.ConvexShape;
import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.floats.Float2ObjectAVLTreeMap;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import org.schema.game.common.data.physics.octree.IntersectionCallback;

public class CubeConvexCastVariableSet
{
  Vector3f outMin = new Vector3f();
  Vector3f outMax = new Vector3f();
  Vector3f fromHelp = new Vector3f();
  Vector3f toHelp = new Vector3f();
  Vector3f fromToHelp = new Vector3f();
  Vector3f normal = new Vector3f();
  Vector3f localMinOut = new Vector3f();
  Vector3f localMaxOut = new Vector3f();
  float[] hitLambda = new float[1];
  IntersectionCallback intersectionCallBack = new IntersectionCallback();
  class_988 outer = new class_988();
  class_988 inner = new class_988();
  class_988 outBB = new class_988();
  class_48 minIntA = new class_48();
  class_48 maxIntA = new class_48();
  class_48 minIntB = new class_48();
  class_48 maxIntB = new class_48();
  ChangableSphereShape sphereShape = new ChangableSphereShape(0.0F);
  CollisionObject cubesObject;
  Transform field_489 = new Transform();
  Transform field_490 = new Transform();
  Float2ObjectAVLTreeMap sortedAABB = new Float2ObjectAVLTreeMap();
  class_35 elemA = new class_35();
  Vector3f elemPosA = new Vector3f();
  class_35 startOut = new class_35();
  class_35 endOut = new class_35();
  Vector3f minOut = new Vector3f();
  Vector3f maxOut = new Vector3f();
  Vector3f field_491 = new Vector3f();
  Transform tmpTrans = new Transform();
  Vector3f distTest = new Vector3f();
  Float2ObjectAVLTreeMap sorted = new Float2ObjectAVLTreeMap();
  class_35[] posCache = new class_35[8];
  int posCachePointer = 0;
  Vector3f castedAABBMin = new Vector3f();
  Vector3f castedAABBMax = new Vector3f();
  Vector3f convexFromAABBMin = new Vector3f();
  Vector3f convexFromAABBMax = new Vector3f();
  Vector3f convexToAABBMin = new Vector3f();
  Vector3f convexToAABBMax = new Vector3f();
  Vector3f tmp = new Vector3f();
  Transform tmpTrans1 = new Transform();
  Transform tmpTrans2 = new Transform();
  Transform tmpTrans3 = new Transform();
  Transform tmpTrans4 = new Transform();
  boolean disableCcd = false;
  BoxShape box0 = new BoxShape(new Vector3f(0.501F, 0.501F, 0.501F));
  SimplexSolverInterface simplexSolver;
  ConvexShape shapeA;
  CubeShape cubesB;
  public Matrix3f absolute1 = new Matrix3f();
  public Vector3f dist = new Vector3f();
  public GjkPairDetectorVariables gjkVar = new GjkPairDetectorVariables();
  public GjkEpaPenetrationDepthSolver gjkEpaPenetrationDepthSolver = new GjkEpaPenetrationDepthSolver();
  public Transform inv = new Transform();
  public AABBVarSet aabbVarSet = new AABBVarSet();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.CubeConvexCastVariableSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */