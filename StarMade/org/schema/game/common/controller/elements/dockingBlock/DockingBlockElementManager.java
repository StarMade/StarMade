/*    */ package org.schema.game.common.controller.elements.dockingBlock;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lA;
/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ import org.schema.game.common.controller.elements.ElementChangeListenerInterface;
/*    */ import org.schema.game.common.controller.elements.UsableControllableElementManager;
/*    */ 
/*    */ public abstract class DockingBlockElementManager extends UsableControllableElementManager
/*    */   implements ElementChangeListenerInterface, DockingElementManagerInterface
/*    */ {
/*    */   private ArrayList dockingManagers;
/*    */ 
/*    */   public DockingBlockElementManager(SegmentController paramSegmentController, short paramShort1, short paramShort2)
/*    */   {
/* 16 */     super(paramShort1, paramShort2, paramSegmentController);
/* 17 */     this.dockingManagers = new ArrayList();
/*    */   }
/*    */ 
/*    */   public ArrayList getCollectionManagers()
/*    */   {
/* 22 */     return this.dockingManagers;
/*    */   }
/*    */ 
/*    */   public void handle(lA paramlA)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void notifyShooting(DockingBlockUnit paramDockingBlockUnit)
/*    */   {
/* 34 */     notifyObservers(paramDockingBlockUnit, "s");
/*    */   }
/*    */ 
/*    */   public void onAddedAnyElement()
/*    */   {
/* 40 */     for (int i = 0; i < this.dockingManagers.size(); i++)
/* 41 */       ((DockingBlockCollectionManager)this.dockingManagers.get(i)).refreshActive();
/*    */   }
/*    */ 
/*    */   public void onRemovedAnyElement()
/*    */   {
/* 47 */     for (int i = 0; i < this.dockingManagers.size(); i++)
/* 48 */       ((DockingBlockCollectionManager)this.dockingManagers.get(i)).refreshActive();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.dockingBlock.DockingBlockElementManager
 * JD-Core Version:    0.6.2
 */