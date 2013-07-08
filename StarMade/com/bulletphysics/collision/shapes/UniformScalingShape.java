package com.bulletphysics.collision.shapes;

import com.bulletphysics..Stack;
import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Vector3f;

public class UniformScalingShape
  extends ConvexShape
{
  private ConvexShape childConvexShape;
  private float uniformScalingFactor;
  
  public UniformScalingShape(ConvexShape convexChildShape, float uniformScalingFactor)
  {
    this.childConvexShape = convexChildShape;
    this.uniformScalingFactor = uniformScalingFactor;
  }
  
  public float getUniformScalingFactor()
  {
    return this.uniformScalingFactor;
  }
  
  public ConvexShape getChildShape()
  {
    return this.childConvexShape;
  }
  
  public Vector3f localGetSupportingVertex(Vector3f vec, Vector3f out)
  {
    this.childConvexShape.localGetSupportingVertex(vec, out);
    out.scale(this.uniformScalingFactor);
    return out;
  }
  
  public Vector3f localGetSupportingVertexWithoutMargin(Vector3f vec, Vector3f out)
  {
    this.childConvexShape.localGetSupportingVertexWithoutMargin(vec, out);
    out.scale(this.uniformScalingFactor);
    return out;
  }
  
  public void batchedUnitVectorGetSupportingVertexWithoutMargin(Vector3f[] vectors, Vector3f[] supportVerticesOut, int numVectors)
  {
    this.childConvexShape.batchedUnitVectorGetSupportingVertexWithoutMargin(vectors, supportVerticesOut, numVectors);
    for (int local_i = 0; local_i < numVectors; local_i++) {
      supportVerticesOut[local_i].scale(this.uniformScalingFactor);
    }
  }
  
  public void getAabbSlow(Transform arg1, Vector3f arg2, Vector3f arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      this.childConvexShape.getAabbSlow(local_t, aabbMin, aabbMax);
      Vector3f aabbCenter = localStack.get$javax$vecmath$Vector3f();
      aabbCenter.add(aabbMax, aabbMin);
      aabbCenter.scale(0.5F);
      Vector3f scaledAabbHalfExtends = localStack.get$javax$vecmath$Vector3f();
      scaledAabbHalfExtends.sub(aabbMax, aabbMin);
      scaledAabbHalfExtends.scale(0.5F * this.uniformScalingFactor);
      aabbMin.sub(aabbCenter, scaledAabbHalfExtends);
      aabbMax.add(aabbCenter, scaledAabbHalfExtends);
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void setLocalScaling(Vector3f scaling)
  {
    this.childConvexShape.setLocalScaling(scaling);
  }
  
  public Vector3f getLocalScaling(Vector3f out)
  {
    this.childConvexShape.getLocalScaling(out);
    return out;
  }
  
  public void setMargin(float margin)
  {
    this.childConvexShape.setMargin(margin);
  }
  
  public float getMargin()
  {
    return this.childConvexShape.getMargin() * this.uniformScalingFactor;
  }
  
  public int getNumPreferredPenetrationDirections()
  {
    return this.childConvexShape.getNumPreferredPenetrationDirections();
  }
  
  public void getPreferredPenetrationDirection(int index, Vector3f penetrationVector)
  {
    this.childConvexShape.getPreferredPenetrationDirection(index, penetrationVector);
  }
  
  public void getAabb(Transform arg1, Vector3f arg2, Vector3f arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      this.childConvexShape.getAabb(local_t, aabbMin, aabbMax);
      Vector3f aabbCenter = localStack.get$javax$vecmath$Vector3f();
      aabbCenter.add(aabbMax, aabbMin);
      aabbCenter.scale(0.5F);
      Vector3f scaledAabbHalfExtends = localStack.get$javax$vecmath$Vector3f();
      scaledAabbHalfExtends.sub(aabbMax, aabbMin);
      scaledAabbHalfExtends.scale(0.5F * this.uniformScalingFactor);
      aabbMin.sub(aabbCenter, scaledAabbHalfExtends);
      aabbMax.add(aabbCenter, scaledAabbHalfExtends);
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public BroadphaseNativeType getShapeType()
  {
    return BroadphaseNativeType.UNIFORM_SCALING_SHAPE_PROXYTYPE;
  }
  
  public void calculateLocalInertia(float mass, Vector3f inertia)
  {
    this.childConvexShape.calculateLocalInertia(mass, inertia);
    inertia.scale(this.uniformScalingFactor);
  }
  
  public String getName()
  {
    return "UniformScalingShape";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.shapes.UniformScalingShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */