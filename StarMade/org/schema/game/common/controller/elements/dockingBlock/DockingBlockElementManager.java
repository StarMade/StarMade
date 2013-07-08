package org.schema.game.common.controller.elements.dockingBlock;

import class_755;
import java.util.ArrayList;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ElementChangeListenerInterface;
import org.schema.game.common.controller.elements.UsableControllableElementManager;

public abstract class DockingBlockElementManager
  extends UsableControllableElementManager
  implements ElementChangeListenerInterface, DockingElementManagerInterface
{
  private ArrayList dockingManagers = new ArrayList();
  
  public DockingBlockElementManager(SegmentController paramSegmentController, short paramShort1, short paramShort2)
  {
    super(paramShort1, paramShort2, paramSegmentController);
  }
  
  public ArrayList getCollectionManagers()
  {
    return this.dockingManagers;
  }
  
  public void handle(class_755 paramclass_755) {}
  
  public void notifyShooting(DockingBlockUnit paramDockingBlockUnit)
  {
    notifyObservers(paramDockingBlockUnit, "s");
  }
  
  public void onAddedAnyElement()
  {
    for (int i = 0; i < this.dockingManagers.size(); i++) {
      ((DockingBlockCollectionManager)this.dockingManagers.get(i)).refreshActive();
    }
  }
  
  public void onRemovedAnyElement()
  {
    for (int i = 0; i < this.dockingManagers.size(); i++) {
      ((DockingBlockCollectionManager)this.dockingManagers.get(i)).refreshActive();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.dockingBlock.DockingBlockElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */