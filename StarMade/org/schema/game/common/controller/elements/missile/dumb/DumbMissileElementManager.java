package org.schema.game.common.controller.elements.missile.dumb;

import class_371;
import class_48;
import class_52;
import class_747;
import class_748;
import class_755;
import class_796;
import class_844;
import class_969;
import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
import com.bulletphysics.linearmath.Transform;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ElementCollectionManager;
import org.schema.game.common.controller.elements.ManagerContainer;
import org.schema.game.common.controller.elements.missile.MissileController;
import org.schema.game.common.controller.elements.missile.MissileElementManager;
import org.schema.game.common.controller.elements.missile.MissileUnit;
import org.schema.game.common.data.element.ControlElementMap;
import org.schema.game.common.data.physics.CubeRayCastResult;
import org.schema.game.common.data.physics.PhysicsExt;
import org.schema.game.common.data.world.Segment;
import org.schema.game.network.objects.NetworkPlayer;
import org.schema.game.server.controller.GameServerController;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.NetworkEntity;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteBooleanArray;
import org.schema.schine.network.objects.remote.RemoteField;

public class DumbMissileElementManager
  extends MissileElementManager
{
  private Segment cachedLastWeaponFireHitSegment;
  private Vector3f shootingDirTemp = new Vector3f();
  private class_48 controlledFromOrig = new class_48();
  private class_48 controlledFrom = new class_48();
  private ArrayList missileManagers = new ArrayList();
  
  public DumbMissileElementManager(SegmentController paramSegmentController)
  {
    super((short)38, (short)32, paramSegmentController);
  }
  
  public ArrayList getCollectionManagers()
  {
    return this.missileManagers;
  }
  
  public MissileController getMissileController()
  {
    return ((GameServerController)getSegmentController().getState().getController()).a54();
  }
  
  public ElementCollectionManager getNewCollectionManager(class_796 paramclass_796)
  {
    return new DumbMissileCollectionManager(paramclass_796, getSegmentController());
  }
  
  public void handle(class_755 paramclass_755)
  {
    if (!((Boolean)paramclass_755.field_1015.a127().activeControllerMask.get(1).get()).booleanValue()) {
      return;
    }
    if (getCollectionManagers().isEmpty()) {
      return;
    }
    if (!convertDeligateControls(paramclass_755, this.controlledFromOrig, this.controlledFrom)) {
      return;
    }
    for (int i = 0; i < getCollectionManagers().size(); i++)
    {
      DumbMissileCollectionManager localDumbMissileCollectionManager;
      int j = 0;
      if (((localDumbMissileCollectionManager = (DumbMissileCollectionManager)getCollectionManagers().get(i)).equalsControllerPos(this.controlledFrom)) && ((this.controlledFromOrig.equals(this.controlledFrom) | getControlElementMap().isControlling(this.controlledFromOrig, localDumbMissileCollectionManager.getControllerPos(), this.controllerId))))
      {
        for (j = 0; j < localDumbMissileCollectionManager.getCollection().size(); j++)
        {
          MissileUnit localMissileUnit;
          if ((localMissileUnit = (MissileUnit)localDumbMissileCollectionManager.getCollection().get(j)).canShoot())
          {
            Object localObject1 = localMissileUnit.getOutput();
            localObject1 = new Vector3f(((class_48)localObject1).field_475 - 8, ((class_48)localObject1).field_476 - 8, ((class_48)localObject1).field_477 - 8);
            getWorldTransform().transform((Vector3f)localObject1);
            (localObject2 = new class_48(this.controlledFromOrig)).c1(class_747.field_136);
            Object localObject2 = getSegmentController().getAbsoluteElementWorldPosition((class_48)localObject2, new Vector3f());
            Vector3f localVector3f = new Vector3f(paramclass_755.field_1015.a8());
            PhysicsExt localPhysicsExt = getSegmentController().getPhysics();
            localVector3f.scale(localMissileUnit.getDistance());
            localVector3f.add((Tuple3f)localObject2);
            if ((localObject2 = localPhysicsExt.testRayCollisionPoint((Vector3f)localObject2, localVector3f, false, getSegmentController(), null, true, this.cachedLastWeaponFireHitSegment, false)).hasHit())
            {
              if ((localObject2 instanceof CubeRayCastResult)) {
                this.cachedLastWeaponFireHitSegment = ((CubeRayCastResult)localObject2).getSegment();
              }
              this.shootingDirTemp.sub(((CollisionWorld.ClosestRayResultCallback)localObject2).hitPointWorld, (Tuple3f)localObject1);
            }
            else
            {
              this.cachedLastWeaponFireHitSegment = null;
              this.shootingDirTemp.set(paramclass_755.field_1015.a8());
            }
            this.shootingDirTemp.normalize();
            this.shootingDirTemp.scale(localMissileUnit.getSpeed());
            localMissileUnit.updateLastShoot();
            if (getSegmentController().isOnServer())
            {
              (localObject2 = new Transform()).setIdentity();
              ((Transform)localObject2).origin.set((Tuple3f)localObject1);
              getMissileController().addDumbMissile(getSegmentController(), (Transform)localObject2, new Vector3f(this.shootingDirTemp), localMissileUnit.getSpeed(), localMissileUnit.getBlastRadius(), localMissileUnit.getDamage(), localMissileUnit.getDistance());
            }
            (localObject2 = new Transform()).setIdentity();
            ((Transform)localObject2).origin.set((Tuple3f)localObject1);
            if (!getSegmentController().isOnServer())
            {
              class_969.a8("0022_spaceship user - missile fire 1", (Transform)localObject2, 5.0F);
              notifyShooting(localMissileUnit);
            }
            getManagerContainer().onAction();
          }
        }
        if ((localDumbMissileCollectionManager.getCollection().isEmpty()) && (clientIsOwnShip())) {
          ((class_371)getState()).a4().d1("WARNING!\n \nNo Weapons connected \nto entry point");
        }
      }
    }
    if ((getCollectionManagers().isEmpty()) && (clientIsOwnShip())) {
      ((class_371)getState()).a4().d1("WARNING!\n \nNo weapon controllers");
    }
  }
  
  public void notifyShooting(MissileUnit paramMissileUnit)
  {
    notifyObservers(paramMissileUnit, "s");
  }
  
  public void updateFromNT(NetworkObject paramNetworkObject) {}
  
  public void updateToFullNT(NetworkObject paramNetworkObject)
  {
    if (getSegmentController().isOnServer()) {
      for (paramNetworkObject = 0; paramNetworkObject < getCollectionManagers().size(); paramNetworkObject++) {
        ((DumbMissileCollectionManager)getCollectionManagers().get(paramNetworkObject)).sendDistribution();
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
      Iterator localIterator = ((DumbMissileCollectionManager)getCollectionManagers().get(i)).getCollection().iterator();
      while (localIterator.hasNext())
      {
        MissileUnit localMissileUnit;
        if ((localMissileUnit = (MissileUnit)localIterator.next()).contains(localclass_48))
        {
          localMissileUnit.setMainPiece(paramclass_796, paramBoolean);
          return;
        }
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.missile.dumb.DumbMissileElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */