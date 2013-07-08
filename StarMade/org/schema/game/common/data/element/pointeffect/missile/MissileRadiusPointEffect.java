package org.schema.game.common.data.element.pointeffect.missile;

import org.schema.common.FastMath;
import org.schema.game.common.data.element.PointDistributionUnit;
import org.schema.game.common.data.element.pointeffect.PointEffect;

public class MissileRadiusPointEffect
  extends PointEffect
{
  public MissileRadiusPointEffect(PointDistributionUnit paramPointDistributionUnit)
  {
    super(paramPointDistributionUnit);
  }
  
  public int getEffectId()
  {
    return 4;
  }
  
  public String getName()
  {
    return "Radius";
  }
  
  protected void recalculate()
  {
    this.value = Math.max(3.0F, 3.0F + FastMath.g(getPointsSpend()) * 3.0F);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.element.pointeffect.missile.MissileRadiusPointEffect
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */