/*    */ package org.schema.game.common.controller.elements.thrust;
/*    */ 
/*    */ import ct;
/*    */ import dj;
/*    */ import eG;
/*    */ import er;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ import org.schema.game.common.controller.elements.ElementCollectionManager;
/*    */ 
/*    */ public class ThrusterCollectionManager extends ElementCollectionManager
/*    */ {
/*    */   private float totalThrust;
/*    */ 
/*    */   public ThrusterCollectionManager(SegmentController paramSegmentController)
/*    */   {
/* 20 */     super((short)8, paramSegmentController);
/*    */   }
/*    */ 
/*    */   public int getMargin()
/*    */   {
/* 25 */     return 0;
/*    */   }
/*    */ 
/*    */   public float getTotalThrust()
/*    */   {
/* 32 */     return this.totalThrust;
/*    */   }
/*    */ 
/*    */   protected Class getType()
/*    */   {
/* 39 */     return ThrusterUnit.class;
/*    */   }
/*    */ 
/*    */   protected void onChangedCollection()
/*    */   {
/* 47 */     refreshMaxThrust();
/* 48 */     if (!getSegmentController().isOnServer())
/* 49 */       for (Iterator localIterator = ((ct)getSegmentController().getState()).a().a().a.values().iterator(); localIterator.hasNext(); ((er)localIterator.next()).e());
/*    */   }
/*    */ 
/*    */   private void refreshMaxThrust()
/*    */   {
/* 59 */     setTotalThrust(0.0F);
/* 60 */     for (Iterator localIterator = getCollection().iterator(); localIterator.hasNext(); )
/*    */     {
/*    */       ThrusterUnit localThrusterUnit;
/* 60 */       (
/* 62 */         localThrusterUnit = (ThrusterUnit)localIterator.next())
/* 62 */         .refreshThrusterCapabilities();
/*    */ 
/* 64 */       setTotalThrust(getTotalThrust() + localThrusterUnit.thrust);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void setTotalThrust(float paramFloat)
/*    */   {
/* 72 */     this.totalThrust = paramFloat;
/*    */   }
/*    */ 
/*    */   public boolean needsUpdate()
/*    */   {
/* 77 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.thrust.ThrusterCollectionManager
 * JD-Core Version:    0.6.2
 */