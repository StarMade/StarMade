/*  1:   */package org.schema.game.common.controller.elements.dockingBlock;
/*  2:   */
/*  3:   */import java.util.ArrayList;
/*  4:   */import lA;
/*  5:   */import org.schema.game.common.controller.SegmentController;
/*  6:   */import org.schema.game.common.controller.elements.ElementChangeListenerInterface;
/*  7:   */import org.schema.game.common.controller.elements.UsableControllableElementManager;
/*  8:   */
/*  9:   */public abstract class DockingBlockElementManager
/* 10:   */  extends UsableControllableElementManager implements ElementChangeListenerInterface, DockingElementManagerInterface
/* 11:   */{
/* 12:   */  private ArrayList dockingManagers;
/* 13:   */  
/* 14:   */  public DockingBlockElementManager(SegmentController paramSegmentController, short paramShort1, short paramShort2)
/* 15:   */  {
/* 16:16 */    super(paramShort1, paramShort2, paramSegmentController);
/* 17:17 */    this.dockingManagers = new ArrayList();
/* 18:   */  }
/* 19:   */  
/* 20:   */  public ArrayList getCollectionManagers()
/* 21:   */  {
/* 22:22 */    return this.dockingManagers;
/* 23:   */  }
/* 24:   */  
/* 28:   */  public void handle(lA paramlA) {}
/* 29:   */  
/* 32:   */  public void notifyShooting(DockingBlockUnit paramDockingBlockUnit)
/* 33:   */  {
/* 34:34 */    notifyObservers(paramDockingBlockUnit, "s");
/* 35:   */  }
/* 36:   */  
/* 38:   */  public void onAddedAnyElement()
/* 39:   */  {
/* 40:40 */    for (int i = 0; i < this.dockingManagers.size(); i++) {
/* 41:41 */      ((DockingBlockCollectionManager)this.dockingManagers.get(i)).refreshActive();
/* 42:   */    }
/* 43:   */  }
/* 44:   */  
/* 45:   */  public void onRemovedAnyElement()
/* 46:   */  {
/* 47:47 */    for (int i = 0; i < this.dockingManagers.size(); i++) {
/* 48:48 */      ((DockingBlockCollectionManager)this.dockingManagers.get(i)).refreshActive();
/* 49:   */    }
/* 50:   */  }
/* 51:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.dockingBlock.DockingBlockElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */