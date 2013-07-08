package it.unimi.dsi.fastutil.ints;

import java.util.Collection;

public abstract interface IntCollection
  extends Collection<Integer>, IntIterable
{
  public abstract IntIterator iterator();
  
  @Deprecated
  public abstract IntIterator intIterator();
  
  public abstract <T> T[] toArray(T[] paramArrayOfT);
  
  public abstract boolean contains(int paramInt);
  
  public abstract int[] toIntArray();
  
  public abstract int[] toIntArray(int[] paramArrayOfInt);
  
  public abstract int[] toArray(int[] paramArrayOfInt);
  
  public abstract boolean add(int paramInt);
  
  public abstract boolean rem(int paramInt);
  
  public abstract boolean addAll(IntCollection paramIntCollection);
  
  public abstract boolean containsAll(IntCollection paramIntCollection);
  
  public abstract boolean removeAll(IntCollection paramIntCollection);
  
  public abstract boolean retainAll(IntCollection paramIntCollection);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntCollection
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */