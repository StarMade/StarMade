package com.bulletphysics.collision.shapes;

import com.bulletphysics..Stack;
import com.bulletphysics.linearmath.AabbUtil2;
import com.bulletphysics.linearmath.MatrixUtil;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.linearmath.VectorUtil;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;

public abstract class TriangleMeshShape
  extends ConcaveShape
{
  protected final Vector3f localAabbMin = new Vector3f();
  protected final Vector3f localAabbMax = new Vector3f();
  protected StridingMeshInterface meshInterface;
  
  protected TriangleMeshShape(StridingMeshInterface meshInterface)
  {
    this.meshInterface = meshInterface;
  }
  
  public Vector3f localGetSupportingVertex(Vector3f arg1, Vector3f arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      tmp7_5.push$javax$vecmath$Vector3f();
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      Vector3f supportVertex = out;
      Transform ident = localStack.get$com$bulletphysics$linearmath$Transform();
      ident.setIdentity();
      SupportVertexCallback supportCallback = new SupportVertexCallback(vec, ident);
      Vector3f aabbMax = localStack.get$javax$vecmath$Vector3f();
      aabbMax.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
      tmp.negate(aabbMax);
      processAllTriangles(supportCallback, tmp, aabbMax);
      supportCallback.getSupportVertexLocal(supportVertex);
      return out;
    }
    finally
    {
      .Stack tmp102_100 = localStack;
      tmp102_100.pop$com$bulletphysics$linearmath$Transform();
      tmp102_100.pop$javax$vecmath$Vector3f();
    }
  }
  
  public Vector3f localGetSupportingVertexWithoutMargin(Vector3f vec, Vector3f out)
  {
    if (!$assertionsDisabled) {
      throw new AssertionError();
    }
    return localGetSupportingVertex(vec, out);
  }
  
  public void recalcLocalAabb()
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      for (int local_i = 0; local_i < 3; local_i++)
      {
        Vector3f vec = localStack.get$javax$vecmath$Vector3f();
        vec.set(0.0F, 0.0F, 0.0F);
        VectorUtil.setCoord(vec, local_i, 1.0F);
        Vector3f tmp = localGetSupportingVertex(vec, localStack.get$javax$vecmath$Vector3f());
        VectorUtil.setCoord(this.localAabbMax, local_i, VectorUtil.getCoord(tmp, local_i) + this.collisionMargin);
        VectorUtil.setCoord(vec, local_i, -1.0F);
        localGetSupportingVertex(vec, tmp);
        VectorUtil.setCoord(this.localAabbMin, local_i, VectorUtil.getCoord(tmp, local_i) - this.collisionMargin);
      }
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
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
      Vector3f localHalfExtents = localStack.get$javax$vecmath$Vector3f();
      localHalfExtents.sub(this.localAabbMax, this.localAabbMin);
      localHalfExtents.scale(0.5F);
      Vector3f localCenter = localStack.get$javax$vecmath$Vector3f();
      localCenter.add(this.localAabbMax, this.localAabbMin);
      localCenter.scale(0.5F);
      Matrix3f abs_b = localStack.get$javax$vecmath$Matrix3f(trans.basis);
      MatrixUtil.absolute(abs_b);
      Vector3f center = localStack.get$javax$vecmath$Vector3f(localCenter);
      trans.transform(center);
      Vector3f extent = localStack.get$javax$vecmath$Vector3f();
      abs_b.getRow(0, tmp);
      extent.field_615 = tmp.dot(localHalfExtents);
      abs_b.getRow(1, tmp);
      extent.field_616 = tmp.dot(localHalfExtents);
      abs_b.getRow(2, tmp);
      extent.field_617 = tmp.dot(localHalfExtents);
      Vector3f margin = localStack.get$javax$vecmath$Vector3f();
      margin.set(getMargin(), getMargin(), getMargin());
      extent.add(margin);
      aabbMin.sub(center, extent);
      aabbMax.add(center, extent);
      return;
    }
    finally
    {
      .Stack tmp234_232 = localStack;
      tmp234_232.pop$javax$vecmath$Vector3f();
      tmp234_232.pop$javax$vecmath$Matrix3f();
    }
  }
  
  public void processAllTriangles(TriangleCallback callback, Vector3f aabbMin, Vector3f aabbMax)
  {
    FilteredCallback filterCallback = new FilteredCallback(callback, aabbMin, aabbMax);
    this.meshInterface.internalProcessAllTriangles(filterCallback, aabbMin, aabbMax);
  }
  
  public void calculateLocalInertia(float mass, Vector3f inertia)
  {
    if (!$assertionsDisabled) {
      throw new AssertionError();
    }
    inertia.set(0.0F, 0.0F, 0.0F);
  }
  
  public void setLocalScaling(Vector3f scaling)
  {
    this.meshInterface.setScaling(scaling);
    recalcLocalAabb();
  }
  
  public Vector3f getLocalScaling(Vector3f out)
  {
    return this.meshInterface.getScaling(out);
  }
  
  public StridingMeshInterface getMeshInterface()
  {
    return this.meshInterface;
  }
  
  public Vector3f getLocalAabbMin(Vector3f out)
  {
    out.set(this.localAabbMin);
    return out;
  }
  
  public Vector3f getLocalAabbMax(Vector3f out)
  {
    out.set(this.localAabbMax);
    return out;
  }
  
  public String getName()
  {
    return "TRIANGLEMESH";
  }
  
  private static class FilteredCallback
    extends InternalTriangleIndexCallback
  {
    public TriangleCallback callback;
    public final Vector3f aabbMin = new Vector3f();
    public final Vector3f aabbMax = new Vector3f();
    
    public FilteredCallback(TriangleCallback callback, Vector3f aabbMin, Vector3f aabbMax)
    {
      this.callback = callback;
      this.aabbMin.set(aabbMin);
      this.aabbMax.set(aabbMax);
    }
    
    public void internalProcessTriangleIndex(Vector3f[] triangle, int partId, int triangleIndex)
    {
      if (AabbUtil2.testTriangleAgainstAabb2(triangle, this.aabbMin, this.aabbMax)) {
        this.callback.processTriangle(triangle, partId, triangleIndex);
      }
    }
  }
  
  private class SupportVertexCallback
    extends TriangleCallback
  {
    private final Vector3f supportVertexLocal = new Vector3f(0.0F, 0.0F, 0.0F);
    public final Transform worldTrans = new Transform();
    public float maxDot = -1.0E+030F;
    public final Vector3f supportVecLocal = new Vector3f();
    
    public SupportVertexCallback(Vector3f supportVecWorld, Transform trans)
    {
      this.worldTrans.set(trans);
      MatrixUtil.transposeTransform(this.supportVecLocal, supportVecWorld, this.worldTrans.basis);
    }
    
    public void processTriangle(Vector3f[] triangle, int partId, int triangleIndex)
    {
      for (int local_i = 0; local_i < 3; local_i++)
      {
        float dot = this.supportVecLocal.dot(triangle[local_i]);
        if (dot > this.maxDot)
        {
          this.maxDot = dot;
          this.supportVertexLocal.set(triangle[local_i]);
        }
      }
    }
    
    public Vector3f getSupportVertexWorldSpace(Vector3f out)
    {
      out.set(this.supportVertexLocal);
      this.worldTrans.transform(out);
      return out;
    }
    
    public Vector3f getSupportVertexLocal(Vector3f out)
    {
      out.set(this.supportVertexLocal);
      return out;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.shapes.TriangleMeshShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */