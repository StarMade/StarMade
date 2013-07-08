/*  1:   */package org.schema.game.common.data.element.pointeffect;
/*  2:   */
/*  3:   */import org.schema.game.common.data.element.PointDistributionUnit;
/*  4:   */
/*  5:   */public class DistancePointEffect extends PointEffect
/*  6:   */{
/*  7: 7 */  private static int distUnit = 40;
/*  8:   */  
/*  9:   */  public DistancePointEffect(PointDistributionUnit paramPointDistributionUnit) {
/* 10:10 */    super(paramPointDistributionUnit);
/* 11:   */  }
/* 12:   */  
/* 13:   */  public int getEffectId() {
/* 14:14 */    return 1;
/* 15:   */  }
/* 16:   */  
/* 17:   */  public String getName()
/* 18:   */  {
/* 19:19 */    return "Distance";
/* 20:   */  }
/* 21:   */  
/* 22:   */  protected void recalculate()
/* 23:   */  {
/* 24:24 */    this.value = ((float)Math.max(distUnit, 290.0D + Math.pow(getPointsSpend(), 0.5D) * distUnit));
/* 25:   */  }
/* 26:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.pointeffect.DistancePointEffect
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */