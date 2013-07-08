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
import org.schema.game.common.data.physics.octree.OctreeVariableSet;

public class CubeRayVariableSet
{
  BoxShape box0 = new BoxShape(new Vector3f(0.5F, 0.5F, 0.5F));
  class_988 outer = new class_988();
  class_988 inner = new class_988();
  class_988 outBB = new class_988();
  class_48 minIntA = new class_48();
  class_48 maxIntA = new class_48();
  class_48 minIntB = new class_48();
  class_48 maxIntB = new class_48();
  OctreeVariableSet oSet;
  SimplexSolverInterface simplexSolver;
  GjkEpaPenetrationDepthSolver gjkEpaPenetrationDepthSolver = new GjkEpaPenetrationDepthSolver();
  ConvexShape shapeA;
  CubeShape cubesB;
  Vector3f outMin = new Vector3f();
  Vector3f outMax = new Vector3f();
  Vector3f fromHelp = new Vector3f();
  Vector3f toHelp = new Vector3f();
  Vector3f fromToHelp = new Vector3f();
  Vector3f localMinOut = new Vector3f();
  Vector3f localMaxOut = new Vector3f();
  Vector3f normal = new Vector3f();
  float[] hitLambda = new float[1];
  IntersectionCallback intersectionCallBack = new IntersectionCallback();
  ChangableSphereShape sphereShape = new ChangableSphereShape(0.0F);
  CollisionObject cubesCollisionObject;
  static final float margin = 0.15F;
  Vector3f distToHit = new Vector3f();
  float lastMinDist = -1.0F;
  Float2ObjectAVLTreeMap sortedAABB = new Float2ObjectAVLTreeMap();
  class_35 elemA = new class_35();
  Vector3f elemPosA = new Vector3f();
  class_35 startOut = new class_35();
  class_35 endOut = new class_35();
  Vector3f minOut = new Vector3f();
  Vector3f maxOut = new Vector3f();
  Vector3f field_1803 = new Vector3f();
  Transform tmpTrans3 = new Transform();
  Vector3f distTest = new Vector3f();
  Float2ObjectAVLTreeMap sorted = new Float2ObjectAVLTreeMap();
  class_35[] posCache = new class_35[8];
  int posCachePointer = 0;
  Vector3f lastHitpointWorld = new Vector3f();
  Vector3f lastDistHitpointWorld = new Vector3f();
  Vector3f[] bbV = new Vector3f[9];
  public Matrix3f absolute = new Matrix3f();
  public RayCubeGridSolver solve = new RayCubeGridSolver();
  public GjkPairDetectorVariables gjkVar = new GjkPairDetectorVariables();
  public AABBVarSet aabbVarSet = new AABBVarSet();
  
  public CubeRayVariableSet()
  {
    for (int i = 0; i < this.bbV.length; i++) {
      this.bbV[i] = new Vector3f();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.CubeRayVariableSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */