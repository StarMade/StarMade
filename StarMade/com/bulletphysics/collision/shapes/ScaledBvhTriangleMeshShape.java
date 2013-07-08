package com.bulletphysics.collision.shapes;

import com.bulletphysics..Stack;
import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
import com.bulletphysics.linearmath.MatrixUtil;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.linearmath.VectorUtil;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;

public class ScaledBvhTriangleMeshShape
  extends ConcaveShape
{
  protected final Vector3f localScaling = new Vector3f();
  protected BvhTriangleMeshShape bvhTriMeshShape;
  
  public ScaledBvhTriangleMeshShape(BvhTriangleMeshShape childShape, Vector3f localScaling)
  {
    this.localScaling.set(localScaling);
    this.bvhTriMeshShape = childShape;
  }
  
  public BvhTriangleMeshShape getChildShape()
  {
    return this.bvhTriMeshShape;
  }
  
  public void processAllTriangles(TriangleCallback arg1, Vector3f arg2, Vector3f arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      ScaledTriangleCallback scaledCallback = new ScaledTriangleCallback(callback, this.localScaling);
      Vector3f invLocalScaling = localStack.get$javax$vecmath$Vector3f();
      invLocalScaling.set(1.0F / this.localScaling.field_615, 1.0F / this.localScaling.field_616, 1.0F / this.localScaling.field_617);
      Vector3f scaledAabbMin = localStack.get$javax$vecmath$Vector3f();
      Vector3f scaledAabbMax = localStack.get$javax$vecmath$Vector3f();
      scaledAabbMin.field_615 = (this.localScaling.field_615 >= 0.0F ? aabbMin.field_615 * invLocalScaling.field_615 : aabbMax.field_615 * invLocalScaling.field_615);
      scaledAabbMin.field_616 = (this.localScaling.field_616 >= 0.0F ? aabbMin.field_616 * invLocalScaling.field_616 : aabbMax.field_616 * invLocalScaling.field_616);
      scaledAabbMin.field_617 = (this.localScaling.field_617 >= 0.0F ? aabbMin.field_617 * invLocalScaling.field_617 : aabbMax.field_617 * invLocalScaling.field_617);
      scaledAabbMax.field_615 = (this.localScaling.field_615 <= 0.0F ? aabbMin.field_615 * invLocalScaling.field_615 : aabbMax.field_615 * invLocalScaling.field_615);
      scaledAabbMax.field_616 = (this.localScaling.field_616 <= 0.0F ? aabbMin.field_616 * invLocalScaling.field_616 : aabbMax.field_616 * invLocalScaling.field_616);
      scaledAabbMax.field_617 = (this.localScaling.field_617 <= 0.0F ? aabbMin.field_617 * invLocalScaling.field_617 : aabbMax.field_617 * invLocalScaling.field_617);
      this.bvhTriMeshShape.processAllTriangles(scaledCallback, scaledAabbMin, scaledAabbMax);
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
      Vector3f localAabbMin = this.bvhTriMeshShape.getLocalAabbMin(localStack.get$javax$vecmath$Vector3f());
      Vector3f localAabbMax = this.bvhTriMeshShape.getLocalAabbMax(localStack.get$javax$vecmath$Vector3f());
      Vector3f tmpLocalAabbMin = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmpLocalAabbMax = localStack.get$javax$vecmath$Vector3f();
      VectorUtil.mul(tmpLocalAabbMin, localAabbMin, this.localScaling);
      VectorUtil.mul(tmpLocalAabbMax, localAabbMax, this.localScaling);
      localAabbMin.field_615 = (this.localScaling.field_615 >= 0.0F ? tmpLocalAabbMin.field_615 : tmpLocalAabbMax.field_615);
      localAabbMin.field_616 = (this.localScaling.field_616 >= 0.0F ? tmpLocalAabbMin.field_616 : tmpLocalAabbMax.field_616);
      localAabbMin.field_617 = (this.localScaling.field_617 >= 0.0F ? tmpLocalAabbMin.field_617 : tmpLocalAabbMax.field_617);
      localAabbMax.field_615 = (this.localScaling.field_615 <= 0.0F ? tmpLocalAabbMin.field_615 : tmpLocalAabbMax.field_615);
      localAabbMax.field_616 = (this.localScaling.field_616 <= 0.0F ? tmpLocalAabbMin.field_616 : tmpLocalAabbMax.field_616);
      localAabbMax.field_617 = (this.localScaling.field_617 <= 0.0F ? tmpLocalAabbMin.field_617 : tmpLocalAabbMax.field_617);
      Vector3f localHalfExtents = localStack.get$javax$vecmath$Vector3f();
      localHalfExtents.sub(localAabbMax, localAabbMin);
      localHalfExtents.scale(0.5F);
      float margin = this.bvhTriMeshShape.getMargin();
      localHalfExtents.field_615 += margin;
      localHalfExtents.field_616 += margin;
      localHalfExtents.field_617 += margin;
      Vector3f localCenter = localStack.get$javax$vecmath$Vector3f();
      localCenter.add(localAabbMax, localAabbMin);
      localCenter.scale(0.5F);
      Matrix3f abs_b = localStack.get$javax$vecmath$Matrix3f(trans.basis);
      MatrixUtil.absolute(abs_b);
      Vector3f center = localStack.get$javax$vecmath$Vector3f(localCenter);
      trans.transform(center);
      Vector3f extent = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      abs_b.getRow(0, tmp);
      extent.field_615 = tmp.dot(localHalfExtents);
      abs_b.getRow(1, tmp);
      extent.field_616 = tmp.dot(localHalfExtents);
      abs_b.getRow(2, tmp);
      extent.field_617 = tmp.dot(localHalfExtents);
      aabbMin.sub(center, extent);
      aabbMax.add(center, extent);
      return;
    }
    finally
    {
      .Stack tmp484_482 = localStack;
      tmp484_482.pop$javax$vecmath$Vector3f();
      tmp484_482.pop$javax$vecmath$Matrix3f();
    }
  }
  
  public BroadphaseNativeType getShapeType()
  {
    return BroadphaseNativeType.SCALED_TRIANGLE_MESH_SHAPE_PROXYTYPE;
  }
  
  public void setLocalScaling(Vector3f scaling)
  {
    this.localScaling.set(scaling);
  }
  
  public Vector3f getLocalScaling(Vector3f out)
  {
    out.set(this.localScaling);
    return out;
  }
  
  public void calculateLocalInertia(float mass, Vector3f inertia) {}
  
  public String getName()
  {
    return "SCALEDBVHTRIANGLEMESH";
  }
  
  private static class ScaledTriangleCallback
    extends TriangleCallback
  {
    private TriangleCallback originalCallback;
    private Vector3f localScaling;
    private Vector3f[] newTriangle = new Vector3f[3];
    
    public ScaledTriangleCallback(TriangleCallback originalCallback, Vector3f localScaling)
    {
      this.originalCallback = originalCallback;
      this.localScaling = localScaling;
      for (int local_i = 0; local_i < this.newTriangle.length; local_i++) {
        this.newTriangle[local_i] = new Vector3f();
      }
    }
    
    public void processTriangle(Vector3f[] triangle, int partId, int triangleIndex)
    {
      VectorUtil.mul(this.newTriangle[0], triangle[0], this.localScaling);
      VectorUtil.mul(this.newTriangle[1], triangle[1], this.localScaling);
      VectorUtil.mul(this.newTriangle[2], triangle[2], this.localScaling);
      this.originalCallback.processTriangle(this.newTriangle, partId, triangleIndex);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.shapes.ScaledBvhTriangleMeshShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */