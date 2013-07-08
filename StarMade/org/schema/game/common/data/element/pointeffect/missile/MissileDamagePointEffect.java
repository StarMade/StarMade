package org.schema.game.common.data.element.pointeffect.missile;

import org.schema.common.FastMath;
import org.schema.game.common.data.element.PointDistributionUnit;
import org.schema.game.common.data.element.pointeffect.PointEffect;

public class MissileDamagePointEffect
  extends PointEffect
{
  public MissileDamagePointEffect(PointDistributionUnit paramPointDistributionUnit)
  {
    super(paramPointDistributionUnit);
  }
  
  public int getEffectId()
  {
    return 0;
  }
  
  public String getName()
  {
    return "Damage";
  }
  
  protected void recalculate()
  {
    this.value = Math.max(10.0F, FastMath.g(getPointsSpend()) * 50.0F);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.element.pointeffect.missile.MissileDamagePointEffect
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */