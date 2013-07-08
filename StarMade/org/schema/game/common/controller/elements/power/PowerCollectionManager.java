/*   1:    */package org.schema.game.common.controller.elements.power;
/*   2:    */
/*   3:    */import java.util.List;
/*   4:    */import ld;
/*   5:    */import org.schema.common.FastMath;
/*   6:    */import org.schema.game.common.controller.SegmentController;
/*   7:    */import org.schema.game.common.controller.elements.ElementCollectionManager;
/*   8:    */import org.schema.game.common.controller.elements.PowerAddOn;
/*   9:    */import org.schema.game.common.controller.elements.PowerManagerInterface;
/*  10:    */
/*  11:    */public class PowerCollectionManager
/*  12:    */  extends ElementCollectionManager
/*  13:    */{
/*  14:    */  private double recharge;
/*  15:    */  private static final double divFactor = 0.333D;
/*  16:    */  private static final double max = 1000000.0D;
/*  17:    */  private static final double growth = 1.000696D;
/*  18:    */  private static final double constantGrowth = 25.0D;
/*  19:    */  
/*  20:    */  public PowerCollectionManager(SegmentController paramSegmentController)
/*  21:    */  {
/*  22: 22 */    super((short)2, paramSegmentController);
/*  23:    */  }
/*  24:    */  
/*  35:    */  public int getMargin()
/*  36:    */  {
/*  37: 37 */    return 0;
/*  38:    */  }
/*  39:    */  
/*  48:    */  public double getRecharge()
/*  49:    */  {
/*  50: 50 */    return this.recharge;
/*  51:    */  }
/*  52:    */  
/*  53:    */  protected Class getType()
/*  54:    */  {
/*  55: 55 */    return PowerUnit.class;
/*  56:    */  }
/*  57:    */  
/*  62:    */  protected void onChangedCollection()
/*  63:    */  {
/*  64: 64 */    refreshMaxPower();
/*  65:    */  }
/*  66:    */  
/*  73:    */  private void refreshMaxPower()
/*  74:    */  {
/*  75: 75 */    setRecharge(0.0D);
/*  76: 76 */    int i = 0;
/*  77: 77 */    for (int j = 0; j < getCollection().size(); j++) {
/*  78: 78 */      PowerUnit localPowerUnit = (PowerUnit)getCollection().get(j);
/*  79: 79 */      i += localPowerUnit.size();
/*  80: 80 */      localPowerUnit.refreshPowerCapabilities();
/*  81: 81 */      setRecharge(getRecharge() + localPowerUnit.getRecharge());
/*  82:    */    }
/*  83:    */    
/*  84: 84 */    this.recharge = Math.max(1.0D, FastMath.b(this.recharge * 0.333D));
/*  85: 85 */    this.recharge += i * 25.0D;
/*  86:    */    
/*  87: 87 */    ((PowerManagerInterface)((ld)getSegmentController()).a()).getPowerAddOn().setRecharge(getRecharge());
/*  88:    */  }
/*  89:    */  
/*  93:    */  public void setRecharge(double paramDouble)
/*  94:    */  {
/*  95: 95 */    this.recharge = paramDouble;
/*  96:    */  }
/*  97:    */  
/* 101:    */  public boolean needsUpdate()
/* 102:    */  {
/* 103:103 */    return false;
/* 104:    */  }
/* 105:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.power.PowerCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */