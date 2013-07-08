/*  1:   */package org.schema.game.common.data.element.pointeffect;
/*  2:   */
/*  3:   */import org.schema.game.common.data.element.PointDistributionUnit;
/*  4:   */
/*  5:   */public class DamagePointEffect
/*  6:   */  extends PointEffect
/*  7:   */{
/*  8:   */  public DamagePointEffect(PointDistributionUnit paramPointDistributionUnit)
/*  9:   */  {
/* 10:10 */    super(paramPointDistributionUnit);
/* 11:   */  }
/* 12:   */  
/* 13:   */  public int getEffectId()
/* 14:   */  {
/* 15:15 */    return 0;
/* 16:   */  }
/* 17:   */  
/* 18:   */  public String getName()
/* 19:   */  {
/* 20:20 */    return "Damage";
/* 21:   */  }
/* 22:   */  
/* 23:   */  protected void recalculate()
/* 24:   */  {
/* 25:25 */    this.value = ((float)Math.max(1.0D, Math.pow(getPointsSpend() * 0.1D, 0.5D) * 80.0D));
/* 26:   */  }
/* 27:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.pointeffect.DamagePointEffect
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */