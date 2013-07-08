/*  1:   */package org.schema.game.common.data.element.pointeffect.missile;
/*  2:   */
/*  3:   */import org.schema.common.FastMath;
/*  4:   */import org.schema.game.common.data.element.PointDistributionUnit;
/*  5:   */import org.schema.game.common.data.element.pointeffect.PointEffect;
/*  6:   */
/*  7:   */public class MissileDistancePointEffect extends PointEffect
/*  8:   */{
/*  9: 9 */  private static int distUnit = 19;
/* 10:   */  
/* 11:   */  public MissileDistancePointEffect(PointDistributionUnit paramPointDistributionUnit) {
/* 12:12 */    super(paramPointDistributionUnit);
/* 13:   */  }
/* 14:   */  
/* 15:   */  public int getEffectId() {
/* 16:16 */    return 1;
/* 17:   */  }
/* 18:   */  
/* 19:   */  public String getName()
/* 20:   */  {
/* 21:21 */    return "Distance";
/* 22:   */  }
/* 23:   */  
/* 24:   */  protected void recalculate()
/* 25:   */  {
/* 26:26 */    this.value = Math.max(distUnit, FastMath.g(getPointsSpend()) * distUnit);
/* 27:   */  }
/* 28:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.pointeffect.missile.MissileDistancePointEffect
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */