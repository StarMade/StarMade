/*    */ package org.schema.game.common.controller.elements.repair;
/*    */ 
/*    */ import org.schema.common.FastMath;
/*    */ import org.schema.game.common.data.element.CustomOutputUnit;
/*    */ import q;
/*    */ 
/*    */ public class RepairUnit extends CustomOutputUnit
/*    */ {
/*    */   private float repairSpeedFactor;
/* 28 */   private q significator = new q(-2147483648, -2147483648, -2147483648);
/* 29 */   private q minSig = new q(0, 0, 0);
/* 30 */   private q maxSig = new q(0, 0, 0);
/*    */ 
/*    */   public float getRepairSpeedFactor()
/*    */   {
/* 16 */     return this.repairSpeedFactor;
/*    */   }
/*    */ 
/*    */   public q getSignificator()
/*    */   {
/* 21 */     return this.significator;
/*    */   }
/*    */ 
/*    */   public void refreshHarvestCapabilities() {
/* 25 */     this.repairSpeedFactor = (3.0F / FastMath.b(Math.max(0.0F, size()), 1.13F));
/*    */   }
/*    */ 
/*    */   protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
/*    */   {
/* 40 */     if (paramInt1 > this.significator.a) {
/* 41 */       this.minSig.b(paramInt1, paramInt2, paramInt3);
/* 42 */       this.maxSig.b(paramInt1, paramInt2, paramInt3);
/* 43 */       this.significator.a = getMax().a;
/* 44 */     } else if (paramInt1 == this.significator.a) {
/* 45 */       this.minSig.b(paramInt1, Math.min(paramInt2, this.significator.b), Math.min(paramInt3, this.significator.c));
/* 46 */       this.maxSig.b(paramInt1, Math.max(paramInt2, this.significator.b), Math.max(paramInt3, this.significator.c));
/*    */     }
/*    */ 
/* 50 */     this.significator.b = (this.maxSig.b - (this.maxSig.b - this.minSig.b) / 2);
/* 51 */     this.significator.c = (this.maxSig.c - (this.maxSig.c - this.minSig.c) / 2);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.repair.RepairUnit
 * JD-Core Version:    0.6.2
 */