/*    */ package org.schema.game.common.controller.elements.harvest;
/*    */ 
/*    */ import ct;
/*    */ import dj;
/*    */ import fe;
/*    */ import jI;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import jq;
/*    */ import le;
/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ import org.schema.game.common.controller.elements.ControlBlockElementCollectionManager;
/*    */ import xq;
/*    */ 
/*    */ public class SalvageBeamCollectionManager extends ControlBlockElementCollectionManager
/*    */   implements jq
/*    */ {
/*    */   private final SalvageBeamHandler handler;
/*    */ 
/*    */   public SalvageBeamCollectionManager(le paramle, SegmentController paramSegmentController)
/*    */   {
/* 24 */     super(paramle, (short)24, paramSegmentController);
/*    */ 
/* 26 */     this.handler = new SalvageBeamHandler((jI)paramSegmentController, this);
/*    */   }
/*    */ 
/*    */   public SalvageBeamHandler getHandler()
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
/* 47 */     return SalvageUnit.class;
/*    */   }
/*    */ 
/*    */   protected void onChangedCollection()
/*    */   {
/* 52 */     refreshSalvageCapabiities();
/* 53 */     getHandler().clearStates();
/*    */ 
/* 56 */     if (!getSegmentController().isOnServer())
/* 57 */       ((ct)getSegmentController().getState()).a().a().a(this);
/*    */   }
/*    */ 
/*    */   public void refreshSalvageCapabiities()
/*    */   {
/* 63 */     for (Iterator localIterator = getCollection().iterator(); localIterator.hasNext(); ) ((SalvageUnit)localIterator.next())
/* 64 */         .refreshSalvageCapabilities();
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
 * Qualified Name:     org.schema.game.common.controller.elements.harvest.SalvageBeamCollectionManager
 * JD-Core Version:    0.6.2
 */