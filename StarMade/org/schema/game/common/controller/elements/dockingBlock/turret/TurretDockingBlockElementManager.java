package org.schema.game.common.controller.elements.dockingBlock.turret;

import class_796;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ElementCollectionManager;
import org.schema.game.common.controller.elements.dockingBlock.DockingBlockElementManager;

public class TurretDockingBlockElementManager
  extends DockingBlockElementManager
{
  public TurretDockingBlockElementManager(SegmentController paramSegmentController)
  {
    super(paramSegmentController, (short)7, (short)88);
  }
  
  public ElementCollectionManager getNewCollectionManager(class_796 paramclass_796)
  {
    return new TurretDockingBlockCollectionManager(paramclass_796, getSegmentController());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.dockingBlock.turret.TurretDockingBlockElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */