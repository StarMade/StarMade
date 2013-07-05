package org.hsqldb.lib;

public abstract interface HsqlList extends Collection
{
  public abstract void add(int paramInt, Object paramObject);

  public abstract boolean add(Object paramObject);

  public abstract Object get(int paramInt);

  public abstract Object remove(int paramInt);

  public abstract Object set(int paramInt, Object paramObject);

  public abstract boolean isEmpty();

  public abstract int size();

  public abstract Iterator iterator();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.HsqlList
 * JD-Core Version:    0.6.2
 */