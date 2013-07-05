/*    */ package org.schema.game.common.controller.elements.repair;
/*    */ 
/*    */ import ct;
/*    */ import dj;
/*    */ import fe;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import jq;
/*    */ import le;
/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ import org.schema.game.common.controller.elements.ControlBlockElementCollectionManager;
/*    */ import xq;
/*    */ 
/*    */ public class RepairBeamCollectionManager extends ControlBlockElementCollectionManager
/*    */   implements jq
/*    */ {
/*    */   private final RepairBeamHandler handler;
/*    */ 
/*    */   public RepairBeamCollectionManager(le paramle, SegmentController paramSegmentController)
/*    */   {
/* 21 */     super(paramle, (short)30, paramSegmentController);
/*    */ 
/* 23 */     this.handler = new RepairBeamHandler(paramSegmentController, this);
/*    */   }
/*    */ 
/*    */   public RepairBeamHandler getHandler()
/*    */   {
/* 33 */     return this.handler;
/*    */   }
/*    */ 
/*    */   public int getMargin()
/*    */   {
/* 41 */     return 0;
/*    */   }
/*    */ 
/*    */   protected Class getType()
/*    */   {
/* 47 */     return RepairUnit.class;
/*    */   }
/*    */ 
/*    */   protected void onChangedCollection()
/*    */   {
/* 52 */     refreshRepairCapabiities();
/* 53 */     getHandler().clearStates();
/*    */ 
/* 56 */     if (!getSegmentController().isOnServer())
/* 57 */       ((ct)getSegmentController().getState()).a().a().a(this);
/*    */   }
/*    */ 
/*    */   public void refreshRepairCapabiities()
/*    */   {
/* 63 */     for (Iterator localIterator = getCollection().iterator(); localIterator.hasNext(); ) ((RepairUnit)localIterator.next())
/* 64 */         .refreshHarvestCapabilities();
/*    */   }
/*    */ 
/*    */   public void update(xq paramxq)
/*    */   {
/* 72 */     this.handler.update(paramxq);
/*    */   }
/*    */ 
/*    */   public boolean needsUpdate()
/*    */   {
/* 77 */     return true;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.repair.RepairBeamCollectionManager
 * JD-Core Version:    0.6.2
 */