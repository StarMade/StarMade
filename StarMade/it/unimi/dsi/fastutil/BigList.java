package it.unimi.dsi.fastutil;

import java.util.Collection;

public abstract interface BigList<K> extends Collection<K>, Size64
{
  public abstract K get(long paramLong);

  public abstract K remove(long paramLong);

  public abstract K set(long paramLong, K paramK);

  public abstract void add(long paramLong, K paramK);

  public abstract void size(long paramLong);

  public abstract boolean addAll(long paramLong, Collection<? extends K> paramCollection);

  public abstract long indexOf(Object paramObject);

  public abstract long lastIndexOf(Object paramObject);

  public abstract BigListIterator<K> listIterator();

  public abstract BigListIterator<K> listIterator(long paramLong);

  public abstract BigList<K> subList(long paramLong1, long paramLong2);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.BigList
 * JD-Core Version:    0.6.2
 */