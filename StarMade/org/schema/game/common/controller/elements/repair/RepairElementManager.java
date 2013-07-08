package org.schema.game.common.controller.elements.repair;

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

public class RepairElementManager
  extends UsableControllableElementManager
  implements BlockActivationListenerInterface
{
  private Vector3f shootingDirTemp = new Vector3f();
  private class_48 controlledFromOrig = new class_48();
  private class_48 controlledFrom = new class_48();
  private ArrayList repairBeamManagers = new ArrayList();
  
  public RepairElementManager(SegmentController paramSegmentController)
  {
    super((short)39, (short)30, paramSegmentController);
  }
  
  public ArrayList getCollectionManagers()
  {
    return this.repairBeamManagers;
  }
  
  public ElementCollectionManager getNewCollectionManager(class_796 paramclass_796)
  {
    return new RepairBeamCollectionManager(paramclass_796, getSegmentController());
  }
  
  public void handle(class_755 paramclass_755)
  {
    if (!((Boolean)paramclass_755.field_1015.a127().activeControllerMask.get(1).get()).booleanValue()) {
      return;
    }
    this.shootingDirTemp.set(paramclass_755.field_1015.a8());
    this.shootingDirTemp.scale(100.0F);
    if (getCollectionManagers().isEmpty()) {
      return;
    }
    if (!convertDeligateControls(paramclass_755, this.controlledFromOrig, this.controlledFrom)) {
      return;
    }
    for (int i = 0; i < getCollectionManagers().size(); i++)
    {
      RepairBeamCollectionManager localRepairBeamCollectionManager;
      int j = 0;
      if (((localRepairBeamCollectionManager = (RepairBeamCollectionManager)getCollectionManagers().get(i)).equalsControllerPos(this.controlledFrom)) && ((this.controlledFromOrig.equals(this.controlledFrom) | getControlElementMap().isControlling(this.controlledFromOrig, localRepairBeamCollectionManager.getControllerPos(), this.controllerId)))) {
        for (j = 0; j < localRepairBeamCollectionManager.getCollection().size(); j++)
        {
          RepairUnit localRepairUnit = (RepairUnit)localRepairBeamCollectionManager.getCollection().get(j);
          Vector3f localVector3f = new Vector3f();
          Object localObject = localRepairUnit.getOutput();
          localObject = new class_48(((class_48)localObject).field_475 - 8, ((class_48)localObject).field_476 - 8, ((class_48)localObject).field_477 - 8);
          getSegmentController().getAbsoluteElementWorldPosition((class_48)localObject, localVector3f);
          (localObject = new Vector3f()).set(localVector3f);
          ((Vector3f)localObject).add(this.shootingDirTemp);
          localRepairBeamCollectionManager.getHandler().addBeam(localRepairUnit.getSignificator(), localVector3f, (Vector3f)localObject, paramclass_755.field_1015, localRepairUnit.getRepairSpeedFactor());
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
    for (int i = 0; i < getCollectionManagers().size(); i++)
    {
      Iterator localIterator = ((RepairBeamCollectionManager)getCollectionManagers().get(i)).getCollection().iterator();
      while (localIterator.hasNext())
      {
        RepairUnit localRepairUnit;
        if ((localRepairUnit = (RepairUnit)localIterator.next()).contains(localclass_48))
        {
          localRepairUnit.setMainPiece(paramclass_796, paramBoolean);
          return;
        }
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.repair.RepairElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */