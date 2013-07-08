package it.unimi.dsi.fastutil.longs;

import java.io.Serializable;

public abstract class AbstractLong2IntFunction
  implements Long2IntFunction, Serializable
{
  public static final long serialVersionUID = -4940583368468432370L;
  protected int defRetValue;
  
  public void defaultReturnValue(int local_rv)
  {
    this.defRetValue = local_rv;
  }
  
  public int defaultReturnValue()
  {
    return this.defRetValue;
  }
  
  public int put(long key, int value)
  {
    throw new UnsupportedOperationException();
  }
  
  public int remove(long key)
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
  
  public Integer get(Object local_ok)
  {
    long local_k = ((Long)local_ok).longValue();
    return containsKey(local_k) ? Integer.valueOf(get(local_k)) : null;
  }
  
  public Integer put(Long local_ok, Integer local_ov)
  {
    long local_k = local_ok.longValue();
    boolean containsKey = containsKey(local_k);
    int local_v = put(local_k, local_ov.intValue());
    return containsKey ? Integer.valueOf(local_v) : null;
  }
  
  public Integer remove(Object local_ok)
  {
    long local_k = ((Long)local_ok).longValue();
    boolean containsKey = containsKey(local_k);
    int local_v = remove(local_k);
    return containsKey ? Integer.valueOf(local_v) : null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLong2IntFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */