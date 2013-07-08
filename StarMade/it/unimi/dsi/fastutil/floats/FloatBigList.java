package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.BigList;

public abstract interface FloatBigList
  extends BigList<Float>, FloatCollection, Comparable<BigList<? extends Float>>
{
  public abstract FloatBigListIterator iterator();
  
  public abstract FloatBigListIterator listIterator();
  
  public abstract FloatBigListIterator listIterator(long paramLong);
  
  public abstract FloatBigList subList(long paramLong1, long paramLong2);
  
  public abstract void getElements(long paramLong1, float[][] paramArrayOfFloat, long paramLong2, long paramLong3);
  
  public abstract void removeElements(long paramLong1, long paramLong2);
  
  public abstract void addElements(long paramLong, float[][] paramArrayOfFloat);
  
  public abstract void addElements(long paramLong1, float[][] paramArrayOfFloat, long paramLong2, long paramLong3);
  
  public abstract void add(long paramLong, float paramFloat);
  
  public abstract boolean addAll(long paramLong, FloatCollection paramFloatCollection);
  
  public abstract boolean addAll(long paramLong, FloatBigList paramFloatBigList);
  
  public abstract boolean addAll(FloatBigList paramFloatBigList);
  
  public abstract float getFloat(long paramLong);
  
  public abstract long indexOf(float paramFloat);
  
  public abstract long lastIndexOf(float paramFloat);
  
  public abstract float removeFloat(long paramLong);
  
  public abstract float set(long paramLong, float paramFloat);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatBigList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */