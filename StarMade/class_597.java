import com.bulletphysics.collision.broadphase.BroadphasePair;
import com.bulletphysics.collision.broadphase.BroadphaseProxy;
import com.bulletphysics.collision.broadphase.DispatcherInfo;
import com.bulletphysics.collision.broadphase.HashedOverlappingPairCache;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.dispatch.CollisionWorld.ConvexResultCallback;
import com.bulletphysics.collision.shapes.SphereShape;
import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.util.ObjectArrayList;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import javax.vecmath.Matrix3f;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.element.Element;
import org.schema.game.common.data.element.ElementDocking;
import org.schema.game.common.data.physics.ClosestConvexResultCallbackExt;
import org.schema.game.common.data.physics.PairCachingGhostObjectUncollidable;
import org.schema.game.common.data.physics.PhysicsExt;
import org.schema.game.common.data.world.SectorNotFoundException;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.Universe;
import org.schema.game.network.objects.NetworkClientChannel;
import org.schema.game.network.objects.remote.RemoteMissileUpdate;
import org.schema.game.network.objects.remote.RemoteMissileUpdateBuffer;
import org.schema.schine.graphicsengine.shader.ErrorDialogException;
import org.schema.schine.network.ControllerInterface;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.container.PhysicsDataContainer;
import org.schema.schine.network.server.ServerState;

public abstract class class_597
  implements class_809, class_1421
{
  private float jdField_field_172_of_type_Float;
  protected final ArrayList field_173;
  protected final ArrayList field_172;
  private float jdField_field_174_of_type_Float;
  private Transform jdField_field_172_of_type_ComBulletphysicsLinearmathTransform;
  class_809 jdField_field_173_of_type_Class_809;
  private ArrayList jdField_field_174_of_type_JavaUtilArrayList;
  private float jdField_field_175_of_type_Float;
  float jdField_field_173_of_type_Float;
  Vector3f jdField_field_173_of_type_JavaxVecmathVector3f;
  private SphereShape jdField_field_173_of_type_ComBulletphysicsCollisionShapesSphereShape;
  private PairCachingGhostObjectUncollidable jdField_field_173_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable;
  private PairCachingGhostObjectUncollidable jdField_field_172_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable;
  boolean jdField_field_173_of_type_Boolean;
  private int jdField_field_172_of_type_Int;
  private SphereShape jdField_field_172_of_type_ComBulletphysicsCollisionShapesSphereShape;
  final Transform jdField_field_173_of_type_ComBulletphysicsLinearmathTransform;
  private final StateInterface jdField_field_173_of_type_OrgSchemaSchineNetworkStateInterface;
  short jdField_field_173_of_type_Short;
  int jdField_field_173_of_type_Int;
  final boolean jdField_field_172_of_type_Boolean;
  private PhysicsDataContainer jdField_field_173_of_type_OrgSchemaSchineNetworkObjectsContainerPhysicsDataContainer;
  private Transform jdField_field_174_of_type_ComBulletphysicsLinearmathTransform;
  private final Transform jdField_field_175_of_type_ComBulletphysicsLinearmathTransform;
  private Transform field_176;
  private Transform field_177;
  private Transform field_178;
  private Vector3f jdField_field_172_of_type_JavaxVecmathVector3f;
  private Vector3f jdField_field_174_of_type_JavaxVecmathVector3f;
  private int jdField_field_174_of_type_Int;
  private Vector3f jdField_field_175_of_type_JavaxVecmathVector3f;
  private final ArrayList jdField_field_175_of_type_JavaUtilArrayList;
  private int jdField_field_175_of_type_Int;
  private long jdField_field_173_of_type_Long;
  private long jdField_field_172_of_type_Long;
  
  public class_597(StateInterface paramStateInterface)
  {
    this.jdField_field_173_of_type_JavaUtilArrayList = new ArrayList();
    this.jdField_field_172_of_type_JavaUtilArrayList = new ArrayList();
    new ArrayList();
    this.jdField_field_174_of_type_Float = 1.0F;
    this.jdField_field_172_of_type_ComBulletphysicsLinearmathTransform = new Transform();
    this.jdField_field_174_of_type_JavaUtilArrayList = new ArrayList();
    this.jdField_field_173_of_type_Float = 100.0F;
    this.jdField_field_173_of_type_JavaxVecmathVector3f = new Vector3f();
    this.jdField_field_173_of_type_Boolean = true;
    this.jdField_field_172_of_type_Int = 1;
    this.jdField_field_173_of_type_Short = -1231;
    this.jdField_field_174_of_type_ComBulletphysicsLinearmathTransform = new Transform();
    this.jdField_field_175_of_type_ComBulletphysicsLinearmathTransform = new Transform();
    this.field_176 = new Transform();
    this.field_177 = new Transform();
    this.field_178 = new Transform();
    this.jdField_field_172_of_type_JavaxVecmathVector3f = new Vector3f();
    this.jdField_field_174_of_type_JavaxVecmathVector3f = new Vector3f();
    this.jdField_field_174_of_type_Int = -1;
    this.jdField_field_175_of_type_JavaxVecmathVector3f = new Vector3f();
    this.jdField_field_175_of_type_JavaUtilArrayList = new ArrayList();
    this.jdField_field_173_of_type_OrgSchemaSchineNetworkStateInterface = paramStateInterface;
    this.jdField_field_172_of_type_Boolean = (paramStateInterface instanceof ServerState);
    this.jdField_field_173_of_type_OrgSchemaSchineNetworkObjectsContainerPhysicsDataContainer = new PhysicsDataContainer();
    this.jdField_field_173_of_type_ComBulletphysicsLinearmathTransform = new Transform();
    this.jdField_field_175_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
    this.jdField_field_173_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
  }
  
  public final float a()
  {
    return this.jdField_field_174_of_type_Float;
  }
  
  public final int a1()
  {
    return this.jdField_field_172_of_type_Int;
  }
  
  public final Vector3f a2()
  {
    return this.jdField_field_173_of_type_JavaxVecmathVector3f;
  }
  
  public final float b()
  {
    return Math.min(5.0F, this.jdField_field_173_of_type_Float);
  }
  
  public final Transform a3()
  {
    return this.jdField_field_174_of_type_ComBulletphysicsLinearmathTransform;
  }
  
  public float getMass()
  {
    return 0.0F;
  }
  
  public PhysicsDataContainer getPhysicsDataContainer()
  {
    return this.jdField_field_173_of_type_OrgSchemaSchineNetworkObjectsContainerPhysicsDataContainer;
  }
  
  public StateInterface getState()
  {
    return this.jdField_field_173_of_type_OrgSchemaSchineNetworkStateInterface;
  }
  
  public void getTransformedAABB(Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat, Vector3f paramVector3f3, Vector3f paramVector3f4) {}
  
  public final class_809 a4()
  {
    return this.jdField_field_173_of_type_Class_809;
  }
  
  public final float c()
  {
    return this.jdField_field_172_of_type_Float;
  }
  
  public void initPhysics()
  {
    this.jdField_field_173_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable = new PairCachingGhostObjectUncollidable(getPhysicsDataContainer());
    this.jdField_field_173_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.setWorldTransform(this.jdField_field_174_of_type_ComBulletphysicsLinearmathTransform);
    this.jdField_field_172_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable = new PairCachingGhostObjectUncollidable(getPhysicsDataContainer());
    this.jdField_field_172_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.setWorldTransform(this.jdField_field_174_of_type_ComBulletphysicsLinearmathTransform);
    this.jdField_field_172_of_type_ComBulletphysicsCollisionShapesSphereShape = new SphereShape(this.jdField_field_174_of_type_Float);
    this.jdField_field_173_of_type_ComBulletphysicsCollisionShapesSphereShape = new SphereShape(0.5F);
    this.jdField_field_173_of_type_ComBulletphysicsCollisionShapesSphereShape.setMargin(0.1F);
    this.jdField_field_173_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.setCollisionShape(this.jdField_field_173_of_type_ComBulletphysicsCollisionShapesSphereShape);
    this.jdField_field_172_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.setCollisionShape(this.jdField_field_172_of_type_ComBulletphysicsCollisionShapesSphereShape);
    this.jdField_field_173_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.setUserPointer(null);
    getPhysicsDataContainer().setObject(this.jdField_field_173_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable);
    getPhysicsDataContainer().setShape(this.jdField_field_173_of_type_ComBulletphysicsCollisionShapesSphereShape);
    getPhysicsDataContainer().updatePhysical();
    this.jdField_field_173_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.setCollisionFlags(this.jdField_field_173_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.getCollisionFlags() | 0x4);
    if (this.jdField_field_172_of_type_Boolean) {
      f();
    }
  }
  
  public final boolean a5()
  {
    return this.jdField_field_173_of_type_Boolean;
  }
  
  private void f()
  {
    if (this.jdField_field_172_of_type_Boolean)
    {
      this.jdField_field_173_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.setCollisionFlags(4);
      this.jdField_field_172_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.setCollisionFlags(4);
      a8().addObject(getPhysicsDataContainer().getObject(), (short)-9, (short)8);
      a8().addObject(this.jdField_field_172_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable, (short)-9, (short)16);
      this.jdField_field_173_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.setCollisionFlags(4);
      this.jdField_field_172_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.setCollisionFlags(4);
    }
  }
  
  public final void a6(int paramInt, class_48 paramclass_48)
  {
    this.jdField_field_174_of_type_Int = -1;
    Object localObject2 = null;
    if (this.jdField_field_173_of_type_Int == paramInt)
    {
      this.jdField_field_175_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_field_173_of_type_ComBulletphysicsLinearmathTransform);
      return;
    }
    int i = 0;
    float f1 = 0.0F;
    Object localObject1;
    if (this.jdField_field_174_of_type_Int != paramInt)
    {
      i = 1;
      localObject1 = new class_48();
      class_48 localclass_48;
      if (this.jdField_field_172_of_type_Boolean)
      {
        if ((localObject2 = ((class_1041)getState()).a62().getSector(this.jdField_field_173_of_type_Int)) != null) {
          localclass_48 = ((class_670)localObject2).field_136;
        }
      }
      else
      {
        if ((localObject2 = (class_665)this.jdField_field_173_of_type_OrgSchemaSchineNetworkStateInterface.getLocalAndRemoteObjectContainer().getLocalObjects().get(this.jdField_field_173_of_type_Int)) == null)
        {
          System.err.println("Exception: Sector Not Found: " + this.jdField_field_173_of_type_Int + " for " + this + "; from sector: " + paramInt);
          this.jdField_field_175_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_field_173_of_type_ComBulletphysicsLinearmathTransform);
          this.jdField_field_175_of_type_ComBulletphysicsLinearmathTransform.origin.set(10000.0F, 10000.0F, 1000.0F);
          return;
        }
        localclass_48 = ((class_665)localObject2).a34();
      }
      localObject2 = class_789.a192(paramclass_48, new class_48());
      if (class_791.a19(paramclass_48))
      {
        f1 = (float)((System.currentTimeMillis() - this.jdField_field_173_of_type_OrgSchemaSchineNetworkStateInterface.getController().calculateStartTime()) % 1200000L) / 1200000.0F;
        if (!class_791.a19(paramclass_48)) {
          f1 = 0.0F;
        }
        ((class_48)localObject2).a5(16);
        ((class_48)localObject2).a(8, 8, 8);
        ((class_48)localObject2).c1(paramclass_48);
        ((class_48)localObject1).b1((class_48)localObject2);
      }
      else
      {
        f1 = 0.0F;
      }
      (localObject2 = new class_48()).a6(localclass_48, paramclass_48);
      this.jdField_field_172_of_type_JavaxVecmathVector3f.set(((class_48)localObject2).field_475 * Universe.getSectorSizeWithMargin(), ((class_48)localObject2).field_476 * Universe.getSectorSizeWithMargin(), ((class_48)localObject2).field_477 * Universe.getSectorSizeWithMargin());
      this.jdField_field_174_of_type_JavaxVecmathVector3f.set(((class_48)localObject1).field_475 * Universe.getSectorSizeWithMargin(), ((class_48)localObject1).field_476 * Universe.getSectorSizeWithMargin(), ((class_48)localObject1).field_477 * Universe.getSectorSizeWithMargin());
      this.field_177.setIdentity();
      if (((class_48)localObject1).a4() > 0.0F)
      {
        this.field_177.origin.add(this.jdField_field_174_of_type_JavaxVecmathVector3f);
        this.field_177.basis.rotX(6.283186F * f1);
        this.jdField_field_175_of_type_JavaxVecmathVector3f.sub(this.jdField_field_172_of_type_JavaxVecmathVector3f, this.jdField_field_174_of_type_JavaxVecmathVector3f);
        this.field_177.origin.add(this.jdField_field_175_of_type_JavaxVecmathVector3f);
        this.field_177.basis.transform(this.field_177.origin);
      }
      else
      {
        this.field_177.basis.setIdentity();
        this.field_177.origin.set(this.jdField_field_172_of_type_JavaxVecmathVector3f);
      }
    }
    if ((this.jdField_field_174_of_type_Int != paramInt) || (!this.field_176.equals(this.jdField_field_173_of_type_ComBulletphysicsLinearmathTransform)))
    {
      this.field_178.set(this.field_177);
      (localObject1 = new Transform()).setIdentity();
      float f2 = -6.283186F * f1;
      ((Transform)localObject1).basis.rotX(f2);
      class_29.a1((Transform)localObject1, this.jdField_field_173_of_type_ComBulletphysicsLinearmathTransform);
      class_29.a1(this.field_178, (Transform)localObject1);
      this.jdField_field_175_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_field_173_of_type_ComBulletphysicsLinearmathTransform);
      this.jdField_field_175_of_type_ComBulletphysicsLinearmathTransform.origin.set(this.field_178.origin);
      this.field_176.set(this.jdField_field_173_of_type_ComBulletphysicsLinearmathTransform);
    }
    if (i != 0) {
      this.jdField_field_174_of_type_Int = paramInt;
    }
  }
  
  public final void a7()
  {
    if (this.jdField_field_173_of_type_Int == ((class_371)getState()).a8())
    {
      Object localObject1;
      if (((localObject1 = (class_371)getState()).a27() != null) && (((class_371)localObject1).a27().a97() != null))
      {
        System.err.println("[CLIENT] Adding Trail for missile " + this.jdField_field_175_of_type_ComBulletphysicsLinearmathTransform.origin);
        Transform localTransform = this.jdField_field_175_of_type_ComBulletphysicsLinearmathTransform;
        synchronized ((localObject1 = ((class_371)localObject1).a27().a97()).field_98)
        {
          ((class_254)localObject1).field_98.add(new class_358(localTransform, true));
          return;
        }
      }
      System.err.println("[CLIENT] Cannot add Trail for missile (drawer not initialized)");
      return;
    }
    System.err.println("[CLIENT] NOT ADDING TRAIL FOR " + this + "; not in sector");
  }
  
  public final void b1()
  {
    Object localObject1;
    if (((localObject1 = (class_371)getState()).a27() != null) && (((class_371)localObject1).a27().a97() != null))
    {
      Transform localTransform = this.jdField_field_175_of_type_ComBulletphysicsLinearmathTransform;
      synchronized ((localObject1 = ((class_371)localObject1).a27().a97()).field_98)
      {
        ((class_254)localObject1).field_98.add(new class_358(localTransform, false));
      }
      System.err.println("[CLIENT] Removing Trail for missile");
    }
  }
  
  private class_1419 a8()
  {
    class_597 localclass_597 = this;
    long l1 = System.currentTimeMillis();
    class_670 localclass_670 = ((class_1041)localclass_597.getState()).a62().getSector(localclass_597.jdField_field_173_of_type_Int);
    long l2;
    if ((l2 = System.currentTimeMillis() - l1) > 5L) {
      System.err.println("[SERVER][STO] WARNING: Loading sector " + localclass_597.jdField_field_173_of_type_Int + " for " + localclass_597 + " took " + l2 + " ms");
    }
    if (localclass_670 == null) {
      throw new SectorNotFoundException(localclass_597.jdField_field_173_of_type_Int);
    }
    return (PhysicsExt)(this.jdField_field_172_of_type_Boolean ? localclass_670 : (class_371)localclass_597.getState()).a();
  }
  
  public final void c1()
  {
    if (this.jdField_field_172_of_type_Boolean)
    {
      a8().removeObject(getPhysicsDataContainer().getObject());
      a8().removeObject(this.jdField_field_172_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable);
    }
  }
  
  private void a9(ClosestConvexResultCallbackExt paramClosestConvexResultCallbackExt)
  {
    ObjectArrayList localObjectArrayList = this.jdField_field_172_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.getOverlappingPairCache().getOverlappingPairArray();
    Sendable localSendable = null;
    if (paramClosestConvexResultCallbackExt.userData == this.jdField_field_173_of_type_Class_809) {
      return;
    }
    int i;
    Object localObject1;
    Object localObject2;
    if (paramClosestConvexResultCallbackExt.userData != null)
    {
      i = 0;
      if ((this.jdField_field_173_of_type_Class_809 instanceof SegmentController))
      {
        if (((localObject1 = (SegmentController)this.jdField_field_173_of_type_Class_809).getDockingController().a4() != null) && (((SegmentController)localObject1).getDockingController().a4().field_1740.a7().a15() == (SegmentController)paramClosestConvexResultCallbackExt.userData)) {
          i = 1;
        }
        if (i == 0)
        {
          localObject2 = ((SegmentController)localObject1).getDockingController().a5().iterator();
          while (((Iterator)localObject2).hasNext()) {
            if (((ElementDocking)((Iterator)localObject2).next()).from.a7().a15() == (SegmentController)paramClosestConvexResultCallbackExt.userData) {
              i = 1;
            }
          }
        }
      }
      if (i == 0) {
        localSendable = (Sendable)paramClosestConvexResultCallbackExt.userData;
      }
    }
    else
    {
      System.err.println("Exception: warning: callback userdata is null (probably not hit with a segmentcontroller)");
    }
    if (localSendable != null) {
      for (i = 0; i < localObjectArrayList.size(); i++) {
        if (((localObject2 = (localObject1 = (BroadphasePair)localObjectArrayList.get(i)).pProxy0.clientObject != this.jdField_field_172_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable ? ((BroadphasePair)localObject1).pProxy0 : ((BroadphasePair)localObject1).pProxy1).clientObject != null) && ((((BroadphaseProxy)localObject2).clientObject instanceof CollisionObject)) && (this.jdField_field_172_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.checkCollideWith((CollisionObject)((BroadphaseProxy)localObject2).clientObject)))
        {
          paramClosestConvexResultCallbackExt = (CollisionObject)((BroadphaseProxy)localObject2).clientObject;
          System.err.println(this + " HIT BOADPHASE " + ((BroadphasePair)localObject1).pProxy0.clientObject + " and " + ((BroadphasePair)localObject1).pProxy1.clientObject);
          if ((paramClosestConvexResultCallbackExt.getUserPointer() != null) && ((paramClosestConvexResultCallbackExt.getUserPointer() instanceof Integer)))
          {
            paramClosestConvexResultCallbackExt = ((Integer)paramClosestConvexResultCallbackExt.getUserPointer()).intValue();
            paramClosestConvexResultCallbackExt = (Sendable)getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(paramClosestConvexResultCallbackExt);
            localObject1 = this.jdField_field_173_of_type_Class_809;
            if ((paramClosestConvexResultCallbackExt == localObject1) && (this.jdField_field_175_of_type_Float < 5.0F))
            {
              System.err.println("[MISSILE]: " + this + " Cannot hit owner: " + this.jdField_field_175_of_type_Float + "/5");
            }
            else if (paramClosestConvexResultCallbackExt != null)
            {
              System.err.println("[MISSILE]: " + this + " REGISTERED HIT: " + paramClosestConvexResultCallbackExt);
              this.jdField_field_174_of_type_JavaUtilArrayList.add(paramClosestConvexResultCallbackExt);
            }
          }
        }
      }
    }
    if (localSendable != null)
    {
      Iterator localIterator = this.jdField_field_174_of_type_JavaUtilArrayList.iterator();
      while (localIterator.hasNext()) {
        if (((localObject1 = (Sendable)localIterator.next()) instanceof C))
        {
          ((C)localObject1).handleHitMissile(this, this.jdField_field_173_of_type_ComBulletphysicsLinearmathTransform);
          this.jdField_field_173_of_type_Boolean = false;
        }
      }
    }
    this.jdField_field_174_of_type_JavaUtilArrayList.clear();
  }
  
  public final void d()
  {
    this.jdField_field_173_of_type_Boolean = false;
  }
  
  public final void a10(float paramFloat)
  {
    this.jdField_field_174_of_type_Float = paramFloat;
  }
  
  public final void a11(int paramInt)
  {
    this.jdField_field_172_of_type_Int = paramInt;
  }
  
  public final void a12(Vector3f paramVector3f)
  {
    this.jdField_field_173_of_type_JavaxVecmathVector3f = paramVector3f;
  }
  
  public final void b2(float paramFloat)
  {
    this.jdField_field_173_of_type_Float = paramFloat;
  }
  
  public final void a13(class_809 paramclass_809)
  {
    this.jdField_field_173_of_type_Class_809 = paramclass_809;
  }
  
  public final void c2(float paramFloat)
  {
    this.jdField_field_172_of_type_Float = paramFloat;
  }
  
  protected final void a14(Transform paramTransform)
  {
    if (this.jdField_field_172_of_type_Boolean) {
      this.jdField_field_173_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.setWorldTransform(paramTransform);
    }
    this.jdField_field_173_of_type_ComBulletphysicsLinearmathTransform.set(paramTransform);
  }
  
  public String toString()
  {
    return "Missile(" + this.jdField_field_173_of_type_Short + " s[" + this.jdField_field_173_of_type_Int + "] " + this.jdField_field_173_of_type_Class_809 + ")";
  }
  
  public abstract void b3(class_941 paramclass_941);
  
  public void a15(class_941 paramclass_941)
  {
    Object localObject1;
    if (this.jdField_field_173_of_type_Boolean) {
      try
      {
        try
        {
          localObject1 = this;
          if (this.jdField_field_173_of_type_Class_809 == null) {
            System.err.println("[MISSILE] Exception: OWNER IS NULL");
          }
          Object localObject2;
          try
          {
            localObject2 = ((class_797)((class_597)localObject1).jdField_field_173_of_type_Class_809).getPhysicsState();
          }
          catch (SectorNotFoundException localSectorNotFoundException)
          {
            localObject2 = null;
            localSectorNotFoundException.printStackTrace();
            System.err.println("[MISSILE] Exception CATCHED: sectors are not kept alive for missiles. terminate taht missile");
            ((class_597)localObject1).jdField_field_173_of_type_Boolean = false;
            break label339;
          }
          if (localObject2 == null)
          {
            System.err.println("[MISSILE] Exception: MISSILE NOT ABLE TO RETRIEVE PHYSICS FOR SECTOR " + ((class_597)localObject1).jdField_field_173_of_type_Int);
            if (((class_597)localObject1).jdField_field_172_of_type_Boolean) {
              ((class_597)localObject1).jdField_field_173_of_type_Boolean = false;
            }
          }
          else
          {
            localObject2 = (PhysicsExt)((class_1407)localObject2).a();
            ((class_597)localObject1).jdField_field_172_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
            ((class_597)localObject1).jdField_field_172_of_type_ComBulletphysicsLinearmathTransform.set(((class_597)localObject1).jdField_field_173_of_type_ComBulletphysicsLinearmathTransform);
            Object localObject3 = new Vector3f(((class_597)localObject1).jdField_field_173_of_type_JavaxVecmathVector3f);
            ((class_597)localObject1).jdField_field_172_of_type_ComBulletphysicsLinearmathTransform.basis.transform((Tuple3f)localObject3);
            ((class_597)localObject1).jdField_field_172_of_type_ComBulletphysicsLinearmathTransform.origin.add((Tuple3f)localObject3);
            if (((class_597)localObject1).jdField_field_173_of_type_ComBulletphysicsLinearmathTransform.basis.determinant() != 0.0F)
            {
              if ((!jdField_field_174_of_type_Boolean) && (localObject2 == null)) {
                throw new AssertionError();
              }
              if ((!jdField_field_174_of_type_Boolean) && (((class_597)localObject1).jdField_field_173_of_type_ComBulletphysicsLinearmathTransform == null)) {
                throw new AssertionError();
              }
              if ((!jdField_field_174_of_type_Boolean) && (((class_597)localObject1).jdField_field_173_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable == null)) {
                throw new AssertionError();
              }
              (localObject3 = new ClosestConvexResultCallbackExt(((class_597)localObject1).jdField_field_173_of_type_ComBulletphysicsLinearmathTransform.origin, ((class_597)localObject1).jdField_field_172_of_type_ComBulletphysicsLinearmathTransform.origin)).checkHasHitOnly = true;
              ((class_597)localObject1).jdField_field_173_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.convexSweepTest(((class_597)localObject1).jdField_field_173_of_type_ComBulletphysicsCollisionShapesSphereShape, ((class_597)localObject1).jdField_field_173_of_type_ComBulletphysicsLinearmathTransform, ((class_597)localObject1).jdField_field_172_of_type_ComBulletphysicsLinearmathTransform, (CollisionWorld.ConvexResultCallback)localObject3, ((PhysicsExt)localObject2).getDynamicsWorld().getDispatchInfo().allowedCcdPenetration);
              if (((ClosestConvexResultCallbackExt)localObject3).hasHit()) {
                try
                {
                  ((class_597)localObject1).a9((ClosestConvexResultCallbackExt)localObject3);
                }
                catch (Exception localException)
                {
                  localException;
                }
              }
            }
            else
            {
              throw new IllegalStateException("[MISSILE] WORLD TRANSFORM IS STRANGE OR PHYSICS NOT INITIALIZED");
            }
          }
          label339:
          this.jdField_field_172_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectUncollidable.setWorldTransform(this.jdField_field_173_of_type_ComBulletphysicsLinearmathTransform);
        }
        catch (IOException localIOException)
        {
          (localObject1 = localIOException).printStackTrace();
          throw new ErrorDialogException(((IOException)localObject1).getMessage());
        }
        catch (InterruptedException localInterruptedException)
        {
          (localObject1 = localInterruptedException).printStackTrace();
          throw new ErrorDialogException(((InterruptedException)localObject1).getMessage());
        }
      }
      catch (IllegalStateException localIllegalStateException)
      {
        localIllegalStateException.printStackTrace();
        b4(this.jdField_field_173_of_type_ComBulletphysicsLinearmathTransform.origin);
      }
    }
    float f = 0.0F;
    this.jdField_field_175_of_type_Float += paramclass_941.a();
    if ((this.jdField_field_173_of_type_Boolean) && (this.jdField_field_175_of_type_Float > this.jdField_field_173_of_type_Float))
    {
      this.jdField_field_173_of_type_Boolean = false;
      System.err.println("[SERVER] MISSILE DIED FROM LIFETIME");
    }
    if (!this.jdField_field_173_of_type_Boolean)
    {
      System.err.println("[SERVER] Deleting missile " + this);
      this.jdField_field_172_of_type_JavaUtilArrayList.add(new class_607(this.jdField_field_173_of_type_Short));
      return;
    }
    if (this.jdField_field_173_of_type_Int != this.jdField_field_175_of_type_Int)
    {
      (localObject1 = new class_617(this.jdField_field_173_of_type_Short)).jdField_field_179_of_type_JavaxVecmathVector3f.set(this.jdField_field_173_of_type_ComBulletphysicsLinearmathTransform.origin);
      ((class_617)localObject1).jdField_field_180_of_type_JavaxVecmathVector3f.set(this.jdField_field_173_of_type_JavaxVecmathVector3f);
      ((class_617)localObject1).jdField_field_179_of_type_Int = this.jdField_field_173_of_type_Int;
      this.jdField_field_173_of_type_JavaUtilArrayList.add(localObject1);
      this.jdField_field_175_of_type_Int = this.jdField_field_173_of_type_Int;
      return;
    }
    if (System.currentTimeMillis() - this.jdField_field_173_of_type_Long > 500L)
    {
      (localObject1 = new class_603(this.jdField_field_173_of_type_Short)).jdField_field_179_of_type_JavaxVecmathVector3f.set(this.jdField_field_173_of_type_ComBulletphysicsLinearmathTransform.origin);
      this.jdField_field_173_of_type_JavaUtilArrayList.add(localObject1);
      this.jdField_field_173_of_type_Long = (System.currentTimeMillis() + Universe.getRandom().nextInt(30));
    }
    if (((this instanceof class_609)) && (System.currentTimeMillis() - this.jdField_field_172_of_type_Long > 500L))
    {
      (localObject1 = new class_605(this.jdField_field_173_of_type_Short)).jdField_field_179_of_type_JavaxVecmathVector3f.set(this.jdField_field_173_of_type_JavaxVecmathVector3f);
      this.jdField_field_173_of_type_JavaUtilArrayList.add(localObject1);
      this.jdField_field_172_of_type_Long = (System.currentTimeMillis() + Universe.getRandom().nextInt(30));
    }
  }
  
  private void b4(Vector3f paramVector3f)
  {
    if ((!jdField_field_174_of_type_Boolean) && (!(this.jdField_field_173_of_type_OrgSchemaSchineNetworkStateInterface instanceof class_1041))) {
      throw new AssertionError();
    }
    Vector3f localVector3f1 = new Vector3f(paramVector3f);
    class_670 localclass_6701;
    if ((localclass_6701 = ((class_1041)this.jdField_field_173_of_type_OrgSchemaSchineNetworkStateInterface).a62().getSector(this.jdField_field_173_of_type_Int)) != null)
    {
      class_48 localclass_481 = localclass_6701.field_136;
      int i = -1;
      Vector3f localVector3f2 = new Vector3f(localVector3f1);
      class_48 localclass_482 = new class_48();
      boolean bool = class_791.a19(localclass_6701.field_136);
      for (int j = 0; j < Element.DIRECTIONSi.length; j++)
      {
        Object localObject;
        (localObject = new class_48(Element.DIRECTIONSi[j])).a1(localclass_481);
        Transform localTransform;
        (localTransform = new Transform()).setIdentity();
        if (bool)
        {
          Universe.calcSecPos(localclass_481, (class_48)localObject, this.jdField_field_173_of_type_OrgSchemaSchineNetworkStateInterface.getController().calculateStartTime(), System.currentTimeMillis(), localTransform);
        }
        else
        {
          localTransform.origin.set(Element.DIRECTIONSi[j].field_475, Element.DIRECTIONSi[j].field_476, Element.DIRECTIONSi[j].field_477);
          localTransform.origin.scale(Universe.getSectorSizeWithMargin());
        }
        (localObject = new Vector3f()).sub(localVector3f1, localTransform.origin);
        if (((Vector3f)localObject).lengthSquared() < localVector3f2.lengthSquared())
        {
          localVector3f2.set((Tuple3f)localObject);
          i = j;
        }
      }
      if (i >= 0)
      {
        localclass_482.b1(localclass_481);
        localclass_482.a1(Element.DIRECTIONSi[i]);
      }
      else
      {
        return;
      }
      try
      {
        if (((class_1041)this.jdField_field_173_of_type_OrgSchemaSchineNetworkStateInterface).a62().isSectorLoaded(localclass_482))
        {
          class_670 localclass_6702 = ((class_1041)this.jdField_field_173_of_type_OrgSchemaSchineNetworkStateInterface).a62().getSector(localclass_482);
          class_8.a10(this.jdField_field_173_of_type_OrgSchemaSchineNetworkStateInterface, localclass_6701, localclass_6702, localVector3f1, paramVector3f);
          c1();
          this.jdField_field_173_of_type_Int = localclass_6702.a3();
          f();
          return;
        }
      }
      catch (Exception localException)
      {
        localException;
      }
    }
    else
    {
      System.err.println("[SERVER][PROJECTILE] Stopping projectile: out of loaded sector range");
    }
    this.jdField_field_173_of_type_Boolean = false;
  }
  
  public final ArrayList a16()
  {
    return this.jdField_field_175_of_type_JavaUtilArrayList;
  }
  
  public final void a17(class_45 paramclass_45)
  {
    for (int i = 0; i < this.jdField_field_173_of_type_JavaUtilArrayList.size(); i++)
    {
      class_611 localclass_611 = (class_611)this.jdField_field_173_of_type_JavaUtilArrayList.get(i);
      paramclass_45.a().missileUpdateBuffer.add(new RemoteMissileUpdate(localclass_611, this.jdField_field_172_of_type_Boolean));
    }
  }
  
  public final void b5(class_45 paramclass_45)
  {
    for (int i = 0; i < this.jdField_field_172_of_type_JavaUtilArrayList.size(); i++)
    {
      class_611 localclass_611 = (class_611)this.jdField_field_172_of_type_JavaUtilArrayList.get(i);
      paramclass_45.a().missileUpdateBuffer.add(new RemoteMissileUpdate(localclass_611, this.jdField_field_172_of_type_Boolean));
      System.err.println("[SERVER] sent missile update BB " + localclass_611);
    }
  }
  
  public final short a18()
  {
    return this.jdField_field_173_of_type_Short;
  }
  
  public final void a19(short paramShort)
  {
    this.jdField_field_173_of_type_Short = paramShort;
  }
  
  public final int b6()
  {
    return this.jdField_field_173_of_type_Int;
  }
  
  public final void b7(int paramInt)
  {
    this.jdField_field_173_of_type_Int = paramInt;
    this.jdField_field_175_of_type_Int = paramInt;
  }
  
  public final Transform b8()
  {
    return this.jdField_field_173_of_type_ComBulletphysicsLinearmathTransform;
  }
  
  public void a20(class_615 paramclass_615)
  {
    this.jdField_field_173_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
    this.jdField_field_173_of_type_Short = paramclass_615.jdField_field_179_of_type_Short;
    this.jdField_field_173_of_type_Int = paramclass_615.jdField_field_180_of_type_Int;
    if ((!jdField_field_174_of_type_Boolean) && (paramclass_615.jdField_field_179_of_type_Byte != a21())) {
      throw new AssertionError(paramclass_615.jdField_field_179_of_type_Byte + " --- " + a21());
    }
    this.jdField_field_173_of_type_JavaxVecmathVector3f.set(paramclass_615.jdField_field_180_of_type_JavaxVecmathVector3f);
    this.jdField_field_173_of_type_ComBulletphysicsLinearmathTransform.origin.set(paramclass_615.jdField_field_179_of_type_JavaxVecmathVector3f);
  }
  
  public abstract byte a21();
  
  public final boolean b9()
  {
    return !this.jdField_field_173_of_type_JavaUtilArrayList.isEmpty();
  }
  
  public final boolean c3()
  {
    return !this.jdField_field_172_of_type_JavaUtilArrayList.isEmpty();
  }
  
  public final void e()
  {
    this.jdField_field_173_of_type_JavaUtilArrayList.clear();
    this.jdField_field_172_of_type_JavaUtilArrayList.clear();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_597
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */