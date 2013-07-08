package org.schema.game.common.data.physics;

import class_35;
import class_48;
import class_988;
import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.linearmath.Transform;
import java.util.ArrayList;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import org.schema.game.common.data.physics.octree.IntersectionCallback;
import org.schema.game.common.data.physics.octree.OctreeVariableSet;

public class CubeConvexVariableSet
{
  Vector3f field_1924 = new Vector3f();
  Transform cubeMeshTransform = new Transform();
  Transform convexShapeTransform = new Transform();
  Transform boxTransformation = new Transform();
  Vector3f tmp = new Vector3f();
  Vector3f pos0 = new Vector3f();
  Vector3f pos1 = new Vector3f();
  Vector3f diff = new Vector3f();
  Vector3f normalOnSurfaceB = new Vector3f();
  class_988 outer = new class_988();
  class_988 inner = new class_988();
  class_988 outBB = new class_988();
  class_48 minIntA = new class_48();
  class_48 maxIntA = new class_48();
  class_48 minIntB = new class_48();
  class_48 maxIntB = new class_48();
  Vector3f min = new Vector3f();
  Vector3f max = new Vector3f();
  Vector3f othermin = new Vector3f();
  Vector3f othermax = new Vector3f();
  Vector3f minOut = new Vector3f();
  Vector3f maxOut = new Vector3f();
  Vector3f localMinOut = new Vector3f();
  Vector3f localMaxOut = new Vector3f();
  Vector3f otherminOut = new Vector3f();
  Vector3f othermaxOut = new Vector3f();
  Vector3f elemPosA = new Vector3f();
  Vector3f elemPosB = new Vector3f();
  Vector3f hitMin = new Vector3f();
  Vector3f hitMax = new Vector3f();
  class_35 startA = new class_35();
  class_35 endA = new class_35();
  BoxShape box0 = new BoxShape(new Vector3f(0.56F, 0.56F, 0.56F));
  ArrayList positionCache = new ArrayList();
  ArrayList blockInfoCache = new ArrayList();
  IntersectionCallback intersectionCallBackAwithB = new IntersectionCallback();
  Vector3f shapeMin = new Vector3f();
  Vector3f shapeMax = new Vector3f();
  Vector3f outMin = new Vector3f();
  Vector3f outMax = new Vector3f();
  public Matrix3f absolute = new Matrix3f();
  public OctreeVariableSet oSet;
  public GjkPairDetectorVariables gjkVars = new GjkPairDetectorVariables();
  public Transform inv = new Transform();
  public AABBVarSet aabbVarSet = new AABBVarSet();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.CubeConvexVariableSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */