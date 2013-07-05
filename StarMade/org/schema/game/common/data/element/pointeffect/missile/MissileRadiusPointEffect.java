/*    */ package org.schema.game.common.data.element.pointeffect.missile;
/*    */ 
/*    */ import org.schema.common.FastMath;
/*    */ import org.schema.game.common.data.element.PointDistributionUnit;
/*    */ import org.schema.game.common.data.element.pointeffect.PointEffect;
/*    */ 
/*    */ public class MissileRadiusPointEffect extends PointEffect
/*    */ {
/*    */   public MissileRadiusPointEffect(PointDistributionUnit paramPointDistributionUnit)
/*    */   {
/* 10 */     super(paramPointDistributionUnit);
/*    */   }
/*    */ 
/*    */   public int getEffectId()
/*    */   {
/* 15 */     return 4;
/*    */   }
/*    */ 
/*    */   public String getName()
/*    */   {
/* 20 */     return "Radius";
/*    */   }
/*    */ 
/*    */   protected void recalculate()
/*    */   {
/* 25 */     this.value = Math.max(3.0F, 3.0F + FastMath.g(getPointsSpend()) * 3.0F);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.pointeffect.missile.MissileRadiusPointEffect
 * JD-Core Version:    0.6.2
 */