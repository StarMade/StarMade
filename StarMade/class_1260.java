import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.io.PrintStream;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ManagerModuleCollection;
import org.schema.game.common.controller.elements.PowerAddOn;
import org.schema.game.common.controller.elements.PowerManagerInterface;
import org.schema.game.common.controller.elements.ShipManagerContainer;
import org.schema.game.common.controller.elements.missile.MissileController;
import org.schema.game.common.controller.elements.missile.MissileUnit;
import org.schema.game.common.controller.elements.missile.dumb.DumbMissileCollectionManager;
import org.schema.game.common.controller.elements.missile.dumb.DumbMissileElementManager;
import org.schema.game.common.controller.elements.missile.heatseeking.HeatMissileCollectionManager;
import org.schema.game.common.controller.elements.missile.heatseeking.HeatMissileElementManager;
import org.schema.game.common.controller.elements.thrust.ThrusterElementManager;
import org.schema.game.common.controller.elements.weapon.WeaponCollectionManager;
import org.schema.game.common.controller.elements.weapon.WeaponElementManager;
import org.schema.game.common.controller.elements.weapon.WeaponUnit;
import org.schema.game.common.data.element.ControlElementMap;
import org.schema.game.common.data.element.ElementDocking;
import org.schema.game.common.data.physics.PhysicsExt;
import org.schema.game.common.data.world.Segment;
import org.schema.game.network.objects.NetworkShip;
import org.schema.game.server.controller.GameServerController;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.graphicsengine.shader.ErrorDialogException;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.container.PhysicsDataContainer;
import org.schema.schine.network.objects.remote.RemoteVector3f;

public class class_1260
  extends class_1262
  implements class_770, class_1382
{
  private final Transform jdField_field_223_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  private class_298 jdField_field_223_of_type_Class_298;
  private float jdField_field_223_of_type_Float;
  private long jdField_field_223_of_type_Long;
  private Vector3f jdField_field_223_of_type_JavaxVecmathVector3f = new Vector3f();
  public final HashSet field_223;
  private class_48 jdField_field_223_of_type_Class_48 = new class_48();
  private Vector3f jdField_field_224_of_type_JavaxVecmathVector3f = new Vector3f();
  private float jdField_field_224_of_type_Float = 10.0F;
  private Vector3f jdField_field_249_of_type_JavaxVecmathVector3f = new Vector3f();
  private long jdField_field_224_of_type_Long;
  private long jdField_field_249_of_type_Long;
  private float jdField_field_249_of_type_Float;
  
  public class_1260(String paramString, class_747 paramclass_747)
  {
    super(paramString, paramclass_747);
    this.jdField_field_223_of_type_JavaUtilHashSet = new HashSet();
    this.jdField_field_223_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
  }
  
  public final HashSet a25()
  {
    return this.jdField_field_223_of_type_JavaUtilHashSet;
  }
  
  public final class_747 a26()
  {
    return (class_747)super.a();
  }
  
  public Transform getWorldTransform()
  {
    return this.jdField_field_223_of_type_ComBulletphysicsLinearmathTransform;
  }
  
  public final void a27(class_941 paramclass_941, Vector3f paramVector3f, boolean paramBoolean)
  {
    float f1;
    if (((f1 = ((class_747)super.a()).a112().getThrusterElementManager().getActualThrust()) == 0.0F) && (((class_747)super.a()).getTotalElements() == 0)) {
      f1 = 1.0F;
    }
    if ((f1 == 0.0F) || (((class_747)super.a()).getDockingController().b3())) {
      return;
    }
    this.jdField_field_223_of_type_Float = (((class_747)super.a()).a112().getThrusterElementManager().getMaxVelocity() - 2.0F);
    float f2 = paramVector3f.length();
    Vector3f localVector3f;
    (localVector3f = new Vector3f(paramVector3f)).normalize();
    RigidBody localRigidBody = (RigidBody)((class_747)super.a()).getPhysicsDataContainer().getObject();
    if (this.jdField_field_223_of_type_Long + 30L < System.currentTimeMillis())
    {
      if (localVector3f.length() > 0.0F)
      {
        localRigidBody.activate();
        if (f2 > 350.0F) {
          localVector3f.scale(f1 * 0.4F);
        } else if (f2 > 100.0F) {
          localVector3f.scale(f1 * 0.1F);
        } else {
          localVector3f.scale(f1 * 0.01F);
        }
        localRigidBody.applyCentralImpulse(localVector3f);
        localRigidBody.getLinearVelocity(this.jdField_field_223_of_type_JavaxVecmathVector3f);
        if (this.jdField_field_223_of_type_JavaxVecmathVector3f.length() > this.jdField_field_223_of_type_Float)
        {
          this.jdField_field_223_of_type_JavaxVecmathVector3f.normalize();
          this.jdField_field_223_of_type_JavaxVecmathVector3f.scale(this.jdField_field_223_of_type_Float);
          localRigidBody.setLinearVelocity(this.jdField_field_223_of_type_JavaxVecmathVector3f);
        }
      }
      if (paramBoolean) {
        a29(paramclass_941, paramVector3f);
      }
      localRigidBody.getLinearVelocity(((class_747)super.a()).a112().getThrusterElementManager().getVelocity());
      this.jdField_field_223_of_type_Long = System.currentTimeMillis();
    }
  }
  
  public final void a28(SegmentController paramSegmentController)
  {
    this.jdField_field_223_of_type_JavaUtilHashSet.add(paramSegmentController);
  }
  
  public final void a29(class_941 paramclass_941, Vector3f paramVector3f)
  {
    if (paramVector3f.length() == 0.0F) {
      return;
    }
    float f = 3.0F / Math.max(1.0F, (((class_747)super.a()).getMass() - 8.0F) / 8.7F);
    f = Math.max(0.1F, f);
    Object localObject1;
    Object localObject3;
    if (((class_747)super.a()).getDockingController().b3())
    {
      if (this.jdField_field_223_of_type_Class_298 == null) {
        this.jdField_field_223_of_type_Class_298 = new class_298(this, (class_747)super.a());
      }
      this.jdField_field_223_of_type_ComBulletphysicsLinearmathTransform.set(((class_747)super.a()).getWorldTransform());
      if (((class_747)super.a()).isOnServer())
      {
        localObject1 = GameServerController.jdField_field_37_of_type_Class_1381;
        localObject2 = GameServerController.jdField_field_37_of_type_OrgLwjglUtilVectorMatrix4f;
      }
      else
      {
        localObject1 = class_52.jdField_field_37_of_type_Class_1381;
        localObject2 = class_969.field_1260;
      }
      localObject3 = new Transform(((class_747)super.a()).getWorldTransform());
      Vector3f localVector3f1 = GlUtil.c(new Vector3f(), (Transform)localObject3);
      GlUtil.a30(GlUtil.e(new Vector3f(), (Transform)localObject3), (Transform)localObject3);
      GlUtil.a30(localVector3f1, (Transform)localObject3);
      ((Transform)localObject3).origin.set(0.0F, 0.0F, 0.0F);
      localVector3f1 = new Vector3f();
      (localVector3f2 = new Vector3f(paramVector3f)).normalize();
      localVector3f2.field_615 = (-localVector3f2.field_615);
      localVector3f2.field_617 = (-localVector3f2.field_617);
      localVector3f2.field_616 = (-localVector3f2.field_616);
      localVector3f1.set(localVector3f2);
      Vector3f localVector3f2 = GlUtil.c(new Vector3f(), (Transform)localObject3);
      Vector3f localVector3f3 = new Vector3f();
      Vector3f localVector3f4 = new Vector3f();
      Object localObject5 = localObject3;
      Object localObject4 = localObject2;
      Vector3f localVector3f7 = localVector3f2;
      Vector3f localVector3f6 = localVector3f3;
      Vector3f localVector3f5 = localVector3f4;
      localObject3 = localVector3f1;
      (localObject1 = localObject1).field_1584.rewind();
      ((class_1381)localObject1).field_1585.rewind();
      ((class_1381)localObject1).field_1583.rewind();
      localObject5 = ((Transform)localObject5).getMatrix(new javax.vecmath.Matrix4f());
      localObject5 = new float[] { ((javax.vecmath.Matrix4f)localObject5).m00, ((javax.vecmath.Matrix4f)localObject5).m01, ((javax.vecmath.Matrix4f)localObject5).m02, ((javax.vecmath.Matrix4f)localObject5).m03, ((javax.vecmath.Matrix4f)localObject5).m10, ((javax.vecmath.Matrix4f)localObject5).m11, ((javax.vecmath.Matrix4f)localObject5).m12, ((javax.vecmath.Matrix4f)localObject5).m13, ((javax.vecmath.Matrix4f)localObject5).m20, ((javax.vecmath.Matrix4f)localObject5).m21, ((javax.vecmath.Matrix4f)localObject5).m22, ((javax.vecmath.Matrix4f)localObject5).m23, ((javax.vecmath.Matrix4f)localObject5).m30, ((javax.vecmath.Matrix4f)localObject5).m31, ((javax.vecmath.Matrix4f)localObject5).m32, ((javax.vecmath.Matrix4f)localObject5).m33 };
      localObject4.store(((class_1381)localObject1).field_1585);
      ((class_1381)localObject1).field_1584.put((float[])localObject5);
      ((class_1381)localObject1).field_1583.put(0);
      ((class_1381)localObject1).field_1583.put(0);
      ((class_1381)localObject1).field_1583.put(795);
      ((class_1381)localObject1).field_1583.put(595);
      ((class_1381)localObject1).field_1583.rewind();
      ((class_1381)localObject1).field_1584.rewind();
      ((class_1381)localObject1).field_1585.rewind();
      ((class_1381)localObject1).a((Vector3f)localObject3, localVector3f5, localVector3f6, localVector3f7, true);
      localObject1 = new Vector3f(localVector3f4);
      localVector3f4.normalize();
      if ((((class_747)super.a()).isOnServer()) && (!jdField_field_223_of_type_Boolean) && (Float.isNaN(localVector3f4.field_615))) {
        throw new AssertionError(paramVector3f + "; " + localVector3f1 + "; " + localVector3f3 + "; " + localVector3f2 + "; \n" + localObject2 + "; \n\n" + ((class_747)super.a()).getWorldTransform().getMatrix(new javax.vecmath.Matrix4f()));
      }
      this.jdField_field_223_of_type_Class_298.jdField_field_123_of_type_Boolean = false;
      this.jdField_field_223_of_type_Class_298.jdField_field_123_of_type_ComBulletphysicsLinearmathTransform.set(((class_747)super.a()).getWorldTransform());
      this.jdField_field_223_of_type_Class_298.a4(((class_747)super.a()).getDockingController().a4().field_1740.a7().a15());
      this.jdField_field_223_of_type_Class_298.a5(org.schema.game.common.data.element.Element.orientationBackMapping[((class_747)super.a()).getDockingController().a4().field_1740.b1()]);
      paramclass_941 = 0.5F * paramclass_941.a();
      paramVector3f = ((Vector3f)localObject1).field_615 > 400.0F ? -paramclass_941 : paramclass_941;
      this.jdField_field_223_of_type_Class_298.a1(paramVector3f, ((Vector3f)localObject1).field_616 > 300.0F ? -paramclass_941 : paramclass_941, 0.0F, 1.0F, 1.0F, 0.0F);
      GlUtil.c(new Vector3f(), this.jdField_field_223_of_type_ComBulletphysicsLinearmathTransform);
      ((class_747)super.a()).getPhysics().orientate((class_747)super.a(), GlUtil.c(new Vector3f(), getWorldTransform()), GlUtil.f(new Vector3f(), getWorldTransform()), GlUtil.e(new Vector3f(), getWorldTransform()), f);
      return;
    }
    (localObject1 = new Vector3f(paramVector3f)).normalize();
    Object localObject2 = GlUtil.f(new Vector3f(), ((class_747)super.a()).getWorldTransform());
    (localObject3 = new Vector3f()).cross((Vector3f)localObject2, (Vector3f)localObject1);
    ((Vector3f)localObject3).normalize();
    ((Vector3f)localObject2).cross((Vector3f)localObject1, (Vector3f)localObject3);
    ((Vector3f)localObject2).normalize();
    ((class_747)super.a()).getPhysics().orientate((class_747)super.a(), (Vector3f)localObject1, (Vector3f)localObject2, (Vector3f)localObject3, f);
  }
  
  public final void a30(Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    WeaponElementManager localWeaponElementManager;
    if ((localWeaponElementManager = (WeaponElementManager)((class_747)super.a()).a112().getWeapon().getElementManager()).getCollectionManagers().isEmpty()) {
      return;
    }
    for (int i = 0; i < localWeaponElementManager.getCollectionManagers().size(); i++)
    {
      Object localObject1 = (WeaponCollectionManager)localWeaponElementManager.getCollectionManagers().get(i);
      if (localWeaponElementManager.getControlElementMap().isControlling(class_747.field_136, ((WeaponCollectionManager)localObject1).getControllerPos(), (short)6))
      {
        localObject1 = ((WeaponCollectionManager)localObject1).getCollection().iterator();
        while (((Iterator)localObject1).hasNext())
        {
          WeaponUnit localWeaponUnit;
          if ((localWeaponUnit = (WeaponUnit)((Iterator)localObject1).next()).canShoot())
          {
            float f = localWeaponUnit.getPowerConsumed();
            Object localObject2 = this;
            if (((((class_747)((class_1262)localObject2).a()).getDockingController().a4() != null) && (((localObject2 = ((class_747)((class_1262)localObject2).a()).getDockingController().a4().field_1740.a7().a15()) instanceof class_798)) && ((((class_798)localObject2).a() instanceof PowerManagerInterface)) ? ((PowerManagerInterface)((class_798)localObject2).a()).getPowerAddOn().consumePowerInstantly(f) : ((class_747)super.a()).a112().getPowerAddOn().consumePowerInstantly(f) ? 1 : 0))
            {
              localObject2 = localWeaponUnit.getOutput();
              localObject2 = new Vector3f(((class_48)localObject2).field_475 - 8, ((class_48)localObject2).field_476 - 8, ((class_48)localObject2).field_477 - 8);
              if (((class_747)super.a()).isOnServer()) {
                ((class_747)super.a()).getWorldTransform().transform((Vector3f)localObject2);
              } else {
                ((class_747)super.a()).getWorldTransformClient().transform((Vector3f)localObject2);
              }
              new class_48(class_747.field_136).c1(class_747.field_136);
              Object localObject3 = class_49.a(paramVector3f1, paramVector3f2, 10.0F * localWeaponUnit.getSpeed(), (Vector3f)localObject2);
              this.jdField_field_224_of_type_JavaxVecmathVector3f.set((Tuple3f)localObject3);
              this.jdField_field_224_of_type_JavaxVecmathVector3f.normalize();
              this.jdField_field_249_of_type_JavaxVecmathVector3f.set(this.jdField_field_224_of_type_JavaxVecmathVector3f);
              this.jdField_field_249_of_type_JavaxVecmathVector3f.scale(localWeaponUnit.getDistance());
              this.jdField_field_224_of_type_JavaxVecmathVector3f.scale(0.01F * localWeaponUnit.getSpeed());
              localWeaponUnit.updateLastShoot();
              ((class_747)super.a()).getParticleController().a((class_747)super.a(), (Vector3f)localObject2, this.jdField_field_224_of_type_JavaxVecmathVector3f, localWeaponUnit.getDamage(), localWeaponUnit.getDistance());
              if (!((class_747)super.a()).isOnServer())
              {
                (localObject3 = new Transform()).setIdentity();
                ((Transform)localObject3).origin.set((Tuple3f)localObject2);
                class_969.a8("0022_spaceship user - laser gun single fire small", (Transform)localObject3, 9.0F);
                localWeaponElementManager.notifyShooting(localWeaponUnit);
              }
            }
          }
        }
      }
    }
  }
  
  public final void a31(Vector3f paramVector3f)
  {
    DumbMissileElementManager localDumbMissileElementManager;
    if ((localDumbMissileElementManager = (DumbMissileElementManager)((class_747)super.a()).a112().getDumbMissile().getElementManager()).getCollectionManagers().isEmpty()) {
      return;
    }
    for (int i = 0; i < localDumbMissileElementManager.getCollectionManagers().size(); i++)
    {
      Object localObject1 = (DumbMissileCollectionManager)localDumbMissileElementManager.getCollectionManagers().get(i);
      if (localDumbMissileElementManager.getControlElementMap().isControlling(class_747.field_136, ((DumbMissileCollectionManager)localObject1).getControllerElement().a2(this.jdField_field_223_of_type_Class_48), (short)38))
      {
        localObject1 = ((DumbMissileCollectionManager)localObject1).getCollection().iterator();
        while (((Iterator)localObject1).hasNext())
        {
          MissileUnit localMissileUnit;
          if ((localMissileUnit = (MissileUnit)((Iterator)localObject1).next()).canShoot())
          {
            Object localObject2 = localMissileUnit.getSignificator();
            localObject2 = new Vector3f(((class_48)localObject2).field_475 - 8, ((class_48)localObject2).field_476 - 8, ((class_48)localObject2).field_477 - 8);
            ((class_747)super.a()).getWorldTransform().transform((Vector3f)localObject2);
            this.jdField_field_224_of_type_JavaxVecmathVector3f.set(paramVector3f);
            this.jdField_field_224_of_type_JavaxVecmathVector3f.normalize();
            this.jdField_field_224_of_type_JavaxVecmathVector3f.scale(3.0F * localMissileUnit.getSpeed());
            localMissileUnit.updateLastShoot();
            Transform localTransform;
            if (((class_747)super.a()).isOnServer())
            {
              System.err.println("FIRERING MISSILE!!!!");
              (localTransform = new Transform()).setIdentity();
              localTransform.origin.set((Tuple3f)localObject2);
              localDumbMissileElementManager.getMissileController().addDumbMissile((class_747)super.a(), localTransform, new Vector3f(this.jdField_field_224_of_type_JavaxVecmathVector3f), localMissileUnit.getSpeed(), localMissileUnit.getBlastRadius(), localMissileUnit.getDamage(), localMissileUnit.getDistance());
            }
            else
            {
              System.err.println("CLIENT FIRERING MISSILE!!!!");
            }
            (localTransform = new Transform()).setIdentity();
            localTransform.origin.set((Tuple3f)localObject2);
            if (!((class_747)super.a()).isOnServer())
            {
              class_969.a8("0022_spaceship user - missile fire 1", localTransform, 9.0F);
              localDumbMissileElementManager.notifyShooting(localMissileUnit);
            }
          }
        }
      }
    }
  }
  
  public final void b3(Vector3f paramVector3f)
  {
    HeatMissileElementManager localHeatMissileElementManager;
    if ((localHeatMissileElementManager = (HeatMissileElementManager)((class_747)super.a()).a112().getHeatMissile().getElementManager()).getCollectionManagers().isEmpty()) {
      return;
    }
    for (int i = 0; i < localHeatMissileElementManager.getCollectionManagers().size(); i++)
    {
      Object localObject1 = (HeatMissileCollectionManager)localHeatMissileElementManager.getCollectionManagers().get(i);
      if (localHeatMissileElementManager.getControlElementMap().isControlling(class_747.field_136, ((HeatMissileCollectionManager)localObject1).getControllerElement().a2(this.jdField_field_223_of_type_Class_48), (short)46))
      {
        localObject1 = ((HeatMissileCollectionManager)localObject1).getCollection().iterator();
        while (((Iterator)localObject1).hasNext())
        {
          MissileUnit localMissileUnit;
          if ((localMissileUnit = (MissileUnit)((Iterator)localObject1).next()).canShoot())
          {
            Object localObject2 = localMissileUnit.getSignificator();
            localObject2 = new Vector3f(((class_48)localObject2).field_475 - 8, ((class_48)localObject2).field_476 - 8, ((class_48)localObject2).field_477 - 8);
            ((class_747)super.a()).getWorldTransform().transform((Vector3f)localObject2);
            this.jdField_field_224_of_type_JavaxVecmathVector3f.set(paramVector3f);
            this.jdField_field_224_of_type_JavaxVecmathVector3f.normalize();
            this.jdField_field_224_of_type_JavaxVecmathVector3f.scale(3.0F * localMissileUnit.getSpeed());
            localMissileUnit.updateLastShoot();
            Transform localTransform;
            if (((class_747)super.a()).isOnServer())
            {
              System.err.println("FIRERING MISSILE!!!!");
              (localTransform = new Transform()).setIdentity();
              localTransform.origin.set((Tuple3f)localObject2);
              localHeatMissileElementManager.getMissileController().addHeatMissile((class_747)super.a(), localTransform, new Vector3f(this.jdField_field_224_of_type_JavaxVecmathVector3f), localMissileUnit.getSpeed(), localMissileUnit.getBlastRadius(), localMissileUnit.getDamage(), localMissileUnit.getDistance());
            }
            else
            {
              System.err.println("CLIENT FIRERING MISSILE!!!!");
            }
            (localTransform = new Transform()).setIdentity();
            localTransform.origin.set((Tuple3f)localObject2);
            if (!((class_747)super.a()).isOnServer())
            {
              class_969.a8("0022_spaceship user - missile fire 1", localTransform, 9.0F);
              localHeatMissileElementManager.notifyShooting(localMissileUnit);
            }
          }
        }
      }
    }
  }
  
  public final void a_(class_941 paramclass_941)
  {
    Vector3f localVector3f1;
    (localVector3f1 = new Vector3f()).set(((class_747)super.a()).a113().moveDir.getVector());
    if (localVector3f1.length() > 0.0F) {
      a27(paramclass_941, localVector3f1, true);
    }
    (localVector3f1 = new Vector3f()).set(((class_747)super.a()).a113().orientationDir.getVector());
    if (localVector3f1.length() > 0.0F)
    {
      void tmp91_88 = new Vector3f(0.0F, 1.0F, 0.0F);
      tmp91_88.cross(localVector3f2 = tmp91_88, localVector3f1);
      localVector3f2.scale(400.0F);
      a27(paramclass_941, localVector3f2, false);
      a29(paramclass_941, localVector3f1);
    }
    Vector3f localVector3f2 = new Vector3f();
    paramclass_941 = new Vector3f();
    localVector3f2.set(((class_747)super.a()).a113().targetPosition.getVector());
    paramclass_941.set(((class_747)super.a()).a113().targetVelocity.getVector());
    if (localVector3f2.length() > 0.0F) {
      try
      {
        a30(localVector3f2, paramclass_941);
        a31(localVector3f2);
        b3(localVector3f2);
        return;
      }
      catch (Exception localException)
      {
        (paramclass_941 = localException).printStackTrace();
        throw new ErrorDialogException(paramclass_941.getMessage());
      }
    }
  }
  
  public void b1(class_941 paramclass_941)
  {
    paramclass_941 = this;
    if (System.currentTimeMillis() - paramclass_941.jdField_field_249_of_type_Long > 3000L)
    {
      paramclass_941.jdField_field_249_of_type_Float = 50.0F;
      WeaponElementManager localWeaponElementManager;
      if (!(localWeaponElementManager = (WeaponElementManager)((class_747)paramclass_941.a()).a112().getWeapon().getElementManager()).getCollectionManagers().isEmpty())
      {
        for (int i = 0; i < localWeaponElementManager.getCollectionManagers().size(); i++)
        {
          Object localObject = (WeaponCollectionManager)localWeaponElementManager.getCollectionManagers().get(i);
          if (localWeaponElementManager.getControlElementMap().isControlling(class_747.field_136, ((WeaponCollectionManager)localObject).getControllerPos(), (short)6))
          {
            localObject = ((WeaponCollectionManager)localObject).getCollection().iterator();
            while (((Iterator)localObject).hasNext())
            {
              WeaponUnit localWeaponUnit = (WeaponUnit)((Iterator)localObject).next();
              paramclass_941.jdField_field_249_of_type_Float = Math.max(paramclass_941.jdField_field_249_of_type_Float, localWeaponUnit.getDistance());
            }
          }
        }
        paramclass_941.jdField_field_249_of_type_Long = System.currentTimeMillis();
      }
    }
    if ((this.field_223 != null) && ((this.field_223 instanceof class_1256)) && (((class_1256)this.field_223).a7() != null))
    {
      paramclass_941 = ((class_1256)this.field_223).a7();
      if (!a6().getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(paramclass_941.getId()))
      {
        ((class_1256)this.field_223).a8(null);
        a5().a12(new class_1115());
        return;
      }
      if (((paramclass_941 instanceof class_747)) && (((class_747)paramclass_941).b2()))
      {
        ((class_1256)this.field_223).a8(null);
        a5().a12(new class_1115());
      }
    }
  }
  
  public void a24(class_809 paramclass_809)
  {
    if (System.currentTimeMillis() - this.jdField_field_224_of_type_Long > 25000L)
    {
      if (((paramclass_809 instanceof class_797)) && (this.field_223 != null) && ((this.field_223 instanceof class_1256)) && (!((class_1041)a6()).a45().b29((class_747)super.a(), (class_797)paramclass_809)) && (((Sendable)paramclass_809).getId() != ((class_1256)this.field_223).a11())) {
        if (((class_747)super.a()).getUniqueIdentifier().startsWith("ENTITY_SHIP_MOB_SIM")) {
          ((class_1041)a6()).a69().a214((class_747)super.a(), (class_797)paramclass_809);
        } else {
          ((class_1256)this.field_223).a12(((Sendable)paramclass_809).getId());
        }
      }
      this.jdField_field_224_of_type_Long = System.currentTimeMillis();
    }
  }
  
  public final float a32()
  {
    return this.jdField_field_224_of_type_Float;
  }
  
  public final float b()
  {
    return this.jdField_field_249_of_type_Float;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1260
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */