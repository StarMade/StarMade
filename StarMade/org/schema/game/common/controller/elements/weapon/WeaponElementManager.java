package org.schema.game.common.controller.elements.weapon;

import class_371;
import class_48;
import class_52;
import class_707;
import class_747;
import class_748;
import class_755;
import class_796;
import class_798;
import class_8;
import class_844;
import class_969;
import class_972;
import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
import com.bulletphysics.linearmath.Transform;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;
import org.schema.common.FastMath;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.BlockActivationListenerInterface;
import org.schema.game.common.controller.elements.ElementCollectionManager;
import org.schema.game.common.controller.elements.ManagerContainer;
import org.schema.game.common.controller.elements.ManagerReloadInterface;
import org.schema.game.common.controller.elements.NTDistributionReceiverInterface;
import org.schema.game.common.controller.elements.NTReceiveInterface;
import org.schema.game.common.controller.elements.NTSenderInterface;
import org.schema.game.common.controller.elements.PowerAddOn;
import org.schema.game.common.controller.elements.PowerManagerInterface;
import org.schema.game.common.controller.elements.UsableDistributionControllableElementManager;
import org.schema.game.common.data.element.ControlElementMap;
import org.schema.game.common.data.element.ElementDocking;
import org.schema.game.common.data.physics.PhysicsExt;
import org.schema.game.common.data.world.Segment;
import org.schema.game.network.objects.NetworkPlayer;
import org.schema.schine.network.objects.NetworkEntity;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteBooleanArray;
import org.schema.schine.network.objects.remote.RemoteField;
import org.schema.schine.network.objects.remote.RemoteVector3i;

public class WeaponElementManager
  extends UsableDistributionControllableElementManager
  implements BlockActivationListenerInterface, ManagerReloadInterface, NTDistributionReceiverInterface, NTReceiveInterface, NTSenderInterface
{
  private Vector3f shootingDirTemp = new Vector3f();
  private class_48 controlledFromOrig = new class_48();
  private class_48 controlledFrom = new class_48();
  private final ArrayList weaponManagers = new ArrayList();
  public static boolean debug = false;
  
  public WeaponElementManager(SegmentController paramSegmentController)
  {
    super((short)6, (short)16, paramSegmentController);
  }
  
  private boolean consumePower(float paramFloat)
  {
    if (getPowerManager().consumePowerInstantly(paramFloat)) {
      return true;
    }
    SegmentController localSegmentController;
    if ((getSegmentController().getDockingController().a4() != null) && (((localSegmentController = getSegmentController().getDockingController().a4().field_1740.a7().a15()) instanceof class_798)) && ((((class_798)localSegmentController).a() instanceof PowerManagerInterface))) {
      return ((PowerManagerInterface)((class_798)localSegmentController).a()).getPowerAddOn().consumePowerInstantly(paramFloat);
    }
    return false;
  }
  
  public ArrayList getCollectionManagers()
  {
    return this.weaponManagers;
  }
  
  public ElementCollectionManager getNewCollectionManager(class_796 paramclass_796)
  {
    return new WeaponCollectionManager(paramclass_796, getSegmentController());
  }
  
  public void handle(class_755 paramclass_755)
  {
    if (!((Boolean)paramclass_755.field_1015.a127().activeControllerMask.get(1).get()).booleanValue())
    {
      if (debug) {
        System.err.println("NOT ACTIVE");
      }
      return;
    }
    if (getCollectionManagers().isEmpty())
    {
      if (debug) {
        System.err.println("NO WEAPONS");
      }
      return;
    }
    if (!convertDeligateControls(paramclass_755, this.controlledFromOrig, this.controlledFrom))
    {
      if (debug) {
        System.err.println("NO SLOT");
      }
      return;
    }
    if ((getPowerManager().getPower() <= 0.5D) && (clientIsOwnShip())) {
      ((class_371)getState()).a4().b1("WARNING!\n \nNo Power Supply \nfor weapon systems");
    }
    if (debug) {
      System.err.println("FIREING CONTROLLERS: " + getState() + ", " + this.weaponManagers.size() + " FROM: " + this.controlledFrom);
    }
    for (int i = 0; i < getCollectionManagers().size(); i++)
    {
      WeaponCollectionManager localWeaponCollectionManager;
      int j = (localWeaponCollectionManager = (WeaponCollectionManager)getCollectionManagers().get(i)).getControllerElement().a1(this.controlledFrom);
      if (debug) {
        System.err.println("SELECTED:: " + j + " " + getState());
      }
      if (j != 0)
      {
        j = this.controlledFromOrig.equals(this.controlledFrom) | getControlElementMap().isControlling(this.controlledFromOrig, localWeaponCollectionManager.getControllerPos(), this.controllerId);
        if (debug) {
          System.err.println("Controlling " + j + " " + getState());
        }
        if (j != 0)
        {
          if (this.controlledFromOrig.equals(class_747.field_136)) {
            this.controlledFromOrig.b1(paramclass_755.field_1015.a127().cockpit.getVector());
          }
          if (debug) {
            System.err.println("Controlling " + j + " " + getState() + ": " + localWeaponCollectionManager.getCollection().size());
          }
          for (j = 0; j < localWeaponCollectionManager.getCollection().size(); j++)
          {
            WeaponUnit localWeaponUnit = (WeaponUnit)localWeaponCollectionManager.getCollection().get(j);
            if (debug) {
              System.err.println("CAN SHOOT: " + localWeaponUnit.canShoot() + "; " + getPowerManager().getPower());
            }
            if ((localWeaponUnit.canShoot()) && (consumePower(localWeaponUnit.getPowerConsumed())))
            {
              if (debug) {
                System.err.println("2CAN SHOOT: " + localWeaponUnit.canShoot() + "; " + getPowerManager().getPower() + ": " + getState() + ": " + getSegmentController().getSectorId() + ";; " + getSegmentController().getPhysics());
              }
              Object localObject = localWeaponUnit.getOutput();
              Vector3f localVector3f1 = new Vector3f(((class_48)localObject).field_475 - 8, ((class_48)localObject).field_476 - 8, ((class_48)localObject).field_477 - 8);
              if (debug) {
                System.err.println("WEAPON OUTPUT ON " + getState() + " -> " + localObject);
              }
              if (getSegmentController().isOnServer()) {
                getSegmentController().getWorldTransform().transform(localVector3f1);
              } else {
                getSegmentController().getWorldTransformClient().transform(localVector3f1);
              }
              (localObject = new class_48(this.controlledFromOrig)).c1(class_747.field_136);
              localObject = getSegmentController().getAbsoluteElementWorldPosition((class_48)localObject, new Vector3f());
              Vector3f localVector3f2 = new Vector3f(paramclass_755.field_1015.a8());
              PhysicsExt localPhysicsExt = getSegmentController().getPhysics();
              localVector3f2.scale(localWeaponUnit.getDistance());
              localVector3f2.add((Tuple3f)localObject);
              if ((localObject = localPhysicsExt.testRayCollisionPoint((Vector3f)localObject, localVector3f2, false, getSegmentController(), null, true, null, false)).hasHit()) {
                this.shootingDirTemp.sub(((CollisionWorld.ClosestRayResultCallback)localObject).hitPointWorld, localVector3f1);
              } else {
                this.shootingDirTemp.sub(localVector3f2, localVector3f1);
              }
              this.shootingDirTemp.normalize();
              this.shootingDirTemp.scale(0.01F * localWeaponUnit.getSpeed());
              localWeaponUnit.updateLastShoot();
              getParticleController().a(getSegmentController(), localVector3f1, this.shootingDirTemp, localWeaponUnit.getDamage(), localWeaponUnit.getDistance());
              (localObject = new Transform()).setIdentity();
              ((Transform)localObject).origin.set(localVector3f1);
              getManagerContainer().onAction();
              if (!getSegmentController().isOnServer()) {
                class_969.a8("0022_spaceship user - laser gun single fire small", (Transform)localObject, 0.99F);
              }
              notifyShooting(localWeaponUnit);
            }
          }
          if ((localWeaponCollectionManager.getCollection().isEmpty()) && (clientIsOwnShip())) {
            ((class_371)getState()).a4().d1("WARNING!\n \nNo Weapons connected \nto entry point");
          }
        }
      }
    }
    if ((getCollectionManagers().isEmpty()) && (clientIsOwnShip())) {
      ((class_371)getState()).a4().d1("WARNING!\n \nNo weapon controllers");
    }
  }
  
  public void notifyShooting(WeaponUnit paramWeaponUnit)
  {
    notifyObservers(paramWeaponUnit, "s");
  }
  
  public void updateFromNT(NetworkObject paramNetworkObject) {}
  
  public void updateToFullNT(NetworkObject paramNetworkObject)
  {
    if (getSegmentController().isOnServer()) {
      for (paramNetworkObject = 0; paramNetworkObject < getCollectionManagers().size(); paramNetworkObject++) {
        ((WeaponCollectionManager)getCollectionManagers().get(paramNetworkObject)).sendDistribution();
      }
    }
  }
  
  public boolean receiveDistribution(class_844 paramclass_844, NetworkEntity paramNetworkEntity)
  {
    return receiveDistribution(paramclass_844);
  }
  
  public void onActivate(class_796 paramclass_796, boolean paramBoolean)
  {
    class_48 localclass_48 = paramclass_796.a2(new class_48());
    for (int i = 0; i < getCollectionManagers().size(); i++)
    {
      Iterator localIterator = ((WeaponCollectionManager)getCollectionManagers().get(i)).getCollection().iterator();
      while (localIterator.hasNext())
      {
        WeaponUnit localWeaponUnit;
        if ((localWeaponUnit = (WeaponUnit)localIterator.next()).contains(localclass_48))
        {
          localWeaponUnit.setMainPiece(paramclass_796, paramBoolean);
          return;
        }
      }
    }
  }
  
  public void drawReloads(class_972 paramclass_972, class_48 paramclass_48)
  {
    float f;
    for (int i = 0; i < getCollectionManagers().size(); f++) {
      if (((WeaponCollectionManager)getCollectionManagers().get(i)).getControllerPos().equals(paramclass_48))
      {
        paramclass_48 = ((WeaponCollectionManager)getCollectionManagers().get(i)).getCollection().iterator();
        while (paramclass_48.hasNext())
        {
          WeaponUnit localWeaponUnit = (WeaponUnit)paramclass_48.next();
          int j = (int)(System.currentTimeMillis() - localWeaponUnit.getLastShoot());
          f = Math.min(2.0F, j / localWeaponUnit.getRelaodTime());
          j = 9;
          if (f <= 1.0F) {
            j = (int)FastMath.a5(9.0F + f * 8.0F, 9.0F, 17.0F);
          }
          paramclass_972.a_2(j);
          paramclass_972.b();
        }
        return;
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.weapon.WeaponElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */