package org.schema.game.common.controller.elements;

import class_755;
import class_796;
import org.schema.game.common.controller.SegmentController;

public class VoidElementManager
  extends UsableControllableSingleElementManager
{
  public VoidElementManager(ElementCollectionManager paramElementCollectionManager, SegmentController paramSegmentController)
  {
    super(paramElementCollectionManager, paramSegmentController);
  }
  
  public ElementCollectionManager getNewCollectionManager(class_796 paramclass_796)
  {
    return null;
  }
  
  public void handle(class_755 paramclass_755) {}
  
  public void onControllerChange() {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.VoidElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */