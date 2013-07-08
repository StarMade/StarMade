/*  1:   */package org.schema.game.common.data.element.pointeffect;
/*  2:   */
/*  3:   */import org.schema.game.common.data.element.PointDistributionUnit;
/*  4:   */
/*  5:   */public class SpeedPointEffect extends PointEffect
/*  6:   */{
/*  7: 7 */  private static int speedUnit = 8;
/*  8:   */  
/*  9:   */  public SpeedPointEffect(PointDistributionUnit paramPointDistributionUnit) {
/* 10:10 */    super(paramPointDistributionUnit);
/* 11:   */  }
/* 12:   */  
/* 13:   */  public int getEffectId()
/* 14:   */  {
/* 15:15 */    return 3;
/* 16:   */  }
/* 17:   */  
/* 18:   */  public String getName()
/* 19:   */  {
/* 20:20 */    return "Speed";
/* 21:   */  }
/* 22:   */  
/* 23:   */  protected void recalculate()
/* 24:   */  {
/* 25:25 */    this.value = ((float)Math.max(1.0D, 15.0D + Math.pow(getPointsSpend() * 0.1D, 0.5D) * speedUnit));
/* 26:   */  }
/* 27:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.pointeffect.SpeedPointEffect
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */