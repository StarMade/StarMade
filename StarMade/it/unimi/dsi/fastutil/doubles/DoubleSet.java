package it.unimi.dsi.fastutil.doubles;

import java.util.Set;

public abstract interface DoubleSet
  extends DoubleCollection, Set<Double>
{
  public abstract DoubleIterator iterator();
  
  public abstract boolean remove(double paramDouble);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */