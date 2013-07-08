package it.unimi.dsi.fastutil.longs;

import java.io.Serializable;

public abstract class AbstractLong2LongFunction
  implements Long2LongFunction, Serializable
{
  public static final long serialVersionUID = -4940583368468432370L;
  protected long defRetValue;
  
  public void defaultReturnValue(long local_rv)
  {
    this.defRetValue = local_rv;
  }
  
  public long defaultReturnValue()
  {
    return this.defRetValue;
  }
  
  public long put(long key, long value)
  {
    throw new UnsupportedOperationException();
  }
  
  public long remove(long key)
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
  
  public Long get(Object local_ok)
  {
    long local_k = ((Long)local_ok).longValue();
    return containsKey(local_k) ? Long.valueOf(get(local_k)) : null;
  }
  
  public Long put(Long local_ok, Long local_ov)
  {
    long local_k = local_ok.longValue();
    boolean containsKey = containsKey(local_k);
    long local_v = put(local_k, local_ov.longValue());
    return containsKey ? Long.valueOf(local_v) : null;
  }
  
  public Long remove(Object local_ok)
  {
    long local_k = ((Long)local_ok).longValue();
    boolean containsKey = containsKey(local_k);
    long local_v = remove(local_k);
    return containsKey ? Long.valueOf(local_v) : null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLong2LongFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */