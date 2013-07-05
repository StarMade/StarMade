/*    */ package org.schema.game.common.controller.elements.cloaking;
/*    */ 
/*    */ import org.schema.game.common.data.element.ElementCollection;
/*    */ import q;
/*    */ 
/*    */ public class CloakingUnit extends ElementCollection
/*    */ {
/* 10 */   q significator = new q();
/*    */   float cloak;
/*    */ 
/*    */   public q getSignificator()
/*    */   {
/* 17 */     return this.significator;
/*    */   }
/*    */ 
/*    */   public void refreshCloakingCapabilities() {
/* 21 */     this.cloak = Math.max(0.0F, getMax().c - getMin().c);
/* 22 */     this.cloak += Math.max(0.0F, getMax().a - getMin().a);
/* 23 */     this.cloak += Math.max(0.0F, getMax().b - getMin().b);
/* 24 */     float f = (float)Math.pow(size(), 1.25D);
/*    */ 
/* 26 */     this.cloak += f;
/* 27 */     this.cloak = Math.max(1.0F, this.cloak);
/*    */   }
/*    */ 
/*    */   protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
/*    */   {
/* 37 */     if ((paramInt1 <= this.significator.a) && 
/* 38 */       (paramInt2 <= this.significator.b) && 
/* 39 */       (paramInt3 < this.significator.c))
/* 40 */       this.significator.b(paramInt1, paramInt2, paramInt3);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.cloaking.CloakingUnit
 * JD-Core Version:    0.6.2
 */