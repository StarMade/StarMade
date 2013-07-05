/*    */ package org.schema.game.common.controller.elements.dockingBlock.turret;
/*    */ 
/*    */ import le;
/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ import org.schema.game.common.controller.elements.dockingBlock.DockingBlockCollectionManager;
/*    */ import q;
/*    */ 
/*    */ public class TurretDockingBlockCollectionManager extends DockingBlockCollectionManager
/*    */ {
/*    */   public TurretDockingBlockCollectionManager(le paramle, SegmentController paramSegmentController)
/*    */   {
/* 13 */     super(paramle, paramSegmentController, (short)88);
/*    */   }
/*    */ 
/*    */   public void getDockingMoved(q paramq1, q paramq2)
/*    */   {
/* 20 */     getDockingArea(paramq1, paramq2);
/* 21 */     paramq2.b += paramq2.b - paramq1.b - 1;
/* 22 */     paramq1.b = -1;
/*    */   }
/*    */ 
/*    */   public boolean needsUpdate()
/*    */   {
/* 30 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.dockingBlock.turret.TurretDockingBlockCollectionManager
 * JD-Core Version:    0.6.2
 */