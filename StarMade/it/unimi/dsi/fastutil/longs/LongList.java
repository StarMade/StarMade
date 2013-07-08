package it.unimi.dsi.fastutil.longs;

import java.util.List;

public abstract interface LongList
  extends List<Long>, Comparable<List<? extends Long>>, LongCollection
{
  public abstract LongListIterator iterator();
  
  @Deprecated
  public abstract LongListIterator longListIterator();
  
  @Deprecated
  public abstract LongListIterator longListIterator(int paramInt);
  
  public abstract LongListIterator listIterator();
  
  public abstract LongListIterator listIterator(int paramInt);
  
  @Deprecated
  public abstract LongList longSubList(int paramInt1, int paramInt2);
  
  public abstract LongList subList(int paramInt1, int paramInt2);
  
  public abstract void size(int paramInt);
  
  public abstract void getElements(int paramInt1, long[] paramArrayOfLong, int paramInt2, int paramInt3);
  
  public abstract void removeElements(int paramInt1, int paramInt2);
  
  public abstract void addElements(int paramInt, long[] paramArrayOfLong);
  
  public abstract void addElements(int paramInt1, long[] paramArrayOfLong, int paramInt2, int paramInt3);
  
  public abstract boolean add(long paramLong);
  
  public abstract void add(int paramInt, long paramLong);
  
  public abstract boolean addAll(int paramInt, LongCollection paramLongCollection);
  
  public abstract boolean addAll(int paramInt, LongList paramLongList);
  
  public abstract boolean addAll(LongList paramLongList);
  
  public abstract long getLong(int paramInt);
  
  public abstract int indexOf(long paramLong);
  
  public abstract int lastIndexOf(long paramLong);
  
  public abstract long removeLong(int paramInt);
  
  public abstract long set(int paramInt, long paramLong);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */