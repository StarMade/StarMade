package org.schema.game.common.controller.elements.powerbeamsupply;

import class_227;
import class_261;
import class_371;
import class_721;
import class_796;
import class_941;
import java.util.Iterator;
import java.util.List;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ControlBlockElementCollectionManager;

public class PowerSupplyBeamCollectionManager
  extends ControlBlockElementCollectionManager
  implements class_721
{
  private final PowerSupplyBeamHandler handler = new PowerSupplyBeamHandler(paramSegmentController, this);
  
  public PowerSupplyBeamCollectionManager(class_796 paramclass_796, SegmentController paramSegmentController)
  {
    super(paramclass_796, (short)335, paramSegmentController);
  }
  
  public PowerSupplyBeamHandler getHandler()
  {
    return this.handler;
  }
  
  public int getMargin()
  {
    return 0;
  }
  
  protected Class getType()
  {
    return PowerSupplyUnit.class;
  }
  
  protected void onChangedCollection()
  {
    refreshBeamCapabiities();
    getHandler().clearStates();
    if (!getSegmentController().isOnServer()) {
      ((class_371)getSegmentController().getState()).a27().a92().a17(this);
    }
  }
  
  public void refreshBeamCapabiities()
  {
    Iterator localIterator = getCollection().iterator();
    while (localIterator.hasNext()) {
      ((PowerSupplyUnit)localIterator.next()).refreshSupplyCapabilities();
    }
  }
  
  public boolean needsUpdate()
  {
    return true;
  }
  
  public void update(class_941 paramclass_941)
  {
    this.handler.update(paramclass_941);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.powerbeamsupply.PowerSupplyBeamCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */