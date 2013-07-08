package com.bulletphysics.collision.shapes;

import com.bulletphysics..Stack;
import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.linearmath.VectorUtil;
import javax.vecmath.Vector3f;

public class ConeShape
  extends ConvexInternalShape
{
  private float sinAngle;
  private float radius;
  private float height;
  private int[] coneIndices = new int[3];
  
  public ConeShape(float radius, float height)
  {
    this.radius = radius;
    this.height = height;
    setConeUpIndex(1);
    this.sinAngle = (radius / (float)Math.sqrt(this.radius * this.radius + this.height * this.height));
  }
  
  public float getRadius()
  {
    return this.radius;
  }
  
  public float getHeight()
  {
    return this.height;
  }
  
  private Vector3f coneLocalSupport(Vector3f local_v, Vector3f out)
  {
    float halfHeight = this.height * 0.5F;
    if (VectorUtil.getCoord(local_v, this.coneIndices[1]) > local_v.length() * this.sinAngle)
    {
      VectorUtil.setCoord(out, this.coneIndices[0], 0.0F);
      VectorUtil.setCoord(out, this.coneIndices[1], halfHeight);
      VectorUtil.setCoord(out, this.coneIndices[2], 0.0F);
      return out;
    }
    float local_v0 = VectorUtil.getCoord(local_v, this.coneIndices[0]);
    float local_v2 = VectorUtil.getCoord(local_v, this.coneIndices[2]);
    float local_s = (float)Math.sqrt(local_v0 * local_v0 + local_v2 * local_v2);
    if (local_s > 1.192093E-007F)
    {
      float local_d = this.radius / local_s;
      VectorUtil.setCoord(out, this.coneIndices[0], VectorUtil.getCoord(local_v, this.coneIndices[0]) * local_d);
      VectorUtil.setCoord(out, this.coneIndices[1], -halfHeight);
      VectorUtil.setCoord(out, this.coneIndices[2], VectorUtil.getCoord(local_v, this.coneIndices[2]) * local_d);
      return out;
    }
    VectorUtil.setCoord(out, this.coneIndices[0], 0.0F);
    VectorUtil.setCoord(out, this.coneIndices[1], -halfHeight);
    VectorUtil.setCoord(out, this.coneIndices[2], 0.0F);
    return out;
  }
  
  public Vector3f localGetSupportingVertexWithoutMargin(Vector3f vec, Vector3f out)
  {
    return coneLocalSupport(vec, out);
  }
  
  public void batchedUnitVectorGetSupportingVertexWithoutMargin(Vector3f[] vectors, Vector3f[] supportVerticesOut, int numVectors)
  {
    for (int local_i = 0; local_i < numVectors; local_i++)
    {
      Vector3f vec = vectors[local_i];
      coneLocalSupport(vec, supportVerticesOut[local_i]);
    }
  }
  
  public Vector3f localGetSupportingVertex(Vector3f arg1, Vector3f arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f supVertex = coneLocalSupport(vec, out);
      if (getMargin() != 0.0F)
      {
        Vector3f vecnorm = localStack.get$javax$vecmath$Vector3f(vec);
        if (vecnorm.lengthSquared() < 1.421086E-014F) {
          vecnorm.set(-1.0F, -1.0F, -1.0F);
        }
        vecnorm.normalize();
        supVertex.scaleAdd(getMargin(), vecnorm, supVertex);
      }
      return supVertex;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public BroadphaseNativeType getShapeType()
  {
    return BroadphaseNativeType.CONE_SHAPE_PROXYTYPE;
  }
  
  public void calculateLocalInertia(float arg1, Vector3f arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      tmp7_5.push$javax$vecmath$Vector3f();
      Transform identity = localStack.get$com$bulletphysics$linearmath$Transform();
      identity.setIdentity();
      Vector3f aabbMin = localStack.get$javax$vecmath$Vector3f();
      Vector3f aabbMax = localStack.get$javax$vecmath$Vector3f();
      getAabb(identity, aabbMin, aabbMax);
      Vector3f halfExtents = localStack.get$javax$vecmath$Vector3f();
      halfExtents.sub(aabbMax, aabbMin);
      halfExtents.scale(0.5F);
      float margin = getMargin();
      float local_lx = 2.0F * (halfExtents.field_615 + margin);
      float local_ly = 2.0F * (halfExtents.field_616 + margin);
      float local_lz = 2.0F * (halfExtents.field_617 + margin);
      float local_x2 = local_lx * local_lx;
      float local_y2 = local_ly * local_ly;
      float local_z2 = local_lz * local_lz;
      float scaledmass = mass * 0.08333333F;
      inertia.set(local_y2 + local_z2, local_x2 + local_z2, local_x2 + local_y2);
      inertia.scale(scaledmass);
      return;
    }
    finally
    {
      .Stack tmp176_174 = localStack;
      tmp176_174.pop$com$bulletphysics$linearmath$Transform();
      tmp176_174.pop$javax$vecmath$Vector3f();
    }
  }
  
  public String getName()
  {
    return "Cone";
  }
  
  protected void setConeUpIndex(int upIndex)
  {
    switch (upIndex)
    {
    case 0: 
      this.coneIndices[0] = 1;
      this.coneIndices[1] = 0;
      this.coneIndices[2] = 2;
      break;
    case 1: 
      this.coneIndices[0] = 0;
      this.coneIndices[1] = 1;
      this.coneIndices[2] = 2;
      break;
    case 2: 
      this.coneIndices[0] = 0;
      this.coneIndices[1] = 2;
      this.coneIndices[2] = 1;
      break;
    default: 
      if (!$assertionsDisabled) {
        throw new AssertionError();
      }
      break;
    }
  }
  
  public int getConeUpIndex()
  {
    return this.coneIndices[1];
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.shapes.ConeShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */