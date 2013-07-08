package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.BigList;

public abstract interface DoubleBigList
  extends BigList<Double>, DoubleCollection, Comparable<BigList<? extends Double>>
{
  public abstract DoubleBigListIterator iterator();
  
  public abstract DoubleBigListIterator listIterator();
  
  public abstract DoubleBigListIterator listIterator(long paramLong);
  
  public abstract DoubleBigList subList(long paramLong1, long paramLong2);
  
  public abstract void getElements(long paramLong1, double[][] paramArrayOfDouble, long paramLong2, long paramLong3);
  
  public abstract void removeElements(long paramLong1, long paramLong2);
  
  public abstract void addElements(long paramLong, double[][] paramArrayOfDouble);
  
  public abstract void addElements(long paramLong1, double[][] paramArrayOfDouble, long paramLong2, long paramLong3);
  
  public abstract void add(long paramLong, double paramDouble);
  
  public abstract boolean addAll(long paramLong, DoubleCollection paramDoubleCollection);
  
  public abstract boolean addAll(long paramLong, DoubleBigList paramDoubleBigList);
  
  public abstract boolean addAll(DoubleBigList paramDoubleBigList);
  
  public abstract double getDouble(long paramLong);
  
  public abstract long indexOf(double paramDouble);
  
  public abstract long lastIndexOf(double paramDouble);
  
  public abstract double removeDouble(long paramLong);
  
  public abstract double set(long paramLong, double paramDouble);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleBigList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */