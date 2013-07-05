/*     */ package org.schema.game.common.controller.elements.power;
/*     */ 
/*     */ import java.util.List;
/*     */ import ld;
/*     */ import org.schema.common.FastMath;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.controller.elements.ElementCollectionManager;
/*     */ import org.schema.game.common.controller.elements.PowerAddOn;
/*     */ import org.schema.game.common.controller.elements.PowerManagerInterface;
/*     */ 
/*     */ public class PowerCollectionManager extends ElementCollectionManager
/*     */ {
/*     */   private double recharge;
/*     */   private static final double divFactor = 0.333D;
/*     */   private static final double max = 1000000.0D;
/*     */   private static final double growth = 1.000696D;
/*     */   private static final double constantGrowth = 25.0D;
/*     */ 
/*     */   public PowerCollectionManager(SegmentController paramSegmentController)
/*     */   {
/*  22 */     super((short)2, paramSegmentController);
/*     */   }
/*     */ 
/*     */   public int getMargin()
/*     */   {
/*  37 */     return 0;
/*     */   }
/*     */ 
/*     */   public double getRecharge()
/*     */   {
/*  50 */     return this.recharge;
/*     */   }
/*     */ 
/*     */   protected Class getType()
/*     */   {
/*  55 */     return PowerUnit.class;
/*     */   }
/*     */ 
/*     */   protected void onChangedCollection()
/*     */   {
/*  64 */     refreshMaxPower();
/*     */   }
/*     */ 
/*     */   private void refreshMaxPower()
/*     */   {
/*  75 */     setRecharge(0.0D);
/*  76 */     int i = 0;
/*  77 */     for (int j = 0; j < getCollection().size(); j++) {
/*  78 */       PowerUnit localPowerUnit = (PowerUnit)getCollection().get(j);
/*  79 */       i += localPowerUnit.size();
/*  80 */       localPowerUnit.refreshPowerCapabilities();
/*  81 */       setRecharge(getRecharge() + localPowerUnit.getRecharge());
/*     */     }
/*     */ 
/*  84 */     this.recharge = Math.max(1.0D, FastMath.b(this.recharge * 0.333D));
/*  85 */     this.recharge += i * 25.0D;
/*     */ 
/*  87 */     ((PowerManagerInterface)((ld)getSegmentController()).a()).getPowerAddOn().setRecharge(getRecharge());
/*     */   }
/*     */ 
/*     */   public void setRecharge(double paramDouble)
/*     */   {
/*  95 */     this.recharge = paramDouble;
/*     */   }
/*     */ 
/*     */   public boolean needsUpdate()
/*     */   {
/* 103 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.power.PowerCollectionManager
 * JD-Core Version:    0.6.2
 */