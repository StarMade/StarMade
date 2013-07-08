package org.schema.game.common.data.element.pointeffect.missile;

import org.schema.common.FastMath;
import org.schema.game.common.data.element.PointDistributionUnit;
import org.schema.game.common.data.element.pointeffect.PointEffect;

public class MissileReloadPointEffect
  extends PointEffect
{
  public MissileReloadPointEffect(PointDistributionUnit paramPointDistributionUnit)
  {
    super(paramPointDistributionUnit);
  }
  
  public int getEffectId()
  {
    return 2;
  }
  
  public String getName()
  {
    return "Reload";
  }
  
  protected void recalculate()
  {
    this.value = Math.max(0.05F, 200.0F / Math.max(1.0F, FastMath.g(getPointsSpend()) * 2.0F));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.element.pointeffect.missile.MissileReloadPointEffect
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */