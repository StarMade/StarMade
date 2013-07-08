package it.unimi.dsi.fastutil.floats;

import java.util.Collection;

public abstract interface FloatCollection
  extends Collection<Float>, FloatIterable
{
  public abstract FloatIterator iterator();
  
  @Deprecated
  public abstract FloatIterator floatIterator();
  
  public abstract <T> T[] toArray(T[] paramArrayOfT);
  
  public abstract boolean contains(float paramFloat);
  
  public abstract float[] toFloatArray();
  
  public abstract float[] toFloatArray(float[] paramArrayOfFloat);
  
  public abstract float[] toArray(float[] paramArrayOfFloat);
  
  public abstract boolean add(float paramFloat);
  
  public abstract boolean rem(float paramFloat);
  
  public abstract boolean addAll(FloatCollection paramFloatCollection);
  
  public abstract boolean containsAll(FloatCollection paramFloatCollection);
  
  public abstract boolean removeAll(FloatCollection paramFloatCollection);
  
  public abstract boolean retainAll(FloatCollection paramFloatCollection);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatCollection
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */