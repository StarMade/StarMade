package org.hsqldb.lib;

public abstract interface HsqlList
  extends Collection
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


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.lib.HsqlList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */