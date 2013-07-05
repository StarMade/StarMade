/*    */ package org.schema.game.common.controller.elements.shield;
/*    */ 
/*    */ import org.schema.game.common.data.element.ElementCollection;
/*    */ import q;
/*    */ 
/*    */ public class ShieldUnit extends ElementCollection
/*    */ {
/*    */   boolean dirty;
/* 10 */   private int shields = 200;
/*    */   private long lastHit;
/* 12 */   q significator = new q();
/*    */ 
/*    */   public int getRechargeAmount() {
/* 15 */     synchronized (this) {
/* 16 */       if ((System.currentTimeMillis() - this.lastHit > 10000L) && 
/* 17 */         (System.currentTimeMillis() - this.lastHit > 1000L))
/*    */       {
/* 19 */         float f = Math.max(1.0F, 1.6F);
/*    */ 
/* 24 */         return (int)Math.min(Math.ceil(getShields() + f), 200.0D) - 
/* 21 */           getShields();
/*    */       }
/*    */ 
/* 27 */       this.dirty = true;
/*    */     }
/* 29 */     return 0;
/*    */   }
/*    */ 
/*    */   public int getShields()
/*    */   {
/* 35 */     return this.shields;
/*    */   }
/*    */ 
/*    */   public q getSignificator()
/*    */   {
/* 41 */     return this.significator;
/*    */   }
/*    */ 
/*    */   public void hit(int paramInt) {
/* 45 */     synchronized (this) {
/* 46 */       this.lastHit = System.currentTimeMillis();
/* 47 */       setShields(Math.max(0, getShields() - paramInt));
/*    */ 
/* 50 */       this.dirty = true;
/* 51 */       return;
/*    */     }
/*    */   }
/*    */ 
/* 55 */   public void incRecharge(int paramInt) { setShields(getShields() + paramInt); }
/*    */ 
/*    */ 
/*    */   public void setShields(int paramInt)
/*    */   {
/* 62 */     this.shields = paramInt;
/*    */   }
/*    */ 
/*    */   protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
/*    */   {
/* 72 */     if ((paramInt1 <= this.significator.a) && (paramInt2 <= this.significator.b) && (paramInt3 < this.significator.c))
/* 73 */       this.significator.b(paramInt1, paramInt2, paramInt3);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.shield.ShieldUnit
 * JD-Core Version:    0.6.2
 */