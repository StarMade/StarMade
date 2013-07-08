package com.bulletphysics.collision.shapes;

import com.bulletphysics..Stack;
import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
import com.bulletphysics.linearmath.VectorUtil;
import com.bulletphysics.util.ObjectArrayList;
import javax.vecmath.Vector3f;

public class ConvexHullShape
  extends PolyhedralConvexShape
{
  private final ObjectArrayList<Vector3f> points = new ObjectArrayList();
  
  public ConvexHullShape(ObjectArrayList<Vector3f> points)
  {
    for (int local_i = 0; local_i < points.size(); local_i++) {
      this.points.add(new Vector3f((Vector3f)points.getQuick(local_i)));
    }
    recalcLocalAabb();
  }
  
  public void setLocalScaling(Vector3f scaling)
  {
    this.localScaling.set(scaling);
    recalcLocalAabb();
  }
  
  public void addPoint(Vector3f point)
  {
    this.points.add(new Vector3f(point));
    recalcLocalAabb();
  }
  
  public ObjectArrayList<Vector3f> getPoints()
  {
    return this.points;
  }
  
  public int getNumPoints()
  {
    return this.points.size();
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
      for (int local_i = 0; local_i < this.points.size(); local_i++)
      {
        VectorUtil.mul(rlen, (Vector3f)this.points.getQuick(local_i), this.localScaling);
        float newDot = vec.dot(rlen);
        if (newDot > maxDot)
        {
          maxDot = newDot;
          supVec.set(rlen);
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
      float[] wcoords = new float[numVectors];
      for (int local_i = 0; local_i < numVectors; local_i++) {
        wcoords[local_i] = -1.0E+030F;
      }
      Vector3f local_i = localStack.get$javax$vecmath$Vector3f();
      for (int local_i1 = 0; local_i1 < this.points.size(); local_i1++)
      {
        VectorUtil.mul(local_i, (Vector3f)this.points.getQuick(local_i1), this.localScaling);
        for (int local_j = 0; local_j < numVectors; local_j++)
        {
          Vector3f vec = vectors[local_j];
          float newDot = vec.dot(local_i);
          if (newDot > wcoords[local_j])
          {
            supportVerticesOut[local_j].set(local_i);
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
  
  public Vector3f localGetSupportingVertex(Vector3f arg1, Vector3f arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f supVertex = localGetSupportingVertexWithoutMargin(vec, out);
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
  
  public int getNumVertices()
  {
    return this.points.size();
  }
  
  public int getNumEdges()
  {
    return this.points.size();
  }
  
  public void getEdge(int local_i, Vector3f local_pa, Vector3f local_pb)
  {
    int index0 = local_i % this.points.size();
    int index1 = (local_i + 1) % this.points.size();
    VectorUtil.mul(local_pa, (Vector3f)this.points.getQuick(index0), this.localScaling);
    VectorUtil.mul(local_pb, (Vector3f)this.points.getQuick(index1), this.localScaling);
  }
  
  public void getVertex(int local_i, Vector3f vtx)
  {
    VectorUtil.mul(vtx, (Vector3f)this.points.getQuick(local_i), this.localScaling);
  }
  
  public int getNumPlanes()
  {
    return 0;
  }
  
  public void getPlane(Vector3f planeNormal, Vector3f planeSupport, int local_i)
  {
    if (!$assertionsDisabled) {
      throw new AssertionError();
    }
  }
  
  public boolean isInside(Vector3f local_pt, float tolerance)
  {
    if (!$assertionsDisabled) {
      throw new AssertionError();
    }
    return false;
  }
  
  public BroadphaseNativeType getShapeType()
  {
    return BroadphaseNativeType.CONVEX_HULL_SHAPE_PROXYTYPE;
  }
  
  public String getName()
  {
    return "Convex";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.shapes.ConvexHullShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */