package org.schema.game.common.controller.elements.dockingBlock.fixed;

import class_48;
import class_796;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.dockingBlock.DockingBlockCollectionManager;

public class FixedDockingBlockCollectionManager
  extends DockingBlockCollectionManager
{
  public FixedDockingBlockCollectionManager(class_796 paramclass_796, SegmentController paramSegmentController)
  {
    super(paramclass_796, paramSegmentController, (short)290);
  }
  
  public void getDockingMoved(class_48 paramclass_481, class_48 paramclass_482)
  {
    getDockingArea(paramclass_481, paramclass_482);
  }
  
  public boolean needsUpdate()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.dockingBlock.fixed.FixedDockingBlockCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */