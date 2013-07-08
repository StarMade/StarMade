package org.schema.game.common.data.physics;

import class_1407;
import class_1409;
import class_1419;
import class_1421;
import class_1427;
import class_29;
import class_707;
import class_796;
import class_886;
import com.bulletphysics.BulletGlobals;
import com.bulletphysics.collision.broadphase.DbvtBroadphase;
import com.bulletphysics.collision.broadphase.OverlappingPairCache;
import com.bulletphysics.collision.dispatch.CollisionDispatcher;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
import com.bulletphysics.collision.dispatch.GhostPairCallback;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.CompoundShape;
import com.bulletphysics.collision.shapes.CompoundShapeChild;
import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
import com.bulletphysics.extras.gimpact.GImpactCollisionAlgorithm;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.util.ObjectArrayList;
import java.io.PrintStream;
import javax.vecmath.Matrix3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.element.ElementDocking;
import org.schema.game.common.data.world.Segment;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.objects.container.PhysicsDataContainer;

public class PhysicsExt
  extends class_1419
{
  private AxisSweep3Ext axisSweep3Ext;
  private DbvtBroadphase dbvtBroadphase;
  
  public PhysicsExt(class_1407 paramclass_1407)
  {
    super(paramclass_1407);
  }
  
  public RigidBody getBodyFromShape(CollisionShape paramCollisionShape, float paramFloat, Transform paramTransform)
  {
    int i = paramFloat != 0.0F ? 1 : 0;
    assert ((!(paramCollisionShape instanceof CompoundShape)) || (((CompoundShape)paramCollisionShape).getNumChildShapes() != 0)) : "tried to add empty compound shape";
    Vector3f localVector3f = new Vector3f();
    if (i != 0) {
      paramCollisionShape.calculateLocalInertia(paramFloat, localVector3f);
    }
    paramCollisionShape.setMargin(0.13F);
    paramTransform = new class_1427(paramTransform);
    paramTransform = new RigidBodyConstructionInfo(paramFloat, paramTransform, paramCollisionShape, localVector3f);
    assert (!(paramCollisionShape instanceof CubeShape));
    if ((paramCollisionShape instanceof CubesCompoundShape)) {
      paramCollisionShape = new RigidBodyExt(((CubesCompoundShape)paramCollisionShape).getSegmentController(), paramTransform);
    } else {
      paramCollisionShape = new RigidBody(paramTransform);
    }
    if (paramFloat > 0.0F) {
      paramCollisionShape.setRestitution(0.1F);
    } else {
      paramCollisionShape.setRestitution(0.0F);
    }
    paramCollisionShape.setFriction(0.7F);
    if (paramFloat > 0.0F) {
      paramCollisionShape.setDamping(getState().b1(), getState().c1());
    } else {
      paramCollisionShape.setDamping(0.0F, 0.0F);
    }
    paramCollisionShape.setMassProps(paramFloat, localVector3f);
    if (paramFloat > 0.0F) {
      paramCollisionShape.updateInertiaTensor();
    }
    if (i == 0) {
      paramCollisionShape.setCollisionFlags(paramCollisionShape.getCollisionFlags() | 0x2);
    }
    return paramCollisionShape;
  }
  
  public void initPhysics()
  {
    setCollisionConfiguration(new CubeCollissionConfiguration());
    setDispatcher(new CollisionDispatcherExt(getCollisionConfiguration()));
    new Vector3f(-2000.0F, -2000.0F, -2000.0F);
    new Vector3f(2000.0F, 2000.0F, 2000.0F);
    this.dbvtBroadphase = new DbvtBroadphaseExt(new HashedOverlappingPairCacheExt());
    setOverlappingPairCache(this.dbvtBroadphase);
    SequentialImpulseConstraintSolverExt localSequentialImpulseConstraintSolverExt = new SequentialImpulseConstraintSolverExt();
    setSolver(localSequentialImpulseConstraintSolverExt);
    setDynamicsWorld(new ModifiedDynamicsWorld(getDispatcher(), getOverlappingPairCache(), getSolver(), getCollisionConfiguration(), getState()));
    GImpactCollisionAlgorithm.registerAlgorithm((CollisionDispatcher)this.dynamicsWorld.getDispatcher());
    getDynamicsWorld().setGravity(new Vector3f(0.0F, -0.0F, 0.0F));
    getDynamicsWorld().getPairCache().setInternalGhostPairCallback(new GhostPairCallback());
    BulletGlobals.setContactBreakingThreshold(0.1F);
    getDynamicsWorld().getSolverInfo().linearSlop = 1.0E-006F;
  }
  
  public void orientate(class_1421 paramclass_1421, Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3, float paramFloat)
  {
    if (((paramclass_1421 instanceof SegmentController)) && (((SegmentController)paramclass_1421).getDockingController().b3()))
    {
      paramFloat = ((CubeShape)paramclass_1421.getPhysicsDataContainer().getShapeChild().childShape).getSegmentBuffer().a12();
      paramclass_1421 = paramFloat.getDockingController().a4().field_1740.a7().a15();
      (localObject = (class_1409)threadLocal.get()).field_1630.set(paramVector3f3);
      ((class_1409)localObject).field_1630.normalize();
      ((class_1409)localObject).field_1629.set(paramVector3f1);
      ((class_1409)localObject).field_1629.normalize();
      ((class_1409)localObject).field_1631.set(paramVector3f2);
      ((class_1409)localObject).field_1631.normalize();
      paramclass_1421.getWorldTransformInverse().basis.transform(((class_1409)localObject).field_1630);
      paramclass_1421.getWorldTransformInverse().basis.transform(((class_1409)localObject).field_1629);
      paramclass_1421.getWorldTransformInverse().basis.transform(((class_1409)localObject).field_1631);
      paramclass_1421 = new Transform();
      GlUtil.a30(((class_1409)localObject).field_1629, paramclass_1421);
      GlUtil.d2(((class_1409)localObject).field_1631, paramclass_1421);
      GlUtil.c3(((class_1409)localObject).field_1630, paramclass_1421);
      paramVector3f3 = new Quat4f();
      class_29.a5(paramclass_1421.basis, paramVector3f3);
      paramFloat.getDockingController().field_973.set(paramVector3f3);
      return;
    }
    RigidBody localRigidBody;
    if (((localRigidBody = (RigidBody)paramclass_1421.getPhysicsDataContainer().getObject()) == null) || (localRigidBody.getCollisionFlags() == 1) || (paramclass_1421.getMass() <= 0.0F)) {
      return;
    }
    class_1409 localclass_1409 = (class_1409)threadLocal.get();
    paramclass_1421 = paramclass_1421.getPhysicsDataContainer().getCurrentPhysicsTransform();
    GlUtil.c(localclass_1409.field_1623, paramclass_1421);
    GlUtil.f(localclass_1409.field_1624, paramclass_1421);
    GlUtil.e(localclass_1409.field_1632, paramclass_1421);
    localclass_1409.field_1630.set(paramVector3f3);
    localclass_1409.field_1630.normalize();
    localclass_1409.field_1629.set(paramVector3f1);
    localclass_1409.field_1629.normalize();
    localclass_1409.field_1631.set(paramVector3f2);
    localclass_1409.field_1631.normalize();
    Object localObject = new Vector3f();
    paramclass_1421 = new Vector3f();
    paramVector3f3 = new Vector3f();
    ((Vector3f)localObject).sub(localclass_1409.field_1623, localclass_1409.field_1629);
    paramclass_1421.sub(localclass_1409.field_1624, localclass_1409.field_1631);
    paramVector3f3.sub(localclass_1409.field_1632, localclass_1409.field_1630);
    localclass_1409.field_1625.cross(localclass_1409.field_1623, localclass_1409.field_1629);
    localclass_1409.field_1625.normalize();
    localclass_1409.field_1626.cross(localclass_1409.field_1624, localclass_1409.field_1631);
    localclass_1409.field_1626.normalize();
    localclass_1409.field_1627.cross(localclass_1409.field_1632, localclass_1409.field_1630);
    localclass_1409.field_1627.normalize();
    if ((((Vector3f)localObject).length() < 1.192093E-007F) && (paramclass_1421.length() < 1.192093E-007F) && (paramVector3f3.length() < 1.192093E-007F)) {
      return;
    }
    localclass_1409.field_1625.scale(((Vector3f)localObject).length());
    localclass_1409.field_1626.scale(paramclass_1421.length());
    localclass_1409.field_1627.scale(paramVector3f3.length());
    localclass_1409.field_1628.add(localclass_1409.field_1625, localclass_1409.field_1626);
    localclass_1409.field_1628.add(localclass_1409.field_1627);
    if ((localclass_1409.field_1628.length() > 5.0E-005F) && (!localRigidBody.isActive())) {
      localRigidBody.activate();
    }
    if ((paramVector3f1 != null) && (paramVector3f1.length() > 0.0F) && (paramVector3f2 != null) && (paramVector3f2.length() > 0.0F))
    {
      paramclass_1421 = new Vector3f();
      localRigidBody.getAngularVelocity(paramclass_1421);
      localclass_1409.field_1628.scale(paramFloat);
      if ((!Float.isNaN(localclass_1409.field_1628.field_615)) && (!Float.isNaN(localclass_1409.field_1628.field_616)) && (!Float.isNaN(localclass_1409.field_1628.field_617))) {
        localRigidBody.setAngularVelocity(localclass_1409.field_1628);
      }
      localclass_1409.field_1633.set(localclass_1409.field_1628);
    }
  }
  
  public CollisionWorld.ClosestRayResultCallback testRayCollisionPoint(Vector3f paramVector3f1, Vector3f paramVector3f2, boolean paramBoolean1, SegmentController paramSegmentController1, SegmentController paramSegmentController2, boolean paramBoolean2, Segment paramSegment, boolean paramBoolean3)
  {
    (paramSegmentController1 = new CubeRayCastResult(paramVector3f1, paramVector3f2, paramSegmentController1, paramSegmentController2)).setRespectShields(paramBoolean2);
    paramSegmentController1.setLastHitSegment(paramSegment);
    paramSegmentController1.setIgnoereNotPhysical(paramBoolean3);
    assert (!paramSegmentController1.hasHit());
    ((ModifiedDynamicsWorld)this.dynamicsWorld).rayTest(paramVector3f1, paramVector3f2, paramSegmentController1);
    if ((paramSegmentController1.collisionObject != null) && ((paramVector3f1 = RigidBody.upcast(paramSegmentController1.collisionObject)) != null) && (paramBoolean1) && (!paramVector3f1.isStaticObject()) && (!paramVector3f1.isKinematicObject())) {
      return null;
    }
    return paramSegmentController1;
  }
  
  public CollisionWorld.ClosestRayResultCallback testRayCollisionPoint(Vector3f paramVector3f1, Vector3f paramVector3f2, CubeRayCastResult paramCubeRayCastResult, boolean paramBoolean)
  {
    if (SubsimplexRayCubesCovexCast.debug) {
      System.err.println("TESTING RAY CAST!!!");
    }
    ((ModifiedDynamicsWorld)this.dynamicsWorld).rayTest(paramVector3f1, paramVector3f2, paramCubeRayCastResult);
    if ((paramCubeRayCastResult.collisionObject != null) && ((paramVector3f1 = RigidBody.upcast(paramCubeRayCastResult.collisionObject)) != null) && (paramBoolean) && (!paramVector3f1.isStaticObject()) && (!paramVector3f1.isKinematicObject())) {
      return null;
    }
    return paramCubeRayCastResult;
  }
  
  public void cleanUp()
  {
    super.cleanUp();
    if (this.axisSweep3Ext != null) {
      this.axisSweep3Ext.cleanUp();
    }
    this.dynamicsWorld.setBroadphase(null);
    setOverlappingPairCache(null);
    setState(null);
  }
  
  public void softClean()
  {
    int i = getDynamicsWorld().getCollisionObjectArray().size();
    for (int j = 0; j < i; j++)
    {
      CollisionObject localCollisionObject = (CollisionObject)getDynamicsWorld().getCollisionObjectArray().get(j);
      System.err.println("WARNING: REMOVING EXCESS OBJECT FROM PHYSICS " + getState() + ": " + localCollisionObject);
      if ((localCollisionObject instanceof RigidBody)) {
        getDynamicsWorld().removeRigidBody((RigidBody)localCollisionObject);
      } else {
        getDynamicsWorld().removeCollisionObject(localCollisionObject);
      }
    }
    if (this.dbvtBroadphase != null) {
      this.dbvtBroadphase = new DbvtBroadphaseExt(new HashedOverlappingPairCacheExt());
    }
    if (this.axisSweep3Ext != null) {
      this.axisSweep3Ext.cleanUpReferences();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.PhysicsExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */