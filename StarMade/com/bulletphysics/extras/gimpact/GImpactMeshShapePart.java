package com.bulletphysics.extras.gimpact;

import com.bulletphysics..Stack;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.StridingMeshInterface;
import com.bulletphysics.collision.shapes.TriangleCallback;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.util.IntArrayList;
import javax.vecmath.Vector3f;

public class GImpactMeshShapePart
  extends GImpactShapeInterface
{
  TrimeshPrimitiveManager primitive_manager = new TrimeshPrimitiveManager();
  private final IntArrayList collided = new IntArrayList();
  
  public GImpactMeshShapePart()
  {
    this.box_set.setPrimitiveManager(this.primitive_manager);
  }
  
  public GImpactMeshShapePart(StridingMeshInterface meshInterface, int part)
  {
    this.primitive_manager.meshInterface = meshInterface;
    this.primitive_manager.part = part;
    this.box_set.setPrimitiveManager(this.primitive_manager);
  }
  
  public boolean childrenHasTransform()
  {
    return false;
  }
  
  public void lockChildShapes()
  {
    TrimeshPrimitiveManager dummymanager = (TrimeshPrimitiveManager)this.box_set.getPrimitiveManager();
    dummymanager.lock();
  }
  
  public void unlockChildShapes()
  {
    TrimeshPrimitiveManager dummymanager = (TrimeshPrimitiveManager)this.box_set.getPrimitiveManager();
    dummymanager.unlock();
  }
  
  public int getNumChildShapes()
  {
    return this.primitive_manager.get_primitive_count();
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
  
  PrimitiveManagerBase getPrimitiveManager()
  {
    return this.primitive_manager;
  }
  
  TrimeshPrimitiveManager getTrimeshPrimitiveManager()
  {
    return this.primitive_manager;
  }
  
  public void calculateLocalInertia(float arg1, Vector3f arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      lockChildShapes();
      inertia.set(0.0F, 0.0F, 0.0F);
      int local_i = getVertexCount();
      float pointmass = mass / local_i;
      Vector3f pointintertia = localStack.get$javax$vecmath$Vector3f();
      while (local_i-- != 0)
      {
        getVertex(local_i, pointintertia);
        GImpactMassUtil.get_point_inertia(pointintertia, pointmass, pointintertia);
        inertia.add(pointintertia);
      }
      unlockChildShapes();
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public String getName()
  {
    return "GImpactMeshShapePart";
  }
  
  ShapeType getGImpactShapeType()
  {
    return ShapeType.TRIMESH_SHAPE_PART;
  }
  
  public boolean needsRetrieveTriangles()
  {
    return true;
  }
  
  public boolean needsRetrieveTetrahedrons()
  {
    return false;
  }
  
  public void getBulletTriangle(int prim_index, TriangleShapeEx triangle)
  {
    this.primitive_manager.get_bullet_triangle(prim_index, triangle);
  }
  
  void getBulletTetrahedron(int prim_index, TetrahedronShapeEx tetrahedron)
  {
    if (!$assertionsDisabled) {
      throw new AssertionError();
    }
  }
  
  public int getVertexCount()
  {
    return this.primitive_manager.get_vertex_count();
  }
  
  public void getVertex(int vertex_index, Vector3f vertex)
  {
    this.primitive_manager.get_vertex(vertex_index, vertex);
  }
  
  public void setMargin(float margin)
  {
    this.primitive_manager.margin = margin;
    postUpdate();
  }
  
  public float getMargin()
  {
    return this.primitive_manager.margin;
  }
  
  public void setLocalScaling(Vector3f scaling)
  {
    this.primitive_manager.scale.set(scaling);
    postUpdate();
  }
  
  public Vector3f getLocalScaling(Vector3f out)
  {
    out.set(this.primitive_manager.scale);
    return out;
  }
  
  public int getPart()
  {
    return this.primitive_manager.part;
  }
  
  public void processAllTriangles(TriangleCallback arg1, Vector3f arg2, Vector3f arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
      tmp7_5.push$com$bulletphysics$extras$gimpact$PrimitiveTriangle();
      lockChildShapes();
      BoxCollision.AABB box = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
      box.min.set(aabbMin);
      box.max.set(aabbMax);
      this.collided.clear();
      this.box_set.boxQuery(box, this.collided);
      if (this.collided.size() == 0)
      {
        unlockChildShapes();
        return;
      }
      int part = getPart();
      PrimitiveTriangle triangle = localStack.get$com$bulletphysics$extras$gimpact$PrimitiveTriangle();
      int local_i = this.collided.size();
      while (local_i-- != 0)
      {
        getPrimitiveTriangle(this.collided.get(local_i), triangle);
        callback.processTriangle(triangle.vertices, part, this.collided.get(local_i));
      }
      unlockChildShapes();
      return;
    }
    finally
    {
      .Stack tmp172_170 = localStack;
      tmp172_170.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
      tmp172_170.pop$com$bulletphysics$extras$gimpact$PrimitiveTriangle();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.extras.gimpact.GImpactMeshShapePart
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */