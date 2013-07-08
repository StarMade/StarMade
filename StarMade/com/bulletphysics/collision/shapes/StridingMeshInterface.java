package com.bulletphysics.collision.shapes;

import com.bulletphysics..Stack;
import com.bulletphysics.linearmath.VectorUtil;
import javax.vecmath.Vector3f;

public abstract class StridingMeshInterface
{
  protected final Vector3f scaling = new Vector3f(1.0F, 1.0F, 1.0F);
  
  public void internalProcessAllTriangles(InternalTriangleIndexCallback arg1, Vector3f arg2, Vector3f arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      int graphicssubparts = getNumSubParts();
      Vector3f[] triangle = { localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f() };
      Vector3f meshScaling = getScaling(localStack.get$javax$vecmath$Vector3f());
      for (int part = 0; part < graphicssubparts; part++)
      {
        VertexData data = getLockedReadOnlyVertexIndexBase(part);
        int local_i = 0;
        int cnt = data.getIndexCount() / 3;
        while (local_i < cnt)
        {
          data.getTriangle(local_i * 3, meshScaling, triangle);
          callback.internalProcessTriangleIndex(triangle, part, local_i);
          local_i++;
        }
        unLockReadOnlyVertexBase(part);
      }
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void calculateAabbBruteForce(Vector3f aabbMin, Vector3f aabbMax)
  {
    AabbCalculationCallback aabbCallback = new AabbCalculationCallback(null);
    aabbMin.set(-1.0E+030F, -1.0E+030F, -1.0E+030F);
    aabbMax.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
    internalProcessAllTriangles(aabbCallback, aabbMin, aabbMax);
    aabbMin.set(aabbCallback.aabbMin);
    aabbMax.set(aabbCallback.aabbMax);
  }
  
  public abstract VertexData getLockedVertexIndexBase(int paramInt);
  
  public abstract VertexData getLockedReadOnlyVertexIndexBase(int paramInt);
  
  public abstract void unLockVertexBase(int paramInt);
  
  public abstract void unLockReadOnlyVertexBase(int paramInt);
  
  public abstract int getNumSubParts();
  
  public abstract void preallocateVertices(int paramInt);
  
  public abstract void preallocateIndices(int paramInt);
  
  public Vector3f getScaling(Vector3f out)
  {
    out.set(this.scaling);
    return out;
  }
  
  public void setScaling(Vector3f scaling)
  {
    this.scaling.set(scaling);
  }
  
  private static class AabbCalculationCallback
    extends InternalTriangleIndexCallback
  {
    public final Vector3f aabbMin = new Vector3f(1.0E+030F, 1.0E+030F, 1.0E+030F);
    public final Vector3f aabbMax = new Vector3f(-1.0E+030F, -1.0E+030F, -1.0E+030F);
    
    public void internalProcessTriangleIndex(Vector3f[] triangle, int partId, int triangleIndex)
    {
      VectorUtil.setMin(this.aabbMin, triangle[0]);
      VectorUtil.setMax(this.aabbMax, triangle[0]);
      VectorUtil.setMin(this.aabbMin, triangle[1]);
      VectorUtil.setMax(this.aabbMax, triangle[1]);
      VectorUtil.setMin(this.aabbMin, triangle[2]);
      VectorUtil.setMax(this.aabbMax, triangle[2]);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.shapes.StridingMeshInterface
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */