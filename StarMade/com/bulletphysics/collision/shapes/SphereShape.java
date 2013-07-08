package com.bulletphysics.collision.shapes;

import com.bulletphysics..Stack;
import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Vector3f;

public class SphereShape
  extends ConvexInternalShape
{
  public SphereShape(float radius)
  {
    this.implicitShapeDimensions.field_615 = radius;
    this.collisionMargin = radius;
  }
  
  public Vector3f localGetSupportingVertexWithoutMargin(Vector3f vec, Vector3f out)
  {
    out.set(0.0F, 0.0F, 0.0F);
    return out;
  }
  
  public void batchedUnitVectorGetSupportingVertexWithoutMargin(Vector3f[] vectors, Vector3f[] supportVerticesOut, int numVectors)
  {
    for (int local_i = 0; local_i < numVectors; local_i++) {
      supportVerticesOut[local_i].set(0.0F, 0.0F, 0.0F);
    }
  }
  
  public void getAabb(Transform arg1, Vector3f arg2, Vector3f arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f center = local_t.origin;
      Vector3f extent = localStack.get$javax$vecmath$Vector3f();
      extent.set(getMargin(), getMargin(), getMargin());
      aabbMin.sub(center, extent);
      aabbMax.add(center, extent);
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public BroadphaseNativeType getShapeType()
  {
    return BroadphaseNativeType.SPHERE_SHAPE_PROXYTYPE;
  }
  
  public void calculateLocalInertia(float mass, Vector3f inertia)
  {
    float elem = 0.4F * mass * getMargin() * getMargin();
    inertia.set(elem, elem, elem);
  }
  
  public String getName()
  {
    return "SPHERE";
  }
  
  public float getRadius()
  {
    return this.implicitShapeDimensions.field_615 * this.localScaling.field_615;
  }
  
  public void setMargin(float margin)
  {
    super.setMargin(margin);
  }
  
  public float getMargin()
  {
    return getRadius();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.shapes.SphereShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */