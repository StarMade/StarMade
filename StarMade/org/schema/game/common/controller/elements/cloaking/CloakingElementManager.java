package org.schema.game.common.controller.elements.cloaking;

import class_365;
import class_48;
import class_747;
import class_748;
import class_755;
import class_796;
import class_798;
import class_941;
import class_985;
import class_991;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.schema.game.common.controller.CannotImmediateRequestOnClientException;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ElementCollectionManager;
import org.schema.game.common.controller.elements.ManagerActivityInterface;
import org.schema.game.common.controller.elements.PowerAddOn;
import org.schema.game.common.controller.elements.PowerManagerInterface;
import org.schema.game.common.controller.elements.UpdatableCollectionManager;
import org.schema.game.common.controller.elements.UsableControllableSingleElementManager;
import org.schema.game.network.objects.NetworkPlayer;
import org.schema.schine.network.objects.remote.RemoteBooleanArray;
import org.schema.schine.network.objects.remote.RemoteField;

public class CloakingElementManager
  extends UsableControllableSingleElementManager
  implements ManagerActivityInterface, UpdatableCollectionManager
{
  private CloakingCollectionManager cloakManager = (CloakingCollectionManager)getCollection();
  private boolean cloaked = false;
  private long cloakStartTime;
  private class_48 controlledFromOrig = new class_48();
  private class_48 controlledFrom = new class_48();
  private float POWER_CONSUME_MULT = 2000.0F;
  
  public CloakingElementManager(SegmentController paramSegmentController)
  {
    super(new CloakingCollectionManager(paramSegmentController), paramSegmentController);
  }
  
  public float getActualCloak()
  {
    return this.cloakManager.getTotalCloak();
  }
  
  public long getCloakStartTime()
  {
    return this.cloakStartTime;
  }
  
  public ElementCollectionManager getNewCollectionManager(class_796 paramclass_796)
  {
    return new CloakingCollectionManager(getSegmentController());
  }
  
  public void handle(class_755 paramclass_755)
  {
    if (getSegmentController().isOnServer())
    {
      if (!((Boolean)paramclass_755.jdField_field_1015_of_type_Class_748.a127().activeControllerMask.get(1).get()).booleanValue()) {
        return;
      }
      if (System.currentTimeMillis() - getCloakStartTime() < 600L) {
        return;
      }
      if ((!class_747.field_136.equals(paramclass_755.jdField_field_1015_of_type_JavaLangObject)) || (this.cloakManager.getCollection().isEmpty())) {
        return;
      }
      if (!convertDeligateControls(paramclass_755, this.controlledFromOrig, this.controlledFrom)) {
        return;
      }
      getActualCloak();
      try
      {
        if (this.controlledFrom.equals(((CloakingUnit)this.cloakManager.getCollection().get(0)).getId().a2(new class_48())))
        {
          if (!isCloaked())
          {
            System.err.println("CLOAKING STARTED");
            setCloakStartTime(System.currentTimeMillis());
            setCloaked(true);
            return;
          }
          stopCloak();
        }
        return;
      }
      catch (CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException)
      {
        localCannotImmediateRequestOnClientException;
      }
    }
  }
  
  public boolean isCloaked()
  {
    if (getSegmentController().isOnServer()) {
      return this.cloaked;
    }
    return ((class_747)getSegmentController()).a7();
  }
  
  public void onControllerChange() {}
  
  public void onHit()
  {
    stopCloak();
  }
  
  public void setCloaked(boolean paramBoolean)
  {
    this.cloaked = paramBoolean;
  }
  
  public void setCloakStartTime(long paramLong)
  {
    this.cloakStartTime = paramLong;
  }
  
  public void stopCloak()
  {
    if (isCloaked())
    {
      setCloaked(false);
      setCloakStartTime(System.currentTimeMillis());
    }
  }
  
  public void update(class_941 paramclass_941)
  {
    PowerAddOn localPowerAddOn = ((PowerManagerInterface)((class_798)getSegmentController()).a()).getPowerAddOn();
    paramclass_941 = getSegmentController().getTotalElements() / 20.0F * paramclass_941.a() * this.POWER_CONSUME_MULT;
    if ((isCloaked()) && (!localPowerAddOn.consumePowerInstantly(paramclass_941)) && (getSegmentController().isOnServer())) {
      stopCloak();
    }
    if ((isCloaked()) && (((class_365)getSegmentController()).a26().isEmpty()) && (!((class_991)getSegmentController()).a().a1()) && (getSegmentController().isOnServer())) {
      stopCloak();
    }
  }
  
  public boolean isActive()
  {
    return isCloaked();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.cloaking.CloakingElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */