import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
import com.bulletphysics.collision.narrowphase.ManifoldPoint;
import com.bulletphysics.collision.shapes.CapsuleShape;
import com.bulletphysics.collision.shapes.ConvexShape;
import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.linearmath.AabbUtil2;
import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.vecmath.Matrix3f;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.physics.CubeRayCastResult;
import org.schema.game.common.data.physics.KinematicCharacterControllerExt;
import org.schema.game.common.data.physics.PairCachingGhostObjectAlignable;
import org.schema.game.common.data.physics.PhysicsExt;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.SegmentData;
import org.schema.game.common.data.world.Universe;
import org.schema.game.network.objects.NetworkPlayerCharacter;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.container.PhysicsDataContainer;
import org.schema.schine.network.objects.remote.RemoteBoolean;
import org.schema.schine.network.objects.remote.RemoteInteger;
import org.schema.schine.network.objects.remote.RemoteString;
import org.schema.schine.network.server.ServerMessage;
import org.schema.schine.network.server.ServerStateInterface;

public class class_750
  extends class_797
  implements class_80, class_7, class_365, class_739, class_752, class_1277
{
  private float jdField_field_136_of_type_Float = 0.2F;
  private float jdField_field_139_of_type_Float = 1.3F;
  private float jdField_field_182_of_type_Float = 0.1F;
  private float jdField_field_183_of_type_Float = 0.0F;
  private float field_184 = 4.0F;
  private final ArrayList jdField_field_136_of_type_JavaUtilArrayList = new ArrayList();
  private KinematicCharacterControllerExt jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsKinematicCharacterControllerExt;
  private NetworkPlayerCharacter jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayerCharacter;
  private String jdField_field_136_of_type_JavaLangString;
  private int jdField_field_136_of_type_Int;
  private final Set jdField_field_136_of_type_JavaUtilSet = new HashSet();
  private ConvexShape jdField_field_136_of_type_ComBulletphysicsCollisionShapesConvexShape;
  private class_748 jdField_field_136_of_type_Class_748;
  private Segment jdField_field_136_of_type_OrgSchemaGameCommonDataWorldSegment;
  private long jdField_field_136_of_type_Long;
  private long jdField_field_139_of_type_Long;
  private PairCachingGhostObjectAlignable jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectAlignable;
  private long jdField_field_182_of_type_Long;
  public boolean field_136;
  private long jdField_field_183_of_type_Long;
  
  public class_750(StateInterface paramStateInterface)
  {
    super(paramStateInterface);
    new Transform();
    this.jdField_field_139_of_type_Long = -1L;
  }
  
  public void cleanUpOnEntityDelete()
  {
    super.cleanUpOnEntityDelete();
    System.err.println("[DELETE] Cleaning up playerCharacter for " + this.jdField_field_136_of_type_Class_748 + " on " + getState());
    if ((getState() instanceof class_371))
    {
      class_371 localclass_371;
      (localclass_371 = (class_371)getState()).a20().a135(this);
      if ((localclass_371.a3() != null) && (localclass_371.a3().getId() == getId()))
      {
        localclass_371.a4().b1("\n    YOU DIED!\n");
        localclass_371.a29(null);
        localclass_371.a14().a18().a79().a60().a51().b();
        localclass_371.a14().a18().a79().a60().a51().c2(false);
        localclass_371.a14().a18().a79().c2(false);
        localclass_371.a14().a18().a76().c2(true);
        localclass_371.a32(null);
      }
    }
  }
  
  public boolean isClientOwnObject()
  {
    return (!isOnServer()) && (this.jdField_field_136_of_type_JavaUtilArrayList.contains(((class_371)getState()).a20()));
  }
  
  private void a119(float paramFloat, class_809 paramclass_809)
  {
    class_748 localclass_748;
    if ((localclass_748 = a147()) != null)
    {
      localclass_748.a119(paramFloat, paramclass_809);
      System.err.println("[SERVER] DAMAGING " + localclass_748 + ": " + paramFloat + " -> " + localclass_748.a14());
      return;
    }
    System.err.println("Exception: trying to Damage: Warning " + this + " has no owner state " + getState());
  }
  
  public final void a117(class_809 paramclass_809)
  {
    if ((isOnServer()) && (!isMarkedForDeleteVolatile()))
    {
      System.err.println("[SERVER] character has been deleted by " + paramclass_809);
      setMarkedForDeleteVolatile(true);
    }
  }
  
  public void fromTagStructure(class_69 paramclass_69)
  {
    if ((!jdField_field_139_of_type_Boolean) && (!"PlayerCharacter".equals(getClass().getSimpleName()))) {
      throw new AssertionError();
    }
    paramclass_69 = (class_69[])paramclass_69.a4();
    setId(((Integer)paramclass_69[0].a4()).intValue());
    this.field_184 = ((Float)paramclass_69[1].a4()).floatValue();
    this.jdField_field_183_of_type_Float = ((Float)paramclass_69[2].a4()).floatValue();
    super.fromTagStructure(paramclass_69[3]);
  }
  
  public final ArrayList a75()
  {
    return this.jdField_field_136_of_type_JavaUtilArrayList;
  }
  
  public final KinematicCharacterControllerExt a144()
  {
    return this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsKinematicCharacterControllerExt;
  }
  
  public final float a14()
  {
    return this.jdField_field_139_of_type_Float;
  }
  
  public final float b1()
  {
    return this.jdField_field_136_of_type_Float;
  }
  
  public final int a3()
  {
    return this.jdField_field_136_of_type_Int;
  }
  
  public final Transform a145()
  {
    Transform localTransform = new Transform(super.getWorldTransform());
    Vector3f localVector3f;
    (localVector3f = GlUtil.f(new Vector3f(), localTransform)).scale(0.485F);
    localTransform.origin.add(localVector3f);
    return localTransform;
  }
  
  public final class_796 a146(boolean paramBoolean)
  {
    try
    {
      boolean bool = paramBoolean;
      paramBoolean = this;
      Object localObject2 = null;
      if (this.jdField_field_136_of_type_JavaUtilArrayList.isEmpty())
      {
        System.err.println("Exception: NO nearest piece (no player attached)");
      }
      else
      {
        localObject2 = (class_748)paramBoolean.jdField_field_136_of_type_JavaUtilArrayList.get(0);
        Object localObject3 = new Vector3f(paramBoolean.a145().origin);
        Vector3f localVector3f = new Vector3f();
        (localObject2 = new Vector3f(((class_748)localObject2).a8())).scale(5.0F);
        localVector3f.add((Tuple3f)localObject3, (Tuple3f)localObject2);
        (localObject2 = new CubeRayCastResult((Vector3f)localObject3, localVector3f, Boolean.valueOf(false), null)).setRespectShields(false);
        ((CubeRayCastResult)localObject2).onlyCubeMeshes = true;
        ((CubeRayCastResult)localObject2).setLastHitSegment(paramBoolean.jdField_field_136_of_type_OrgSchemaGameCommonDataWorldSegment);
        ((CubeRayCastResult)localObject2).setIgnoereNotPhysical(bool);
        Object localObject1;
        if (((localObject1 = paramBoolean.getPhysics().testRayCollisionPoint((Vector3f)localObject3, localVector3f, (CubeRayCastResult)localObject2, false)).hasHit()) && (((CollisionWorld.ClosestRayResultCallback)localObject1).collisionObject != null))
        {
          if (((localObject1 instanceof CubeRayCastResult)) && (((CubeRayCastResult)localObject1).getSegment() != null))
          {
            localObject2 = (localObject1 = (CubeRayCastResult)localObject1).getSegment().a16().getSegmentController();
            localObject3 = ((CubeRayCastResult)localObject1).getSegment();
            paramBoolean.jdField_field_136_of_type_OrgSchemaGameCommonDataWorldSegment = ((Segment)localObject3);
            paramBoolean = new class_796((Segment)localObject3, ((CubeRayCastResult)localObject1).cubePos);
            System.err.println("HIT RESULT near: " + paramBoolean + ", on " + localObject2);
            return paramBoolean;
          }
        }
        else {
          paramBoolean.jdField_field_136_of_type_OrgSchemaGameCommonDataWorldSegment = null;
        }
      }
      return null;
    }
    catch (Exception localException)
    {
      localException;
    }
    return null;
  }
  
  private class_748 a147()
  {
    if (this.jdField_field_136_of_type_Class_748 == null) {
      synchronized (getState().getLocalAndRemoteObjectContainer().getLocalObjects())
      {
        Iterator localIterator = getState().getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator();
        while (localIterator.hasNext())
        {
          Object localObject2;
          if (((localObject2 = (Sendable)localIterator.next()) instanceof class_748))
          {
            localObject2 = (class_748)localObject2;
            if (this.jdField_field_136_of_type_Int == ((class_748)localObject2).a3())
            {
              this.jdField_field_136_of_type_Class_748 = ((class_748)localObject2);
              return this.jdField_field_136_of_type_Class_748;
            }
          }
        }
      }
    }
    return this.jdField_field_136_of_type_Class_748;
  }
  
  public final Set a114()
  {
    return this.jdField_field_136_of_type_JavaUtilSet;
  }
  
  public void getTransformedAABB(Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat, Vector3f paramVector3f3, Vector3f paramVector3f4)
  {
    paramVector3f3 = this.jdField_field_136_of_type_ComBulletphysicsCollisionShapesConvexShape.getMargin();
    this.jdField_field_136_of_type_ComBulletphysicsCollisionShapesConvexShape.setMargin(paramFloat);
    this.jdField_field_136_of_type_ComBulletphysicsCollisionShapesConvexShape.getAabb(getWorldTransform(), paramVector3f1, paramVector3f2);
    this.jdField_field_136_of_type_ComBulletphysicsCollisionShapesConvexShape.setMargin(paramVector3f3);
  }
  
  public String getUniqueIdentifier()
  {
    return this.jdField_field_136_of_type_JavaLangString;
  }
  
  public final void a90(class_941 paramclass_941, class_755 paramclass_755)
  {
    paramclass_755.field_1015.a114().addAll(this.jdField_field_136_of_type_JavaUtilSet);
    handleMovingInput(paramclass_941, paramclass_755);
  }
  
  public void getGravityAABB(Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    getPhysicsDataContainer().getObject().getWorldTransform(new Transform());
    AabbUtil2.transformAabb(new Vector3f(-1.0F, -1.0F, -1.0F), new Vector3f(1.0F, 1.0F, 1.0F), 16.0F, getWorldTransform(), paramVector3f1, paramVector3f2);
  }
  
  protected void handleGravity()
  {
    if (isHidden()) {
      return;
    }
    if (getGravity().a())
    {
      SegmentController localSegmentController;
      if (((localSegmentController = (SegmentController)getGravity().jdField_field_928_of_type_Class_797).getPhysicsDataContainer().isInitialized()) && (getGravity().jdField_field_928_of_type_Boolean))
      {
        System.err.println(getState() + "[PLAYERCHARACTER] " + this + " INITIALIZING GRAVITY FOR " + (SegmentController)getGravity().jdField_field_928_of_type_Class_797);
        this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsKinematicCharacterControllerExt.upAxisDirection[0].set(KinematicCharacterControllerExt.upAxisDirectionDefault[0]);
        this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsKinematicCharacterControllerExt.upAxisDirection[1].set(KinematicCharacterControllerExt.upAxisDirectionDefault[1]);
        this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsKinematicCharacterControllerExt.upAxisDirection[2].set(KinematicCharacterControllerExt.upAxisDirectionDefault[2]);
        GlUtil.e(this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsKinematicCharacterControllerExt.upAxisDirection[0], localSegmentController.getWorldTransform());
        GlUtil.f(this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsKinematicCharacterControllerExt.upAxisDirection[1], localSegmentController.getWorldTransform());
        GlUtil.c(this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsKinematicCharacterControllerExt.upAxisDirection[2], localSegmentController.getWorldTransform());
        GlUtil.c3(this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsKinematicCharacterControllerExt.upAxisDirection[0], getWorldTransform());
        GlUtil.d2(this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsKinematicCharacterControllerExt.upAxisDirection[1], getWorldTransform());
        GlUtil.a30(this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsKinematicCharacterControllerExt.upAxisDirection[2], getWorldTransform());
        this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectAlignable.localWorldTransform = new Transform();
        this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectAlignable.localWorldTransform.set(localSegmentController.getWorldTransformInverse());
        this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectAlignable.localWorldTransform.mul(getWorldTransform());
        this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectAlignable.setAttached(localSegmentController);
        getPhysicsDataContainer().updatePhysical();
        getRemoteTransformable().a14();
      }
      this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsKinematicCharacterControllerExt.setGravity(-getGravity().jdField_field_928_of_type_JavaxVecmathVector3f.field_616);
      return;
    }
    if (getGravity().jdField_field_928_of_type_Boolean)
    {
      this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectAlignable.localWorldTransform = null;
      this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectAlignable.setAttached(null);
      this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsKinematicCharacterControllerExt.setGravity(0.0F);
      this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsKinematicCharacterControllerExt.upAxisDirection[0].set(KinematicCharacterControllerExt.upAxisDirectionDefault[0]);
      this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsKinematicCharacterControllerExt.upAxisDirection[1].set(KinematicCharacterControllerExt.upAxisDirectionDefault[1]);
      this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsKinematicCharacterControllerExt.upAxisDirection[2].set(KinematicCharacterControllerExt.upAxisDirectionDefault[2]);
    }
  }
  
  public void handleHit(CollisionWorld.ClosestRayResultCallback paramClosestRayResultCallback, class_809 paramclass_809, float paramFloat)
  {
    if (isOnServer())
    {
      if (((paramClosestRayResultCallback = ((class_1041)getState()).a62().getSector(getSectorId())) != null) && (paramClosestRayResultCallback.b2()))
      {
        if ((paramclass_809 != null) && ((paramclass_809 instanceof class_365)))
        {
          paramClosestRayResultCallback = ((class_365)paramclass_809).a26();
          for (paramclass_809 = 0; paramclass_809 < paramClosestRayResultCallback.size(); paramclass_809++)
          {
            paramFloat = (class_748)paramClosestRayResultCallback.get(paramclass_809);
            if (System.currentTimeMillis() - paramFloat.jdField_field_136_of_type_Long > 5000L)
            {
              paramFloat.jdField_field_136_of_type_Long = System.currentTimeMillis();
              paramFloat.a129(new ServerMessage("This Sector is Protected!", 2, paramFloat.getId()));
            }
          }
        }
        return;
      }
      a119(paramFloat, paramclass_809);
      return;
    }
    ((class_371)getState()).a27().a91().a22(paramClosestRayResultCallback);
  }
  
  public void handleHitMissile(class_597 paramclass_597, Transform paramTransform)
  {
    if (isOnServer())
    {
      if (((paramTransform = ((class_1041)getState()).a62().getSector(getSectorId())) != null) && (paramTransform.b2())) {
        return;
      }
      System.err.println("PLAYER CHARACTER ON MISSILE HIT " + this + ": " + getState());
      if ((isOnServer()) && (!this.jdField_field_136_of_type_JavaUtilArrayList.isEmpty()))
      {
        System.err.println("[SERVER] DAMAGING PLAYER BY MISSLE " + this);
        a119(paramclass_597.a1(), paramclass_597);
      }
    }
  }
  
  public void handleMovingInput(class_941 paramclass_941, class_755 paramclass_755)
  {
    Vector3f localVector3f1 = paramclass_755.field_1015.c9();
    Vector3f localVector3f2;
    (localVector3f2 = new Vector3f(localVector3f1)).scale(-1.0F);
    Vector3f localVector3f3 = paramclass_755.field_1015.b18();
    Vector3f localVector3f4;
    (localVector3f4 = new Vector3f(localVector3f3)).scale(-1.0F);
    Vector3f localVector3f5 = paramclass_755.field_1015.a8();
    Vector3f localVector3f6;
    (localVector3f6 = new Vector3f(localVector3f5)).scale(-1.0F);
    Vector3f localVector3f7;
    (localVector3f7 = new Vector3f()).set(0.0F, 0.0F, 0.0F);
    if (paramclass_755.field_1015.a132(class_367.field_729))
    {
      if (getGravity().a())
      {
        this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsKinematicCharacterControllerExt.jump();
      }
      else if (System.currentTimeMillis() - this.jdField_field_182_of_type_Long > 1000L)
      {
        Object localObject;
        if ((localObject = a146(true)) != null)
        {
          localObject = ((class_796)localObject).a7().a15();
          getGravity().jdField_field_928_of_type_JavaxVecmathVector3f.set(0.0F, 0.0F, 0.0F);
          getGravity().jdField_field_928_of_type_Class_797 = ((class_797)localObject);
          getGravity().jdField_field_928_of_type_Boolean = true;
        }
        this.jdField_field_182_of_type_Long = System.currentTimeMillis();
      }
    }
    else {
      this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsKinematicCharacterControllerExt.breakJump(paramclass_941);
    }
    if (paramclass_755.field_1015.a132(class_367.field_713)) {
      localVector3f7.add(localVector3f5);
    }
    if (paramclass_755.field_1015.a132(class_367.field_714)) {
      localVector3f7.add(localVector3f6);
    }
    if (paramclass_755.field_1015.a132(class_367.field_711)) {
      localVector3f7.add(localVector3f4);
    }
    if (paramclass_755.field_1015.a132(class_367.field_712)) {
      localVector3f7.add(localVector3f3);
    }
    if (paramclass_755.field_1015.a132(class_367.field_715)) {
      localVector3f7.add(localVector3f1);
    }
    if (paramclass_755.field_1015.a132(class_367.field_716)) {
      localVector3f7.add(localVector3f2);
    }
    if (this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsKinematicCharacterControllerExt.getGravity() > 0.0F)
    {
      GlUtil.c(localVector3f5, getWorldTransform());
      GlUtil.e(localVector3f4, getWorldTransform());
      localVector3f1 = GlUtil.f(localVector3f1, getWorldTransform());
      localVector3f7 = GlUtil.a22(localVector3f7, localVector3f7, localVector3f1);
    }
    localVector3f7.scale(paramclass_941.a() * this.field_184);
    if (localVector3f7.length() > 0.0F) {
      this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsKinematicCharacterControllerExt.setWalkDirectionStacked(localVector3f7);
    }
  }
  
  public void initFromNetworkObject(NetworkObject paramNetworkObject)
  {
    super.initFromNetworkObject(paramNetworkObject);
    paramNetworkObject = (NetworkPlayerCharacter)paramNetworkObject;
    this.jdField_field_136_of_type_JavaLangString = ((String)paramNetworkObject.uniqueId.get());
    this.jdField_field_136_of_type_Int = ((Integer)this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayerCharacter.clientOwnerId.get()).intValue();
    if ((getState() instanceof ServerStateInterface)) {
      this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayerCharacter.spawnedOnServer.set(Boolean.valueOf(true));
    }
  }
  
  public void initPhysics()
  {
    getPhysics();
    this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectAlignable = new PairCachingGhostObjectAlignable(getPhysicsDataContainer());
    this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectAlignable.setWorldTransform(getInitialTransform());
    this.jdField_field_136_of_type_ComBulletphysicsCollisionShapesConvexShape = new CapsuleShape(this.jdField_field_136_of_type_Float, this.jdField_field_139_of_type_Float);
    this.jdField_field_136_of_type_ComBulletphysicsCollisionShapesConvexShape.setMargin(this.jdField_field_182_of_type_Float);
    this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectAlignable.setCollisionShape(this.jdField_field_136_of_type_ComBulletphysicsCollisionShapesConvexShape);
    this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectAlignable.setCollisionFlags(16);
    this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectAlignable.setUserPointer(Integer.valueOf(getId()));
    this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsKinematicCharacterControllerExt = new KinematicCharacterControllerExt(this, this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectAlignable, this.jdField_field_136_of_type_ComBulletphysicsCollisionShapesConvexShape, this.jdField_field_183_of_type_Float);
    getPhysicsDataContainer().setObject(this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectAlignable);
    getPhysicsDataContainer().setShape(this.jdField_field_136_of_type_ComBulletphysicsCollisionShapesConvexShape);
    getPhysicsDataContainer().updatePhysical();
    this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsKinematicCharacterControllerExt.setGravity(0.0F);
    setFlagPhysicsInit(true);
    if (!isOnServer()) {
      this.jdField_field_139_of_type_Long = (System.currentTimeMillis() + 3000L);
    }
  }
  
  public boolean isVolatile()
  {
    return false;
  }
  
  protected boolean isCheckSectorActive()
  {
    return (!isHidden()) && (System.currentTimeMillis() - this.jdField_field_183_of_type_Long > 6000L);
  }
  
  public void newNetworkObject()
  {
    this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayerCharacter = new NetworkPlayerCharacter(getState());
  }
  
  public final void a91(class_748 paramclass_748, Sendable paramSendable, class_48 paramclass_48)
  {
    this.jdField_field_183_of_type_Long = System.currentTimeMillis();
    setHidden(false);
    onPhysicsRemove();
    Transform localTransform = new Transform(getWorldTransform());
    if (paramSendable != null)
    {
      setSectorId(((class_797)paramSendable).getSectorId());
      if (!((class_797)paramSendable).getRemoteTransformable().jdField_field_116_of_type_JavaUtilArrayList.isEmpty())
      {
        localObject = (Transform)((class_797)paramSendable).getRemoteTransformable().jdField_field_116_of_type_JavaUtilArrayList.get(((class_797)paramSendable).getRemoteTransformable().jdField_field_116_of_type_JavaUtilArrayList.size() - 1);
        localTransform.set((Transform)localObject);
        System.err.println("[CHARACTER] on Attach " + this + ": WARPED TANSFORM USED " + localTransform.origin);
      }
      else
      {
        localTransform.set(((class_797)paramSendable).getWorldTransform());
      }
    }
    Object localObject = new Vector3f(paramclass_48.field_475 - 8, paramclass_48.field_476 - 8, paramclass_48.field_477 - 8);
    localTransform.basis.transform((Tuple3f)localObject);
    localTransform.origin.add((Tuple3f)localObject);
    System.err.println("[CHARACTER] on Attach " + this + ": " + getWorldTransform().origin + " -> " + localTransform.origin + " (from = " + paramSendable + ")");
    getInitialTransform().set(localTransform);
    initPhysics();
    if ((!isOnServer()) && (((class_371)getState()).a20() == paramclass_748))
    {
      getState().needsNotify(this);
      if (!((class_371)getState()).a14().a18().a79().c()) {
        ((class_371)getState()).a14().a18().a79().c2(true);
      }
      ((class_371)getState()).a14().a18().a79().a60().a52().c2(true);
    }
    if (paramclass_748.a7()) {
      class_969.a7("0022_ambience loop - space wind omnious light warbling tones (loop)");
    }
    this.flagGravityUpdate = true;
  }
  
  public boolean needsManifoldCollision()
  {
    return false;
  }
  
  public void onCollision(ManifoldPoint paramManifoldPoint, Sendable paramSendable)
  {
    if ((paramManifoldPoint.combinedRestitution > 0.0F) || (paramManifoldPoint.appliedImpulseLateral1 > 0.0F) || (paramManifoldPoint.appliedImpulseLateral2 > 0.0F)) {
      System.err.println("Restitution " + paramManifoldPoint.combinedRestitution + "; A " + paramManifoldPoint.appliedImpulseLateral1 + "; B " + paramManifoldPoint.appliedImpulseLateral2 + "; Com " + paramManifoldPoint.appliedImpulse);
    }
  }
  
  public final void a92(class_748 paramclass_748, boolean paramBoolean)
  {
    if (!isHidden())
    {
      setHidden(paramBoolean);
      onPhysicsRemove();
    }
    if ((!isOnServer()) && (((class_371)getState()).a20() == paramclass_748))
    {
      getState().needsNotify(this);
      ((class_371)getState()).a14().a18().a79().a60().a52().c2(false);
    }
    if (paramclass_748.a7())
    {
      class_969.a();
      class_76.e();
    }
  }
  
  public void onPhysicsAdd()
  {
    super.onPhysicsAdd();
    PhysicsExt localPhysicsExt = getPhysics();
    if (isClientOwnObject())
    {
      Object localObject = getPhysics();
      GlUtil.c(new Vector3f(), getWorldTransform());
      Transform localTransform = new Transform(getWorldTransform());
      this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsKinematicCharacterControllerExt.warpOutOfCollision(getState(), ((PhysicsExt)localObject).getDynamicsWorld(), getWorldTransform());
      localObject = this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectAlignable.getWorldTransform(new Transform());
      System.err.println("[PLAYERCHARACTER][DETACHED][" + getState() + "] WARPING OUT OF COLLISION: " + this + ": " + localTransform.origin + " -> " + ((Transform)localObject).origin);
      getRemoteTransformable().a12((Transform)localObject);
    }
    if (((isOnServer()) || (getSectorId() == ((class_371)getState()).a8())) && (!localPhysicsExt.containsAction(this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsKinematicCharacterControllerExt))) {
      localPhysicsExt.getDynamicsWorld().addAction(this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsKinematicCharacterControllerExt);
    }
  }
  
  public void onPhysicsRemove()
  {
    super.onPhysicsRemove();
    if ((!isOnServer()) || (((class_1041)getState()).a62().getSector(getSectorId()) != null)) {
      getPhysics().getDynamicsWorld().removeAction(this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsKinematicCharacterControllerExt);
    }
  }
  
  public final void a36(int paramInt)
  {
    this.jdField_field_136_of_type_Int = paramInt;
  }
  
  public final void a106(String paramString)
  {
    this.jdField_field_136_of_type_JavaLangString = paramString;
  }
  
  public String toNiceString()
  {
    if (this.jdField_field_136_of_type_JavaUtilArrayList.isEmpty()) {
      return "ghost <bug: player without host>";
    }
    return ((class_748)this.jdField_field_136_of_type_JavaUtilArrayList.get(0)).getName();
  }
  
  public String toString()
  {
    return "PlayerCharacter[(" + this.jdField_field_136_of_type_JavaLangString + ")(" + getId() + ")]";
  }
  
  public class_69 toTagStructure()
  {
    class_69 localclass_691 = new class_69(class_79.field_551, "id", Integer.valueOf(getId()));
    class_69 localclass_692 = new class_69(class_79.field_553, "speed", Float.valueOf(this.field_184));
    class_69 localclass_693 = new class_69(class_79.field_553, "stepHeight", Float.valueOf(this.jdField_field_183_of_type_Float));
    return new class_69(class_79.field_561, "PlayerCharacter", new class_69[] { localclass_691, localclass_692, localclass_693, super.toTagStructure(), new class_69(class_79.field_548, null, null) });
  }
  
  public final void a13()
  {
    this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsKinematicCharacterControllerExt.loadAttached(getPhysics().getDynamicsWorld());
    getPhysicsDataContainer().updatePhysical();
  }
  
  public void updateLocal(class_941 paramclass_941)
  {
    super.updateLocal(paramclass_941);
    this.jdField_field_136_of_type_Boolean = false;
    getPhysicsDataContainer().getObject().activate(true);
    if ((this.jdField_field_139_of_type_Long > 0L) && (System.currentTimeMillis() > this.jdField_field_139_of_type_Long))
    {
      if (getPhysics().containsObject(this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectAlignable))
      {
        paramclass_941 = this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectAlignable.getWorldTransform(new Transform());
        System.err.println("SCHEDULED WARP CHECK: OLD POSITION: " + paramclass_941.origin);
        this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsKinematicCharacterControllerExt.warpOutOfCollision(getState(), getPhysics().getDynamicsWorld(), paramclass_941);
        Transform localTransform = this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectAlignable.getWorldTransform(new Transform());
        if (!paramclass_941.origin.equals(localTransform.origin))
        {
          this.jdField_field_136_of_type_OrgSchemaGameCommonDataPhysicsPairCachingGhostObjectAlignable.setWorldTransform(localTransform);
          System.err.println(getState() + "[PLAYERCHARACTER] WARPING OUT OF COLLISION " + paramclass_941.origin + " -> " + localTransform.origin);
          if (isClientOwnObject()) {
            ((class_371)getState()).a4().d1("SPAWN-STUCK-PROTECTION\nWarping out of\npossible collisions");
          }
        }
        else
        {
          System.err.println("NO WARP NECESSARY");
        }
      }
      this.jdField_field_139_of_type_Long = -1L;
    }
    if (!isOnServer())
    {
      if (this.jdField_field_136_of_type_Int == ((class_371)getState()).getId())
      {
        getRemoteTransformable().jdField_field_116_of_type_Boolean = false;
        getRemoteTransformable().a2(true);
        return;
      }
      getRemoteTransformable().jdField_field_116_of_type_Boolean = true;
      getRemoteTransformable().a2(false);
    }
  }
  
  public void updateToFullNetworkObject()
  {
    super.updateToFullNetworkObject();
    this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayerCharacter.uniqueId.set(getUniqueIdentifier(), true);
    this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayerCharacter.clientOwnerId.set(Integer.valueOf(this.jdField_field_136_of_type_Int), true);
  }
  
  public final void a133(class_796 paramclass_796)
  {
    if ((isOnServer()) && (paramclass_796.a9() == 80) && (System.currentTimeMillis() - this.jdField_field_136_of_type_Long > 500L))
    {
      Iterator localIterator = this.jdField_field_136_of_type_JavaUtilArrayList.iterator();
      while (localIterator.hasNext()) {
        ((class_748)localIterator.next()).a119(50.0F, paramclass_796.a7().a15());
      }
      this.jdField_field_136_of_type_Long = System.currentTimeMillis();
    }
  }
  
  public void destroyPersistent()
  {
    if ((!jdField_field_139_of_type_Boolean) && (!isOnServer())) {
      throw new AssertionError();
    }
    String str = "ENTITY_PLAYERCHARACTER_" + a147().getName();
    new File(class_1041.field_144 + str + ".ent").delete();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_750
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */