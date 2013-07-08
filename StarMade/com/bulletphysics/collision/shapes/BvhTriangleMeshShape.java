package com.bulletphysics.collision.shapes;

import com.bulletphysics..Stack;
import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
import com.bulletphysics.linearmath.VectorUtil;
import com.bulletphysics.util.ObjectPool;
import javax.vecmath.Vector3f;

public class BvhTriangleMeshShape
  extends TriangleMeshShape
{
  private OptimizedBvh bvh = null;
  private boolean useQuantizedAabbCompression;
  private boolean ownsBvh;
  private ObjectPool<MyNodeOverlapCallback> myNodeCallbacks = ObjectPool.get(MyNodeOverlapCallback.class);
  
  public BvhTriangleMeshShape()
  {
    super(null);
    this.ownsBvh = false;
  }
  
  public BvhTriangleMeshShape(StridingMeshInterface meshInterface, boolean useQuantizedAabbCompression)
  {
    this(meshInterface, useQuantizedAabbCompression, true);
  }
  
  public BvhTriangleMeshShape(StridingMeshInterface meshInterface, boolean useQuantizedAabbCompression, boolean buildBvh)
  {
    super(meshInterface);
    this.useQuantizedAabbCompression = useQuantizedAabbCompression;
    this.ownsBvh = false;
    Vector3f bvhAabbMin = new Vector3f();
    Vector3f bvhAabbMax = new Vector3f();
    meshInterface.calculateAabbBruteForce(bvhAabbMin, bvhAabbMax);
    if (buildBvh)
    {
      this.bvh = new OptimizedBvh();
      this.bvh.build(meshInterface, useQuantizedAabbCompression, bvhAabbMin, bvhAabbMax);
      this.ownsBvh = true;
      recalcLocalAabb();
    }
  }
  
  public BvhTriangleMeshShape(StridingMeshInterface meshInterface, boolean useQuantizedAabbCompression, Vector3f bvhAabbMin, Vector3f bvhAabbMax)
  {
    this(meshInterface, useQuantizedAabbCompression, bvhAabbMin, bvhAabbMax, true);
  }
  
  public BvhTriangleMeshShape(StridingMeshInterface meshInterface, boolean useQuantizedAabbCompression, Vector3f bvhAabbMin, Vector3f bvhAabbMax, boolean buildBvh)
  {
    super(meshInterface);
    this.useQuantizedAabbCompression = useQuantizedAabbCompression;
    this.ownsBvh = false;
    if (buildBvh)
    {
      this.bvh = new OptimizedBvh();
      this.bvh.build(meshInterface, useQuantizedAabbCompression, bvhAabbMin, bvhAabbMax);
      this.ownsBvh = true;
    }
    recalcLocalAabb();
  }
  
  public boolean getOwnsBvh()
  {
    return this.ownsBvh;
  }
  
  public BroadphaseNativeType getShapeType()
  {
    return BroadphaseNativeType.TRIANGLE_MESH_SHAPE_PROXYTYPE;
  }
  
  public void performRaycast(TriangleCallback callback, Vector3f raySource, Vector3f rayTarget)
  {
    MyNodeOverlapCallback myNodeCallback = (MyNodeOverlapCallback)this.myNodeCallbacks.get();
    myNodeCallback.init(callback, this.meshInterface);
    this.bvh.reportRayOverlappingNodex(myNodeCallback, raySource, rayTarget);
    this.myNodeCallbacks.release(myNodeCallback);
  }
  
  public void performConvexcast(TriangleCallback callback, Vector3f raySource, Vector3f rayTarget, Vector3f aabbMin, Vector3f aabbMax)
  {
    MyNodeOverlapCallback myNodeCallback = (MyNodeOverlapCallback)this.myNodeCallbacks.get();
    myNodeCallback.init(callback, this.meshInterface);
    this.bvh.reportBoxCastOverlappingNodex(myNodeCallback, raySource, rayTarget, aabbMin, aabbMax);
    this.myNodeCallbacks.release(myNodeCallback);
  }
  
  public void processAllTriangles(TriangleCallback callback, Vector3f aabbMin, Vector3f aabbMax)
  {
    MyNodeOverlapCallback myNodeCallback = (MyNodeOverlapCallback)this.myNodeCallbacks.get();
    myNodeCallback.init(callback, this.meshInterface);
    this.bvh.reportAabbOverlappingNodex(myNodeCallback, aabbMin, aabbMax);
    this.myNodeCallbacks.release(myNodeCallback);
  }
  
  public void refitTree(Vector3f aabbMin, Vector3f aabbMax)
  {
    this.bvh.refit(this.meshInterface);
    recalcLocalAabb();
  }
  
  public void partialRefitTree(Vector3f aabbMin, Vector3f aabbMax)
  {
    this.bvh.refitPartial(this.meshInterface, aabbMin, aabbMax);
    VectorUtil.setMin(this.localAabbMin, aabbMin);
    VectorUtil.setMax(this.localAabbMax, aabbMax);
  }
  
  public String getName()
  {
    return "BVHTRIANGLEMESH";
  }
  
  public void setLocalScaling(Vector3f arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      tmp.sub(getLocalScaling(localStack.get$javax$vecmath$Vector3f()), scaling);
      if (tmp.lengthSquared() > 1.192093E-007F)
      {
        super.setLocalScaling(scaling);
        this.bvh = new OptimizedBvh();
        this.bvh.build(this.meshInterface, this.useQuantizedAabbCompression, this.localAabbMin, this.localAabbMax);
        this.ownsBvh = true;
      }
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public OptimizedBvh getOptimizedBvh()
  {
    return this.bvh;
  }
  
  public void setOptimizedBvh(OptimizedBvh arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f scaling = localStack.get$javax$vecmath$Vector3f();
      scaling.set(1.0F, 1.0F, 1.0F);
      setOptimizedBvh(bvh, scaling);
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void setOptimizedBvh(OptimizedBvh arg1, Vector3f arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      assert (this.bvh == null);
      assert (!this.ownsBvh);
      this.bvh = bvh;
      this.ownsBvh = false;
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      tmp.sub(getLocalScaling(localStack.get$javax$vecmath$Vector3f()), scaling);
      if (tmp.lengthSquared() > 1.192093E-007F) {
        super.setLocalScaling(scaling);
      }
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public boolean usesQuantizedAabbCompression()
  {
    return this.useQuantizedAabbCompression;
  }
  
  protected static class MyNodeOverlapCallback
    extends NodeOverlapCallback
  {
    public StridingMeshInterface meshInterface;
    public TriangleCallback callback;
    private Vector3f[] triangle = { new Vector3f(), new Vector3f(), new Vector3f() };
    
    public void init(TriangleCallback callback, StridingMeshInterface meshInterface)
    {
      this.meshInterface = meshInterface;
      this.callback = callback;
    }
    
    public void processNode(int arg1, int arg2)
    {
      .Stack localStack = .Stack.get();
      try
      {
        localStack.push$javax$vecmath$Vector3f();
        VertexData data = this.meshInterface.getLockedReadOnlyVertexIndexBase(nodeSubPart);
        Vector3f meshScaling = this.meshInterface.getScaling(localStack.get$javax$vecmath$Vector3f());
        data.getTriangle(nodeTriangleIndex * 3, meshScaling, this.triangle);
        this.callback.processTriangle(this.triangle, nodeSubPart, nodeTriangleIndex);
        this.meshInterface.unLockReadOnlyVertexBase(nodeSubPart);
        return;
      }
      finally
      {
        localStack.pop$javax$vecmath$Vector3f();
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.shapes.BvhTriangleMeshShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */