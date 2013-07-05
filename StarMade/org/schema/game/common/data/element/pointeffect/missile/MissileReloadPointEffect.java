/*    */ package org.schema.game.common.data.element.pointeffect.missile;
/*    */ 
/*    */ import org.schema.common.FastMath;
/*    */ import org.schema.game.common.data.element.PointDistributionUnit;
/*    */ import org.schema.game.common.data.element.pointeffect.PointEffect;
/*    */ 
/*    */ public class MissileReloadPointEffect extends PointEffect
/*    */ {
/*    */   public MissileReloadPointEffect(PointDistributionUnit paramPointDistributionUnit)
/*    */   {
/* 10 */     super(paramPointDistributionUnit);
/*    */   }
/*    */ 
/*    */   public int getEffectId()
/*    */   {
/* 15 */     return 2;
/*    */   }
/*    */ 
/*    */   public String getName()
/*    */   {
/* 20 */     return "Reload";
/*    */   }
/*    */ 
/*    */   protected void recalculate()
/*    */   {
/* 25 */     this.value = Math.max(0.05F, 200.0F / Math.max(1.0F, FastMath.g(getPointsSpend()) * 2.0F));
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.pointeffect.missile.MissileReloadPointEffect
 * JD-Core Version:    0.6.2
 */