package it.unimi.dsi.fastutil.floats;

import java.io.Serializable;

public abstract class AbstractFloat2LongFunction
  implements Float2LongFunction, Serializable
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
  
  public long put(float key, long value)
  {
    throw new UnsupportedOperationException();
  }
  
  public long remove(float key)
  {
    throw new UnsupportedOperationException();
  }
  
  public void clear()
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean containsKey(Object local_ok)
  {
    return containsKey(((Float)local_ok).floatValue());
  }
  
  public Long get(Object local_ok)
  {
    float local_k = ((Float)local_ok).floatValue();
    return containsKey(local_k) ? Long.valueOf(get(local_k)) : null;
  }
  
  public Long put(Float local_ok, Long local_ov)
  {
    float local_k = local_ok.floatValue();
    boolean containsKey = containsKey(local_k);
    long local_v = put(local_k, local_ov.longValue());
    return containsKey ? Long.valueOf(local_v) : null;
  }
  
  public Long remove(Object local_ok)
  {
    float local_k = ((Float)local_ok).floatValue();
    boolean containsKey = containsKey(local_k);
    long local_v = remove(local_k);
    return containsKey ? Long.valueOf(local_v) : null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloat2LongFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */