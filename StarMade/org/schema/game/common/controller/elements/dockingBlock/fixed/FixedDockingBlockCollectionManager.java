/*    */ package org.schema.game.common.controller.elements.dockingBlock.fixed;
/*    */ 
/*    */ import le;
/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ import org.schema.game.common.controller.elements.dockingBlock.DockingBlockCollectionManager;
/*    */ import q;
/*    */ 
/*    */ public class FixedDockingBlockCollectionManager extends DockingBlockCollectionManager
/*    */ {
/*    */   public FixedDockingBlockCollectionManager(le paramle, SegmentController paramSegmentController)
/*    */   {
/* 13 */     super(paramle, paramSegmentController, (short)290);
/*    */   }
/*    */ 
/*    */   public void getDockingMoved(q paramq1, q paramq2)
/*    */   {
/* 20 */     getDockingArea(paramq1, paramq2);
/*    */   }
/*    */ 
/*    */   public boolean needsUpdate()
/*    */   {
/* 31 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.dockingBlock.fixed.FixedDockingBlockCollectionManager
 * JD-Core Version:    0.6.2
 */