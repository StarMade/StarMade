/*     */ package org.schema.game.common.data.element;
/*     */ 
/*     */ import Ad;
/*     */ import Ag;
/*     */ import jE;
/*     */ import java.util.Arrays;
/*     */ import q;
/*     */ 
/*     */ public class PointDistributionTagDummy
/*     */   implements Ag
/*     */ {
/*     */   private PointDistributionTagDummy.PointEffectDummy[] effects;
/*     */   private q controllerPos;
/*     */   private q idPos;
/*     */ 
/*     */   public PointDistributionTagDummy()
/*     */   {
/*     */   }
/*     */ 
/*     */   public PointDistributionTagDummy(jE paramjE)
/*     */   {
/*  78 */     this.controllerPos = paramjE.jdField_a_of_type_Q;
/*  79 */     this.idPos = paramjE.jdField_b_of_type_Q;
/*  80 */     this.effects = new PointDistributionTagDummy.PointEffectDummy[1];
/*  81 */     this.effects[0] = new PointDistributionTagDummy.PointEffectDummy(this);
/*  82 */     this.effects[0].setId(Integer.valueOf(paramjE.jdField_a_of_type_Int));
/*  83 */     PointDistributionTagDummy.PointEffectDummy.access$002(this.effects[0], Integer.valueOf(paramjE.jdField_b_of_type_Int));
/*     */   }
/*     */ 
/*     */   public void fromTagStructure(Ad paramAd)
/*     */   {
/*  90 */     paramAd = (Ad[])paramAd.a();
/*  91 */     setControllerPos((q)paramAd[0].a());
/*  92 */     setIdPos((q)paramAd[1].a());
/*  93 */     getFromEffectTagStruct(paramAd[2]);
/*     */   }
/*     */ 
/*     */   public q getControllerPos()
/*     */   {
/*  99 */     return this.controllerPos;
/*     */   }
/*     */ 
/*     */   public PointDistributionTagDummy.PointEffectDummy[] getEffects()
/*     */   {
/* 106 */     return this.effects;
/*     */   }
/*     */   public void getFromEffectTagStruct(Ad paramAd) {
/* 109 */     paramAd = (Ad[])paramAd.a();
/* 110 */     setEffects(new PointDistributionTagDummy.PointEffectDummy[paramAd.length - 1]);
/*     */ 
/* 112 */     for (int i = 0; i < getEffects().length; i++) {
/* 113 */       getEffects()[i] = new PointDistributionTagDummy.PointEffectDummy(this);
/* 114 */       getEffects()[i].fromTagStructure(paramAd[i]);
/*     */     }
/*     */   }
/*     */ 
/*     */   public q getIdPos()
/*     */   {
/* 121 */     return this.idPos;
/*     */   }
/*     */ 
/*     */   public String getUniqueIdentifier()
/*     */   {
/* 130 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean isVolatile()
/*     */   {
/* 135 */     return false;
/*     */   }
/*     */ 
/*     */   public void setControllerPos(q paramq)
/*     */   {
/* 141 */     this.controllerPos = paramq;
/*     */   }
/*     */ 
/*     */   public void setEffects(PointDistributionTagDummy.PointEffectDummy[] paramArrayOfPointEffectDummy)
/*     */   {
/* 147 */     this.effects = paramArrayOfPointEffectDummy;
/*     */   }
/*     */ 
/*     */   public void setIdPos(q paramq)
/*     */   {
/* 153 */     this.idPos = paramq;
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 157 */     return "[" + getControllerPos() + "; " + getIdPos() + "; " + Arrays.toString(getEffects()) + "]";
/*     */   }
/*     */ 
/*     */   public Ad toTagStructure() {
/* 161 */     return null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.PointDistributionTagDummy
 * JD-Core Version:    0.6.2
 */