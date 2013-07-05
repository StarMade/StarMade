/*    */ package org.schema.game.common.data.element.pointeffect;
/*    */ 
/*    */ import org.schema.game.common.data.element.PointDistributionUnit;
/*    */ 
/*    */ public class DamagePointEffect extends PointEffect
/*    */ {
/*    */   public DamagePointEffect(PointDistributionUnit paramPointDistributionUnit)
/*    */   {
/* 10 */     super(paramPointDistributionUnit);
/*    */   }
/*    */ 
/*    */   public int getEffectId()
/*    */   {
/* 15 */     return 0;
/*    */   }
/*    */ 
/*    */   public String getName()
/*    */   {
/* 20 */     return "Damage";
/*    */   }
/*    */ 
/*    */   protected void recalculate()
/*    */   {
/* 25 */     this.value = ((float)Math.max(1.0D, Math.pow(getPointsSpend() * 0.1D, 0.5D) * 80.0D));
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.pointeffect.DamagePointEffect
 * JD-Core Version:    0.6.2
 */