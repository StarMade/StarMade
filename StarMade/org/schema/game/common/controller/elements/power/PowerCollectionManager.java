package org.schema.game.common.controller.elements.power;

import class_798;
import java.util.List;
import org.schema.common.FastMath;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ElementCollectionManager;
import org.schema.game.common.controller.elements.PowerAddOn;
import org.schema.game.common.controller.elements.PowerManagerInterface;

public class PowerCollectionManager
  extends ElementCollectionManager
{
  private double recharge;
  private static final double divFactor = 0.333D;
  private static final double max = 1000000.0D;
  private static final double growth = 1.000696D;
  private static final double constantGrowth = 25.0D;
  
  public PowerCollectionManager(SegmentController paramSegmentController)
  {
    super((short)2, paramSegmentController);
  }
  
  public int getMargin()
  {
    return 0;
  }
  
  public double getRecharge()
  {
    return this.recharge;
  }
  
  protected Class getType()
  {
    return PowerUnit.class;
  }
  
  protected void onChangedCollection()
  {
    refreshMaxPower();
  }
  
  private void refreshMaxPower()
  {
    setRecharge(0.0D);
    int i = 0;
    for (int j = 0; j < getCollection().size(); j++)
    {
      PowerUnit localPowerUnit = (PowerUnit)getCollection().get(j);
      i += localPowerUnit.size();
      localPowerUnit.refreshPowerCapabilities();
      setRecharge(getRecharge() + localPowerUnit.getRecharge());
    }
    this.recharge = Math.max(1.0D, FastMath.b3(this.recharge * 0.333D));
    this.recharge += i * 25.0D;
    ((PowerManagerInterface)((class_798)getSegmentController()).a()).getPowerAddOn().setRecharge(getRecharge());
  }
  
  public void setRecharge(double paramDouble)
  {
    this.recharge = paramDouble;
  }
  
  public boolean needsUpdate()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.power.PowerCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */