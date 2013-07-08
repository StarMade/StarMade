package org.schema.game.common.controller.elements.power;

import class_48;
import org.schema.game.common.data.element.ElementCollection;

public class PowerUnit
  extends ElementCollection
{
  private class_48 significator = new class_48();
  private double recharge;
  
  public double getRecharge()
  {
    return this.recharge;
  }
  
  public class_48 getSignificator()
  {
    return this.significator;
  }
  
  public void refreshPowerCapabilities()
  {
    this.recharge = (Math.max(0.0F, getMax().field_477 - getMin().field_477) + 1.0D);
    this.recharge += Math.max(0.0F, getMax().field_475 - getMin().field_475) + 1.0D;
    this.recharge += Math.max(0.0F, getMax().field_476 - getMin().field_476) + 1.0D;
    this.recharge = Math.max(1.0D, Math.pow(this.recharge / 3.0D, 1.7D));
  }
  
  private static double f(double paramDouble)
  {
    return Math.max(0.1D, 1.0D - 0.5D * Math.pow(2.0D * (1.0D - paramDouble), 2.5D));
  }
  
  public static double integrate(double paramDouble1, double paramDouble2)
  {
    double d1 = (paramDouble2 - paramDouble1) / 9.0D;
    double d2 = 0.3333333333333333D * (f(paramDouble1) + f(paramDouble2));
    double d3;
    for (paramDouble2 = 1; paramDouble2 < 9; paramDouble2 += 2)
    {
      d3 = paramDouble1 + d1 * paramDouble2;
      d2 += 1.333333333333333D * f(d3);
    }
    for (paramDouble2 = 2; paramDouble2 < 9; paramDouble2 += 2)
    {
      d3 = paramDouble1 + d1 * paramDouble2;
      d2 += 0.6666666666666666D * f(d3);
    }
    return d2 * d1;
  }
  
  protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
  {
    this.significator.field_475 = (getMax().field_475 - (getMax().field_475 - getMin().field_475) / 2);
    this.significator.field_476 = (getMax().field_476 - (getMax().field_476 - getMin().field_476) / 2);
    this.significator.field_477 = (getMax().field_477 - (getMax().field_477 - getMin().field_477) / 2);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.power.PowerUnit
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */