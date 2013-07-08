package org.schema.game.common.controller.elements.thrust;

import class_1041;
import class_367;
import class_371;
import class_48;
import class_52;
import class_707;
import class_710;
import class_747;
import class_748;
import class_755;
import class_796;
import class_800;
import com.bulletphysics.dynamics.RigidBody;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ElementCollectionManager;
import org.schema.game.common.controller.elements.PowerAddOn;
import org.schema.game.common.controller.elements.UsableControllableSingleElementManager;
import org.schema.game.common.data.physics.PhysicsExt;
import org.schema.game.network.objects.NetworkPlayer;
import org.schema.schine.network.objects.container.PhysicsDataContainer;
import org.schema.schine.network.objects.remote.RemoteBooleanArray;
import org.schema.schine.network.objects.remote.RemoteField;

public class ThrusterElementManager
  extends UsableControllableSingleElementManager
{
  public static final float MAX_VELOCITY_UNIT = 2.0F;
  private Vector3f velocity = new Vector3f();
  private long lastMoveUpdate;
  private Vector3f linearVelocityTmp = new Vector3f();
  private ThrusterCollectionManager thrustManager = (ThrusterCollectionManager)getCollection();
  private final Vector3f field_377 = new Vector3f();
  private final Vector3f down = new Vector3f();
  private final Vector3f left = new Vector3f();
  private final Vector3f right = new Vector3f();
  private final Vector3f forward = new Vector3f();
  private final Vector3f backward = new Vector3f();
  private final Vector3f dir = new Vector3f();
  
  public ThrusterElementManager(SegmentController paramSegmentController)
  {
    super(new ThrusterCollectionManager(paramSegmentController), paramSegmentController);
    if (!paramSegmentController.isOnServer()) {
      addObserver((class_710)paramSegmentController.getState());
    }
  }
  
  public boolean canHandle(class_755 paramclass_755)
  {
    return true;
  }
  
  public float getActualThrust()
  {
    float f;
    if ((f = this.thrustManager.getTotalThrust()) == 0.0F) {
      return 0.0F;
    }
    return Math.max(0.5F, f);
  }
  
  public float getMaxVelocity()
  {
    if (getSegmentController().isOnServer()) {
      return ((class_1041)getSegmentController().getState()).a12().a54();
    }
    return ((class_371)getSegmentController().getState()).a12().a54();
  }
  
  public ElementCollectionManager getNewCollectionManager(class_796 paramclass_796)
  {
    return new ThrusterCollectionManager(getSegmentController());
  }
  
  public Vector3f getVelocity()
  {
    return this.velocity;
  }
  
  public void handle(class_755 paramclass_755)
  {
    if (getSegmentController().getMass() <= 0.0F) {
      return;
    }
    if (!((Boolean)paramclass_755.jdField_field_1015_of_type_Class_748.a127().activeControllerMask.get(1).get()).booleanValue()) {
      return;
    }
    if (!class_747.field_136.equals(paramclass_755.jdField_field_1015_of_type_JavaLangObject)) {
      return;
    }
    float f1 = 3.0F / Math.max(1.0F, getSegmentController().getMass() / 8.7F);
    f1 = Math.max(0.1F, f1);
    float f2 = getActualThrust();
    if (getSegmentController().getDockingController().a4() != null)
    {
      paramclass_755.jdField_field_1015_of_type_Class_748.c10(this.field_377);
      this.down.set(this.field_377);
      this.down.negate();
      paramclass_755.jdField_field_1015_of_type_Class_748.b19(this.left);
      this.right.set(this.left);
      this.right.negate();
      paramclass_755.jdField_field_1015_of_type_Class_748.a126(this.forward);
      this.backward.set(this.forward);
      this.backward.negate();
      getSegmentController().getPhysics().orientate(getSegmentController(), this.forward, this.field_377, this.right, f1);
      return;
    }
    if (f2 == 0.0F)
    {
      if (clientIsOwnShip()) {
        ((class_371)getState()).a4().d1("WARNING!\n \nNo thrusters connected to core");
      }
      f2 = 0.1F;
    }
    else if ((f2 <= 0.5F) && (clientIsOwnShip()))
    {
      ((class_371)getState()).a4().d1("WARNING!\n \nNot enough Thrusters for\n the mass of your ship\n-> Ship can only move slow");
    }
    paramclass_755.jdField_field_1015_of_type_Class_748.c10(this.field_377);
    this.down.set(this.field_377);
    this.down.negate();
    paramclass_755.jdField_field_1015_of_type_Class_748.b19(this.left);
    this.right.set(this.left);
    this.right.negate();
    paramclass_755.jdField_field_1015_of_type_Class_748.a126(this.forward);
    this.backward.set(this.forward);
    this.backward.negate();
    this.dir.set(0.0F, 0.0F, 0.0F);
    if (paramclass_755.jdField_field_1015_of_type_Class_748.a132(class_367.field_719)) {
      this.dir.add(this.forward);
    }
    if (paramclass_755.jdField_field_1015_of_type_Class_748.a132(class_367.field_720)) {
      this.dir.add(this.backward);
    }
    if (paramclass_755.jdField_field_1015_of_type_Class_748.a132(class_367.field_717)) {
      this.dir.add(this.right);
    }
    if (paramclass_755.jdField_field_1015_of_type_Class_748.a132(class_367.field_718)) {
      this.dir.add(this.left);
    }
    if (paramclass_755.jdField_field_1015_of_type_Class_748.a132(class_367.field_721)) {
      this.dir.add(this.field_377);
    }
    if (paramclass_755.jdField_field_1015_of_type_Class_748.a132(class_367.field_722)) {
      this.dir.add(this.down);
    }
    RigidBody localRigidBody = (RigidBody)getPhysicsDataContainer().getObject();
    if (this.lastMoveUpdate + 30L < System.currentTimeMillis())
    {
      if (this.dir.length() > 0.0F)
      {
        paramclass_755 = this.thrustManager.getTotalThrust();
        if (getPowerManager().getPower() < this.thrustManager.getTotalThrust())
        {
          if (getPowerManager().getPower() <= 0.0D) {
            return;
          }
          double d1 = getPowerManager().getPower();
          getPowerManager().consumePowerInstantly(d1);
          f2 = (float)d1;
        }
        else if (!getPowerManager().consumePowerInstantly(paramclass_755))
        {
          if (clientIsOwnShip()) {
            ((class_371)getState()).a4().d1("WARNING!\n \nThrusters have no power");
          }
          return;
        }
        localRigidBody.activate();
        this.dir.scale(f2 * 0.5F);
        localRigidBody.applyCentralImpulse(this.dir);
        localRigidBody.getLinearVelocity(this.linearVelocityTmp);
        if (this.linearVelocityTmp.length() > getMaxVelocity())
        {
          this.linearVelocityTmp.normalize();
          this.linearVelocityTmp.scale(getMaxVelocity());
          localRigidBody.setLinearVelocity(this.linearVelocityTmp);
        }
      }
      else if (paramclass_755.jdField_field_1015_of_type_Class_748.a132(class_367.field_726))
      {
        if ((paramclass_755 = localRigidBody.getLinearVelocity(new Vector3f())).length() > 1.0F)
        {
          float f3 = this.thrustManager.getTotalThrust();
          if (getPowerManager().getPower() < this.thrustManager.getTotalThrust())
          {
            if (getPowerManager().getPower() > 0.0D)
            {
              double d2 = getPowerManager().getPower();
              getPowerManager().consumePowerInstantly(d2);
              f2 = (float)d2;
              break label887;
            }
          }
          else
          {
            if (getPowerManager().consumePowerInstantly(f3)) {
              break label887;
            }
            if (clientIsOwnShip()) {
              ((class_371)getState()).a4().d1("WARNING!\n \nThrusters have no power");
            }
          }
          f2 = 0.1F;
          label887:
          Vector3f localVector3f1;
          (localVector3f1 = new Vector3f(paramclass_755)).normalize();
          localVector3f1.negate();
          localVector3f1.scale(f2 * 0.5F);
          Vector3f localVector3f2;
          (localVector3f2 = new Vector3f(localVector3f1)).scale(localRigidBody.getInvMass());
          if (paramclass_755.length() < localVector3f2.length())
          {
            System.err.println("INSTA ZERO: " + paramclass_755.length() + " < " + localVector3f1.length());
          }
          else
          {
            localRigidBody.applyCentralImpulse(localVector3f1);
            break label1014;
          }
        }
        paramclass_755.set(0.0F, 0.0F, 0.0F);
        localRigidBody.setLinearVelocity(paramclass_755);
      }
      label1014:
      if (!getAttachedPlayers().isEmpty()) {
        getSegmentController().getPhysics().orientate(getSegmentController(), this.forward, this.field_377, this.right, f1);
      }
      localRigidBody.getLinearVelocity(getVelocity());
      this.lastMoveUpdate = System.currentTimeMillis();
    }
  }
  
  public void onControllerChange() {}
  
  public void setVelocity(Vector3f paramVector3f)
  {
    this.velocity = paramVector3f;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.thrust.ThrusterElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */