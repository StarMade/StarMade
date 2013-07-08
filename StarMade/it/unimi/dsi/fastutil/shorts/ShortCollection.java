package it.unimi.dsi.fastutil.shorts;

import java.util.Collection;

public abstract interface ShortCollection
  extends Collection<Short>, ShortIterable
{
  public abstract ShortIterator iterator();
  
  @Deprecated
  public abstract ShortIterator shortIterator();
  
  public abstract <T> T[] toArray(T[] paramArrayOfT);
  
  public abstract boolean contains(short paramShort);
  
  public abstract short[] toShortArray();
  
  public abstract short[] toShortArray(short[] paramArrayOfShort);
  
  public abstract short[] toArray(short[] paramArrayOfShort);
  
  public abstract boolean add(short paramShort);
  
  public abstract boolean rem(short paramShort);
  
  public abstract boolean addAll(ShortCollection paramShortCollection);
  
  public abstract boolean containsAll(ShortCollection paramShortCollection);
  
  public abstract boolean removeAll(ShortCollection paramShortCollection);
  
  public abstract boolean retainAll(ShortCollection paramShortCollection);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortCollection
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */