package org.schema.game.common.data.physics;

import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
import com.bulletphysics.collision.broadphase.Dispatcher;
import com.bulletphysics.collision.broadphase.DispatcherInfo;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.dispatch.ManifoldResult;
import com.bulletphysics.collision.narrowphase.PersistentManifold;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.CompoundShape;
import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.util.ObjectArrayList;
import java.io.PrintStream;
import org.schema.game.common.controller.SegmentController;

public class CompoundCollisionAlgorithmExt
  extends CollisionAlgorithm
{
  private CollisionObject compoundObject;
  private CollisionObject otherObject;
  public boolean swapped;
  private PersistentManifold manifoldPtr;
  private static ThreadLocal threadLocal = new CompoundCollisionAlgorithmExt.1();
  private final ObjectArrayList childCollisionAlgorithms = new ObjectArrayList();
  private final ObjectArrayList childCollisionAlgorithmsCOM = new ObjectArrayList();
  private CompoundCollisionVariableSet field_264;
  
  public String toString()
  {
    return "CompoundAlgo[" + this.compoundObject + "->" + this.otherObject + "]";
  }
  
  public void init(CollisionAlgorithmConstructionInfo paramCollisionAlgorithmConstructionInfo)
  {
    super.init(paramCollisionAlgorithmConstructionInfo);
    this.field_264 = ((CompoundCollisionVariableSet)threadLocal.get());
    this.field_264.instances += 1;
    assert (this.compoundObject.getCollisionShape().isCompound());
    assert ((!(this.compoundObject.getCollisionShape() instanceof CubeShape)) && (!(this.otherObject.getCollisionShape() instanceof CubeShape))) : (this.compoundObject + " --- " + this.otherObject);
    CompoundShape localCompoundShape;
    int i = (localCompoundShape = (CompoundShape)this.compoundObject.getCollisionShape()).getNumChildShapes();
    for (int j = 0; j < i; j++)
    {
      CollisionShape localCollisionShape1 = this.compoundObject.getCollisionShape();
      Object localObject = localCompoundShape.getChildShape(j);
      this.compoundObject.internalSetTemporaryCollisionShape((CollisionShape)localObject);
      if (this.otherObject.getCollisionShape().isCompound())
      {
        localObject = (CompoundShape)this.otherObject.getCollisionShape();
        for (int k = 0; k < ((CompoundShape)localObject).getNumChildShapes(); k++)
        {
          CollisionShape localCollisionShape2 = this.otherObject.getCollisionShape();
          CollisionShape localCollisionShape3 = ((CompoundShape)localObject).getChildShape(k);
          this.otherObject.internalSetTemporaryCollisionShape(localCollisionShape3);
          this.childCollisionAlgorithmsCOM.add(paramCollisionAlgorithmConstructionInfo.dispatcher1.findAlgorithm(this.compoundObject, this.otherObject));
          this.otherObject.internalSetTemporaryCollisionShape(localCollisionShape2);
        }
      }
      else
      {
        this.childCollisionAlgorithms.add(paramCollisionAlgorithmConstructionInfo.dispatcher1.findAlgorithm(this.compoundObject, this.otherObject));
      }
      this.compoundObject.internalSetTemporaryCollisionShape(localCollisionShape1);
    }
  }
  
  public void destroy()
  {
    if (this.manifoldPtr != null)
    {
      this.dispatcher.releaseManifold(this.manifoldPtr);
      this.manifoldPtr = null;
    }
    else if (!$assertionsDisabled)
    {
      throw new AssertionError();
    }
    int i = this.childCollisionAlgorithms.size();
    for (int j = 0; j < i; j++)
    {
      ((CollisionAlgorithm)this.childCollisionAlgorithms.get(j)).destroy();
      this.dispatcher.freeCollisionAlgorithm((CollisionAlgorithm)this.childCollisionAlgorithms.getQuick(j));
    }
    this.childCollisionAlgorithms.clear();
    i = this.childCollisionAlgorithmsCOM.size();
    for (j = 0; j < i; j++) {
      this.dispatcher.freeCollisionAlgorithm((CollisionAlgorithm)this.childCollisionAlgorithmsCOM.getQuick(j));
    }
    this.childCollisionAlgorithmsCOM.clear();
    this.compoundObject = null;
    this.otherObject = null;
    this.childCollisionAlgorithms.clear();
    this.childCollisionAlgorithmsCOM.clear();
    this.field_264.instances -= 1;
  }
  
  public void processCollision(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult)
  {
    if ((paramCollisionObject1 == this.otherObject) && (paramCollisionObject2 == this.compoundObject))
    {
      paramCollisionObject1 = this.compoundObject;
      paramCollisionObject2 = this.otherObject;
    }
    if ((paramCollisionObject1 != this.compoundObject) || (paramCollisionObject2 != this.otherObject)) {
      System.err.println("COMPOUND ALGORITHM MULTIUSE ?!?!\n---> " + paramCollisionObject1 + ";         " + this.compoundObject + "\n---> " + paramCollisionObject2 + ";         " + this.otherObject);
    }
    assert (paramCollisionObject1 == this.compoundObject) : (paramCollisionObject1 + "; " + this.compoundObject);
    assert (paramCollisionObject2 == this.otherObject) : (paramCollisionObject2 + "; " + this.otherObject);
    if (this.manifoldPtr == null) {
      this.manifoldPtr = this.dispatcher.getNewManifold(paramCollisionObject1, paramCollisionObject2);
    }
    if ((this.manifoldPtr.getBody0() != paramCollisionObject1) || (this.manifoldPtr.getBody1() != paramCollisionObject2))
    {
      this.dispatcher.releaseManifold(this.manifoldPtr);
      System.err.println("[COMPOUNDECOLLISION] Exception: wrong manifold: \n----> " + this.manifoldPtr.getBody0() + " != " + paramCollisionObject1 + " or \n----> " + this.manifoldPtr.getBody1() + " != " + paramCollisionObject2);
      localObject1 = ((RigidBodyExt)this.compoundObject).getSegmentController().getPhysics().getDynamicsWorld().getCollisionObjectArray();
      for (int i = 0; i < ((ObjectArrayList)localObject1).size(); i++) {
        System.err.println("OBJECTS LISTED " + ((ObjectArrayList)localObject1).getQuick(i));
      }
      this.manifoldPtr = this.dispatcher.getNewManifold(paramCollisionObject1, paramCollisionObject2);
    }
    paramManifoldResult.setPersistentManifold(this.manifoldPtr);
    assert (this.compoundObject.getCollisionShape().isCompound());
    Object localObject1 = (CompoundShape)this.compoundObject.getCollisionShape();
    paramCollisionObject1 = this.field_264.orgTrans;
    paramCollisionObject2 = this.field_264.chieldTrans;
    Transform localTransform1 = this.field_264.interpolationTrans;
    Transform localTransform2 = this.field_264.newChildWorldTrans;
    int j = ((CompoundShape)localObject1).getNumChildShapes();
    int m = 0;
    for (int k = 0; k < j; k++)
    {
      Object localObject2 = ((CompoundShape)localObject1).getChildShape(k);
      this.compoundObject.getWorldTransform(paramCollisionObject1);
      this.compoundObject.getInterpolationWorldTransform(localTransform1);
      ((CompoundShape)localObject1).getChildTransform(k, paramCollisionObject2);
      localTransform2.mul(paramCollisionObject1, paramCollisionObject2);
      this.compoundObject.setWorldTransform(localTransform2);
      this.compoundObject.setInterpolationWorldTransform(localTransform2);
      CollisionShape localCollisionShape = this.compoundObject.getCollisionShape();
      this.compoundObject.internalSetTemporaryCollisionShape((CollisionShape)localObject2);
      Object localObject3;
      if (this.otherObject.getCollisionShape().isCompound())
      {
        localObject2 = (CompoundShape)this.otherObject.getCollisionShape();
        for (int n = 0; n < ((CompoundShape)localObject2).getNumChildShapes(); n++)
        {
          localObject3 = (CollisionAlgorithm)this.childCollisionAlgorithmsCOM.getQuick(m);
          Transform localTransform3 = this.field_264.orgTransO;
          Object localObject4 = this.field_264.chieldTransO;
          Transform localTransform4 = this.field_264.interpolationTransO;
          Object localObject5 = this.field_264.newChildWorldTransO;
          Object localObject6 = ((CompoundShape)localObject2).getChildShape(n);
          this.otherObject.getWorldTransform(localTransform3);
          this.otherObject.getInterpolationWorldTransform(localTransform4);
          ((CompoundShape)localObject2).getChildTransform(n, (Transform)localObject4);
          ((Transform)localObject5).mul(localTransform3, (Transform)localObject4);
          this.otherObject.setWorldTransform((Transform)localObject5);
          this.otherObject.setInterpolationWorldTransform((Transform)localObject5);
          localObject4 = this.otherObject.getCollisionShape();
          this.otherObject.internalSetTemporaryCollisionShape((CollisionShape)localObject6);
          localObject5 = this.compoundObject;
          localObject6 = this.otherObject;
          ((CollisionAlgorithm)localObject3).processCollision((CollisionObject)localObject5, (CollisionObject)localObject6, paramDispatcherInfo, paramManifoldResult);
          this.otherObject.internalSetTemporaryCollisionShape((CollisionShape)localObject4);
          this.otherObject.setWorldTransform(localTransform3);
          this.otherObject.setInterpolationWorldTransform(localTransform4);
          m++;
        }
      }
      else
      {
        localObject2 = (CollisionAlgorithm)this.childCollisionAlgorithms.getQuick(k);
        if (((this.compoundObject.getCollisionShape() instanceof CubeShape)) && ((this.otherObject.getCollisionShape() instanceof CubeShape)))
        {
          if (!$assertionsDisabled) {
            throw new AssertionError();
          }
          System.err.println("CC COLLISSION sw " + this.swapped + "; " + this.compoundObject.getCollisionShape() + "; " + this.otherObject.getCollisionShape());
          CollisionObject localCollisionObject = this.compoundObject;
          localObject3 = this.otherObject;
          this.dispatcher.findAlgorithm(localCollisionObject, (CollisionObject)localObject3).processCollision(localCollisionObject, (CollisionObject)localObject3, paramDispatcherInfo, paramManifoldResult);
        }
        else
        {
          ((CollisionAlgorithm)localObject2).processCollision(this.compoundObject, this.otherObject, paramDispatcherInfo, paramManifoldResult);
        }
      }
      this.compoundObject.internalSetTemporaryCollisionShape(localCollisionShape);
      this.compoundObject.setWorldTransform(paramCollisionObject1);
      this.compoundObject.setInterpolationWorldTransform(localTransform1);
    }
  }
  
  public float calculateTimeOfImpact(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult)
  {
    assert (this.compoundObject.getCollisionShape().isCompound());
    paramCollisionObject1 = (CompoundShape)this.compoundObject.getCollisionShape();
    paramCollisionObject2 = this.field_264.tmpTrans;
    Transform localTransform1 = this.field_264.orgTrans;
    Transform localTransform2 = this.field_264.chieldTrans;
    float f1 = 1.0F;
    int i = this.childCollisionAlgorithms.size();
    for (int j = 0; j < i; j++)
    {
      CollisionShape localCollisionShape1 = paramCollisionObject1.getChildShape(j);
      this.compoundObject.getWorldTransform(localTransform1);
      paramCollisionObject1.getChildTransform(j, localTransform2);
      paramCollisionObject2.set(localTransform1);
      paramCollisionObject2.mul(localTransform2);
      this.compoundObject.setWorldTransform(paramCollisionObject2);
      CollisionShape localCollisionShape2 = this.compoundObject.getCollisionShape();
      this.compoundObject.internalSetTemporaryCollisionShape(localCollisionShape1);
      float f2;
      if ((f2 = ((CollisionAlgorithm)this.childCollisionAlgorithms.getQuick(j)).calculateTimeOfImpact(this.compoundObject, this.otherObject, paramDispatcherInfo, paramManifoldResult)) < f1) {
        f1 = f2;
      }
      this.compoundObject.internalSetTemporaryCollisionShape(localCollisionShape2);
      this.compoundObject.setWorldTransform(localTransform1);
    }
    return f1;
  }
  
  public void getAllContactManifolds(ObjectArrayList paramObjectArrayList)
  {
    for (int i = 0; i < this.childCollisionAlgorithms.size(); i++) {
      ((CollisionAlgorithm)this.childCollisionAlgorithms.getQuick(i)).getAllContactManifolds(paramObjectArrayList);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.CompoundCollisionAlgorithmExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */