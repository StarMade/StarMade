/*    */ package org.schema.game.common.controller.elements.jamming;
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
/*    */ public class JammingCollectionManager extends ElementCollectionManager
/*    */ {
/*    */   private float totalJam;
/*    */ 
/*    */   public JammingCollectionManager(SegmentController paramSegmentController)
/*    */   {
/* 21 */     super((short)15, paramSegmentController);
/*    */   }
/*    */ 
/*    */   public int getMargin()
/*    */   {
/* 26 */     return 0;
/*    */   }
/*    */ 
/*    */   public float getTotalJam()
/*    */   {
/* 33 */     return this.totalJam;
/*    */   }
/*    */ 
/*    */   protected Class getType()
/*    */   {
/* 40 */     return JammingUnit.class;
/*    */   }
/*    */ 
/*    */   protected void onChangedCollection()
/*    */   {
/* 48 */     refreshMaxJam();
/*    */ 
/* 51 */     if (!getSegmentController().isOnServer()) {
/* 52 */       ((ct)getSegmentController().getState()).a().a().a(this);
/*    */     }
/*    */ 
/* 55 */     if (getCollection().isEmpty())
/*    */     {
/*    */       JammingElementManager localJammingElementManager;
/* 58 */       if ((
/* 58 */         localJammingElementManager = ((ShipManagerContainer)((ld)getSegmentController()).a()).getJammingElementManager())
/* 58 */         .isJamming())
/* 59 */         localJammingElementManager.stopJamming();
/*    */     }
/*    */   }
/*    */ 
/*    */   private void refreshMaxJam()
/*    */   {
/* 66 */     setTotalJam(0.0F);
/* 67 */     for (Iterator localIterator = getCollection().iterator(); localIterator.hasNext(); )
/*    */     {
/*    */       JammingUnit localJammingUnit;
/* 67 */       (
/* 69 */         localJammingUnit = (JammingUnit)localIterator.next())
/* 69 */         .refreshJammingCapabilities();
/*    */ 
/* 71 */       setTotalJam(getTotalJam() + localJammingUnit.jam);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void setTotalJam(float paramFloat)
/*    */   {
/* 79 */     this.totalJam = paramFloat;
/*    */   }
/*    */ 
/*    */   public boolean needsUpdate()
/*    */   {
/* 84 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.jamming.JammingCollectionManager
 * JD-Core Version:    0.6.2
 */