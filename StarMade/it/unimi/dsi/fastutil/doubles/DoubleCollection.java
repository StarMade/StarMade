package it.unimi.dsi.fastutil.doubles;

import java.util.Collection;

public abstract interface DoubleCollection
  extends Collection<Double>, DoubleIterable
{
  public abstract DoubleIterator iterator();
  
  @Deprecated
  public abstract DoubleIterator doubleIterator();
  
  public abstract <T> T[] toArray(T[] paramArrayOfT);
  
  public abstract boolean contains(double paramDouble);
  
  public abstract double[] toDoubleArray();
  
  public abstract double[] toDoubleArray(double[] paramArrayOfDouble);
  
  public abstract double[] toArray(double[] paramArrayOfDouble);
  
  public abstract boolean add(double paramDouble);
  
  public abstract boolean rem(double paramDouble);
  
  public abstract boolean addAll(DoubleCollection paramDoubleCollection);
  
  public abstract boolean containsAll(DoubleCollection paramDoubleCollection);
  
  public abstract boolean removeAll(DoubleCollection paramDoubleCollection);
  
  public abstract boolean retainAll(DoubleCollection paramDoubleCollection);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleCollection
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */