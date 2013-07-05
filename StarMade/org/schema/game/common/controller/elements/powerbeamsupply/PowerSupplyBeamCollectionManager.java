/*    */ package org.schema.game.common.controller.elements.powerbeamsupply;
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
/*    */ public class PowerSupplyBeamCollectionManager extends ControlBlockElementCollectionManager
/*    */   implements jq
/*    */ {
/*    */   private final PowerSupplyBeamHandler handler;
/*    */ 
/*    */   public PowerSupplyBeamCollectionManager(le paramle, SegmentController paramSegmentController)
/*    */   {
/* 24 */     super(paramle, (short)335, paramSegmentController);
/*    */ 
/* 26 */     this.handler = new PowerSupplyBeamHandler(paramSegmentController, this);
/*    */   }
/*    */ 
/*    */   public PowerSupplyBeamHandler getHandler()
/*    */   {
/* 35 */     return this.handler;
/*    */   }
/*    */ 
/*    */   public int getMargin()
/*    */   {
/* 42 */     return 0;
/*    */   }
/*    */ 
/*    */   protected Class getType()
/*    */   {
/* 47 */     return PowerSupplyUnit.class;
/*    */   }
/*    */ 
/*    */   protected void onChangedCollection()
/*    */   {
/* 52 */     refreshBeamCapabiities();
/* 53 */     getHandler().clearStates();
/*    */ 
/* 56 */     if (!getSegmentController().isOnServer())
/* 57 */       ((ct)getSegmentController().getState()).a().a().a(this);
/*    */   }
/*    */ 
/*    */   public void refreshBeamCapabiities()
/*    */   {
/* 63 */     for (Iterator localIterator = getCollection().iterator(); localIterator.hasNext(); ) ((PowerSupplyUnit)localIterator.next())
/* 64 */         .refreshSupplyCapabilities();
/*    */   }
/*    */ 
/*    */   public boolean needsUpdate()
/*    */   {
/* 71 */     return true;
/*    */   }
/*    */ 
/*    */   public void update(xq paramxq) {
/* 75 */     this.handler.update(paramxq);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.powerbeamsupply.PowerSupplyBeamCollectionManager
 * JD-Core Version:    0.6.2
 */