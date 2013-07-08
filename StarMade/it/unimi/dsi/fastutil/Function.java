package it.unimi.dsi.fastutil;

public abstract interface Function<K, V>
{
  public abstract V put(K paramK, V paramV);
  
  public abstract V get(Object paramObject);
  
  public abstract boolean containsKey(Object paramObject);
  
  public abstract V remove(Object paramObject);
  
  public abstract int size();
  
  public abstract void clear();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.Function
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */