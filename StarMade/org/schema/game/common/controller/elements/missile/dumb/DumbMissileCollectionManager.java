/*    */ package org.schema.game.common.controller.elements.missile.dumb;
/*    */ 
/*    */ import ct;
/*    */ import dj;
/*    */ import fe;
/*    */ import le;
/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ import org.schema.game.common.controller.elements.DistributionCollectionManager;
/*    */ import org.schema.game.common.controller.elements.missile.MissileUnit;
/*    */ 
/*    */ public class DumbMissileCollectionManager extends DistributionCollectionManager
/*    */ {
/*    */   public DumbMissileCollectionManager(le paramle, SegmentController paramSegmentController)
/*    */   {
/* 15 */     super(paramle, (short)32, paramSegmentController);
/*    */   }
/*    */ 
/*    */   public int getMargin()
/*    */   {
/* 22 */     return 0;
/*    */   }
/*    */ 
/*    */   protected Class getType()
/*    */   {
/* 30 */     return MissileUnit.class;
/*    */   }
/*    */ 
/*    */   protected void onChangedCollection()
/*    */   {
/* 39 */     if (!getSegmentController().isOnServer())
/* 40 */       ((ct)getSegmentController().getState()).a().a().a(this);
/*    */   }
/*    */ 
/*    */   public boolean needsUpdate()
/*    */   {
/* 47 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.missile.dumb.DumbMissileCollectionManager
 * JD-Core Version:    0.6.2
 */