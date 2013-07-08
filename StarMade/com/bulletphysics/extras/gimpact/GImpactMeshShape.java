package com.bulletphysics.extras.gimpact;

import com.bulletphysics..Stack;
import com.bulletphysics.collision.dispatch.CollisionWorld.RayResultCallback;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.StridingMeshInterface;
import com.bulletphysics.collision.shapes.TriangleCallback;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.util.ObjectArrayList;
import javax.vecmath.Vector3f;

public class GImpactMeshShape
  extends GImpactShapeInterface
{
  protected ObjectArrayList<GImpactMeshShapePart> mesh_parts = new ObjectArrayList();
  
  public GImpactMeshShape(StridingMeshInterface meshInterface)
  {
    buildMeshParts(meshInterface);
  }
  
  public int getMeshPartCount()
  {
    return this.mesh_parts.size();
  }
  
  public GImpactMeshShapePart getMeshPart(int index)
  {
    return (GImpactMeshShapePart)this.mesh_parts.getQuick(index);
  }
  
  public void setLocalScaling(Vector3f scaling)
  {
    this.localScaling.set(scaling);
    int local_i = this.mesh_parts.size();
    while (local_i-- != 0)
    {
      GImpactMeshShapePart part = (GImpactMeshShapePart)this.mesh_parts.getQuick(local_i);
      part.setLocalScaling(scaling);
    }
    this.needs_update = true;
  }
  
  public void setMargin(float margin)
  {
    this.collisionMargin = margin;
    int local_i = this.mesh_parts.size();
    while (local_i-- != 0)
    {
      GImpactMeshShapePart part = (GImpactMeshShapePart)this.mesh_parts.getQuick(local_i);
      part.setMargin(margin);
    }
    this.needs_update = true;
  }
  
  public void postUpdate()
  {
    int local_i = this.mesh_parts.size();
    while (local_i-- != 0)
    {
      GImpactMeshShapePart part = (GImpactMeshShapePart)this.mesh_parts.getQuick(local_i);
      part.postUpdate();
    }
    this.needs_update = true;
  }
  
  public void calculateLocalInertia(float arg1, Vector3f arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      inertia.set(0.0F, 0.0F, 0.0F);
      int local_i = getMeshPartCount();
      float partmass = mass / local_i;
      Vector3f partinertia = localStack.get$javax$vecmath$Vector3f();
      while (local_i-- != 0)
      {
        getMeshPart(local_i).calculateLocalInertia(partmass, partinertia);
        inertia.add(partinertia);
      }
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  PrimitiveManagerBase getPrimitiveManager()
  {
    if (!$assertionsDisabled) {
      throw new AssertionError();
    }
    return null;
  }
  
  public int getNumChildShapes()
  {
    if (!$assertionsDisabled) {
      throw new AssertionError();
    }
    return 0;
  }
  
  public boolean childrenHasTransform()
  {
    if (!$assertionsDisabled) {
      throw new AssertionError();
    }
    return false;
  }
  
  public boolean needsRetrieveTriangles()
  {
    if (!$assertionsDisabled) {
      throw new AssertionError();
    }
    return false;
  }
  
  public boolean needsRetrieveTetrahedrons()
  {
    if (!$assertionsDisabled) {
      throw new AssertionError();
    }
    return false;
  }
  
  public void getBulletTriangle(int prim_index, TriangleShapeEx triangle)
  {
    if (!$assertionsDisabled) {
      throw new AssertionError();
    }
  }
  
  void getBulletTetrahedron(int prim_index, TetrahedronShapeEx tetrahedron)
  {
    if (!$assertionsDisabled) {
      throw new AssertionError();
    }
  }
  
  public void lockChildShapes()
  {
    if (!$assertionsDisabled) {
      throw new AssertionError();
    }
  }
  
  public void unlockChildShapes()
  {
    if (!$assertionsDisabled) {
      throw new AssertionError();
    }
  }
  
  public void getChildAabb(int child_index, Transform local_t, Vector3f aabbMin, Vector3f aabbMax)
  {
    if (!$assertionsDisabled) {
      throw new AssertionError();
    }
  }
  
  public CollisionShape getChildShape(int index)
  {
    if (!$assertionsDisabled) {
      throw new AssertionError();
    }
    return null;
  }
  
  public Transform getChildTransform(int index)
  {
    if (!$assertionsDisabled) {
      throw new AssertionError();
    }
    return null;
  }
  
  public void setChildTransform(int index, Transform transform)
  {
    if (!$assertionsDisabled) {
      throw new AssertionError();
    }
  }
  
  ShapeType getGImpactShapeType()
  {
    return ShapeType.TRIMESH_SHAPE;
  }
  
  public String getName()
  {
    return "GImpactMesh";
  }
  
  public void rayTest(Vector3f rayFrom, Vector3f rayTo, CollisionWorld.RayResultCallback resultCallback) {}
  
  public void processAllTriangles(TriangleCallback callback, Vector3f aabbMin, Vector3f aabbMax)
  {
    int local_i = this.mesh_parts.size();
    while (local_i-- != 0) {
      ((GImpactMeshShapePart)this.mesh_parts.getQuick(local_i)).processAllTriangles(callback, aabbMin, aabbMax);
    }
  }
  
  protected void buildMeshParts(StridingMeshInterface meshInterface)
  {
    for (int local_i = 0; local_i < meshInterface.getNumSubParts(); local_i++)
    {
      GImpactMeshShapePart newpart = new GImpactMeshShapePart(meshInterface, local_i);
      this.mesh_parts.add(newpart);
    }
  }
  
  protected void calcLocalAABB()
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
      BoxCollision.AABB tmpAABB = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
      this.localAABB.invalidate();
      int local_i = this.mesh_parts.size();
      while (local_i-- != 0)
      {
        ((GImpactMeshShapePart)this.mesh_parts.getQuick(local_i)).updateBound();
        this.localAABB.merge(((GImpactMeshShapePart)this.mesh_parts.getQuick(local_i)).getLocalBox(tmpAABB));
      }
      return;
    }
    finally
    {
      localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.extras.gimpact.GImpactMeshShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */