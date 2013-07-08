package org.hsqldb.lib;

public abstract interface HsqlHeap
{
  public abstract void clear();
  
  public abstract boolean isEmpty();
  
  public abstract boolean isFull();
  
  public abstract void add(Object paramObject)
    throws IllegalArgumentException, RuntimeException;
  
  public abstract Object peek();
  
  public abstract Object remove();
  
  public abstract int size();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.lib.HsqlHeap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */