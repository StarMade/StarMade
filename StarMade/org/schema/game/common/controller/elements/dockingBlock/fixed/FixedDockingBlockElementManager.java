/*    */ package org.schema.game.common.controller.elements.dockingBlock.fixed;
/*    */ 
/*    */ import le;
/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ import org.schema.game.common.controller.elements.ElementCollectionManager;
/*    */ import org.schema.game.common.controller.elements.dockingBlock.DockingBlockElementManager;
/*    */ 
/*    */ public class FixedDockingBlockElementManager extends DockingBlockElementManager
/*    */ {
/*    */   public FixedDockingBlockElementManager(SegmentController paramSegmentController)
/*    */   {
/* 13 */     super(paramSegmentController, (short)289, (short)290);
/*    */   }
/*    */ 
/*    */   public ElementCollectionManager getNewCollectionManager(le paramle)
/*    */   {
/* 19 */     return new FixedDockingBlockCollectionManager(paramle, getSegmentController());
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.dockingBlock.fixed.FixedDockingBlockElementManager
 * JD-Core Version:    0.6.2
 */