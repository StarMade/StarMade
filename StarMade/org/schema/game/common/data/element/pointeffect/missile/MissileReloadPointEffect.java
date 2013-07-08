/*  1:   */package org.schema.game.common.data.element.pointeffect.missile;
/*  2:   */
/*  3:   */import org.schema.common.FastMath;
/*  4:   */import org.schema.game.common.data.element.pointeffect.PointEffect;
/*  5:   */
/*  6:   */public class MissileReloadPointEffect extends PointEffect
/*  7:   */{
/*  8:   */  public MissileReloadPointEffect(org.schema.game.common.data.element.PointDistributionUnit paramPointDistributionUnit)
/*  9:   */  {
/* 10:10 */    super(paramPointDistributionUnit);
/* 11:   */  }
/* 12:   */  
/* 13:   */  public int getEffectId()
/* 14:   */  {
/* 15:15 */    return 2;
/* 16:   */  }
/* 17:   */  
/* 18:   */  public String getName()
/* 19:   */  {
/* 20:20 */    return "Reload";
/* 21:   */  }
/* 22:   */  
/* 23:   */  protected void recalculate()
/* 24:   */  {
/* 25:25 */    this.value = Math.max(0.05F, 200.0F / Math.max(1.0F, FastMath.g(getPointsSpend()) * 2.0F));
/* 26:   */  }
/* 27:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.pointeffect.missile.MissileReloadPointEffect
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */