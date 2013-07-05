/*     */ package org.schema.game.common.controller.elements.missile;
/*     */ 
/*     */ import org.schema.game.common.data.element.PointDistributionCustomOutputUnit;
/*     */ import org.schema.game.common.data.element.pointeffect.PointEffect;
/*     */ import org.schema.game.common.data.element.pointeffect.missile.MissileDamagePointEffect;
/*     */ import org.schema.game.common.data.element.pointeffect.missile.MissileDistancePointEffect;
/*     */ import org.schema.game.common.data.element.pointeffect.missile.MissileRadiusPointEffect;
/*     */ import org.schema.game.common.data.element.pointeffect.missile.MissileReloadPointEffect;
/*     */ import org.schema.game.common.data.element.pointeffect.missile.MissileSpeedPointEffect;
/*     */ import q;
/*     */ 
/*     */ public class MissileUnit extends PointDistributionCustomOutputUnit
/*     */ {
/*     */   private static final int DAMAGE = 0;
/*     */   private static final int DISTANCE = 1;
/*     */   private static final int RELOAD = 2;
/*     */   private static final int SPEED = 3;
/*     */   private static final int RADIUS = 4;
/*     */   private long lastShoot;
/*  76 */   private q significator = new q(-2147483648, -2147483648, -2147483648);
/*  77 */   private q minSig = new q(0, 0, 0);
/*  78 */   private q maxSig = new q(0, 0, 0);
/*     */ 
/*     */   public boolean canShoot()
/*     */   {
/*  29 */     if ((float)(System.currentTimeMillis() - getLastShoot()) > getRelaodTime()) {
/*  30 */       return true;
/*     */     }
/*  32 */     return false;
/*     */   }
/*     */ 
/*     */   public int getBlastRadius()
/*     */   {
/*  40 */     return (int)getEffects()[4].getValue();
/*     */   }
/*     */ 
/*     */   public float getDamage()
/*     */   {
/*  47 */     return getEffects()[0].getValue();
/*     */   }
/*     */ 
/*     */   public float getDistance() {
/*  51 */     return getEffects()[1].getValue();
/*     */   }
/*     */ 
/*     */   public long getLastShoot()
/*     */   {
/*  59 */     return this.lastShoot;
/*     */   }
/*     */   public float getRelaodTime() {
/*  62 */     return getEffects()[2].getValue() * 1000.0F;
/*     */   }
/*     */ 
/*     */   public q getSignificator() {
/*  66 */     return this.significator;
/*     */   }
/*     */   public float getSpeed() {
/*  69 */     return getEffects()[3].getValue() * 2.0F;
/*     */   }
/*     */ 
/*     */   protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  88 */     if (paramInt1 > this.significator.a) {
/*  89 */       this.minSig.b(paramInt1, paramInt2, paramInt3);
/*  90 */       this.maxSig.b(paramInt1, paramInt2, paramInt3);
/*  91 */       this.significator.a = getMax().a;
/*  92 */     } else if (paramInt1 == this.significator.a) {
/*  93 */       this.minSig.b(paramInt1, Math.min(paramInt2, this.significator.b), Math.min(paramInt3, this.significator.c));
/*  94 */       this.maxSig.b(paramInt1, Math.max(paramInt2, this.significator.b), Math.max(paramInt3, this.significator.c));
/*     */     }
/*     */ 
/*  98 */     this.significator.b = (this.maxSig.b - (this.maxSig.b - this.minSig.b) / 2);
/*  99 */     this.significator.c = (this.maxSig.c - (this.maxSig.c - this.minSig.c) / 2);
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 108 */     return "HeatMissileUnit [significator=" + this.significator + "]";
/*     */   }
/*     */ 
/*     */   public void updateLastShoot()
/*     */   {
/* 113 */     this.lastShoot = System.currentTimeMillis();
/*     */   }
/*     */ 
/*     */   protected PointEffect[] initializeEffects()
/*     */   {
/*     */     PointEffect[] arrayOfPointEffect;
/* 119 */     (
/* 120 */       arrayOfPointEffect = new PointEffect[5])[
/* 120 */       0] = new MissileDamagePointEffect(this);
/* 121 */     arrayOfPointEffect[1] = new MissileDistancePointEffect(this);
/* 122 */     arrayOfPointEffect[2] = new MissileReloadPointEffect(this);
/* 123 */     arrayOfPointEffect[3] = new MissileSpeedPointEffect(this);
/* 124 */     arrayOfPointEffect[4] = new MissileRadiusPointEffect(this);
/* 125 */     return arrayOfPointEffect;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.missile.MissileUnit
 * JD-Core Version:    0.6.2
 */