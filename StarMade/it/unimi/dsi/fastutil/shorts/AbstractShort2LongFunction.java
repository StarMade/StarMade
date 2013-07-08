package it.unimi.dsi.fastutil.shorts;

import java.io.Serializable;

public abstract class AbstractShort2LongFunction
  implements Short2LongFunction, Serializable
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
  
  public long put(short key, long value)
  {
    throw new UnsupportedOperationException();
  }
  
  public long remove(short key)
  {
    throw new UnsupportedOperationException();
  }
  
  public void clear()
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean containsKey(Object local_ok)
  {
    return containsKey(((Short)local_ok).shortValue());
  }
  
  public Long get(Object local_ok)
  {
    short local_k = ((Short)local_ok).shortValue();
    return containsKey(local_k) ? Long.valueOf(get(local_k)) : null;
  }
  
  public Long put(Short local_ok, Long local_ov)
  {
    short local_k = local_ok.shortValue();
    boolean containsKey = containsKey(local_k);
    long local_v = put(local_k, local_ov.longValue());
    return containsKey ? Long.valueOf(local_v) : null;
  }
  
  public Long remove(Object local_ok)
  {
    short local_k = ((Short)local_ok).shortValue();
    boolean containsKey = containsKey(local_k);
    long local_v = remove(local_k);
    return containsKey ? Long.valueOf(local_v) : null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShort2LongFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */