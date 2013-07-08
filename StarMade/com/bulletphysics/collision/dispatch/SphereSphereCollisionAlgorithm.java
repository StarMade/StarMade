package com.bulletphysics.collision.dispatch;

import com.bulletphysics..Stack;
import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
import com.bulletphysics.collision.broadphase.Dispatcher;
import com.bulletphysics.collision.broadphase.DispatcherInfo;
import com.bulletphysics.collision.narrowphase.PersistentManifold;
import com.bulletphysics.collision.shapes.SphereShape;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.util.ObjectArrayList;
import com.bulletphysics.util.ObjectPool;
import javax.vecmath.Vector3f;

public class SphereSphereCollisionAlgorithm
  extends CollisionAlgorithm
{
  private boolean ownManifold;
  private PersistentManifold manifoldPtr;
  
  public void init(PersistentManifold local_mf, CollisionAlgorithmConstructionInfo local_ci, CollisionObject col0, CollisionObject col1)
  {
    super.init(local_ci);
    this.manifoldPtr = local_mf;
    if (this.manifoldPtr == null)
    {
      this.manifoldPtr = this.dispatcher.getNewManifold(col0, col1);
      this.ownManifold = true;
    }
  }
  
  public void init(CollisionAlgorithmConstructionInfo local_ci)
  {
    super.init(local_ci);
  }
  
  public void destroy()
  {
    if (this.ownManifold)
    {
      if (this.manifoldPtr != null) {
        this.dispatcher.releaseManifold(this.manifoldPtr);
      }
      this.manifoldPtr = null;
    }
  }
  
  public void processCollision(CollisionObject arg1, CollisionObject arg2, DispatcherInfo arg3, ManifoldResult arg4)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      tmp7_5.push$javax$vecmath$Vector3f();
      if (this.manifoldPtr == null) {
        return;
      }
      Transform tmpTrans1 = localStack.get$com$bulletphysics$linearmath$Transform();
      Transform tmpTrans2 = localStack.get$com$bulletphysics$linearmath$Transform();
      resultOut.setPersistentManifold(this.manifoldPtr);
      SphereShape sphere0 = (SphereShape)col0.getCollisionShape();
      SphereShape sphere1 = (SphereShape)col1.getCollisionShape();
      Vector3f diff = localStack.get$javax$vecmath$Vector3f();
      diff.sub(col0.getWorldTransform(tmpTrans1).origin, col1.getWorldTransform(tmpTrans2).origin);
      float len = diff.length();
      float radius0 = sphere0.getRadius();
      float radius1 = sphere1.getRadius();
      if (len > radius0 + radius1)
      {
        resultOut.refreshContactPoints();
        return;
      }
      float dist = len - (radius0 + radius1);
      Vector3f normalOnSurfaceB = localStack.get$javax$vecmath$Vector3f();
      normalOnSurfaceB.set(1.0F, 0.0F, 0.0F);
      if (len > 1.192093E-007F) {
        normalOnSurfaceB.scale(1.0F / len, diff);
      }
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      Vector3f pos0 = localStack.get$javax$vecmath$Vector3f();
      tmp.scale(radius0, normalOnSurfaceB);
      pos0.sub(col0.getWorldTransform(tmpTrans1).origin, tmp);
      Vector3f pos1 = localStack.get$javax$vecmath$Vector3f();
      tmp.scale(radius1, normalOnSurfaceB);
      pos1.add(col1.getWorldTransform(tmpTrans2).origin, tmp);
      resultOut.addContactPoint(normalOnSurfaceB, pos1, dist);
      resultOut.refreshContactPoints();
      return;
    }
    finally
    {
      .Stack tmp292_290 = localStack;
      tmp292_290.pop$com$bulletphysics$linearmath$Transform();
      tmp292_290.pop$javax$vecmath$Vector3f();
    }
  }
  
  public float calculateTimeOfImpact(CollisionObject body0, CollisionObject body1, DispatcherInfo dispatchInfo, ManifoldResult resultOut)
  {
    return 1.0F;
  }
  
  public void getAllContactManifolds(ObjectArrayList<PersistentManifold> manifoldArray)
  {
    if ((this.manifoldPtr != null) && (this.ownManifold)) {
      manifoldArray.add(this.manifoldPtr);
    }
  }
  
  public static class CreateFunc
    extends CollisionAlgorithmCreateFunc
  {
    private final ObjectPool<SphereSphereCollisionAlgorithm> pool = ObjectPool.get(SphereSphereCollisionAlgorithm.class);
    
    public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo local_ci, CollisionObject body0, CollisionObject body1)
    {
      SphereSphereCollisionAlgorithm algo = (SphereSphereCollisionAlgorithm)this.pool.get();
      algo.init(null, local_ci, body0, body1);
      return algo;
    }
    
    public void releaseCollisionAlgorithm(CollisionAlgorithm algo)
    {
      this.pool.release((SphereSphereCollisionAlgorithm)algo);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.dispatch.SphereSphereCollisionAlgorithm
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */