/*     */ package org.schema.game.common.data.element;
/*     */ 
/*     */ import Ad;
/*     */ import Af;
/*     */ import Ag;
/*     */ import jE;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Arrays;
/*     */ import le;
/*     */ import org.schema.common.FastMath;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.controller.elements.ControlBlockElementCollectionManager;
/*     */ import org.schema.game.common.controller.elements.DistributionInterface;
/*     */ import org.schema.game.common.controller.elements.ElementCollectionManager;
/*     */ import org.schema.game.common.data.element.pointeffect.PointEffect;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*     */ import org.schema.schine.network.objects.remote.RemoteIntArray;
/*     */ import org.schema.schine.network.objects.remote.RemoteIntArrayBuffer;
/*     */ import q;
/*     */ 
/*     */ public abstract class PointDistributionUnit extends ElementCollection
/*     */   implements Ag
/*     */ {
/*     */   private PointEffect[] effects;
/*     */   private int maxPoints;
/*     */   private boolean flagRedistribute;
/*     */ 
/*     */   public void applyDummy(PointDistributionTagDummy paramPointDistributionTagDummy)
/*     */   {
/*  34 */     for (int i = 0; i < paramPointDistributionTagDummy.getEffects().length; i++) {
/*  35 */       PointDistributionTagDummy.PointEffectDummy localPointEffectDummy = paramPointDistributionTagDummy.getEffects()[i];
/*     */       PointEffect localPointEffect;
/*  36 */       (
/*  37 */         localPointEffect = getEffectById(localPointEffectDummy.getId().intValue()))
/*  37 */         .setDistribution(localPointEffectDummy.getDist().intValue());
/*  38 */       sendDistChange(localPointEffect);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/*  47 */     super.clear();
/*  48 */     resetPointsSpent();
/*  49 */     setMaxPoints(0);
/*     */   }
/*     */ 
/*     */   public void distributePoints() {
/*  53 */     resetPointsSpent();
/*     */ 
/*  55 */     int i = 0;
/*  56 */     for (int j = 0; j < this.effects.length; j++) {
/*  57 */       int k = FastMath.a(this.effects[j].getDistribution() / 100.0F * getMaxPoints());
/*  58 */       this.effects[j].setPointsSpend(k);
/*  59 */       i += k;
/*     */     }
/*  61 */     j = 0;
/*  62 */     while (i < getMaxPoints()) {
/*  63 */       this.effects[j].setPointsSpend(this.effects[j].getPointsSpend() + 1);
/*  64 */       j = (j + 1) % this.effects.length;
/*  65 */       i++;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void fromTagStructure(Ad paramAd)
/*     */   {
/*  73 */     (
/*  74 */       paramAd = (Ad[])paramAd.a())[
/*  74 */       0].a();
/*  75 */     paramAd[1].a();
/*  76 */     getFromEffectTagStruct(paramAd[2]);
/*     */   }
/*     */ 
/*     */   public int getAvailableDist() {
/*  80 */     int i = 0;
/*  81 */     for (PointEffect localPointEffect : this.effects) {
/*  82 */       i += localPointEffect.getDistribution();
/*     */     }
/*     */ 
/*  86 */     return 100 - i;
/*     */   }
/*     */ 
/*     */   public PointEffect getEffectById(int paramInt)
/*     */   {
/*     */     PointEffect[] arrayOfPointEffect;
/*  89 */     int i = (arrayOfPointEffect = getEffects()).length; for (int j = 0; j < i; j++)
/*     */     {
/*     */       PointEffect localPointEffect;
/*  90 */       if ((
/*  90 */         localPointEffect = arrayOfPointEffect[j])
/*  90 */         .getEffectId() == paramInt) {
/*  91 */         return localPointEffect;
/*     */       }
/*     */     }
/*  94 */     throw new IllegalArgumentException("Effect Id " + paramInt + " not found in " + Arrays.toString(getEffects()));
/*     */   }
/*     */ 
/*     */   public PointEffect[] getEffects()
/*     */   {
/* 100 */     if (this.flagRedistribute) {
/* 101 */       distributePoints();
/* 102 */       this.flagRedistribute = false;
/*     */     }
/* 104 */     return this.effects;
/*     */   }
/*     */   public Ad getEffectTagStruct() {
/* 107 */     Ad[] arrayOfAd = new Ad[this.effects.length + 1];
/* 108 */     for (int i = 0; i < this.effects.length; i++) {
/* 109 */       arrayOfAd[i] = getEffects()[i].toTagStructure();
/*     */     }
/* 111 */     arrayOfAd[this.effects.length] = new Ad(Af.a, null, null);
/*     */ 
/* 113 */     return new Ad(Af.n, "EffectStruct", arrayOfAd);
/*     */   }
/*     */ 
/*     */   public void getFromEffectTagStruct(Ad paramAd) {
/* 117 */     paramAd = (Ad[])paramAd.a();
/* 118 */     for (int i = 0; i < this.effects.length; i++) {
/* 119 */       this.effects[i].fromTagStructure(paramAd[i]);
/*     */     }
/* 121 */     distributePoints();
/*     */   }
/*     */   public int getMaxPoints() {
/* 124 */     return this.maxPoints;
/*     */   }
/*     */ 
/*     */   private int getPointValue(long paramLong) {
/* 128 */     return 4;
/*     */   }
/*     */ 
/*     */   public String getUniqueIdentifier()
/*     */   {
/* 135 */     return null;
/*     */   }
/*     */ 
/*     */   public void initialize(short paramShort, ElementCollectionManager paramElementCollectionManager, SegmentController paramSegmentController)
/*     */   {
/* 143 */     super.initialize(paramShort, paramElementCollectionManager, paramSegmentController);
/*     */ 
/* 145 */     this.effects = initializeEffects();
/* 146 */     initializeDistribution();
/*     */   }
/*     */   protected void initializeDistribution() {
/* 149 */     float f = 1.0F / this.effects.length;
/* 150 */     for (int i = 0; i < this.effects.length; i++)
/* 151 */       this.effects[i].setDistribution((int)(f * 100.0F));
/*     */   }
/*     */ 
/*     */   protected abstract PointEffect[] initializeEffects();
/*     */ 
/*     */   public boolean isVolatile()
/*     */   {
/* 160 */     return false;
/*     */   }
/*     */   protected void onAdd(long paramLong) {
/* 163 */     setMaxPoints(getMaxPoints() + getPointValue(paramLong));
/*     */   }
/*     */ 
/*     */   public boolean addElement(long paramLong)
/*     */   {
/*     */     boolean bool;
/* 171 */     if ((
/* 171 */       bool = super.addElement(paramLong)))
/*     */     {
/* 172 */       onAdd(paramLong);
/*     */     }
/* 174 */     return bool;
/*     */   }
/*     */ 
/*     */   public void onChangeFinished()
/*     */   {
/* 181 */     super.onChangeFinished();
/* 182 */     distributePoints();
/*     */   }
/*     */ 
/*     */   protected void onRemove(q paramq)
/*     */   {
/* 189 */     super.onRemove(paramq);
/* 190 */     setMaxPoints(getMaxPoints() - getPointValue(getIndex(paramq)));
/* 191 */     System.err.println("REMOVING four points " + this + " " + getMaxPoints());
/*     */   }
/*     */   public void receiveDistChange(jE paramjE) {
/* 194 */     if (getController().getState().getId() != paramjE.c)
/*     */     {
/*     */       PointEffect localPointEffect;
/* 195 */       (
/* 196 */         localPointEffect = getEffectById(paramjE.a))
/* 196 */         .setDistribution(paramjE.b);
/*     */ 
/* 198 */       distributePoints();
/*     */ 
/* 200 */       getAvailableDist();
/*     */ 
/* 202 */       if (getController().isOnServer())
/* 203 */         sendDistChange(localPointEffect);
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean remove(q paramq)
/*     */   {
/* 215 */     return super.remove(paramq);
/*     */   }
/*     */ 
/*     */   public void resetPointsSpent()
/*     */   {
/* 218 */     PointEffect[] arrayOfPointEffect;
/* 218 */     int i = (arrayOfPointEffect = this.effects).length; for (int j = 0; j < i; j++) arrayOfPointEffect[j]
/* 219 */         .reset();
/*     */   }
/*     */ 
/*     */   public void sendAllDistChange()
/*     */   {
/* 224 */     for (PointEffect localPointEffect : this.effects)
/* 225 */       sendDistChange(localPointEffect);
/*     */   }
/*     */ 
/*     */   public void sendDistChange(PointEffect paramPointEffect) {
/* 229 */     if (getId() == null) {
/* 230 */       if (!$assertionsDisabled) throw new AssertionError();
/* 231 */       throw new NullPointerException("PointDistribution] Could Not load ID POS");
/*     */     }
/*     */ 
/* 234 */     RemoteIntArrayBuffer localRemoteIntArrayBuffer = ((DistributionInterface)getController().getNetworkObject()).getDistributionModification();
/* 235 */     RemoteIntArray localRemoteIntArray = new RemoteIntArray(9, getController().getNetworkObject());
/* 236 */     q localq1 = getId().a(new q());
/* 237 */     q localq2 = ((ControlBlockElementCollectionManager)this.col).getControllerPos();
/*     */ 
/* 239 */     localRemoteIntArray.set(0, localq2.a);
/* 240 */     localRemoteIntArray.set(1, localq2.b);
/* 241 */     localRemoteIntArray.set(2, localq2.c);
/*     */ 
/* 243 */     localRemoteIntArray.set(3, localq1.a);
/* 244 */     localRemoteIntArray.set(4, localq1.b);
/* 245 */     localRemoteIntArray.set(5, localq1.c);
/*     */ 
/* 247 */     localRemoteIntArray.set(6, paramPointEffect.getEffectId());
/* 248 */     localRemoteIntArray.set(7, paramPointEffect.getDistribution());
/* 249 */     localRemoteIntArray.set(8, getController().getState().getId());
/* 250 */     localRemoteIntArrayBuffer.add(localRemoteIntArray);
/*     */   }
/*     */ 
/*     */   public void setMaxPoints(int paramInt)
/*     */   {
/* 256 */     int i = this.maxPoints;
/* 257 */     this.maxPoints = paramInt;
/* 258 */     if (i != paramInt)
/* 259 */       this.flagRedistribute = true;
/*     */   }
/*     */ 
/*     */   public Ad toTagStructure()
/*     */   {
/* 272 */     Object localObject1 = getId().a(new q());
/*     */ 
/* 274 */     Object localObject2 = ((ControlBlockElementCollectionManager)this.col).getControllerPos();
/*     */ 
/* 276 */     localObject2 = new Ad(Af.k, "controller", localObject2);
/* 277 */     localObject1 = new Ad(Af.k, "idPos", localObject1);
/*     */ 
/* 279 */     return new Ad(Af.n, "PointDist", new Ad[] { localObject2, localObject1, getEffectTagStruct(), new Ad(Af.a, null, null) });
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.PointDistributionUnit
 * JD-Core Version:    0.6.2
 */