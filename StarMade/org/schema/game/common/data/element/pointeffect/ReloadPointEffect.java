package org.schema.game.common.data.element.pointeffect;

import java.io.PrintStream;
import org.schema.game.common.data.element.PointDistributionUnit;

public class ReloadPointEffect
  extends PointEffect
{
  public ReloadPointEffect(PointDistributionUnit paramPointDistributionUnit)
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
    double d = Math.pow(getPointsSpend() * 0.5D + 10.0D, -0.9D) * 5000.0D + 50.0D;
    this.value = ((float)Math.max(50.0D, d));
  }
  
  public static void main(String[] paramArrayOfString)
  {
    for (paramArrayOfString = 0; paramArrayOfString < 1000; paramArrayOfString++)
    {
      double d = Math.pow(paramArrayOfString * 0.17D + 10.0D, -0.9D) * 6000.0D + 50.0D;
      System.err.println(paramArrayOfString + ": " + d);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.element.pointeffect.ReloadPointEffect
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */