package it.unimi.dsi.fastutil.doubles;

public abstract class AbstractDoubleComparator
  implements DoubleComparator
{
  public int compare(Double ok1, Double ok2)
  {
    return compare(ok1.doubleValue(), ok2.doubleValue());
  }
  
  public abstract int compare(double paramDouble1, double paramDouble2);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDoubleComparator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */