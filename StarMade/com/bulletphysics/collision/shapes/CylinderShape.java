package com.bulletphysics.collision.shapes;

import com.bulletphysics..Stack;
import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.linearmath.VectorUtil;
import javax.vecmath.Vector3f;

public class CylinderShape
  extends BoxShape
{
  protected int upAxis;
  
  public CylinderShape(Vector3f halfExtents)
  {
    super(halfExtents);
    this.upAxis = 1;
    recalcLocalAabb();
  }
  
  protected CylinderShape(Vector3f halfExtents, boolean unused)
  {
    super(halfExtents);
  }
  
  public void getAabb(Transform local_t, Vector3f aabbMin, Vector3f aabbMax)
  {
    _PolyhedralConvexShape_getAabb(local_t, aabbMin, aabbMax);
  }
  
  protected Vector3f cylinderLocalSupportX(Vector3f halfExtents, Vector3f local_v, Vector3f out)
  {
    return cylinderLocalSupport(halfExtents, local_v, 0, 1, 0, 2, out);
  }
  
  protected Vector3f cylinderLocalSupportY(Vector3f halfExtents, Vector3f local_v, Vector3f out)
  {
    return cylinderLocalSupport(halfExtents, local_v, 1, 0, 1, 2, out);
  }
  
  protected Vector3f cylinderLocalSupportZ(Vector3f halfExtents, Vector3f local_v, Vector3f out)
  {
    return cylinderLocalSupport(halfExtents, local_v, 2, 0, 2, 1, out);
  }
  
  private Vector3f cylinderLocalSupport(Vector3f halfExtents, Vector3f local_v, int cylinderUpAxis, int local_XX, int local_YY, int local_ZZ, Vector3f out)
  {
    float radius = VectorUtil.getCoord(halfExtents, local_XX);
    float halfHeight = VectorUtil.getCoord(halfExtents, cylinderUpAxis);
    float local_s = (float)Math.sqrt(VectorUtil.getCoord(local_v, local_XX) * VectorUtil.getCoord(local_v, local_XX) + VectorUtil.getCoord(local_v, local_ZZ) * VectorUtil.getCoord(local_v, local_ZZ));
    if (local_s != 0.0F)
    {
      float local_d = radius / local_s;
      VectorUtil.setCoord(out, local_XX, VectorUtil.getCoord(local_v, local_XX) * local_d);
      VectorUtil.setCoord(out, local_YY, VectorUtil.getCoord(local_v, local_YY) < 0.0F ? -halfHeight : halfHeight);
      VectorUtil.setCoord(out, local_ZZ, VectorUtil.getCoord(local_v, local_ZZ) * local_d);
      return out;
    }
    VectorUtil.setCoord(out, local_XX, radius);
    VectorUtil.setCoord(out, local_YY, VectorUtil.getCoord(local_v, local_YY) < 0.0F ? -halfHeight : halfHeight);
    VectorUtil.setCoord(out, local_ZZ, 0.0F);
    return out;
  }
  
  public Vector3f localGetSupportingVertexWithoutMargin(Vector3f arg1, Vector3f arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      return cylinderLocalSupportY(getHalfExtentsWithoutMargin(localStack.get$javax$vecmath$Vector3f()), vec, out);
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void batchedUnitVectorGetSupportingVertexWithoutMargin(Vector3f[] arg1, Vector3f[] arg2, int arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      for (int local_i = 0; local_i < numVectors; local_i++) {
        cylinderLocalSupportY(getHalfExtentsWithoutMargin(localStack.get$javax$vecmath$Vector3f()), vectors[local_i], supportVerticesOut[local_i]);
      }
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public Vector3f localGetSupportingVertex(Vector3f arg1, Vector3f arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f supVertex = out;
      localGetSupportingVertexWithoutMargin(vec, supVertex);
      if (getMargin() != 0.0F)
      {
        Vector3f vecnorm = localStack.get$javax$vecmath$Vector3f(vec);
        if (vecnorm.lengthSquared() < 1.421086E-014F) {
          vecnorm.set(-1.0F, -1.0F, -1.0F);
        }
        vecnorm.normalize();
        supVertex.scaleAdd(getMargin(), vecnorm, supVertex);
      }
      return out;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public BroadphaseNativeType getShapeType()
  {
    return BroadphaseNativeType.CYLINDER_SHAPE_PROXYTYPE;
  }
  
  public int getUpAxis()
  {
    return this.upAxis;
  }
  
  public float getRadius()
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      return getHalfExtentsWithMargin(localStack.get$javax$vecmath$Vector3f()).field_615;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public String getName()
  {
    return "CylinderY";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.shapes.CylinderShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */