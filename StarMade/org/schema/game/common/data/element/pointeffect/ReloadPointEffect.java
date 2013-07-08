/*  1:   */package org.schema.game.common.data.element.pointeffect;
/*  2:   */
/*  3:   */import java.io.PrintStream;
/*  4:   */
/*  5:   */public class ReloadPointEffect extends PointEffect
/*  6:   */{
/*  7:   */  public ReloadPointEffect(org.schema.game.common.data.element.PointDistributionUnit paramPointDistributionUnit) {
/*  8: 8 */    super(paramPointDistributionUnit);
/*  9:   */  }
/* 10:   */  
/* 11:   */  public int getEffectId()
/* 12:   */  {
/* 13:13 */    return 2;
/* 14:   */  }
/* 15:   */  
/* 16:   */  public String getName()
/* 17:   */  {
/* 18:18 */    return "Reload";
/* 19:   */  }
/* 20:   */  
/* 21:   */  protected void recalculate()
/* 22:   */  {
/* 23:23 */    double d = Math.pow(getPointsSpend() * 0.5D + 10.0D, -0.9D) * 5000.0D + 50.0D;
/* 24:24 */    this.value = ((float)Math.max(50.0D, d));
/* 25:   */  }
/* 26:   */  
/* 27:27 */  public static void main(String[] paramArrayOfString) { for (paramArrayOfString = 0; paramArrayOfString < 1000; paramArrayOfString++) {
/* 28:28 */      double d = Math.pow(paramArrayOfString * 0.17D + 10.0D, -0.9D) * 6000.0D + 50.0D;
/* 29:29 */      System.err.println(paramArrayOfString + ": " + d);
/* 30:   */    }
/* 31:   */  }
/* 32:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.pointeffect.ReloadPointEffect
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */