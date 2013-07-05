/*    */ package org.schema.game.common.controller.elements;
/*    */ 
/*    */ import Ad;
/*    */ import Af;
/*    */ import java.util.ArrayList;
/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ 
/*    */ public abstract class UsableDistributionControllableElementManager extends UsableControllableElementManager
/*    */ {
/*    */   public UsableDistributionControllableElementManager(short paramShort1, short paramShort2, SegmentController paramSegmentController)
/*    */   {
/* 16 */     super(paramShort1, paramShort2, paramSegmentController);
/*    */   }
/*    */ 
/*    */   public Ad toDistributionTagStructure() {
/* 20 */     Ad[] arrayOfAd = new Ad[getCollectionManagers().size() + 1];
/* 21 */     for (int i = 0; i < getCollectionManagers().size(); i++) {
/* 22 */       arrayOfAd[i] = ((DistributionCollectionManager)getCollectionManagers().get(i)).toDistributionTagStructure();
/*    */     }
/* 24 */     arrayOfAd[getCollectionManagers().size()] = new Ad(Af.a, null, null);
/*    */ 
/* 26 */     return new Ad(Af.n, "wepContr", arrayOfAd);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.UsableDistributionControllableElementManager
 * JD-Core Version:    0.6.2
 */