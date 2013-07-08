package com.bulletphysics.collision.shapes;

import com.bulletphysics..Stack;
import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
import com.bulletphysics.linearmath.MatrixUtil;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.linearmath.VectorUtil;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;

public class CapsuleShape
  extends ConvexInternalShape
{
  protected int upAxis;
  
  CapsuleShape() {}
  
  public CapsuleShape(float radius, float height)
  {
    this.upAxis = 1;
    this.implicitShapeDimensions.set(radius, 0.5F * height, radius);
  }
  
  public Vector3f localGetSupportingVertexWithoutMargin(Vector3f arg1, Vector3f arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f supVec = out;
      supVec.set(0.0F, 0.0F, 0.0F);
      float maxDot = -1.0E+030F;
      Vector3f vec = localStack.get$javax$vecmath$Vector3f(vec0);
      float lenSqr = vec.lengthSquared();
      if (lenSqr < 1.0E-004F)
      {
        vec.set(1.0F, 0.0F, 0.0F);
      }
      else
      {
        float rlen = 1.0F / (float)Math.sqrt(lenSqr);
        vec.scale(rlen);
      }
      Vector3f rlen = localStack.get$javax$vecmath$Vector3f();
      float radius = getRadius();
      Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
      Vector3f pos = localStack.get$javax$vecmath$Vector3f();
      pos.set(0.0F, 0.0F, 0.0F);
      VectorUtil.setCoord(pos, getUpAxis(), getHalfHeight());
      VectorUtil.mul(tmp1, vec, this.localScaling);
      tmp1.scale(radius);
      tmp2.scale(getMargin(), vec);
      rlen.add(pos, tmp1);
      rlen.sub(tmp2);
      float newDot = vec.dot(rlen);
      if (newDot > maxDot)
      {
        maxDot = newDot;
        supVec.set(rlen);
      }
      pos.set(0.0F, 0.0F, 0.0F);
      VectorUtil.setCoord(pos, getUpAxis(), -getHalfHeight());
      VectorUtil.mul(tmp1, vec, this.localScaling);
      tmp1.scale(radius);
      tmp2.scale(getMargin(), vec);
      rlen.add(pos, tmp1);
      rlen.sub(tmp2);
      newDot = vec.dot(rlen);
      if (newDot > maxDot)
      {
        maxDot = newDot;
        supVec.set(rlen);
      }
      return out;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void batchedUnitVectorGetSupportingVertexWithoutMargin(Vector3f[] vectors, Vector3f[] supportVerticesOut, int numVectors)
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public void calculateLocalInertia(float arg1, Vector3f arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      tmp7_5.push$javax$vecmath$Vector3f();
      Transform ident = localStack.get$com$bulletphysics$linearmath$Transform();
      ident.setIdentity();
      float radius = getRadius();
      Vector3f halfExtents = localStack.get$javax$vecmath$Vector3f();
      halfExtents.set(radius, radius, radius);
      VectorUtil.setCoord(halfExtents, getUpAxis(), radius + getHalfHeight());
      float margin = 0.04F;
      float local_lx = 2.0F * (halfExtents.field_615 + margin);
      float local_ly = 2.0F * (halfExtents.field_616 + margin);
      float local_lz = 2.0F * (halfExtents.field_617 + margin);
      float local_x2 = local_lx * local_lx;
      float local_y2 = local_ly * local_ly;
      float local_z2 = local_lz * local_lz;
      float scaledmass = mass * 0.08333333F;
      inertia.field_615 = (scaledmass * (local_y2 + local_z2));
      inertia.field_616 = (scaledmass * (local_x2 + local_z2));
      inertia.field_617 = (scaledmass * (local_x2 + local_y2));
      return;
    }
    finally
    {
      .Stack tmp179_177 = localStack;
      tmp179_177.pop$com$bulletphysics$linearmath$Transform();
      tmp179_177.pop$javax$vecmath$Vector3f();
    }
  }
  
  public BroadphaseNativeType getShapeType()
  {
    return BroadphaseNativeType.CAPSULE_SHAPE_PROXYTYPE;
  }
  
  public void getAabb(Transform arg1, Vector3f arg2, Vector3f arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$javax$vecmath$Vector3f();
      tmp7_5.push$javax$vecmath$Matrix3f();
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      Vector3f halfExtents = localStack.get$javax$vecmath$Vector3f();
      halfExtents.set(getRadius(), getRadius(), getRadius());
      VectorUtil.setCoord(halfExtents, this.upAxis, getRadius() + getHalfHeight());
      halfExtents.field_615 += getMargin();
      halfExtents.field_616 += getMargin();
      halfExtents.field_617 += getMargin();
      Matrix3f abs_b = localStack.get$javax$vecmath$Matrix3f();
      abs_b.set(local_t.basis);
      MatrixUtil.absolute(abs_b);
      Vector3f center = local_t.origin;
      Vector3f extent = localStack.get$javax$vecmath$Vector3f();
      abs_b.getRow(0, tmp);
      extent.field_615 = tmp.dot(halfExtents);
      abs_b.getRow(1, tmp);
      extent.field_616 = tmp.dot(halfExtents);
      abs_b.getRow(2, tmp);
      extent.field_617 = tmp.dot(halfExtents);
      aabbMin.sub(center, extent);
      aabbMax.add(center, extent);
      return;
    }
    finally
    {
      .Stack tmp227_225 = localStack;
      tmp227_225.pop$javax$vecmath$Vector3f();
      tmp227_225.pop$javax$vecmath$Matrix3f();
    }
  }
  
  public String getName()
  {
    return "CapsuleShape";
  }
  
  public int getUpAxis()
  {
    return this.upAxis;
  }
  
  public float getRadius()
  {
    int radiusAxis = (this.upAxis + 2) % 3;
    return VectorUtil.getCoord(this.implicitShapeDimensions, radiusAxis);
  }
  
  public float getHalfHeight()
  {
    return VectorUtil.getCoord(this.implicitShapeDimensions, this.upAxis);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.shapes.CapsuleShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */