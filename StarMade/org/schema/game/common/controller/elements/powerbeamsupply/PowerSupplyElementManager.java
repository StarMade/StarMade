package org.schema.game.common.controller.elements.powerbeamsupply;

import class_227;
import class_233;
import class_371;
import class_48;
import class_748;
import class_755;
import class_796;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.BlockActivationListenerInterface;
import org.schema.game.common.controller.elements.ElementCollectionManager;
import org.schema.game.common.controller.elements.ManagerContainer;
import org.schema.game.common.controller.elements.UsableControllableElementManager;
import org.schema.game.common.data.element.ControlElementMap;
import org.schema.game.network.objects.NetworkPlayer;
import org.schema.schine.network.objects.remote.RemoteBooleanArray;
import org.schema.schine.network.objects.remote.RemoteField;

public class PowerSupplyElementManager
  extends UsableControllableElementManager
  implements BlockActivationListenerInterface
{
  private Vector3f shootingDirTemp = new Vector3f();
  private class_48 controlledFromOrig = new class_48();
  private class_48 controlledFrom = new class_48();
  private ArrayList powerSupplyBeamCollectionManagers = new ArrayList();
  
  public PowerSupplyElementManager(SegmentController paramSegmentController)
  {
    super((short)334, (short)335, paramSegmentController);
  }
  
  public ArrayList getCollectionManagers()
  {
    return this.powerSupplyBeamCollectionManagers;
  }
  
  public ElementCollectionManager getNewCollectionManager(class_796 paramclass_796)
  {
    return new PowerSupplyBeamCollectionManager(paramclass_796, getSegmentController());
  }
  
  public void handle(class_755 paramclass_755)
  {
    if (!((Boolean)paramclass_755.field_1015.a127().activeControllerMask.get(1).get()).booleanValue()) {
      return;
    }
    this.shootingDirTemp.set(paramclass_755.field_1015.a8());
    this.shootingDirTemp.normalize();
    this.shootingDirTemp.scale(100.0F);
    if (this.powerSupplyBeamCollectionManagers.isEmpty()) {
      return;
    }
    if (!convertDeligateControls(paramclass_755, this.controlledFromOrig, this.controlledFrom)) {
      return;
    }
    int i = this.powerSupplyBeamCollectionManagers.size();
    for (int j = 0; j < i; j++)
    {
      PowerSupplyBeamCollectionManager localPowerSupplyBeamCollectionManager;
      int k = 0;
      if (((localPowerSupplyBeamCollectionManager = (PowerSupplyBeamCollectionManager)this.powerSupplyBeamCollectionManagers.get(j)).equalsControllerPos(this.controlledFrom)) && ((this.controlledFromOrig.equals(this.controlledFrom) | getControlElementMap().isControlling(this.controlledFromOrig, localPowerSupplyBeamCollectionManager.getControllerPos(), this.controllerId)))) {
        for (k = 0; k < localPowerSupplyBeamCollectionManager.getCollection().size(); k++)
        {
          PowerSupplyUnit localPowerSupplyUnit = (PowerSupplyUnit)localPowerSupplyBeamCollectionManager.getCollection().get(k);
          Vector3f localVector3f = new Vector3f();
          Object localObject = localPowerSupplyUnit.getOutput();
          localObject = new class_48(((class_48)localObject).field_475 - 8, ((class_48)localObject).field_476 - 8, ((class_48)localObject).field_477 - 8);
          getSegmentController().getAbsoluteElementWorldPosition((class_48)localObject, localVector3f);
          (localObject = new Vector3f()).set(localVector3f);
          ((Vector3f)localObject).add(this.shootingDirTemp);
          localPowerSupplyBeamCollectionManager.getHandler().addBeam(localPowerSupplyUnit.getSignificator(), localVector3f, (Vector3f)localObject, paramclass_755.field_1015, localPowerSupplyUnit.getSupplySpeedFactor());
          getManagerContainer().onAction();
        }
      }
    }
  }
  
  public void onControllerBlockAdd()
  {
    if (!getSegmentController().isOnServer()) {
      ((class_371)getSegmentController().getState()).a27().a89().a4(this, Boolean.valueOf(true), null);
    }
  }
  
  public void onControllerBlockRemove()
  {
    if (!getSegmentController().isOnServer()) {
      ((class_371)getSegmentController().getState()).a27().a89().a4(this, Boolean.valueOf(false), null);
    }
  }
  
  public void onActivate(class_796 paramclass_796, boolean paramBoolean)
  {
    class_48 localclass_48 = paramclass_796.a2(new class_48());
    for (int i = 0; i < this.powerSupplyBeamCollectionManagers.size(); i++)
    {
      Iterator localIterator = ((PowerSupplyBeamCollectionManager)this.powerSupplyBeamCollectionManagers.get(i)).getCollection().iterator();
      while (localIterator.hasNext())
      {
        PowerSupplyUnit localPowerSupplyUnit;
        if ((localPowerSupplyUnit = (PowerSupplyUnit)localIterator.next()).contains(localclass_48))
        {
          localPowerSupplyUnit.setMainPiece(paramclass_796, paramBoolean);
          return;
        }
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.powerbeamsupply.PowerSupplyElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */