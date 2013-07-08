package it.unimi.dsi.fastutil.longs;

import java.io.Serializable;

public abstract class AbstractLong2ObjectFunction<V>
  implements Long2ObjectFunction<V>, Serializable
{
  public static final long serialVersionUID = -4940583368468432370L;
  protected V defRetValue;
  
  public void defaultReturnValue(V local_rv)
  {
    this.defRetValue = local_rv;
  }
  
  public V defaultReturnValue()
  {
    return this.defRetValue;
  }
  
  public V put(long key, V value)
  {
    throw new UnsupportedOperationException();
  }
  
  public V remove(long key)
  {
    throw new UnsupportedOperationException();
  }
  
  public void clear()
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean containsKey(Object local_ok)
  {
    return containsKey(((Long)local_ok).longValue());
  }
  
  public V get(Object local_ok)
  {
    long local_k = ((Long)local_ok).longValue();
    return containsKey(local_k) ? get(local_k) : null;
  }
  
  public V put(Long local_ok, V local_ov)
  {
    long local_k = local_ok.longValue();
    boolean containsKey = containsKey(local_k);
    V local_v = put(local_k, local_ov);
    return containsKey ? local_v : null;
  }
  
  public V remove(Object local_ok)
  {
    long local_k = ((Long)local_ok).longValue();
    boolean containsKey = containsKey(local_k);
    V local_v = remove(local_k);
    return containsKey ? local_v : null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLong2ObjectFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */