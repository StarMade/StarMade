/*    */ package org.schema.game.common.controller.elements.missile.fireandforget;
/*    */ 
/*    */ import ct;
/*    */ import dj;
/*    */ import fe;
/*    */ import le;
/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ import org.schema.game.common.controller.elements.DistributionCollectionManager;
/*    */ import org.schema.game.common.controller.elements.missile.MissileUnit;
/*    */ 
/*    */ public class FafoMissileCollectionManager extends DistributionCollectionManager
/*    */ {
/*    */   public FafoMissileCollectionManager(le paramle, SegmentController paramSegmentController)
/*    */   {
/* 15 */     super(paramle, (short)48, paramSegmentController);
/*    */   }
/*    */ 
/*    */   public int getMargin()
/*    */   {
/* 22 */     return 0;
/*    */   }
/*    */ 
/*    */   protected Class getType()
/*    */   {
/* 29 */     return MissileUnit.class;
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
/* 46 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.missile.fireandforget.FafoMissileCollectionManager
 * JD-Core Version:    0.6.2
 */