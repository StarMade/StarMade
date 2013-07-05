package it.unimi.dsi.fastutil.floats;

import java.util.List;

public abstract interface FloatList extends List<Float>, Comparable<List<? extends Float>>, FloatCollection
{
  public abstract FloatListIterator iterator();

  @Deprecated
  public abstract FloatListIterator floatListIterator();

  @Deprecated
  public abstract FloatListIterator floatListIterator(int paramInt);

  public abstract FloatListIterator listIterator();

  public abstract FloatListIterator listIterator(int paramInt);

  @Deprecated
  public abstract FloatList floatSubList(int paramInt1, int paramInt2);

  public abstract FloatList subList(int paramInt1, int paramInt2);

  public abstract void size(int paramInt);

  public abstract void getElements(int paramInt1, float[] paramArrayOfFloat, int paramInt2, int paramInt3);

  public abstract void removeElements(int paramInt1, int paramInt2);

  public abstract void addElements(int paramInt, float[] paramArrayOfFloat);

  public abstract void addElements(int paramInt1, float[] paramArrayOfFloat, int paramInt2, int paramInt3);

  public abstract boolean add(float paramFloat);

  public abstract void add(int paramInt, float paramFloat);

  public abstract boolean addAll(int paramInt, FloatCollection paramFloatCollection);

  public abstract boolean addAll(int paramInt, FloatList paramFloatList);

  public abstract boolean addAll(FloatList paramFloatList);

  public abstract float getFloat(int paramInt);

  public abstract int indexOf(float paramFloat);

  public abstract int lastIndexOf(float paramFloat);

  public abstract float removeFloat(int paramInt);

  public abstract float set(int paramInt, float paramFloat);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatList
 * JD-Core Version:    0.6.2
 */