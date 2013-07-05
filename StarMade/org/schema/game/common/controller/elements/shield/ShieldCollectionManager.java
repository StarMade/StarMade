/*     */ package org.schema.game.common.controller.elements.shield;
/*     */ 
/*     */ import ct;
/*     */ import java.util.List;
/*     */ import ld;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.controller.elements.ElementCollectionManager;
/*     */ import org.schema.game.common.controller.elements.HittableInterface;
/*     */ import org.schema.game.common.controller.elements.NTReceiveInterface;
/*     */ import org.schema.game.common.controller.elements.PowerAddOn;
/*     */ import org.schema.game.common.controller.elements.ShieldContainerInterface;
/*     */ import org.schema.schine.network.objects.NetworkObject;
/*     */ import q;
/*     */ import x;
/*     */ import xq;
/*     */ 
/*     */ public class ShieldCollectionManager extends ElementCollectionManager
/*     */   implements HittableInterface, NTReceiveInterface
/*     */ {
/*     */   static final int SHIELD_RECHARGE_DELAY = 1000;
/*     */   static final int SHIELD_RECHARGE_AFTER_HIT = 10000;
/*     */   public static final int MAX_SHIELDS_UNIT = 200;
/*     */   public static final int RECHARGE_RATE = 2;
/*  25 */   private int shieldMargin = 3;
/*     */   private double shields;
/*  31 */   q posTmp = new q();
/*     */   private long shieldCapacityHP;
/*     */   private long shieldRechargeRate;
/*     */   private float accPower;
/*     */   private double recovery;
/*  36 */   private int RECOVERY_TIME_IN_SEC = 10;
/*  37 */   private float POWER_CONSUME_MULT = 1.0F;
/*     */   private double initialShields;
/*     */ 
/*     */   public ShieldCollectionManager(SegmentController paramSegmentController)
/*     */   {
/*  41 */     super((short)3, paramSegmentController);
/*     */   }
/*     */ 
/*     */   public int getMargin() {
/*  45 */     return this.shieldMargin;
/*     */   }
/*     */ 
/*     */   protected Class getType() {
/*  49 */     return ShieldUnit.class;
/*     */   }
/*     */ 
/*     */   protected void onChangedCollection() {
/*  53 */     updateCapabilities();
/*     */   }
/*     */ 
/*     */   private void updateCapabilities() {
/*  57 */     this.shieldCapacityHP = 0L;
/*  58 */     this.shieldRechargeRate = 0L;
/*     */ 
/*  60 */     for (ShieldUnit localShieldUnit : getCollection()) {
/*  61 */       this.shieldCapacityHP += localShieldUnit.size() * 200;
/*  62 */       this.shieldRechargeRate += (localShieldUnit.size() << 1);
/*     */     }
/*  64 */     this.shieldCapacityHP = (()(Math.pow(this.shieldCapacityHP, 0.5D) * 64.0D));
/*     */ 
/*  66 */     this.shields = Math.min(this.shields, this.shieldCapacityHP);
/*     */   }
/*     */ 
/*     */   public void onHit(int paramInt)
/*     */   {
/*  71 */     this.shields = Math.max(0.0D, this.shields - paramInt);
/*  72 */     if (this.shields == 0.0D) {
/*  73 */       this.recovery = this.RECOVERY_TIME_IN_SEC;
/*  74 */       if (getSegmentController().isClientOwnObject())
/*  75 */         ((ct)getSegmentController().getState()).a().b("   !!!WARNING!!!\n\nShields DOWN\n(" + this.RECOVERY_TIME_IN_SEC + " sec recovery)");
/*     */     }
/*     */   }
/*     */ 
/*     */   public void update(xq paramxq)
/*     */   {
/*  84 */     if (getCollection().isEmpty()) {
/*  85 */       return;
/*     */     }
/*  87 */     if (this.shields >= this.shieldCapacityHP) {
/*  88 */       return;
/*     */     }
/*  90 */     if (getInitialShields() > 0.0D) {
/*  91 */       this.shields = getInitialShields();
/*  92 */       setInitialShields(0.0D);
/*     */     }
/*     */ 
/*  95 */     if (this.recovery > 0.0D) {
/*  96 */       this.recovery -= paramxq.a(); return;
/*     */     }
/*  98 */     PowerAddOn localPowerAddOn = ((ShieldContainerInterface)((ld)getSegmentController()).a()).getPowerAddOn();
/*  99 */     this.accPower += paramxq.a();
/*     */ 
/* 101 */     if (this.accPower > 1.0F) {
/* 102 */       if (localPowerAddOn.getPower() < this.shieldRechargeRate) {
/* 103 */         double d = localPowerAddOn.getPower();
/* 104 */         localPowerAddOn.consumePowerInstantly(d);
/* 105 */         this.shields = ((int)Math.min(this.shieldCapacityHP, this.shields + d));
/*     */       }
/* 107 */       else if (localPowerAddOn.consumePowerInstantly(this.shieldRechargeRate)) {
/* 108 */         this.shields = ((int)Math.min(this.shieldCapacityHP, this.shields + this.shieldRechargeRate));
/*     */       }
/*     */ 
/* 111 */       this.accPower -= 1.0F;
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean needsUpdate()
/*     */   {
/* 118 */     return true;
/*     */   }
/*     */ 
/*     */   public void updateFromNT(NetworkObject paramNetworkObject)
/*     */   {
/*     */   }
/*     */ 
/*     */   public double getShields()
/*     */   {
/* 129 */     return this.shields;
/*     */   }
/*     */ 
/*     */   public long getShieldCapabilityHP()
/*     */   {
/* 135 */     return this.shieldCapacityHP;
/*     */   }
/*     */ 
/*     */   public long getShieldRechargeRate()
/*     */   {
/* 142 */     return this.shieldRechargeRate;
/*     */   }
/*     */   public void setInitialShields(double paramDouble) {
/* 145 */     this.initialShields = paramDouble;
/*     */   }
/*     */ 
/*     */   public double getInitialShields()
/*     */   {
/* 151 */     return this.initialShields;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.shield.ShieldCollectionManager
 * JD-Core Version:    0.6.2
 */