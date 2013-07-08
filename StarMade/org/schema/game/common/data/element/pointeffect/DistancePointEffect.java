package org.schema.game.common.data.element.pointeffect;

import org.schema.game.common.data.element.PointDistributionUnit;

public class DistancePointEffect
  extends PointEffect
{
  private static int distUnit = 40;
  
  public DistancePointEffect(PointDistributionUnit paramPointDistributionUnit)
  {
    super(paramPointDistributionUnit);
  }
  
  public int getEffectId()
  {
    return 1;
  }
  
  public String getName()
  {
    return "Distance";
  }
  
  protected void recalculate()
  {
    this.value = ((float)Math.max(distUnit, 290.0D + Math.pow(getPointsSpend(), 0.5D) * distUnit));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.element.pointeffect.DistancePointEffect
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */