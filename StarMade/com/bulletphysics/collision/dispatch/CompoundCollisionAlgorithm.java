package com.bulletphysics.collision.dispatch;

import com.bulletphysics..Stack;
import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
import com.bulletphysics.collision.broadphase.Dispatcher;
import com.bulletphysics.collision.broadphase.DispatcherInfo;
import com.bulletphysics.collision.narrowphase.PersistentManifold;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.CompoundShape;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.util.ObjectArrayList;
import com.bulletphysics.util.ObjectPool;

public class CompoundCollisionAlgorithm
  extends CollisionAlgorithm
{
  private final ObjectArrayList<CollisionAlgorithm> childCollisionAlgorithms = new ObjectArrayList();
  private boolean isSwapped;
  
  public void init(CollisionAlgorithmConstructionInfo local_ci, CollisionObject body0, CollisionObject body1, boolean isSwapped)
  {
    super.init(local_ci);
    this.isSwapped = isSwapped;
    CollisionObject colObj = isSwapped ? body1 : body0;
    CollisionObject otherObj = isSwapped ? body0 : body1;
    assert (colObj.getCollisionShape().isCompound());
    CompoundShape compoundShape = (CompoundShape)colObj.getCollisionShape();
    int numChildren = compoundShape.getNumChildShapes();
    for (int local_i = 0; local_i < numChildren; local_i++)
    {
      CollisionShape tmpShape = colObj.getCollisionShape();
      CollisionShape childShape = compoundShape.getChildShape(local_i);
      colObj.internalSetTemporaryCollisionShape(childShape);
      this.childCollisionAlgorithms.add(local_ci.dispatcher1.findAlgorithm(colObj, otherObj));
      colObj.internalSetTemporaryCollisionShape(tmpShape);
    }
  }
  
  public void destroy()
  {
    int numChildren = this.childCollisionAlgorithms.size();
    for (int local_i = 0; local_i < numChildren; local_i++) {
      this.dispatcher.freeCollisionAlgorithm((CollisionAlgorithm)this.childCollisionAlgorithms.getQuick(local_i));
    }
    this.childCollisionAlgorithms.clear();
  }
  
  public void processCollision(CollisionObject arg1, CollisionObject arg2, DispatcherInfo arg3, ManifoldResult arg4)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$com$bulletphysics$linearmath$Transform();
      CollisionObject colObj = this.isSwapped ? body1 : body0;
      CollisionObject otherObj = this.isSwapped ? body0 : body1;
      assert (colObj.getCollisionShape().isCompound());
      CompoundShape compoundShape = (CompoundShape)colObj.getCollisionShape();
      Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
      Transform orgTrans = localStack.get$com$bulletphysics$linearmath$Transform();
      Transform childTrans = localStack.get$com$bulletphysics$linearmath$Transform();
      Transform orgInterpolationTrans = localStack.get$com$bulletphysics$linearmath$Transform();
      Transform newChildWorldTrans = localStack.get$com$bulletphysics$linearmath$Transform();
      int numChildren = this.childCollisionAlgorithms.size();
      for (int local_i = 0; local_i < numChildren; local_i++)
      {
        CollisionShape childShape = compoundShape.getChildShape(local_i);
        colObj.getWorldTransform(orgTrans);
        colObj.getInterpolationWorldTransform(orgInterpolationTrans);
        compoundShape.getChildTransform(local_i, childTrans);
        newChildWorldTrans.mul(orgTrans, childTrans);
        colObj.setWorldTransform(newChildWorldTrans);
        colObj.setInterpolationWorldTransform(newChildWorldTrans);
        CollisionShape tmpShape = colObj.getCollisionShape();
        colObj.internalSetTemporaryCollisionShape(childShape);
        ((CollisionAlgorithm)this.childCollisionAlgorithms.getQuick(local_i)).processCollision(colObj, otherObj, dispatchInfo, resultOut);
        colObj.internalSetTemporaryCollisionShape(tmpShape);
        colObj.setWorldTransform(orgTrans);
        colObj.setInterpolationWorldTransform(orgInterpolationTrans);
      }
      return;
    }
    finally
    {
      localStack.pop$com$bulletphysics$linearmath$Transform();
    }
  }
  
  public float calculateTimeOfImpact(CollisionObject arg1, CollisionObject arg2, DispatcherInfo arg3, ManifoldResult arg4)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$com$bulletphysics$linearmath$Transform();
      CollisionObject colObj = this.isSwapped ? body1 : body0;
      CollisionObject otherObj = this.isSwapped ? body0 : body1;
      assert (colObj.getCollisionShape().isCompound());
      CompoundShape compoundShape = (CompoundShape)colObj.getCollisionShape();
      Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
      Transform orgTrans = localStack.get$com$bulletphysics$linearmath$Transform();
      Transform childTrans = localStack.get$com$bulletphysics$linearmath$Transform();
      float hitFraction = 1.0F;
      int numChildren = this.childCollisionAlgorithms.size();
      for (int local_i = 0; local_i < numChildren; local_i++)
      {
        CollisionShape childShape = compoundShape.getChildShape(local_i);
        colObj.getWorldTransform(orgTrans);
        compoundShape.getChildTransform(local_i, childTrans);
        tmpTrans.set(orgTrans);
        tmpTrans.mul(childTrans);
        colObj.setWorldTransform(tmpTrans);
        CollisionShape tmpShape = colObj.getCollisionShape();
        colObj.internalSetTemporaryCollisionShape(childShape);
        float frac = ((CollisionAlgorithm)this.childCollisionAlgorithms.getQuick(local_i)).calculateTimeOfImpact(colObj, otherObj, dispatchInfo, resultOut);
        if (frac < hitFraction) {
          hitFraction = frac;
        }
        colObj.internalSetTemporaryCollisionShape(tmpShape);
        colObj.setWorldTransform(orgTrans);
      }
      return hitFraction;
    }
    finally
    {
      localStack.pop$com$bulletphysics$linearmath$Transform();
    }
  }
  
  public void getAllContactManifolds(ObjectArrayList<PersistentManifold> manifoldArray)
  {
    for (int local_i = 0; local_i < this.childCollisionAlgorithms.size(); local_i++) {
      ((CollisionAlgorithm)this.childCollisionAlgorithms.getQuick(local_i)).getAllContactManifolds(manifoldArray);
    }
  }
  
  public static class SwappedCreateFunc
    extends CollisionAlgorithmCreateFunc
  {
    private final ObjectPool<CompoundCollisionAlgorithm> pool = ObjectPool.get(CompoundCollisionAlgorithm.class);
    
    public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo local_ci, CollisionObject body0, CollisionObject body1)
    {
      CompoundCollisionAlgorithm algo = (CompoundCollisionAlgorithm)this.pool.get();
      algo.init(local_ci, body0, body1, true);
      return algo;
    }
    
    public void releaseCollisionAlgorithm(CollisionAlgorithm algo)
    {
      this.pool.release((CompoundCollisionAlgorithm)algo);
    }
  }
  
  public static class CreateFunc
    extends CollisionAlgorithmCreateFunc
  {
    private final ObjectPool<CompoundCollisionAlgorithm> pool = ObjectPool.get(CompoundCollisionAlgorithm.class);
    
    public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo local_ci, CollisionObject body0, CollisionObject body1)
    {
      CompoundCollisionAlgorithm algo = (CompoundCollisionAlgorithm)this.pool.get();
      algo.init(local_ci, body0, body1, false);
      return algo;
    }
    
    public void releaseCollisionAlgorithm(CollisionAlgorithm algo)
    {
      this.pool.release((CompoundCollisionAlgorithm)algo);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.dispatch.CompoundCollisionAlgorithm
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */