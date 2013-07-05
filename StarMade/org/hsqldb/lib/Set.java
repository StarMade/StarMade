package org.hsqldb.lib;

public abstract interface Set extends Collection
{
  public abstract int size();

  public abstract boolean isEmpty();

  public abstract boolean contains(Object paramObject);

  public abstract Iterator iterator();

  public abstract boolean add(Object paramObject);

  public abstract Object get(Object paramObject);

  public abstract boolean remove(Object paramObject);

  public abstract void clear();

  public abstract boolean equals(Object paramObject);

  public abstract int hashCode();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.Set
 * JD-Core Version:    0.6.2
 */