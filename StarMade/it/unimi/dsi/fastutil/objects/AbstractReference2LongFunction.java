package it.unimi.dsi.fastutil.objects;

import java.io.Serializable;

public abstract class AbstractReference2LongFunction<K>
  implements Reference2LongFunction<K>, Serializable
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
  
  public long put(K key, long value)
  {
    throw new UnsupportedOperationException();
  }
  
  public long removeLong(Object key)
  {
    throw new UnsupportedOperationException();
  }
  
  public void clear()
  {
    throw new UnsupportedOperationException();
  }
  
  public Long get(Object local_ok)
  {
    Object local_k = local_ok;
    return containsKey(local_k) ? Long.valueOf(getLong(local_k)) : null;
  }
  
  public Long put(K local_ok, Long local_ov)
  {
    K local_k = local_ok;
    boolean containsKey = containsKey(local_k);
    long local_v = put(local_k, local_ov.longValue());
    return containsKey ? Long.valueOf(local_v) : null;
  }
  
  public Long remove(Object local_ok)
  {
    Object local_k = local_ok;
    boolean containsKey = containsKey(local_k);
    long local_v = removeLong(local_k);
    return containsKey ? Long.valueOf(local_v) : null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReference2LongFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */