/*    */ package org.schema.game.common.controller.elements.dockingBlock.turret;
/*    */ 
/*    */ import le;
/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ import org.schema.game.common.controller.elements.ElementCollectionManager;
/*    */ import org.schema.game.common.controller.elements.dockingBlock.DockingBlockElementManager;
/*    */ 
/*    */ public class TurretDockingBlockElementManager extends DockingBlockElementManager
/*    */ {
/*    */   public TurretDockingBlockElementManager(SegmentController paramSegmentController)
/*    */   {
/* 13 */     super(paramSegmentController, (short)7, (short)88);
/*    */   }
/*    */ 
/*    */   public ElementCollectionManager getNewCollectionManager(le paramle)
/*    */   {
/* 19 */     return new TurretDockingBlockCollectionManager(paramle, getSegmentController());
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.dockingBlock.turret.TurretDockingBlockElementManager
 * JD-Core Version:    0.6.2
 */