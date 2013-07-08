package it.unimi.dsi.fastutil.objects;

import java.io.Serializable;

public abstract class AbstractObject2ShortFunction<K>
  implements Object2ShortFunction<K>, Serializable
{
  public static final long serialVersionUID = -4940583368468432370L;
  protected short defRetValue;
  
  public void defaultReturnValue(short local_rv)
  {
    this.defRetValue = local_rv;
  }
  
  public short defaultReturnValue()
  {
    return this.defRetValue;
  }
  
  public short put(K key, short value)
  {
    throw new UnsupportedOperationException();
  }
  
  public short removeShort(Object key)
  {
    throw new UnsupportedOperationException();
  }
  
  public void clear()
  {
    throw new UnsupportedOperationException();
  }
  
  public Short get(Object local_ok)
  {
    Object local_k = local_ok;
    return containsKey(local_k) ? Short.valueOf(getShort(local_k)) : null;
  }
  
  public Short put(K local_ok, Short local_ov)
  {
    K local_k = local_ok;
    boolean containsKey = containsKey(local_k);
    short local_v = put(local_k, local_ov.shortValue());
    return containsKey ? Short.valueOf(local_v) : null;
  }
  
  public Short remove(Object local_ok)
  {
    Object local_k = local_ok;
    boolean containsKey = containsKey(local_k);
    short local_v = removeShort(local_k);
    return containsKey ? Short.valueOf(local_v) : null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObject2ShortFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */