/*    */ package org.schema.game.common.controller.elements.power;
/*    */ 
/*    */ import org.schema.game.common.data.element.ElementCollection;
/*    */ import q;
/*    */ 
/*    */ public class PowerUnit extends ElementCollection
/*    */ {
/* 12 */   private q significator = new q();
/*    */   private double recharge;
/*    */ 
/*    */   public double getRecharge()
/*    */   {
/* 25 */     return this.recharge;
/*    */   }
/*    */ 
/*    */   public q getSignificator()
/*    */   {
/* 30 */     return this.significator;
/*    */   }
/*    */ 
/*    */   public void refreshPowerCapabilities()
/*    */   {
/* 40 */     this.recharge = (Math.max(0.0F, getMax().c - getMin().c) + 1.0D);
/* 41 */     this.recharge += Math.max(0.0F, getMax().a - getMin().a) + 1.0D;
/* 42 */     this.recharge += Math.max(0.0F, getMax().b - getMin().b) + 1.0D;
/* 43 */     this.recharge = Math.max(1.0D, Math.pow(this.recharge / 3.0D, 1.7D));
/*    */   }
/*    */   private static double f(double paramDouble) {
/* 46 */     return Math.max(0.1D, 1.0D - 0.5D * Math.pow(2.0D * (1.0D - paramDouble), 2.5D));
/*    */   }
/*    */   public static double integrate(double paramDouble1, double paramDouble2) {
/* 49 */     double d1 = (paramDouble2 - paramDouble1) / 9.0D;
/*    */ 
/* 53 */     double d2 = 0.3333333333333333D * (f(paramDouble1) + f(paramDouble2));
/*    */     double d3;
/* 56 */     for (paramDouble2 = 1; paramDouble2 < 9; paramDouble2 += 2) {
/* 57 */       d3 = paramDouble1 + d1 * paramDouble2;
/* 58 */       d2 += 1.333333333333333D * f(d3);
/*    */     }
/*    */ 
/* 62 */     for (paramDouble2 = 2; paramDouble2 < 9; paramDouble2 += 2) {
/* 63 */       d3 = paramDouble1 + d1 * paramDouble2;
/* 64 */       d2 += 0.6666666666666666D * f(d3);
/*    */     }
/*    */ 
/* 67 */     return d2 * d1;
/*    */   }
/*    */ 
/*    */   protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
/*    */   {
/* 80 */     this.significator.a = (getMax().a - (getMax().a - getMin().a) / 2);
/* 81 */     this.significator.b = (getMax().b - (getMax().b - getMin().b) / 2);
/* 82 */     this.significator.c = (getMax().c - (getMax().c - getMin().c) / 2);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.power.PowerUnit
 * JD-Core Version:    0.6.2
 */