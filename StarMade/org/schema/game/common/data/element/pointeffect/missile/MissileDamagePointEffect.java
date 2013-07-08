/*  1:   */package org.schema.game.common.data.element.pointeffect.missile;
/*  2:   */
/*  3:   */import org.schema.common.FastMath;
/*  4:   */import org.schema.game.common.data.element.PointDistributionUnit;
/*  5:   */import org.schema.game.common.data.element.pointeffect.PointEffect;
/*  6:   */
/*  7:   */public class MissileDamagePointEffect
/*  8:   */  extends PointEffect
/*  9:   */{
/* 10:   */  public MissileDamagePointEffect(PointDistributionUnit paramPointDistributionUnit)
/* 11:   */  {
/* 12:12 */    super(paramPointDistributionUnit);
/* 13:   */  }
/* 14:   */  
/* 15:   */  public int getEffectId()
/* 16:   */  {
/* 17:17 */    return 0;
/* 18:   */  }
/* 19:   */  
/* 20:   */  public String getName()
/* 21:   */  {
/* 22:22 */    return "Damage";
/* 23:   */  }
/* 24:   */  
/* 25:   */  protected void recalculate()
/* 26:   */  {
/* 27:27 */    this.value = Math.max(10.0F, FastMath.g(getPointsSpend()) * 50.0F);
/* 28:   */  }
/* 29:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.pointeffect.missile.MissileDamagePointEffect
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */