/*    */ package org.schema.game.common.controller.elements.cloaking;
/*    */ 
/*    */ import ct;
/*    */ import dj;
/*    */ import fe;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import ld;
/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ import org.schema.game.common.controller.elements.ElementCollectionManager;
/*    */ import org.schema.game.common.controller.elements.ShipManagerContainer;
/*    */ 
/*    */ public class CloakingCollectionManager extends ElementCollectionManager
/*    */ {
/*    */   private float totalCloak;
/*    */ 
/*    */   public CloakingCollectionManager(SegmentController paramSegmentController)
/*    */   {
/* 19 */     super((short)22, paramSegmentController);
/*    */   }
/*    */ 
/*    */   public int getMargin()
/*    */   {
/* 24 */     return 0;
/*    */   }
/*    */ 
/*    */   public float getTotalCloak()
/*    */   {
/* 31 */     return this.totalCloak;
/*    */   }
/*    */ 
/*    */   protected Class getType()
/*    */   {
/* 38 */     return CloakingUnit.class;
/*    */   }
/*    */ 
/*    */   protected void onChangedCollection()
/*    */   {
/* 44 */     refreshMaxCloak();
/*    */ 
/* 46 */     if (!getSegmentController().isOnServer()) {
/* 47 */       ((ct)getSegmentController().getState()).a().a().a(this);
/*    */     }
/*    */ 
/* 50 */     if (getCollection().isEmpty())
/*    */     {
/*    */       CloakingElementManager localCloakingElementManager;
/* 53 */       if ((
/* 53 */         localCloakingElementManager = ((ShipManagerContainer)((ld)getSegmentController()).a()).getCloakElementManager())
/* 53 */         .isCloaked())
/* 54 */         localCloakingElementManager.stopCloak();
/*    */     }
/*    */   }
/*    */ 
/*    */   private void refreshMaxCloak()
/*    */   {
/* 61 */     setTotalCloak(0.0F);
/* 62 */     for (Iterator localIterator = getCollection().iterator(); localIterator.hasNext(); )
/*    */     {
/*    */       CloakingUnit localCloakingUnit;
/* 62 */       (
/* 64 */         localCloakingUnit = (CloakingUnit)localIterator.next())
/* 64 */         .refreshCloakingCapabilities();
/*    */ 
/* 66 */       setTotalCloak(getTotalCloak() + localCloakingUnit.cloak);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void setTotalCloak(float paramFloat)
/*    */   {
/* 74 */     this.totalCloak = paramFloat;
/*    */   }
/*    */ 
/*    */   public boolean needsUpdate()
/*    */   {
/* 80 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.cloaking.CloakingCollectionManager
 * JD-Core Version:    0.6.2
 */