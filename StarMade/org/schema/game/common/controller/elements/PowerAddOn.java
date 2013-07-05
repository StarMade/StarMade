/*     */ package org.schema.game.common.controller.elements;
/*     */ 
/*     */ import Ad;
/*     */ import Af;
/*     */ import jv;
/*     */ import ld;
/*     */ import le;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.controller.elements.powercap.PowerCapacityCollectionManager;
/*     */ import org.schema.game.common.data.element.ElementDocking;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import xq;
/*     */ 
/*     */ public class PowerAddOn
/*     */ {
/*     */   private static final long RECOVERY_TIME = 1000L;
/*     */   private static final int FIXED_BASE_CAPACITY = 20000;
/*     */   private final PowerManagerInterface powerManager;
/*     */   private double power;
/*     */   private double maxPower;
/*     */   private double recharge;
/*     */   private long recovery;
/*     */   private final SegmentController segmentController;
/*     */   private double initialPower;
/*     */ 
/*     */   public PowerAddOn(PowerManagerInterface paramPowerManagerInterface, SegmentController paramSegmentController)
/*     */   {
/*  21 */     this.powerManager = paramPowerManagerInterface;
/*  22 */     this.segmentController = paramSegmentController;
/*     */   }
/*     */   public int getBaseCapacity() {
/*  25 */     return 20000;
/*     */   }
/*     */ 
/*     */   public void incPower(double paramDouble) {
/*  29 */     double d = getBaseCapacity() + this.maxPower;
/*  30 */     this.power = Math.min(d, this.power + paramDouble);
/*     */   }
/*     */   public void update(xq paramxq) {
/*  33 */     double d1 = getBaseCapacity() + this.maxPower;
/*  34 */     if (this.initialPower > 0.0D)
/*     */     {
/*     */       double d2;
/*  38 */       if ((
/*  38 */         d2 = d1 - this.power) > 
/*  38 */         0.0D) {
/*  39 */         d2 = Math.min(d2, this.initialPower);
/*  40 */         this.initialPower -= d2;
/*  41 */         this.power += d2;
/*     */       }
/*     */     } else {
/*  44 */       this.power = Math.min(d1, this.power + paramxq.a() * this.recharge);
/*     */     }
/*     */ 
/*  48 */     if (System.currentTimeMillis() - this.recovery > 1000L)
/*  49 */       this.recovery = -1L;
/*     */   }
/*     */ 
/*     */   private boolean sufficientPower(double paramDouble) {
/*  53 */     return paramDouble <= getPower();
/*     */   }
/*     */   public boolean isInRecovery() {
/*  56 */     return this.recovery >= 0L;
/*     */   }
/*     */   public double consumePower(double paramDouble, xq paramxq) {
/*  59 */     if (consumePowerInstantly(paramDouble * paramxq.a())) {
/*  60 */       return paramDouble * paramxq.a();
/*     */     }
/*  62 */     return 0.0D;
/*     */   }
/*     */ 
/*     */   public boolean consumePowerInstantly(double paramDouble) {
/*  66 */     if ((!this.segmentController.getDockingController().b()) && (!sufficientPower(paramDouble))) {
/*  67 */       this.recovery = System.currentTimeMillis();
/*  68 */       return false;
/*     */     }
/*     */ 
/*  71 */     if (this.segmentController.getDockingController().b())
/*     */     {
/*     */       Object localObject;
/*  73 */       if ((((
/*  73 */         localObject = this.segmentController.getDockingController().a().to.a().a()) instanceof ld)) && 
/*  73 */         ((((ld)localObject).a() instanceof PowerManagerInterface))) {
/*  74 */         localObject = (PowerManagerInterface)((ld)localObject).a();
/*     */ 
/*  77 */         if (paramDouble > this.power) {
/*  78 */           ((PowerManagerInterface)localObject).getPowerAddOn().consumePowerInstantly(paramDouble);
/*     */ 
/*  86 */           break label157;
/*     */         }
/*     */       }
/*  88 */       this.power = Math.max(0.0D, this.power - paramDouble);
/*     */     }
/*     */     else {
/*  91 */       this.power = Math.max(0.0D, this.power - paramDouble);
/*     */     }
/*     */ 
/*  95 */     label157: return true;
/*     */   }
/*     */   public void setRecharge(double paramDouble) {
/*  98 */     this.recharge = paramDouble;
/*     */   }
/*     */ 
/*     */   public double getPower() {
/* 102 */     return this.power;
/*     */   }
/*     */ 
/*     */   public double getMaxPower()
/*     */   {
/*     */     double d;
/* 107 */     if (this.segmentController.getDockingController().a() != null)
/*     */     {
/*     */       Object localObject;
/* 109 */       if ((((
/* 109 */         localObject = this.segmentController.getDockingController().a().to.a().a()) instanceof ld)) && 
/* 109 */         ((((ld)localObject).a() instanceof PowerManagerInterface))) {
/* 110 */         localObject = (PowerManagerInterface)((ld)localObject).a();
/* 111 */         d = this.maxPower + ((PowerManagerInterface)localObject).getPowerCapacityManager().getMaxPower() + getBaseCapacity();
/*     */       }
/*     */       else {
/* 114 */         d = this.maxPower;
/*     */       }
/*     */     } else {
/* 117 */       d = this.maxPower;
/*     */     }
/*     */ 
/* 121 */     return getBaseCapacity() + d;
/*     */   }
/*     */ 
/*     */   public double getRecharge() {
/* 125 */     return this.recharge;
/*     */   }
/*     */ 
/*     */   public void setMaxPower(double paramDouble) {
/* 129 */     this.maxPower = paramDouble;
/*     */   }
/*     */   public Ad toTagStructure() {
/* 132 */     return new Ad(Af.g, "pw", Double.valueOf(this.power));
/*     */   }
/*     */   public void fromTagStructure(Ad paramAd) {
/* 135 */     this.initialPower = ((Double)paramAd.a()).doubleValue();
/*     */   }
/*     */ 
/*     */   public double getInitialPower()
/*     */   {
/* 141 */     return this.initialPower;
/*     */   }
/*     */ 
/*     */   public void setInitialPower(double paramDouble)
/*     */   {
/* 147 */     this.initialPower = paramDouble;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.PowerAddOn
 * JD-Core Version:    0.6.2
 */