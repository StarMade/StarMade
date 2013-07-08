package it.unimi.dsi.fastutil.doubles;

import java.util.List;

public abstract interface DoubleList
  extends List<Double>, Comparable<List<? extends Double>>, DoubleCollection
{
  public abstract DoubleListIterator iterator();
  
  @Deprecated
  public abstract DoubleListIterator doubleListIterator();
  
  @Deprecated
  public abstract DoubleListIterator doubleListIterator(int paramInt);
  
  public abstract DoubleListIterator listIterator();
  
  public abstract DoubleListIterator listIterator(int paramInt);
  
  @Deprecated
  public abstract DoubleList doubleSubList(int paramInt1, int paramInt2);
  
  public abstract DoubleList subList(int paramInt1, int paramInt2);
  
  public abstract void size(int paramInt);
  
  public abstract void getElements(int paramInt1, double[] paramArrayOfDouble, int paramInt2, int paramInt3);
  
  public abstract void removeElements(int paramInt1, int paramInt2);
  
  public abstract void addElements(int paramInt, double[] paramArrayOfDouble);
  
  public abstract void addElements(int paramInt1, double[] paramArrayOfDouble, int paramInt2, int paramInt3);
  
  public abstract boolean add(double paramDouble);
  
  public abstract void add(int paramInt, double paramDouble);
  
  public abstract boolean addAll(int paramInt, DoubleCollection paramDoubleCollection);
  
  public abstract boolean addAll(int paramInt, DoubleList paramDoubleList);
  
  public abstract boolean addAll(DoubleList paramDoubleList);
  
  public abstract double getDouble(int paramInt);
  
  public abstract int indexOf(double paramDouble);
  
  public abstract int lastIndexOf(double paramDouble);
  
  public abstract double removeDouble(int paramInt);
  
  public abstract double set(int paramInt, double paramDouble);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */