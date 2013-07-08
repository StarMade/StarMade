/*  1:   */package org.schema.game.common.controller.elements.dockingBlock.turret;
/*  2:   */
/*  3:   */import le;
/*  4:   */import org.schema.game.common.controller.SegmentController;
/*  5:   */import org.schema.game.common.controller.elements.dockingBlock.DockingBlockCollectionManager;
/*  6:   */import q;
/*  7:   */
/*  8:   */public class TurretDockingBlockCollectionManager
/*  9:   */  extends DockingBlockCollectionManager
/* 10:   */{
/* 11:   */  public TurretDockingBlockCollectionManager(le paramle, SegmentController paramSegmentController)
/* 12:   */  {
/* 13:13 */    super(paramle, paramSegmentController, (short)88);
/* 14:   */  }
/* 15:   */  
/* 18:   */  public void getDockingMoved(q paramq1, q paramq2)
/* 19:   */  {
/* 20:20 */    getDockingArea(paramq1, paramq2);
/* 21:21 */    paramq2.b += paramq2.b - paramq1.b - 1;
/* 22:22 */    paramq1.b = -1;
/* 23:   */  }
/* 24:   */  
/* 28:   */  public boolean needsUpdate()
/* 29:   */  {
/* 30:30 */    return false;
/* 31:   */  }
/* 32:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.dockingBlock.turret.TurretDockingBlockCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */