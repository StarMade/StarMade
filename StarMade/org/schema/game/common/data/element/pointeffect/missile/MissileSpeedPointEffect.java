package org.schema.game.common.data.element.pointeffect.missile;

import org.schema.common.FastMath;
import org.schema.game.common.data.element.PointDistributionUnit;
import org.schema.game.common.data.element.pointeffect.PointEffect;

public class MissileSpeedPointEffect
  extends PointEffect
{
  public MissileSpeedPointEffect(PointDistributionUnit paramPointDistributionUnit)
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
    this.value = Math.max(0.2F, 0.2F + FastMath.g(getPointsSpend()) * 0.5F);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.element.pointeffect.missile.MissileSpeedPointEffect
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */