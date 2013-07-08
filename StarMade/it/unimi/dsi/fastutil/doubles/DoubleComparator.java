package it.unimi.dsi.fastutil.doubles;

import java.util.Comparator;

public abstract interface DoubleComparator
  extends Comparator<Double>
{
  public abstract int compare(double paramDouble1, double paramDouble2);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleComparator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */