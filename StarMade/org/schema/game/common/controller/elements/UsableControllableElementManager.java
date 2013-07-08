package org.schema.game.common.controller.elements;

import class_359;
import class_48;
import class_69;
import class_748;
import class_755;
import class_79;
import class_796;
import class_844;
import class_882;
import class_886;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import org.schema.game.common.controller.CannotImmediateRequestOnClientException;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.player.ShipConfigurationNotFoundException;

public abstract class UsableControllableElementManager
  extends UsableElementManager
{
  protected short controllerId;
  private short controllingId;
  private ElementInformation controllerInfo;
  private ElementInformation controllingInfo;
  
  public UsableControllableElementManager(short paramShort1, short paramShort2, SegmentController paramSegmentController)
  {
    super(paramSegmentController);
    this.controllerId = paramShort1;
    this.controllingId = paramShort2;
    this.controllerInfo = ElementKeyMap.getInfo(paramShort1);
    this.controllingInfo = ElementKeyMap.getInfo(paramShort2);
    assert (this.controllerInfo.getControlling().contains(Short.valueOf(paramShort2))) : (this.controllerInfo.getControlling() + " : " + paramShort2);
    assert (this.controllingInfo.getControlledBy().contains(Short.valueOf(paramShort1))) : (this.controllingInfo.getName() + ": controlled by set " + this.controllingInfo.getControlledBy() + " does not contain " + ElementKeyMap.getInfo(paramShort1) + "(" + paramShort1 + ")");
  }
  
  public void addControllerBlockIfNecessary(class_48 paramclass_481, class_48 paramclass_482, short arg3)
  {
    if (??? == this.controllingId) {
      synchronized (getCollectionManagers())
      {
        Iterator localIterator = getCollectionManagers().iterator();
        while (localIterator.hasNext())
        {
          ControlBlockElementCollectionManager localControlBlockElementCollectionManager;
          if ((localControlBlockElementCollectionManager = (ControlBlockElementCollectionManager)localIterator.next()).equalsControllerPos(paramclass_481))
          {
            localControlBlockElementCollectionManager.addModded(paramclass_482);
            notifyObservers(class_882.field_1117);
            return;
          }
        }
        return;
      }
    }
  }
  
  protected boolean receiveDistribution(class_844 paramclass_844)
  {
    Iterator localIterator = getCollectionManagers().iterator();
    while (localIterator.hasNext())
    {
      ControlBlockElementCollectionManager localControlBlockElementCollectionManager;
      if (((localControlBlockElementCollectionManager = (ControlBlockElementCollectionManager)localIterator.next()).getControllerPos().equals(paramclass_844.field_1091)) && (localControlBlockElementCollectionManager.receiveDistribution(paramclass_844))) {
        return true;
      }
    }
    return false;
  }
  
  protected boolean convertDeligateControls(class_755 paramclass_755, class_48 paramclass_481, class_48 paramclass_482)
  {
    paramclass_481.b1((class_48)paramclass_755.jdField_field_1015_of_type_JavaLangObject);
    paramclass_482.b1((class_48)paramclass_755.jdField_field_1015_of_type_JavaLangObject);
    paramclass_481 = null;
    try
    {
      if (((paramclass_481 = getSegmentBuffer().a10(paramclass_482, true, new class_796())) != null) && (paramclass_481.a9() == 1)) {
        try
        {
          paramclass_481 = checkShipConfig(paramclass_755);
          paramclass_755 = paramclass_755.jdField_field_1015_of_type_Class_748.d1();
          if (!paramclass_481.a18(paramclass_755)) {
            return false;
          }
          paramclass_482.b1(paramclass_481.a17(paramclass_755));
        }
        catch (ShipConfigurationNotFoundException localShipConfigurationNotFoundException)
        {
          return false;
        }
      }
    }
    catch (CannotImmediateRequestOnClientException paramclass_481)
    {
      System.err.println("[CLIENT][WARNING] deferring request for deligated control. segment has been requested: " + paramclass_481.a());
      return false;
    }
    return true;
  }
  
  public abstract ArrayList getCollectionManagers();
  
  public void onControllerBlockAdd() {}
  
  public void onControllerBlockRemove() {}
  
  public void removeControllerIfNecessary(class_48 paramclass_481, class_48 paramclass_482, short arg3)
  {
    if (??? == this.controllingId) {
      synchronized (getCollectionManagers())
      {
        Iterator localIterator = getCollectionManagers().iterator();
        while (localIterator.hasNext())
        {
          ControlBlockElementCollectionManager localControlBlockElementCollectionManager;
          if ((localControlBlockElementCollectionManager = (ControlBlockElementCollectionManager)localIterator.next()).equalsControllerPos(paramclass_481))
          {
            localControlBlockElementCollectionManager.remove(paramclass_482);
            notifyObservers(class_882.field_1117);
            return;
          }
        }
        return;
      }
    }
  }
  
  public boolean hasMetaData()
  {
    return (getCollectionManagers().size() > 0) && (((ControlBlockElementCollectionManager)getCollectionManagers().get(0)).hasMetaData());
  }
  
  public class_69 toTagStructure()
  {
    class_69[] arrayOfclass_69 = new class_69[getCollectionManagers().size() + 1];
    for (int i = 0; i < getCollectionManagers().size(); i++) {
      arrayOfclass_69[i] = ((ControlBlockElementCollectionManager)getCollectionManagers().get(i)).toTagStructure();
    }
    arrayOfclass_69[getCollectionManagers().size()] = new class_69(class_79.field_548, null, null);
    return new class_69(class_79.field_561, "wepContr", arrayOfclass_69);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.UsableControllableElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */