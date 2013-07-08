package org.schema.game.common.controller.elements.powerbeamsupply;

import class_48;
import org.schema.common.FastMath;
import org.schema.game.common.data.element.CustomOutputUnit;

public class PowerSupplyUnit
  extends CustomOutputUnit
{
  private float drainSupplyFactor;
  private class_48 significator = new class_48(-2147483648, -2147483648, -2147483648);
  
  public float getSupplySpeedFactor()
  {
    return this.drainSupplyFactor;
  }
  
  public class_48 getSignificator()
  {
    return this.significator;
  }
  
  public void refreshSupplyCapabilities()
  {
    this.drainSupplyFactor = (7.0F / FastMath.b2(Math.max(0.0F, size()), 1.2F));
  }
  
  protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt3 > this.significator.field_477) {
      this.significator.b(paramInt1, paramInt2, paramInt3);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.powerbeamsupply.PowerSupplyUnit
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */