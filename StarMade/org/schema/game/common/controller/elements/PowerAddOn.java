/*   1:    */package org.schema.game.common.controller.elements;
/*   2:    */
/*   3:    */import Aj;
/*   4:    */import le;
/*   5:    */import org.schema.game.common.controller.elements.powercap.PowerCapacityCollectionManager;
/*   6:    */
/*   7:    */public class PowerAddOn
/*   8:    */{
/*   9:    */  private static final long RECOVERY_TIME = 1000L;
/*  10:    */  private static final int FIXED_BASE_CAPACITY = 20000;
/*  11:    */  private final PowerManagerInterface powerManager;
/*  12:    */  private double power;
/*  13:    */  private double maxPower;
/*  14:    */  private double recharge;
/*  15:    */  private long recovery;
/*  16:    */  private final org.schema.game.common.controller.SegmentController segmentController;
/*  17:    */  private double initialPower;
/*  18:    */  
/*  19:    */  public PowerAddOn(PowerManagerInterface paramPowerManagerInterface, org.schema.game.common.controller.SegmentController paramSegmentController)
/*  20:    */  {
/*  21: 21 */    this.powerManager = paramPowerManagerInterface;
/*  22: 22 */    this.segmentController = paramSegmentController;
/*  23:    */  }
/*  24:    */  
/*  25: 25 */  public int getBaseCapacity() { return 20000; }
/*  26:    */  
/*  27:    */  public void incPower(double paramDouble)
/*  28:    */  {
/*  29: 29 */    double d = getBaseCapacity() + this.maxPower;
/*  30: 30 */    this.power = Math.min(d, this.power + paramDouble);
/*  31:    */  }
/*  32:    */  
/*  33: 33 */  public void update(xq paramxq) { double d1 = getBaseCapacity() + this.maxPower;
/*  34: 34 */    if (this.initialPower > 0.0D)
/*  35:    */    {
/*  36:    */      double d2;
/*  37:    */      
/*  38: 38 */      if ((d2 = d1 - this.power) > 0.0D) {
/*  39: 39 */        d2 = Math.min(d2, this.initialPower);
/*  40: 40 */        this.initialPower -= d2;
/*  41: 41 */        this.power += d2;
/*  42:    */      }
/*  43:    */    } else {
/*  44: 44 */      this.power = Math.min(d1, this.power + paramxq.a() * this.recharge);
/*  45:    */    }
/*  46:    */    
/*  48: 48 */    if (System.currentTimeMillis() - this.recovery > 1000L)
/*  49: 49 */      this.recovery = -1L;
/*  50:    */  }
/*  51:    */  
/*  52:    */  private boolean sufficientPower(double paramDouble) {
/*  53: 53 */    return paramDouble <= getPower();
/*  54:    */  }
/*  55:    */  
/*  56: 56 */  public boolean isInRecovery() { return this.recovery >= 0L; }
/*  57:    */  
/*  58:    */  public double consumePower(double paramDouble, xq paramxq) {
/*  59: 59 */    if (consumePowerInstantly(paramDouble * paramxq.a())) {
/*  60: 60 */      return paramDouble * paramxq.a();
/*  61:    */    }
/*  62: 62 */    return 0.0D;
/*  63:    */  }
/*  64:    */  
/*  65:    */  public boolean consumePowerInstantly(double paramDouble) {
/*  66: 66 */    if ((!this.segmentController.getDockingController().b()) && (!sufficientPower(paramDouble))) {
/*  67: 67 */      this.recovery = System.currentTimeMillis();
/*  68: 68 */      return false;
/*  69:    */    }
/*  70:    */    
/*  71: 71 */    if (this.segmentController.getDockingController().b()) {
/*  72:    */      Object localObject;
/*  73: 73 */      if ((((localObject = this.segmentController.getDockingController().a().to.a().a()) instanceof ld)) && ((((ld)localObject).a() instanceof PowerManagerInterface))) {
/*  74: 74 */        localObject = (PowerManagerInterface)((ld)localObject).a();
/*  75:    */        
/*  77: 77 */        if (paramDouble > this.power) {
/*  78: 78 */          ((PowerManagerInterface)localObject).getPowerAddOn().consumePowerInstantly(paramDouble);
/*  79:    */          
/*  82:    */          break label157;
/*  83:    */        }
/*  84:    */      }
/*  85:    */      
/*  88: 88 */      this.power = Math.max(0.0D, this.power - paramDouble);
/*  89:    */    }
/*  90:    */    else {
/*  91: 91 */      this.power = Math.max(0.0D, this.power - paramDouble);
/*  92:    */    }
/*  93:    */    
/*  94:    */    label157:
/*  95: 95 */    return true;
/*  96:    */  }
/*  97:    */  
/*  98: 98 */  public void setRecharge(double paramDouble) { this.recharge = paramDouble; }
/*  99:    */  
/* 100:    */  public double getPower()
/* 101:    */  {
/* 102:102 */    return this.power;
/* 103:    */  }
/* 104:    */  
/* 105:    */  public double getMaxPower() {
/* 106:    */    double d;
/* 107:107 */    if (this.segmentController.getDockingController().a() != null) {
/* 108:    */      Object localObject;
/* 109:109 */      if ((((localObject = this.segmentController.getDockingController().a().to.a().a()) instanceof ld)) && ((((ld)localObject).a() instanceof PowerManagerInterface))) {
/* 110:110 */        localObject = (PowerManagerInterface)((ld)localObject).a();
/* 111:111 */        d = this.maxPower + ((PowerManagerInterface)localObject).getPowerCapacityManager().getMaxPower() + getBaseCapacity();
/* 112:    */      }
/* 113:    */      else {
/* 114:114 */        d = this.maxPower;
/* 115:    */      }
/* 116:    */    } else {
/* 117:117 */      d = this.maxPower;
/* 118:    */    }
/* 119:    */    
/* 121:121 */    return getBaseCapacity() + d;
/* 122:    */  }
/* 123:    */  
/* 124:    */  public double getRecharge() {
/* 125:125 */    return this.recharge;
/* 126:    */  }
/* 127:    */  
/* 128:    */  public void setMaxPower(double paramDouble) {
/* 129:129 */    this.maxPower = paramDouble;
/* 130:    */  }
/* 131:    */  
/* 132:132 */  public Ah toTagStructure() { return new Ah(Aj.g, "pw", Double.valueOf(this.power)); }
/* 133:    */  
/* 134:    */  public void fromTagStructure(Ah paramAh) {
/* 135:135 */    this.initialPower = ((Double)paramAh.a()).doubleValue();
/* 136:    */  }
/* 137:    */  
/* 139:    */  public double getInitialPower()
/* 140:    */  {
/* 141:141 */    return this.initialPower;
/* 142:    */  }
/* 143:    */  
/* 145:    */  public void setInitialPower(double paramDouble)
/* 146:    */  {
/* 147:147 */    this.initialPower = paramDouble;
/* 148:    */  }
/* 149:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.PowerAddOn
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */