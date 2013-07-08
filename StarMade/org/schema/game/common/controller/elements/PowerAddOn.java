package org.schema.game.common.controller.elements;

import class_69;
import class_707;
import class_79;
import class_796;
import class_798;
import class_941;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.powercap.PowerCapacityCollectionManager;
import org.schema.game.common.data.element.ElementDocking;
import org.schema.game.common.data.world.Segment;

public class PowerAddOn
{
  private static final long RECOVERY_TIME = 1000L;
  private static final int FIXED_BASE_CAPACITY = 20000;
  private final PowerManagerInterface powerManager;
  private double power;
  private double maxPower;
  private double recharge;
  private long recovery;
  private final SegmentController segmentController;
  private double initialPower;
  
  public PowerAddOn(PowerManagerInterface paramPowerManagerInterface, SegmentController paramSegmentController)
  {
    this.powerManager = paramPowerManagerInterface;
    this.segmentController = paramSegmentController;
  }
  
  public int getBaseCapacity()
  {
    return 20000;
  }
  
  public void incPower(double paramDouble)
  {
    double d = getBaseCapacity() + this.maxPower;
    this.power = Math.min(d, this.power + paramDouble);
  }
  
  public void update(class_941 paramclass_941)
  {
    double d1 = getBaseCapacity() + this.maxPower;
    if (this.initialPower > 0.0D)
    {
      double d2;
      if ((d2 = d1 - this.power) > 0.0D)
      {
        d2 = Math.min(d2, this.initialPower);
        this.initialPower -= d2;
        this.power += d2;
      }
    }
    else
    {
      this.power = Math.min(d1, this.power + paramclass_941.a() * this.recharge);
    }
    if (System.currentTimeMillis() - this.recovery > 1000L) {
      this.recovery = -1L;
    }
  }
  
  private boolean sufficientPower(double paramDouble)
  {
    return paramDouble <= getPower();
  }
  
  public boolean isInRecovery()
  {
    return this.recovery >= 0L;
  }
  
  public double consumePower(double paramDouble, class_941 paramclass_941)
  {
    if (consumePowerInstantly(paramDouble * paramclass_941.a())) {
      return paramDouble * paramclass_941.a();
    }
    return 0.0D;
  }
  
  public boolean consumePowerInstantly(double paramDouble)
  {
    if ((!this.segmentController.getDockingController().b3()) && (!sufficientPower(paramDouble)))
    {
      this.recovery = System.currentTimeMillis();
      return false;
    }
    if (this.segmentController.getDockingController().b3())
    {
      Object localObject;
      if ((((localObject = this.segmentController.getDockingController().a4().field_1740.a7().a15()) instanceof class_798)) && ((((class_798)localObject).a() instanceof PowerManagerInterface)))
      {
        localObject = (PowerManagerInterface)((class_798)localObject).a();
        if (paramDouble > this.power)
        {
          ((PowerManagerInterface)localObject).getPowerAddOn().consumePowerInstantly(paramDouble);
          break label157;
        }
      }
      this.power = Math.max(0.0D, this.power - paramDouble);
    }
    else
    {
      this.power = Math.max(0.0D, this.power - paramDouble);
    }
    label157:
    return true;
  }
  
  public void setRecharge(double paramDouble)
  {
    this.recharge = paramDouble;
  }
  
  public double getPower()
  {
    return this.power;
  }
  
  public double getMaxPower()
  {
    double d;
    if (this.segmentController.getDockingController().a4() != null)
    {
      Object localObject;
      if ((((localObject = this.segmentController.getDockingController().a4().field_1740.a7().a15()) instanceof class_798)) && ((((class_798)localObject).a() instanceof PowerManagerInterface)))
      {
        localObject = (PowerManagerInterface)((class_798)localObject).a();
        d = this.maxPower + ((PowerManagerInterface)localObject).getPowerCapacityManager().getMaxPower() + getBaseCapacity();
      }
      else
      {
        d = this.maxPower;
      }
    }
    else
    {
      d = this.maxPower;
    }
    return getBaseCapacity() + d;
  }
  
  public double getRecharge()
  {
    return this.recharge;
  }
  
  public void setMaxPower(double paramDouble)
  {
    this.maxPower = paramDouble;
  }
  
  public class_69 toTagStructure()
  {
    return new class_69(class_79.field_554, "pw", Double.valueOf(this.power));
  }
  
  public void fromTagStructure(class_69 paramclass_69)
  {
    this.initialPower = ((Double)paramclass_69.a4()).doubleValue();
  }
  
  public double getInitialPower()
  {
    return this.initialPower;
  }
  
  public void setInitialPower(double paramDouble)
  {
    this.initialPower = paramDouble;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.PowerAddOn
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */