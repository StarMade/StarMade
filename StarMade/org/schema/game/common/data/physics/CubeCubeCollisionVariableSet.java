package org.schema.game.common.data.physics;

import class_35;
import class_48;
import class_796;
import class_988;
import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import org.schema.game.common.data.physics.octree.IntersectionCallback;
import org.schema.game.common.data.physics.octree.OctreeVariableSet;

public class CubeCubeCollisionVariableSet
{
  Transform tmpTrans1 = new Transform();
  Transform tmpTrans2 = new Transform();
  Transform tmpTrans3 = new Transform();
  Transform tmpTrans4 = new Transform();
  Vector3f localMinOut = new Vector3f();
  Vector3f localMaxOut = new Vector3f();
  Vector3f tmp = new Vector3f();
  Vector3f pos0 = new Vector3f();
  Vector3f pos1 = new Vector3f();
  Vector3f diff = new Vector3f();
  Vector3f normalOnSurfaceB = new Vector3f();
  OctreeVariableSet oSet;
  class_988 outer = new class_988();
  class_988 inner = new class_988();
  class_988 outBB = new class_988();
  class_988 outBBCopy = new class_988();
  class_48 minIntA = new class_48();
  class_48 maxIntA = new class_48();
  class_48 minIntB = new class_48();
  class_48 maxIntB = new class_48();
  Vector3f min = new Vector3f();
  Vector3f max = new Vector3f();
  Vector3f bMinOut = new Vector3f();
  Vector3f bMaxOut = new Vector3f();
  Vector3f minOut = new Vector3f();
  Vector3f maxOut = new Vector3f();
  Vector3f othermin = new Vector3f();
  Vector3f othermax = new Vector3f();
  Vector3f elemPosA = new Vector3f();
  Vector3f elemPosB = new Vector3f();
  Vector3f elemPosAAbs = new Vector3f();
  Vector3f elemPosBAbs = new Vector3f();
  class_35 startA = new class_35();
  class_35 startB = new class_35();
  class_35 endA = new class_35();
  class_35 endB = new class_35();
  BoxShape box0 = new BoxShape(new Vector3f(0.5F, 0.5F, 0.5F));
  BoxShape box1 = new BoxShape(new Vector3f(0.5F, 0.5F, 0.5F));
  IntersectionCallback intersectionCallBackAwithB = new IntersectionCallback();
  IntersectionCallback intersectionCallBackBwithA = new IntersectionCallback();
  Vector3f outInnerMin = new Vector3f();
  Vector3f outInnerMax = new Vector3f();
  Vector3f outOuterMin = new Vector3f();
  Vector3f outOuterMax = new Vector3f();
  Vector3f field_1867 = new Vector3f();
  Vector3f field_1868 = new Vector3f();
  Vector3f otherMinIn = new Vector3f();
  Vector3f otherMaxIn = new Vector3f();
  class_988 bbOuterSeg = new class_988();
  class_988 bbInnerSeg = new class_988();
  class_988 bbSectorIntersection = new class_988();
  class_988 bbSectorIntersectionTest = new class_988();
  class_988 bbOuterOct = new class_988();
  class_988 bbInnerOct = new class_988();
  class_988 bbOctIntersection = new class_988();
  public Matrix3f absolute1 = new Matrix3f();
  public Matrix3f absolute2 = new Matrix3f();
  public GjkPairDetectorVariables gjkVar = new GjkPairDetectorVariables();
  public Transform wtInv1 = new Transform();
  public Transform wtInv0 = new Transform();
  public AABBVarSet aabbVarSet = new AABBVarSet();
  public Vector3f elemPosTest = new Vector3f();
  public Vector3f elemPosTestTmp = new Vector3f();
  public class_48 elemPosCheck = new class_48();
  public class_48 elemPosCheckD = new class_48();
  public class_796 pieceTmp = new class_796();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.CubeCubeCollisionVariableSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */