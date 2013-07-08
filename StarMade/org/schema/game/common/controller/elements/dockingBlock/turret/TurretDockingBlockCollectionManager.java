package org.schema.game.common.controller.elements.dockingBlock.turret;

import class_48;
import class_796;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.dockingBlock.DockingBlockCollectionManager;

public class TurretDockingBlockCollectionManager
  extends DockingBlockCollectionManager
{
  public TurretDockingBlockCollectionManager(class_796 paramclass_796, SegmentController paramSegmentController)
  {
    super(paramclass_796, paramSegmentController, (short)88);
  }
  
  public void getDockingMoved(class_48 paramclass_481, class_48 paramclass_482)
  {
    getDockingArea(paramclass_481, paramclass_482);
    paramclass_482.field_476 += paramclass_482.field_476 - paramclass_481.field_476 - 1;
    paramclass_481.field_476 = -1;
  }
  
  public boolean needsUpdate()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.dockingBlock.turret.TurretDockingBlockCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */