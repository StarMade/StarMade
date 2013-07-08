package org.schema.game.common.data.element.pointeffect;

import org.schema.game.common.data.element.PointDistributionUnit;

public class SpeedPointEffect
  extends PointEffect
{
  private static int speedUnit = 8;
  
  public SpeedPointEffect(PointDistributionUnit paramPointDistributionUnit)
  {
    super(paramPointDistributionUnit);
  }
  
  public int getEffectId()
  {
    return 3;
  }
  
  public String getName()
  {
    return "Speed";
  }
  
  protected void recalculate()
  {
    this.value = ((float)Math.max(1.0D, 15.0D + Math.pow(getPointsSpend() * 0.1D, 0.5D) * speedUnit));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.element.pointeffect.SpeedPointEffect
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */