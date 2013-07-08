/*  1:   */package org.schema.game.common.controller.elements.dockingBlock.turret;
/*  2:   */
/*  3:   */import le;
/*  4:   */import org.schema.game.common.controller.SegmentController;
/*  5:   */import org.schema.game.common.controller.elements.ElementCollectionManager;
/*  6:   */import org.schema.game.common.controller.elements.dockingBlock.DockingBlockElementManager;
/*  7:   */
/*  8:   */public class TurretDockingBlockElementManager
/*  9:   */  extends DockingBlockElementManager
/* 10:   */{
/* 11:   */  public TurretDockingBlockElementManager(SegmentController paramSegmentController)
/* 12:   */  {
/* 13:13 */    super(paramSegmentController, (short)7, (short)88);
/* 14:   */  }
/* 15:   */  
/* 17:   */  public ElementCollectionManager getNewCollectionManager(le paramle)
/* 18:   */  {
/* 19:19 */    return new TurretDockingBlockCollectionManager(paramle, getSegmentController());
/* 20:   */  }
/* 21:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.dockingBlock.turret.TurretDockingBlockElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */