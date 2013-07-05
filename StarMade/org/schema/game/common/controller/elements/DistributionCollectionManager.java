/*    */ package org.schema.game.common.controller.elements;
/*    */ 
/*    */ import Ad;
/*    */ import Af;
/*    */ import ct;
/*    */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import le;
/*    */ import org.schema.game.common.controller.CannotImmediateRequestOnClientException;
/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ import org.schema.game.common.data.element.ElementCollection;
/*    */ import org.schema.game.common.data.element.PointDistributionTagDummy;
/*    */ import org.schema.game.common.data.element.PointDistributionUnit;
/*    */ import q;
/*    */ import xq;
/*    */ 
/*    */ public abstract class DistributionCollectionManager extends ControlBlockElementCollectionManager
/*    */ {
/*    */   public static final String TAG_ID = "D";
/*    */ 
/*    */   public DistributionCollectionManager(le paramle, short paramShort, SegmentController paramSegmentController)
/*    */   {
/* 23 */     super(paramle, paramShort, paramSegmentController);
/*    */   }
/*    */ 
/*    */   public void updateStructure(long paramLong)
/*    */   {
/* 35 */     if ((!getContainer().getInitialPointDists().isEmpty()) && (
/* 36 */       (getSegmentController().isOnServer()) || (((ct)getSegmentController().getState()).a().containsKey(getSegmentController().getId()))))
/*    */     {
/*    */       PointDistributionTagDummy localPointDistributionTagDummy;
/* 39 */       for (int i = 0; i < getContainer().getInitialPointDists().size(); i++) {
/* 40 */         localPointDistributionTagDummy = (PointDistributionTagDummy)getContainer().getInitialPointDists().get(i);
/* 41 */         if (getControllerPos().equals(localPointDistributionTagDummy.getControllerPos())) {
/* 42 */           for (PointDistributionUnit localPointDistributionUnit : getCollection()) {
/*    */             try {
/* 44 */               if (((localPointDistributionUnit instanceof PointDistributionUnit)) && (localPointDistributionUnit.getId() != null) && (localPointDistributionUnit.getId().a(new q()).equals(localPointDistributionTagDummy.getIdPos()))) {
/* 45 */                 ((PointDistributionUnit)localPointDistributionUnit).applyDummy(localPointDistributionTagDummy);
/*    */ 
/* 47 */                 getContainer().getInitialPointDists().remove(i);
/* 48 */                 i--;
/* 49 */                 break;
/*    */               }
/*    */             }
/*    */             catch (CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException)
/*    */             {
/*    */             }
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */ 
/* 60 */     super.updateStructure(paramLong);
/*    */   }
/*    */ 
/*    */   public void update(xq paramxq)
/*    */   {
/* 68 */     super.update(paramxq);
/*    */   }
/*    */ 
/*    */   public void sendDistribution()
/*    */   {
/* 73 */     for (Iterator localIterator = getCollection().iterator(); localIterator.hasNext(); ) ((PointDistributionUnit)localIterator.next())
/* 74 */         .sendAllDistChange();
/*    */   }
/*    */ 
/*    */   public Ad toDistributionTagStructure()
/*    */   {
/* 79 */     Ad[] arrayOfAd = new Ad[getCollection().size() + 1];
/*    */ 
/* 81 */     for (int i = 0; i < arrayOfAd.length - 1; i++) {
/* 82 */       arrayOfAd[i] = ((PointDistributionUnit)getCollection().get(i)).toTagStructure();
/*    */     }
/* 84 */     arrayOfAd[(arrayOfAd.length - 1)] = new Ad(Af.a, null, null);
/* 85 */     return new Ad(Af.n, "D", arrayOfAd);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.DistributionCollectionManager
 * JD-Core Version:    0.6.2
 */