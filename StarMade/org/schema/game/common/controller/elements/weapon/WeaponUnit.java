/*     */ package org.schema.game.common.controller.elements.weapon;
/*     */ 
/*     */ import org.schema.game.common.data.element.PointDistributionCustomOutputUnit;
/*     */ import org.schema.game.common.data.element.pointeffect.DamagePointEffect;
/*     */ import org.schema.game.common.data.element.pointeffect.DistancePointEffect;
/*     */ import org.schema.game.common.data.element.pointeffect.PointEffect;
/*     */ import org.schema.game.common.data.element.pointeffect.ReloadPointEffect;
/*     */ import org.schema.game.common.data.element.pointeffect.SpeedPointEffect;
/*     */ import q;
/*     */ 
/*     */ public class WeaponUnit extends PointDistributionCustomOutputUnit
/*     */ {
/*     */   private static final int DAMAGE = 0;
/*     */   private static final int DISTANCE = 1;
/*     */   private static final int RELOAD = 2;
/*     */   private static final int SPEED = 3;
/*     */   private long lastShoot;
/*  87 */   private final q significator = new q(-2147483648, -2147483648, -2147483648);
/*     */ 
/*     */   public boolean canShoot()
/*     */   {
/*  27 */     if ((float)(System.currentTimeMillis() - getLastShoot()) > getRelaodTime()) {
/*  28 */       return true;
/*     */     }
/*     */ 
/*  31 */     return false;
/*     */   }
/*     */ 
/*     */   public float getDamage()
/*     */   {
/*  37 */     return getEffects()[0].getValue();
/*     */   }
/*     */ 
/*     */   public float getDistance()
/*     */   {
/*  42 */     return getEffects()[1].getValue();
/*     */   }
/*     */ 
/*     */   public long getLastShoot()
/*     */   {
/*  49 */     return this.lastShoot;
/*     */   }
/*     */ 
/*     */   public float getPowerConsumed()
/*     */   {
/*  56 */     return size() * 10.0F;
/*     */   }
/*     */ 
/*     */   public float getRelaodTime()
/*     */   {
/*  61 */     return getEffects()[2].getValue();
/*     */   }
/*     */ 
/*     */   public q getSignificator() {
/*  65 */     return this.significator;
/*     */   }
/*     */   public float getSpeed() {
/*  68 */     return getEffects()[3].getValue();
/*     */   }
/*     */ 
/*     */   protected PointEffect[] initializeEffects()
/*     */   {
/*     */     PointEffect[] arrayOfPointEffect;
/*  72 */     (
/*  73 */       arrayOfPointEffect = new PointEffect[4])[
/*  73 */       0] = new DamagePointEffect(this);
/*  74 */     arrayOfPointEffect[1] = new DistancePointEffect(this);
/*  75 */     arrayOfPointEffect[2] = new ReloadPointEffect(this);
/*  76 */     arrayOfPointEffect[3] = new SpeedPointEffect(this);
/*  77 */     return arrayOfPointEffect;
/*     */   }
/*     */ 
/*     */   protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  97 */     if (paramInt3 > this.significator.c)
/*  98 */       this.significator.b(paramInt1, paramInt2, paramInt3);
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 109 */     return "WeaponUnit [significator=" + this.significator + "]";
/*     */   }
/*     */ 
/*     */   public void updateLastShoot()
/*     */   {
/* 114 */     this.lastShoot = System.currentTimeMillis();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.weapon.WeaponUnit
 * JD-Core Version:    0.6.2
 */