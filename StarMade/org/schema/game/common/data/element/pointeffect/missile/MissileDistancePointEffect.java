/*    */ package org.schema.game.common.data.element.pointeffect.missile;
/*    */ 
/*    */ import org.schema.common.FastMath;
/*    */ import org.schema.game.common.data.element.PointDistributionUnit;
/*    */ import org.schema.game.common.data.element.pointeffect.PointEffect;
/*    */ 
/*    */ public class MissileDistancePointEffect extends PointEffect
/*    */ {
/*  9 */   private static int distUnit = 19;
/*    */ 
/*    */   public MissileDistancePointEffect(PointDistributionUnit paramPointDistributionUnit) {
/* 12 */     super(paramPointDistributionUnit);
/*    */   }
/*    */ 
/*    */   public int getEffectId() {
/* 16 */     return 1;
/*    */   }
/*    */ 
/*    */   public String getName()
/*    */   {
/* 21 */     return "Distance";
/*    */   }
/*    */ 
/*    */   protected void recalculate()
/*    */   {
/* 26 */     this.value = Math.max(distUnit, FastMath.g(getPointsSpend()) * distUnit);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.pointeffect.missile.MissileDistancePointEffect
 * JD-Core Version:    0.6.2
 */