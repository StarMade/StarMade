package org.schema.game.common.data.element.pointeffect;

import org.schema.game.common.data.element.PointDistributionUnit;

public class DamagePointEffect
  extends PointEffect
{
  public DamagePointEffect(PointDistributionUnit paramPointDistributionUnit)
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
    this.value = ((float)Math.max(1.0D, Math.pow(getPointsSpend() * 0.1D, 0.5D) * 80.0D));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.element.pointeffect.DamagePointEffect
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */