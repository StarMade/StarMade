package it.unimi.dsi.fastutil.objects;

import java.io.Serializable;

public abstract class AbstractReference2IntFunction<K>
  implements Reference2IntFunction<K>, Serializable
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
  
  public int put(K key, int value)
  {
    throw new UnsupportedOperationException();
  }
  
  public int removeInt(Object key)
  {
    throw new UnsupportedOperationException();
  }
  
  public void clear()
  {
    throw new UnsupportedOperationException();
  }
  
  public Integer get(Object local_ok)
  {
    Object local_k = local_ok;
    return containsKey(local_k) ? Integer.valueOf(getInt(local_k)) : null;
  }
  
  public Integer put(K local_ok, Integer local_ov)
  {
    K local_k = local_ok;
    boolean containsKey = containsKey(local_k);
    int local_v = put(local_k, local_ov.intValue());
    return containsKey ? Integer.valueOf(local_v) : null;
  }
  
  public Integer remove(Object local_ok)
  {
    Object local_k = local_ok;
    boolean containsKey = containsKey(local_k);
    int local_v = removeInt(local_k);
    return containsKey ? Integer.valueOf(local_v) : null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReference2IntFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */