package com.bulletphysics.collision.shapes;

import com.bulletphysics..Stack;
import com.bulletphysics.linearmath.MatrixUtil;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.linearmath.VectorUtil;
import javax.vecmath.Vector3f;

public abstract class ConvexInternalShape
  extends ConvexShape
{
  protected final Vector3f localScaling = new Vector3f(1.0F, 1.0F, 1.0F);
  protected final Vector3f implicitShapeDimensions = new Vector3f();
  protected float collisionMargin = 0.04F;
  
  public void getAabb(Transform local_t, Vector3f aabbMin, Vector3f aabbMax)
  {
    getAabbSlow(local_t, aabbMin, aabbMax);
  }
  
  public void getAabbSlow(Transform arg1, Vector3f arg2, Vector3f arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      float margin = getMargin();
      Vector3f vec = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
      for (int local_i = 0; local_i < 3; local_i++)
      {
        vec.set(0.0F, 0.0F, 0.0F);
        VectorUtil.setCoord(vec, local_i, 1.0F);
        MatrixUtil.transposeTransform(tmp1, vec, trans.basis);
        localGetSupportingVertex(tmp1, tmp2);
        trans.transform(tmp2);
        VectorUtil.setCoord(maxAabb, local_i, VectorUtil.getCoord(tmp2, local_i) + margin);
        VectorUtil.setCoord(vec, local_i, -1.0F);
        MatrixUtil.transposeTransform(tmp1, vec, trans.basis);
        localGetSupportingVertex(tmp1, tmp2);
        trans.transform(tmp2);
        VectorUtil.setCoord(minAabb, local_i, VectorUtil.getCoord(tmp2, local_i) - margin);
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
  
  public void setLocalScaling(Vector3f scaling)
  {
    this.localScaling.absolute(scaling);
  }
  
  public Vector3f getLocalScaling(Vector3f out)
  {
    out.set(this.localScaling);
    return out;
  }
  
  public float getMargin()
  {
    return this.collisionMargin;
  }
  
  public void setMargin(float margin)
  {
    this.collisionMargin = margin;
  }
  
  public int getNumPreferredPenetrationDirections()
  {
    return 0;
  }
  
  public void getPreferredPenetrationDirection(int index, Vector3f penetrationVector)
  {
    throw new InternalError();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.shapes.ConvexInternalShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */