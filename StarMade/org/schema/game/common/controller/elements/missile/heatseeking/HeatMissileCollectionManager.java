/*    */ package org.schema.game.common.controller.elements.missile.heatseeking;
/*    */ 
/*    */ import ct;
/*    */ import dj;
/*    */ import fe;
/*    */ import le;
/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ import org.schema.game.common.controller.elements.DistributionCollectionManager;
/*    */ import org.schema.game.common.controller.elements.missile.MissileUnit;
/*    */ 
/*    */ public class HeatMissileCollectionManager extends DistributionCollectionManager
/*    */ {
/*    */   public HeatMissileCollectionManager(le paramle, SegmentController paramSegmentController)
/*    */   {
/* 16 */     super(paramle, (short)40, paramSegmentController);
/*    */   }
/*    */ 
/*    */   public int getMargin()
/*    */   {
/* 23 */     return 0;
/*    */   }
/*    */ 
/*    */   protected Class getType()
/*    */   {
/* 28 */     return MissileUnit.class;
/*    */   }
/*    */ 
/*    */   protected void onChangedCollection()
/*    */   {
/* 38 */     if (!getSegmentController().isOnServer())
/* 39 */       ((ct)getSegmentController().getState()).a().a().a(this);
/*    */   }
/*    */ 
/*    */   public boolean needsUpdate()
/*    */   {
/* 47 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.missile.heatseeking.HeatMissileCollectionManager
 * JD-Core Version:    0.6.2
 */