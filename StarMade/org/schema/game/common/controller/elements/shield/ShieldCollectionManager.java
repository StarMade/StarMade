/*   1:    */package org.schema.game.common.controller.elements.shield;
/*   2:    */
/*   3:    */import ct;
/*   4:    */import java.util.List;
/*   5:    */import ld;
/*   6:    */import org.schema.game.common.controller.SegmentController;
/*   7:    */import org.schema.game.common.controller.elements.ElementCollectionManager;
/*   8:    */import org.schema.game.common.controller.elements.HittableInterface;
/*   9:    */import org.schema.game.common.controller.elements.NTReceiveInterface;
/*  10:    */import org.schema.game.common.controller.elements.PowerAddOn;
/*  11:    */import org.schema.game.common.controller.elements.ShieldContainerInterface;
/*  12:    */import org.schema.schine.network.objects.NetworkObject;
/*  13:    */import q;
/*  14:    */import x;
/*  15:    */import xq;
/*  16:    */
/*  17:    */public class ShieldCollectionManager
/*  18:    */  extends ElementCollectionManager
/*  19:    */  implements HittableInterface, NTReceiveInterface
/*  20:    */{
/*  21:    */  static final int SHIELD_RECHARGE_DELAY = 1000;
/*  22:    */  static final int SHIELD_RECHARGE_AFTER_HIT = 10000;
/*  23:    */  public static final int MAX_SHIELDS_UNIT = 200;
/*  24:    */  public static final int RECHARGE_RATE = 2;
/*  25: 25 */  private int shieldMargin = 3;
/*  26:    */  
/*  28:    */  private double shields;
/*  29:    */  
/*  31: 31 */  q posTmp = new q();
/*  32:    */  private long shieldCapacityHP;
/*  33:    */  private long shieldRechargeRate;
/*  34:    */  private float accPower;
/*  35:    */  private double recovery;
/*  36: 36 */  private int RECOVERY_TIME_IN_SEC = 10;
/*  37: 37 */  private float POWER_CONSUME_MULT = 1.0F;
/*  38:    */  private double initialShields;
/*  39:    */  
/*  40:    */  public ShieldCollectionManager(SegmentController paramSegmentController) {
/*  41: 41 */    super((short)3, paramSegmentController);
/*  42:    */  }
/*  43:    */  
/*  44:    */  public int getMargin() {
/*  45: 45 */    return this.shieldMargin;
/*  46:    */  }
/*  47:    */  
/*  48:    */  protected Class getType() {
/*  49: 49 */    return ShieldUnit.class;
/*  50:    */  }
/*  51:    */  
/*  52:    */  protected void onChangedCollection() {
/*  53: 53 */    updateCapabilities();
/*  54:    */  }
/*  55:    */  
/*  56:    */  private void updateCapabilities() {
/*  57: 57 */    this.shieldCapacityHP = 0L;
/*  58: 58 */    this.shieldRechargeRate = 0L;
/*  59:    */    
/*  60: 60 */    for (ShieldUnit localShieldUnit : getCollection()) {
/*  61: 61 */      this.shieldCapacityHP += localShieldUnit.size() * 200;
/*  62: 62 */      this.shieldRechargeRate += (localShieldUnit.size() << 1);
/*  63:    */    }
/*  64: 64 */    this.shieldCapacityHP = ((Math.pow(this.shieldCapacityHP, 0.5D) * 64.0D));
/*  65:    */    
/*  66: 66 */    this.shields = Math.min(this.shields, this.shieldCapacityHP);
/*  67:    */  }
/*  68:    */  
/*  69:    */  public void onHit(int paramInt)
/*  70:    */  {
/*  71: 71 */    this.shields = Math.max(0.0D, this.shields - paramInt);
/*  72: 72 */    if (this.shields == 0.0D) {
/*  73: 73 */      this.recovery = this.RECOVERY_TIME_IN_SEC;
/*  74: 74 */      if (getSegmentController().isClientOwnObject()) {
/*  75: 75 */        ((ct)getSegmentController().getState()).a().b("   !!!WARNING!!!\n\nShields DOWN\n(" + this.RECOVERY_TIME_IN_SEC + " sec recovery)");
/*  76:    */      }
/*  77:    */    }
/*  78:    */  }
/*  79:    */  
/*  82:    */  public void update(xq paramxq)
/*  83:    */  {
/*  84: 84 */    if (getCollection().isEmpty()) {
/*  85: 85 */      return;
/*  86:    */    }
/*  87: 87 */    if (this.shields >= this.shieldCapacityHP) {
/*  88: 88 */      return;
/*  89:    */    }
/*  90: 90 */    if (getInitialShields() > 0.0D) {
/*  91: 91 */      this.shields = getInitialShields();
/*  92: 92 */      setInitialShields(0.0D);
/*  93:    */    }
/*  94:    */    
/*  95: 95 */    if (this.recovery > 0.0D) {
/*  96: 96 */      this.recovery -= paramxq.a();return;
/*  97:    */    }
/*  98: 98 */    PowerAddOn localPowerAddOn = ((ShieldContainerInterface)((ld)getSegmentController()).a()).getPowerAddOn();
/*  99: 99 */    this.accPower += paramxq.a();
/* 100:    */    
/* 101:101 */    if (this.accPower > 1.0F) {
/* 102:102 */      if (localPowerAddOn.getPower() < this.shieldRechargeRate) {
/* 103:103 */        double d = localPowerAddOn.getPower();
/* 104:104 */        localPowerAddOn.consumePowerInstantly(d);
/* 105:105 */        this.shields = ((int)Math.min(this.shieldCapacityHP, this.shields + d));
/* 106:    */      }
/* 107:107 */      else if (localPowerAddOn.consumePowerInstantly(this.shieldRechargeRate)) {
/* 108:108 */        this.shields = ((int)Math.min(this.shieldCapacityHP, this.shields + this.shieldRechargeRate));
/* 109:    */      }
/* 110:    */      
/* 111:111 */      this.accPower -= 1.0F;
/* 112:    */    }
/* 113:    */  }
/* 114:    */  
/* 116:    */  public boolean needsUpdate()
/* 117:    */  {
/* 118:118 */    return true;
/* 119:    */  }
/* 120:    */  
/* 123:    */  public void updateFromNT(NetworkObject paramNetworkObject) {}
/* 124:    */  
/* 127:    */  public double getShields()
/* 128:    */  {
/* 129:129 */    return this.shields;
/* 130:    */  }
/* 131:    */  
/* 133:    */  public long getShieldCapabilityHP()
/* 134:    */  {
/* 135:135 */    return this.shieldCapacityHP;
/* 136:    */  }
/* 137:    */  
/* 140:    */  public long getShieldRechargeRate()
/* 141:    */  {
/* 142:142 */    return this.shieldRechargeRate;
/* 143:    */  }
/* 144:    */  
/* 145:145 */  public void setInitialShields(double paramDouble) { this.initialShields = paramDouble; }
/* 146:    */  
/* 149:    */  public double getInitialShields()
/* 150:    */  {
/* 151:151 */    return this.initialShields;
/* 152:    */  }
/* 153:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.shield.ShieldCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */