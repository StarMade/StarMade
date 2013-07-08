package org.schema.game.common.data.physics;

import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.dispatch.CollisionWorld.ConvexResultCallback;
import com.bulletphysics.collision.dispatch.PairCachingGhostObject;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.ConvexShape;
import com.bulletphysics.linearmath.AabbUtil2;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.util.ObjectArrayList;
import java.io.PrintStream;
import javax.vecmath.Matrix3f;
import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import org.schema.common.util.linAlg.TransformTools;
import org.schema.game.common.controller.SegmentController;
import org.schema.schine.network.objects.container.PhysicsDataContainer;

public abstract class PairCachingGhostObjectExt
  extends PairCachingGhostObject
{
  private PhysicsDataContainer pCon;
  private SegmentController attached;
  Vector3f castShapeAabbMin = new Vector3f();
  Vector3f castShapeAabbMax = new Vector3f();
  Transform convexFromTrans = new Transform();
  Transform convexToTrans = new Transform();
  Vector3f linVel = new Vector3f();
  Vector3f angVel = new Vector3f();
  Transform tmpTrans = new Transform();
  Vector3f collisionObjectAabbMin = new Vector3f();
  Vector3f collisionObjectAabbMax = new Vector3f();
  Vector3f hitNormal = new Vector3f();
  Transform field_380 = new Transform();
  Quat4f quat = new Quat4f();
  private Matrix3f tmp = new Matrix3f();
  private final Vector3f axis = new Vector3f();
  private final Matrix3f dmat = new Matrix3f();
  private final Quat4f dorn = new Quat4f();
  
  public PairCachingGhostObjectExt(PhysicsDataContainer paramPhysicsDataContainer)
  {
    this.pCon = paramPhysicsDataContainer;
  }
  
  public String toString()
  {
    return "PCGhostObjExt(" + getUserPointer() + ")@" + hashCode();
  }
  
  public void setWorldTransform(Transform paramTransform)
  {
    this.worldTransform.set(paramTransform);
  }
  
  public void convexSweepTest(ConvexShape paramConvexShape, Transform paramTransform1, Transform paramTransform2, CollisionWorld.ConvexResultCallback paramConvexResultCallback, float paramFloat)
  {
    this.convexFromTrans.set(paramTransform1);
    this.convexToTrans.set(paramTransform2);
    this.quat.field_596 = 0.0F;
    this.quat.field_597 = 0.0F;
    this.quat.field_596 = 0.0F;
    this.quat.field_599 = 0.0F;
    assert (paramTransform1.getMatrix(new Matrix4f()).determinant() != 0.0F) : paramTransform1.getMatrix(new Matrix4f());
    assert (paramTransform2.getMatrix(new Matrix4f()).determinant() != 0.0F) : paramTransform2.getMatrix(new Matrix4f());
    TransformTools.a1(this.convexFromTrans, this.convexToTrans, 1.0F, this.linVel, this.angVel, this.axis, this.tmp, this.dmat, this.dorn);
    this.field_380.setIdentity();
    this.field_380.setRotation(this.convexFromTrans.getRotation(this.quat));
    paramConvexShape.calculateTemporalAabb(this.field_380, this.linVel, this.angVel, 1.0F, this.castShapeAabbMin, this.castShapeAabbMax);
    long l = System.currentTimeMillis();
    for (int i = 0; i < this.overlappingObjects.size(); i++)
    {
      CollisionObject localCollisionObject;
      if (((localCollisionObject = (CollisionObject)this.overlappingObjects.getQuick(i)) != this.pCon.getObject()) && (paramConvexResultCallback.needsCollision(localCollisionObject.getBroadphaseHandle())))
      {
        localCollisionObject.getCollisionShape().getAabb(localCollisionObject.getWorldTransform(this.tmpTrans), this.collisionObjectAabbMin, this.collisionObjectAabbMax);
        AabbUtil2.aabbExpand(this.collisionObjectAabbMin, this.collisionObjectAabbMax, this.castShapeAabbMin, this.castShapeAabbMax);
        float[] arrayOfFloat = { 1.0F };
        this.hitNormal.set(0.0F, 0.0F, 0.0F);
        if (AabbUtil2.rayAabb(paramTransform1.origin, paramTransform2.origin, this.collisionObjectAabbMin, this.collisionObjectAabbMax, arrayOfFloat, this.hitNormal)) {
          ModifiedDynamicsWorld.objectQuerySingle(paramConvexShape, this.convexFromTrans, this.convexToTrans, localCollisionObject, localCollisionObject.getCollisionShape(), localCollisionObject.getWorldTransform(this.tmpTrans), paramConvexResultCallback, paramFloat);
        }
      }
    }
    if ((i = (int)(System.currentTimeMillis() - l)) > 15) {
      System.err.println("[GHOST-OBJECT] SWEEP TEST TIME: " + i);
    }
  }
  
  public SegmentController getAttached()
  {
    return this.attached;
  }
  
  public void setAttached(SegmentController paramSegmentController)
  {
    this.attached = paramSegmentController;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.PairCachingGhostObjectExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */