/*    */ package org.schema.game.common.data.element.pointeffect.missile;
/*    */ 
/*    */ import org.schema.common.FastMath;
/*    */ import org.schema.game.common.data.element.PointDistributionUnit;
/*    */ import org.schema.game.common.data.element.pointeffect.PointEffect;
/*    */ 
/*    */ public class MissileSpeedPointEffect extends PointEffect
/*    */ {
/*    */   public MissileSpeedPointEffect(PointDistributionUnit paramPointDistributionUnit)
/*    */   {
/* 10 */     super(paramPointDistributionUnit);
/*    */   }
/*    */ 
/*    */   public int getEffectId()
/*    */   {
/* 15 */     return 3;
/*    */   }
/*    */ 
/*    */   public String getName()
/*    */   {
/* 20 */     return "Speed";
/*    */   }
/*    */ 
/*    */   protected void recalculate()
/*    */   {
/* 25 */     this.value = Math.max(0.2F, 0.2F + FastMath.g(getPointsSpend()) * 0.5F);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.pointeffect.missile.MissileSpeedPointEffect
 * JD-Core Version:    0.6.2
 */