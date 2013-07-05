/*    */ package org.schema.game.common.controller.elements.weapon;
/*    */ 
/*    */ import ct;
/*    */ import dj;
/*    */ import fe;
/*    */ import le;
/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ import org.schema.game.common.controller.elements.DistributionCollectionManager;
/*    */ 
/*    */ public class WeaponCollectionManager extends DistributionCollectionManager
/*    */ {
/*    */   public WeaponCollectionManager(le paramle, SegmentController paramSegmentController)
/*    */   {
/* 15 */     super(paramle, (short)16, paramSegmentController);
/*    */   }
/*    */ 
/*    */   public int getMargin()
/*    */   {
/* 22 */     return 0;
/*    */   }
/*    */ 
/*    */   protected Class getType()
/*    */   {
/* 30 */     return WeaponUnit.class;
/*    */   }
/*    */ 
/*    */   protected void onChangedCollection()
/*    */   {
/* 36 */     if (!getSegmentController().isOnServer())
/* 37 */       ((ct)getSegmentController().getState()).a().a().a(this);
/*    */   }
/*    */ 
/*    */   public boolean needsUpdate()
/*    */   {
/* 43 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.weapon.WeaponCollectionManager
 * JD-Core Version:    0.6.2
 */