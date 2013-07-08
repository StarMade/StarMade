package com.bulletphysics.collision.shapes;

import com.bulletphysics..Stack;
import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
import com.bulletphysics.linearmath.MatrixUtil;
import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Vector3f;

public class MinkowskiSumShape
  extends ConvexInternalShape
{
  private final Transform transA = new Transform();
  private final Transform transB = new Transform();
  private ConvexShape shapeA;
  private ConvexShape shapeB;
  
  public MinkowskiSumShape(ConvexShape shapeA, ConvexShape shapeB)
  {
    this.shapeA = shapeA;
    this.shapeB = shapeB;
    this.transA.setIdentity();
    this.transB.setIdentity();
  }
  
  public Vector3f localGetSupportingVertexWithoutMargin(Vector3f arg1, Vector3f arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      Vector3f supVertexA = localStack.get$javax$vecmath$Vector3f();
      Vector3f supVertexB = localStack.get$javax$vecmath$Vector3f();
      tmp.negate(vec);
      MatrixUtil.transposeTransform(tmp, tmp, this.transA.basis);
      this.shapeA.localGetSupportingVertexWithoutMargin(tmp, supVertexA);
      this.transA.transform(supVertexA);
      MatrixUtil.transposeTransform(tmp, vec, this.transB.basis);
      this.shapeB.localGetSupportingVertexWithoutMargin(tmp, supVertexB);
      this.transB.transform(supVertexB);
      out.sub(supVertexA, supVertexB);
      return out;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void batchedUnitVectorGetSupportingVertexWithoutMargin(Vector3f[] vectors, Vector3f[] supportVerticesOut, int numVectors)
  {
    for (int local_i = 0; local_i < numVectors; local_i++) {
      localGetSupportingVertexWithoutMargin(vectors[local_i], supportVerticesOut[local_i]);
    }
  }
  
  public void getAabb(Transform local_t, Vector3f aabbMin, Vector3f aabbMax)
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public BroadphaseNativeType getShapeType()
  {
    return BroadphaseNativeType.MINKOWSKI_SUM_SHAPE_PROXYTYPE;
  }
  
  public void calculateLocalInertia(float mass, Vector3f inertia)
  {
    if (!$assertionsDisabled) {
      throw new AssertionError();
    }
    inertia.set(0.0F, 0.0F, 0.0F);
  }
  
  public String getName()
  {
    return "MinkowskiSum";
  }
  
  public float getMargin()
  {
    return this.shapeA.getMargin() + this.shapeB.getMargin();
  }
  
  public void setTransformA(Transform transA)
  {
    this.transA.set(transA);
  }
  
  public void setTransformB(Transform transB)
  {
    this.transB.set(transB);
  }
  
  public void getTransformA(Transform dest)
  {
    dest.set(this.transA);
  }
  
  public void getTransformB(Transform dest)
  {
    dest.set(this.transB);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.shapes.MinkowskiSumShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */