/*    */ package org.schema.game.common.controller.elements.explosive;
/*    */ 
/*    */ import org.schema.game.common.data.element.ElementCollection;
/*    */ import q;
/*    */ 
/*    */ public class ExplosiveUnit extends ElementCollection
/*    */ {
/*  9 */   q significator = new q();
/*    */   float explosive;
/*    */ 
/*    */   public q getSignificator()
/*    */   {
/* 15 */     return this.significator;
/*    */   }
/*    */ 
/*    */   public void refreshExplosiveCapabilities() {
/* 19 */     this.explosive = Math.max(0.0F, getMax().c - getMin().c);
/* 20 */     this.explosive += Math.max(0.0F, getMax().a - getMin().a);
/* 21 */     this.explosive += Math.max(0.0F, getMax().b - getMin().b);
/* 22 */     float f = (float)Math.pow(size(), 1.25D);
/*    */ 
/* 24 */     this.explosive += f;
/* 25 */     this.explosive = Math.max(1.0F, this.explosive);
/*    */   }
/*    */ 
/*    */   protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
/*    */   {
/* 35 */     if ((paramInt1 <= this.significator.a) && 
/* 36 */       (paramInt2 <= this.significator.b) && 
/* 37 */       (paramInt3 < this.significator.c))
/* 38 */       this.significator.b(paramInt1, paramInt2, paramInt3);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.explosive.ExplosiveUnit
 * JD-Core Version:    0.6.2
 */