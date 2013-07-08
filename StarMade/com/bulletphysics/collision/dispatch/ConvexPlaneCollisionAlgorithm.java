package com.bulletphysics.collision.dispatch;

import com.bulletphysics..Stack;
import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
import com.bulletphysics.collision.broadphase.Dispatcher;
import com.bulletphysics.collision.broadphase.DispatcherInfo;
import com.bulletphysics.collision.narrowphase.PersistentManifold;
import com.bulletphysics.collision.shapes.ConvexShape;
import com.bulletphysics.collision.shapes.StaticPlaneShape;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.util.ObjectArrayList;
import com.bulletphysics.util.ObjectPool;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;

public class ConvexPlaneCollisionAlgorithm
  extends CollisionAlgorithm
{
  private boolean ownManifold;
  private PersistentManifold manifoldPtr;
  private boolean isSwapped;
  
  public void init(PersistentManifold local_mf, CollisionAlgorithmConstructionInfo local_ci, CollisionObject col0, CollisionObject col1, boolean isSwapped)
  {
    super.init(local_ci);
    this.ownManifold = false;
    this.manifoldPtr = local_mf;
    this.isSwapped = isSwapped;
    CollisionObject convexObj = isSwapped ? col1 : col0;
    CollisionObject planeObj = isSwapped ? col0 : col1;
    if ((this.manifoldPtr == null) && (this.dispatcher.needsCollision(convexObj, planeObj)))
    {
      this.manifoldPtr = this.dispatcher.getNewManifold(convexObj, planeObj);
      this.ownManifold = true;
    }
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
      Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
      CollisionObject convexObj = this.isSwapped ? body1 : body0;
      CollisionObject planeObj = this.isSwapped ? body0 : body1;
      ConvexShape convexShape = (ConvexShape)convexObj.getCollisionShape();
      StaticPlaneShape planeShape = (StaticPlaneShape)planeObj.getCollisionShape();
      boolean hasCollision = false;
      Vector3f planeNormal = planeShape.getPlaneNormal(localStack.get$javax$vecmath$Vector3f());
      float planeConstant = planeShape.getPlaneConstant();
      Transform planeInConvex = localStack.get$com$bulletphysics$linearmath$Transform();
      convexObj.getWorldTransform(planeInConvex);
      planeInConvex.inverse();
      planeInConvex.mul(planeObj.getWorldTransform(tmpTrans));
      Transform convexInPlaneTrans = localStack.get$com$bulletphysics$linearmath$Transform();
      convexInPlaneTrans.inverse(planeObj.getWorldTransform(tmpTrans));
      convexInPlaneTrans.mul(convexObj.getWorldTransform(tmpTrans));
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      tmp.negate(planeNormal);
      planeInConvex.basis.transform(tmp);
      Vector3f vtx = convexShape.localGetSupportingVertex(tmp, localStack.get$javax$vecmath$Vector3f());
      Vector3f vtxInPlane = localStack.get$javax$vecmath$Vector3f(vtx);
      convexInPlaneTrans.transform(vtxInPlane);
      float distance = planeNormal.dot(vtxInPlane) - planeConstant;
      Vector3f vtxInPlaneProjected = localStack.get$javax$vecmath$Vector3f();
      tmp.scale(distance, planeNormal);
      vtxInPlaneProjected.sub(vtxInPlane, tmp);
      Vector3f vtxInPlaneWorld = localStack.get$javax$vecmath$Vector3f(vtxInPlaneProjected);
      planeObj.getWorldTransform(tmpTrans).transform(vtxInPlaneWorld);
      hasCollision = distance < this.manifoldPtr.getContactBreakingThreshold();
      resultOut.setPersistentManifold(this.manifoldPtr);
      if (hasCollision)
      {
        Vector3f normalOnSurfaceB = localStack.get$javax$vecmath$Vector3f(planeNormal);
        planeObj.getWorldTransform(tmpTrans).basis.transform(normalOnSurfaceB);
        Vector3f pOnB = localStack.get$javax$vecmath$Vector3f(vtxInPlaneWorld);
        resultOut.addContactPoint(normalOnSurfaceB, pOnB, distance);
      }
      if ((this.ownManifold) && (this.manifoldPtr.getNumContacts() != 0)) {
        resultOut.refreshContactPoints();
      }
      return;
    }
    finally
    {
      .Stack tmp399_397 = localStack;
      tmp399_397.pop$com$bulletphysics$linearmath$Transform();
      tmp399_397.pop$javax$vecmath$Vector3f();
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
    private final ObjectPool<ConvexPlaneCollisionAlgorithm> pool = ObjectPool.get(ConvexPlaneCollisionAlgorithm.class);
    
    public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo local_ci, CollisionObject body0, CollisionObject body1)
    {
      ConvexPlaneCollisionAlgorithm algo = (ConvexPlaneCollisionAlgorithm)this.pool.get();
      if (!this.swapped) {
        algo.init(null, local_ci, body0, body1, false);
      } else {
        algo.init(null, local_ci, body0, body1, true);
      }
      return algo;
    }
    
    public void releaseCollisionAlgorithm(CollisionAlgorithm algo)
    {
      this.pool.release((ConvexPlaneCollisionAlgorithm)algo);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.dispatch.ConvexPlaneCollisionAlgorithm
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */