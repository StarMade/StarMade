package it.unimi.dsi.fastutil.doubles;

import java.util.SortedSet;

public abstract interface DoubleSortedSet extends DoubleSet, SortedSet<Double>
{
  public abstract DoubleBidirectionalIterator iterator(double paramDouble);

  @Deprecated
  public abstract DoubleBidirectionalIterator doubleIterator();

  public abstract DoubleBidirectionalIterator iterator();

  public abstract DoubleSortedSet subSet(Double paramDouble1, Double paramDouble2);

  public abstract DoubleSortedSet headSet(Double paramDouble);

  public abstract DoubleSortedSet tailSet(Double paramDouble);

  public abstract DoubleComparator comparator();

  public abstract DoubleSortedSet subSet(double paramDouble1, double paramDouble2);

  public abstract DoubleSortedSet headSet(double paramDouble);

  public abstract DoubleSortedSet tailSet(double paramDouble);

  public abstract double firstDouble();

  public abstract double lastDouble();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleSortedSet
 * JD-Core Version:    0.6.2
 */