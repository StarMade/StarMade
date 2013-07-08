package com.bulletphysics.extras.gimpact;

import com.bulletphysics..Stack;
import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
import com.bulletphysics.collision.dispatch.CollisionWorld.RayResultCallback;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.ConcaveShape;
import com.bulletphysics.collision.shapes.TriangleCallback;
import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Vector3f;

public abstract class GImpactShapeInterface
  extends ConcaveShape
{
  protected BoxCollision.AABB localAABB = new BoxCollision.AABB();
  protected boolean needs_update;
  protected final Vector3f localScaling = new Vector3f();
  GImpactBvh box_set = new GImpactBvh();
  
  public GImpactShapeInterface()
  {
    this.localAABB.invalidate();
    this.needs_update = true;
    this.localScaling.set(1.0F, 1.0F, 1.0F);
  }
  
  public void updateBound()
  {
    if (!this.needs_update) {
      return;
    }
    calcLocalAABB();
    this.needs_update = false;
  }
  
  public void getAabb(Transform arg1, Vector3f arg2, Vector3f arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
      BoxCollision.AABB transformedbox = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB(this.localAABB);
      transformedbox.appy_transform(local_t);
      aabbMin.set(transformedbox.min);
      aabbMax.set(transformedbox.max);
      return;
    }
    finally
    {
      localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
    }
  }
  
  public void postUpdate()
  {
    this.needs_update = true;
  }
  
  public BoxCollision.AABB getLocalBox(BoxCollision.AABB out)
  {
    out.set(this.localAABB);
    return out;
  }
  
  public BroadphaseNativeType getShapeType()
  {
    return BroadphaseNativeType.GIMPACT_SHAPE_PROXYTYPE;
  }
  
  public void setLocalScaling(Vector3f scaling)
  {
    this.localScaling.set(scaling);
    postUpdate();
  }
  
  public Vector3f getLocalScaling(Vector3f out)
  {
    out.set(this.localScaling);
    return out;
  }
  
  public void setMargin(float margin)
  {
    this.collisionMargin = margin;
    int local_i = getNumChildShapes();
    while (local_i-- != 0)
    {
      CollisionShape child = getChildShape(local_i);
      child.setMargin(margin);
    }
    this.needs_update = true;
  }
  
  abstract ShapeType getGImpactShapeType();
  
  GImpactBvh getBoxSet()
  {
    return this.box_set;
  }
  
  public boolean hasBoxSet()
  {
    return this.box_set.getNodeCount() != 0;
  }
  
  abstract PrimitiveManagerBase getPrimitiveManager();
  
  public abstract int getNumChildShapes();
  
  public abstract boolean childrenHasTransform();
  
  public abstract boolean needsRetrieveTriangles();
  
  public abstract boolean needsRetrieveTetrahedrons();
  
  public abstract void getBulletTriangle(int paramInt, TriangleShapeEx paramTriangleShapeEx);
  
  abstract void getBulletTetrahedron(int paramInt, TetrahedronShapeEx paramTetrahedronShapeEx);
  
  public void lockChildShapes() {}
  
  public void unlockChildShapes() {}
  
  void getPrimitiveTriangle(int index, PrimitiveTriangle triangle)
  {
    getPrimitiveManager().get_primitive_triangle(index, triangle);
  }
  
  protected void calcLocalAABB()
  {
    lockChildShapes();
    if (this.box_set.getNodeCount() == 0) {
      this.box_set.buildSet();
    } else {
      this.box_set.update();
    }
    unlockChildShapes();
    this.box_set.getGlobalBox(this.localAABB);
  }
  
  public void getChildAabb(int arg1, Transform arg2, Vector3f arg3, Vector3f arg4)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
      BoxCollision.AABB child_aabb = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
      getPrimitiveManager().get_primitive_box(child_index, child_aabb);
      child_aabb.appy_transform(local_t);
      aabbMin.set(child_aabb.min);
      aabbMax.set(child_aabb.max);
      return;
    }
    finally
    {
      localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
    }
  }
  
  public abstract CollisionShape getChildShape(int paramInt);
  
  public abstract Transform getChildTransform(int paramInt);
  
  public abstract void setChildTransform(int paramInt, Transform paramTransform);
  
  public void rayTest(Vector3f rayFrom, Vector3f rayTo, CollisionWorld.RayResultCallback resultCallback) {}
  
  public void processAllTriangles(TriangleCallback callback, Vector3f aabbMin, Vector3f aabbMax) {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.extras.gimpact.GImpactShapeInterface
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */