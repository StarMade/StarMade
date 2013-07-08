/*  1:   */package org.schema.game.common.controller.elements.dockingBlock.fixed;
/*  2:   */
/*  3:   */import le;
/*  4:   */import org.schema.game.common.controller.SegmentController;
/*  5:   */import org.schema.game.common.controller.elements.dockingBlock.DockingBlockCollectionManager;
/*  6:   */import q;
/*  7:   */
/*  8:   */public class FixedDockingBlockCollectionManager
/*  9:   */  extends DockingBlockCollectionManager
/* 10:   */{
/* 11:   */  public FixedDockingBlockCollectionManager(le paramle, SegmentController paramSegmentController)
/* 12:   */  {
/* 13:13 */    super(paramle, paramSegmentController, (short)290);
/* 14:   */  }
/* 15:   */  
/* 18:   */  public void getDockingMoved(q paramq1, q paramq2)
/* 19:   */  {
/* 20:20 */    getDockingArea(paramq1, paramq2);
/* 21:   */  }
/* 22:   */  
/* 29:   */  public boolean needsUpdate()
/* 30:   */  {
/* 31:31 */    return false;
/* 32:   */  }
/* 33:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.dockingBlock.fixed.FixedDockingBlockCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */