package org.schema.game.common.controller.elements.dockingBlock.fixed;

import class_796;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ElementCollectionManager;
import org.schema.game.common.controller.elements.dockingBlock.DockingBlockElementManager;

public class FixedDockingBlockElementManager
  extends DockingBlockElementManager
{
  public FixedDockingBlockElementManager(SegmentController paramSegmentController)
  {
    super(paramSegmentController, (short)289, (short)290);
  }
  
  public ElementCollectionManager getNewCollectionManager(class_796 paramclass_796)
  {
    return new FixedDockingBlockCollectionManager(paramclass_796, getSegmentController());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.dockingBlock.fixed.FixedDockingBlockElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */