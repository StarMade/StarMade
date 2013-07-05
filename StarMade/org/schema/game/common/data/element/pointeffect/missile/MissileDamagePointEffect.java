/*    */ package org.schema.game.common.data.element.pointeffect.missile;
/*    */ 
/*    */ import org.schema.common.FastMath;
/*    */ import org.schema.game.common.data.element.PointDistributionUnit;
/*    */ import org.schema.game.common.data.element.pointeffect.PointEffect;
/*    */ 
/*    */ public class MissileDamagePointEffect extends PointEffect
/*    */ {
/*    */   public MissileDamagePointEffect(PointDistributionUnit paramPointDistributionUnit)
/*    */   {
/* 12 */     super(paramPointDistributionUnit);
/*    */   }
/*    */ 
/*    */   public int getEffectId()
/*    */   {
/* 17 */     return 0;
/*    */   }
/*    */ 
/*    */   public String getName()
/*    */   {
/* 22 */     return "Damage";
/*    */   }
/*    */ 
/*    */   protected void recalculate()
/*    */   {
/* 27 */     this.value = Math.max(10.0F, FastMath.g(getPointsSpend()) * 50.0F);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.pointeffect.missile.MissileDamagePointEffect
 * JD-Core Version:    0.6.2
 */