package com.bulletphysics.collision.shapes;

import com.bulletphysics..Stack;
import com.bulletphysics.linearmath.AabbUtil2;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.linearmath.VectorUtil;
import javax.vecmath.Vector3f;

public abstract class PolyhedralConvexShape
  extends ConvexInternalShape
{
  private static Vector3f[] _directions = { new Vector3f(1.0F, 0.0F, 0.0F), new Vector3f(0.0F, 1.0F, 0.0F), new Vector3f(0.0F, 0.0F, 1.0F), new Vector3f(-1.0F, 0.0F, 0.0F), new Vector3f(0.0F, -1.0F, 0.0F), new Vector3f(0.0F, 0.0F, -1.0F) };
  private static Vector3f[] _supporting = { new Vector3f(0.0F, 0.0F, 0.0F), new Vector3f(0.0F, 0.0F, 0.0F), new Vector3f(0.0F, 0.0F, 0.0F), new Vector3f(0.0F, 0.0F, 0.0F), new Vector3f(0.0F, 0.0F, 0.0F), new Vector3f(0.0F, 0.0F, 0.0F) };
  protected final Vector3f localAabbMin = new Vector3f(1.0F, 1.0F, 1.0F);
  protected final Vector3f localAabbMax = new Vector3f(-1.0F, -1.0F, -1.0F);
  protected boolean isLocalAabbValid = false;
  
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
      for (int local_i = 0; local_i < getNumVertices(); local_i++)
      {
        getVertex(local_i, rlen);
        float newDot = vec.dot(rlen);
        if (newDot > maxDot)
        {
          maxDot = newDot;
          supVec = rlen;
        }
      }
      return out;
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
      Vector3f vtx = localStack.get$javax$vecmath$Vector3f();
      float[] wcoords = new float[numVectors];
      for (int local_i = 0; local_i < numVectors; local_i++) {
        wcoords[local_i] = -1.0E+030F;
      }
      for (int local_j = 0; local_j < numVectors; local_j++)
      {
        Vector3f vec = vectors[local_j];
        for (local_i = 0; local_i < getNumVertices(); local_i++)
        {
          getVertex(local_i, vtx);
          float newDot = vec.dot(vtx);
          if (newDot > wcoords[local_j])
          {
            supportVerticesOut[local_j].set(vtx);
            wcoords[local_j] = newDot;
          }
        }
      }
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void calculateLocalInertia(float arg1, Vector3f arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      tmp7_5.push$javax$vecmath$Vector3f();
      float margin = getMargin();
      Transform ident = localStack.get$com$bulletphysics$linearmath$Transform();
      ident.setIdentity();
      Vector3f aabbMin = localStack.get$javax$vecmath$Vector3f();
      Vector3f aabbMax = localStack.get$javax$vecmath$Vector3f();
      getAabb(ident, aabbMin, aabbMax);
      Vector3f halfExtents = localStack.get$javax$vecmath$Vector3f();
      halfExtents.sub(aabbMax, aabbMin);
      halfExtents.scale(0.5F);
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
      .Stack tmp175_173 = localStack;
      tmp175_173.pop$com$bulletphysics$linearmath$Transform();
      tmp175_173.pop$javax$vecmath$Vector3f();
    }
  }
  
  private void getNonvirtualAabb(Transform trans, Vector3f aabbMin, Vector3f aabbMax, float margin)
  {
    assert (this.isLocalAabbValid);
    AabbUtil2.transformAabb(this.localAabbMin, this.localAabbMax, margin, trans, aabbMin, aabbMax);
  }
  
  public void getAabb(Transform trans, Vector3f aabbMin, Vector3f aabbMax)
  {
    getNonvirtualAabb(trans, aabbMin, aabbMax, getMargin());
  }
  
  protected final void _PolyhedralConvexShape_getAabb(Transform trans, Vector3f aabbMin, Vector3f aabbMax)
  {
    getNonvirtualAabb(trans, aabbMin, aabbMax, getMargin());
  }
  
  public void recalcLocalAabb()
  {
    this.isLocalAabbValid = true;
    batchedUnitVectorGetSupportingVertexWithoutMargin(_directions, _supporting, 6);
    for (int local_i = 0; local_i < 3; local_i++)
    {
      VectorUtil.setCoord(this.localAabbMax, local_i, VectorUtil.getCoord(_supporting[local_i], local_i) + this.collisionMargin);
      VectorUtil.setCoord(this.localAabbMin, local_i, VectorUtil.getCoord(_supporting[(local_i + 3)], local_i) - this.collisionMargin);
    }
  }
  
  public void setLocalScaling(Vector3f scaling)
  {
    super.setLocalScaling(scaling);
    recalcLocalAabb();
  }
  
  public abstract int getNumVertices();
  
  public abstract int getNumEdges();
  
  public abstract void getEdge(int paramInt, Vector3f paramVector3f1, Vector3f paramVector3f2);
  
  public abstract void getVertex(int paramInt, Vector3f paramVector3f);
  
  public abstract int getNumPlanes();
  
  public abstract void getPlane(Vector3f paramVector3f1, Vector3f paramVector3f2, int paramInt);
  
  public abstract boolean isInside(Vector3f paramVector3f, float paramFloat);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.shapes.PolyhedralConvexShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */