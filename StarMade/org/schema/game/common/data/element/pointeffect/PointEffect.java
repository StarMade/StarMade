/*     */ package org.schema.game.common.data.element.pointeffect;
/*     */ 
/*     */ import Ad;
/*     */ import Af;
/*     */ import Ag;
/*     */ import java.util.Observable;
/*     */ import org.schema.game.common.data.element.PointDistributionUnit;
/*     */ 
/*     */ public abstract class PointEffect extends Observable
/*     */   implements Ag
/*     */ {
/*     */   public static final int DAMAGE_EFFECT_ID = 0;
/*     */   public static final int DISTANCE_EFFECT_ID = 1;
/*     */   public static final int RELOAD_EFFECT_ID = 2;
/*     */   public static final int SPEED_EFFECT_ID = 3;
/*     */   public static final int RADIUS_EFFECT_ID = 4;
/*  19 */   private int distribution = 0;
/*     */   protected PointDistributionUnit unit;
/*     */   private int pointsSpend;
/*     */   protected float value;
/*  23 */   private boolean recalc = true;
/*     */ 
/*  25 */   public PointEffect(PointDistributionUnit paramPointDistributionUnit) { this.unit = paramPointDistributionUnit; }
/*     */ 
/*     */   public void decreaseDist(int paramInt)
/*     */   {
/*  29 */     if (this.distribution > 0) {
/*  30 */       this.distribution = Math.max(this.distribution - paramInt, 0);
/*  31 */       this.unit.sendDistChange(this);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void flagRecalculate() {
/*  36 */     this.recalc = true;
/*     */   }
/*     */ 
/*     */   public void fromTagStructure(Ad paramAd)
/*     */   {
/*  44 */     paramAd = (Ad[])paramAd.a();
/*  45 */     assert (((Integer)paramAd[0].a()).intValue() == getPointsSpend());
/*  46 */     setDistribution(((Integer)paramAd[1].a()).intValue());
/*     */   }
/*     */ 
/*     */   public int getDistribution()
/*     */   {
/*  55 */     return this.distribution;
/*     */   }
/*     */ 
/*     */   public abstract int getEffectId();
/*     */ 
/*     */   public abstract String getName();
/*     */ 
/*     */   public int getPointsSpend()
/*     */   {
/*  66 */     return this.pointsSpend;
/*     */   }
/*     */ 
/*     */   public String getUniqueIdentifier()
/*     */   {
/*  74 */     return "effect" + getEffectId();
/*     */   }
/*     */ 
/*     */   public float getValue()
/*     */   {
/*  79 */     if (this.recalc) {
/*  80 */       float f = this.value;
/*  81 */       recalculate();
/*  82 */       if (this.value != f) {
/*  83 */         setChanged();
/*  84 */         notifyObservers();
/*     */       }
/*     */ 
/*  87 */       this.recalc = false;
/*     */     }
/*  89 */     return this.value;
/*     */   }
/*     */ 
/*     */   public void increaseDist(int paramInt)
/*     */   {
/*     */     int i;
/*  96 */     if ((
/*  96 */       i = this.unit.getAvailableDist()) > 0)
/*     */     {
/*  97 */       if (paramInt > i) {
/*  98 */         paramInt = i;
/*     */       }
/* 100 */       this.distribution += paramInt;
/* 101 */       this.unit.sendDistChange(this);
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean isVolatile()
/*     */   {
/* 110 */     return false;
/*     */   }
/*     */   protected abstract void recalculate();
/*     */ 
/*     */   public void reset() {
/* 115 */     setPointsSpend(0);
/*     */   }
/*     */ 
/*     */   public void setDistribution(int paramInt)
/*     */   {
/* 122 */     this.distribution = paramInt;
/*     */   }
/*     */ 
/*     */   public void setPointsSpend(int paramInt)
/*     */   {
/* 130 */     int i = this.pointsSpend;
/* 131 */     this.pointsSpend = paramInt;
/* 132 */     if (paramInt != i) {
/* 133 */       flagRecalculate();
/*     */     }
/* 135 */     setChanged();
/* 136 */     notifyObservers();
/*     */ 
/* 138 */     assert (getPointsSpend() <= this.unit.getMaxPoints()) : (getPointsSpend() + " / " + this.unit.getMaxPoints() + " on " + this);
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 143 */     return getName() + "[" + this.distribution + "%](" + this.pointsSpend + ")";
/*     */   }
/*     */ 
/*     */   public Ad toTagStructure()
/*     */   {
/* 151 */     Ad localAd1 = new Ad(Af.d, "id", Integer.valueOf(getEffectId()));
/* 152 */     Ad localAd2 = new Ad(Af.d, "dist", Integer.valueOf(getDistribution()));
/* 153 */     return new Ad(Af.n, getClass().getSimpleName(), new Ad[] { localAd1, localAd2, new Ad(Af.a, null, null) });
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.pointeffect.PointEffect
 * JD-Core Version:    0.6.2
 */