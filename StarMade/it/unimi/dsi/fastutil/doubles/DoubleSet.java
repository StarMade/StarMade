package it.unimi.dsi.fastutil.doubles;

import java.util.Set;

public abstract interface DoubleSet extends DoubleCollection, Set<Double>
{
  public abstract DoubleIterator iterator();

  public abstract boolean remove(double paramDouble);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleSet
 * JD-Core Version:    0.6.2
 */