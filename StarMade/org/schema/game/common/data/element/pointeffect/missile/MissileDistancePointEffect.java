package org.schema.game.common.data.element.pointeffect.missile;

import org.schema.common.FastMath;
import org.schema.game.common.data.element.PointDistributionUnit;
import org.schema.game.common.data.element.pointeffect.PointEffect;

public class MissileDistancePointEffect
  extends PointEffect
{
  private static int distUnit = 19;
  
  public MissileDistancePointEffect(PointDistributionUnit paramPointDistributionUnit)
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
    this.value = Math.max(distUnit, FastMath.g(getPointsSpend()) * distUnit);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.element.pointeffect.missile.MissileDistancePointEffect
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */