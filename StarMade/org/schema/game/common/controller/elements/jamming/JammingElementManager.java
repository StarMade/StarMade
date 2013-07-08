package org.schema.game.common.controller.elements.jamming;

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

public class JammingElementManager
  extends UsableControllableSingleElementManager
  implements ManagerActivityInterface, UpdatableCollectionManager
{
  private JammingCollectionManager jamManager;
  private boolean jamming = false;
  private long jamStartTime;
  private class_48 controlledFromOrig = new class_48();
  private class_48 controlledFrom = new class_48();
  private float POWER_CONSUME_MULT = 1000.0F;
  
  public JammingElementManager(JammingCollectionManager paramJammingCollectionManager, SegmentController paramSegmentController)
  {
    super(paramJammingCollectionManager, paramSegmentController);
    this.jamManager = paramJammingCollectionManager;
  }
  
  public float getActualJam()
  {
    return this.jamManager.getTotalJam();
  }
  
  public long getJamStartTime()
  {
    return this.jamStartTime;
  }
  
  public ElementCollectionManager getNewCollectionManager(class_796 paramclass_796)
  {
    return new JammingCollectionManager(getSegmentController());
  }
  
  public void handle(class_755 paramclass_755)
  {
    if (getSegmentController().isOnServer())
    {
      if (!((Boolean)paramclass_755.jdField_field_1015_of_type_Class_748.a127().activeControllerMask.get(1).get()).booleanValue()) {
        return;
      }
      if (System.currentTimeMillis() - this.jamStartTime < 600L) {
        return;
      }
      if ((!class_747.field_136.equals(paramclass_755.jdField_field_1015_of_type_JavaLangObject)) || (this.jamManager.getCollection().isEmpty())) {
        return;
      }
      if (!convertDeligateControls(paramclass_755, this.controlledFromOrig, this.controlledFrom)) {
        return;
      }
      getActualJam();
      try
      {
        if (this.controlledFrom.equals(((JammingUnit)this.jamManager.getCollection().get(0)).getId().a2(new class_48())))
        {
          if (!isJamming())
          {
            System.err.println("[JAMMING] NOW JAMMING");
            setJamStartTime(System.currentTimeMillis());
            setJamming(true);
            return;
          }
          stopJamming();
        }
        return;
      }
      catch (CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException)
      {
        localCannotImmediateRequestOnClientException;
      }
    }
  }
  
  public boolean isJamming()
  {
    if (getSegmentController().isOnServer()) {
      return this.jamming;
    }
    return ((class_747)getSegmentController()).c3();
  }
  
  public void onControllerChange() {}
  
  public void onHit()
  {
    stopJamming();
  }
  
  public void setJamming(boolean paramBoolean)
  {
    this.jamming = paramBoolean;
  }
  
  public void setJamStartTime(long paramLong)
  {
    this.jamStartTime = paramLong;
  }
  
  public void stopJamming()
  {
    if (isJamming())
    {
      System.err.println("Stopped jamming -> reloading");
      setJamming(false);
      setJamStartTime(System.currentTimeMillis());
    }
  }
  
  public void update(class_941 paramclass_941)
  {
    PowerAddOn localPowerAddOn = ((PowerManagerInterface)((class_798)getSegmentController()).a()).getPowerAddOn();
    paramclass_941 = getSegmentController().getTotalElements() / 20.0F * paramclass_941.a() * this.POWER_CONSUME_MULT;
    if ((isJamming()) && (!localPowerAddOn.consumePowerInstantly(paramclass_941)) && (getSegmentController().isOnServer())) {
      stopJamming();
    }
    if ((isJamming()) && (((class_365)getSegmentController()).a26().isEmpty()) && (!((class_991)getSegmentController()).a().a1()) && (getSegmentController().isOnServer())) {
      stopJamming();
    }
  }
  
  public boolean isActive()
  {
    return isJamming();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.jamming.JammingElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */