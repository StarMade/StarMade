/*    */ package org.schema.game.common.controller.elements.explosive;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ import org.schema.game.common.controller.elements.ElementCollectionManager;
/*    */ 
/*    */ public class ExplosiveCollectionManager extends ElementCollectionManager
/*    */ {
/*    */   private float totalExplosive;
/*    */ 
/*    */   public ExplosiveCollectionManager(SegmentController paramSegmentController)
/*    */   {
/* 17 */     super((short)14, paramSegmentController);
/*    */   }
/*    */ 
/*    */   public int getMargin()
/*    */   {
/* 22 */     return 0;
/*    */   }
/*    */ 
/*    */   public float getTotalExplosive()
/*    */   {
/* 29 */     return this.totalExplosive;
/*    */   }
/*    */ 
/*    */   protected Class getType()
/*    */   {
/* 36 */     return ExplosiveUnit.class;
/*    */   }
/*    */ 
/*    */   protected void onChangedCollection()
/*    */   {
/* 43 */     refreshMaxExplosive();
/*    */   }
/*    */ 
/*    */   private void refreshMaxExplosive()
/*    */   {
/* 48 */     setTotalExplosive(0.0F);
/* 49 */     for (Iterator localIterator = getCollection().iterator(); localIterator.hasNext(); )
/*    */     {
/*    */       ExplosiveUnit localExplosiveUnit;
/* 49 */       (
/* 51 */         localExplosiveUnit = (ExplosiveUnit)localIterator.next())
/* 51 */         .refreshExplosiveCapabilities();
/*    */ 
/* 53 */       setTotalExplosive(getTotalExplosive() + localExplosiveUnit.explosive);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void setTotalExplosive(float paramFloat)
/*    */   {
/* 61 */     this.totalExplosive = paramFloat;
/*    */   }
/*    */ 
/*    */   public boolean needsUpdate()
/*    */   {
/* 66 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.explosive.ExplosiveCollectionManager
 * JD-Core Version:    0.6.2
 */