/*    */ package org.schema.game.common.data.element.pointeffect;
/*    */ 
/*    */ import org.schema.game.common.data.element.PointDistributionUnit;
/*    */ 
/*    */ public class DistancePointEffect extends PointEffect
/*    */ {
/*  7 */   private static int distUnit = 40;
/*    */ 
/*    */   public DistancePointEffect(PointDistributionUnit paramPointDistributionUnit) {
/* 10 */     super(paramPointDistributionUnit);
/*    */   }
/*    */ 
/*    */   public int getEffectId() {
/* 14 */     return 1;
/*    */   }
/*    */ 
/*    */   public String getName()
/*    */   {
/* 19 */     return "Distance";
/*    */   }
/*    */ 
/*    */   protected void recalculate()
/*    */   {
/* 24 */     this.value = ((float)Math.max(distUnit, 290.0D + Math.pow(getPointsSpend(), 0.5D) * distUnit));
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.pointeffect.DistancePointEffect
 * JD-Core Version:    0.6.2
 */