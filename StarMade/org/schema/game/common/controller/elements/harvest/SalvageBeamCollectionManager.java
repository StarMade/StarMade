package org.schema.game.common.controller.elements.harvest;

import class_227;
import class_261;
import class_371;
import class_721;
import class_796;
import class_852;
import class_941;
import java.util.Iterator;
import java.util.List;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ControlBlockElementCollectionManager;

public class SalvageBeamCollectionManager
  extends ControlBlockElementCollectionManager
  implements class_721
{
  private final SalvageBeamHandler handler = new SalvageBeamHandler((class_852)paramSegmentController, this);
  
  public SalvageBeamCollectionManager(class_796 paramclass_796, SegmentController paramSegmentController)
  {
    super(paramclass_796, (short)24, paramSegmentController);
  }
  
  public SalvageBeamHandler getHandler()
  {
    return this.handler;
  }
  
  public int getMargin()
  {
    return 0;
  }
  
  protected Class getType()
  {
    return SalvageUnit.class;
  }
  
  protected void onChangedCollection()
  {
    refreshSalvageCapabiities();
    getHandler().clearStates();
    if (!getSegmentController().isOnServer()) {
      ((class_371)getSegmentController().getState()).a27().a92().a17(this);
    }
  }
  
  public void refreshSalvageCapabiities()
  {
    Iterator localIterator = getCollection().iterator();
    while (localIterator.hasNext()) {
      ((SalvageUnit)localIterator.next()).refreshSalvageCapabilities();
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
 * Qualified Name:     org.schema.game.common.controller.elements.harvest.SalvageBeamCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */