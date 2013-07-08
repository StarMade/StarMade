package it.unimi.dsi.fastutil.objects;

import java.io.Serializable;

public abstract class AbstractObject2BooleanFunction<K>
  implements Object2BooleanFunction<K>, Serializable
{
  public static final long serialVersionUID = -4940583368468432370L;
  protected boolean defRetValue;
  
  public void defaultReturnValue(boolean local_rv)
  {
    this.defRetValue = local_rv;
  }
  
  public boolean defaultReturnValue()
  {
    return this.defRetValue;
  }
  
  public boolean put(K key, boolean value)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean removeBoolean(Object key)
  {
    throw new UnsupportedOperationException();
  }
  
  public void clear()
  {
    throw new UnsupportedOperationException();
  }
  
  public Boolean get(Object local_ok)
  {
    Object local_k = local_ok;
    return containsKey(local_k) ? Boolean.valueOf(getBoolean(local_k)) : null;
  }
  
  public Boolean put(K local_ok, Boolean local_ov)
  {
    K local_k = local_ok;
    boolean containsKey = containsKey(local_k);
    boolean local_v = put(local_k, local_ov.booleanValue());
    return containsKey ? Boolean.valueOf(local_v) : null;
  }
  
  public Boolean remove(Object local_ok)
  {
    Object local_k = local_ok;
    boolean containsKey = containsKey(local_k);
    boolean local_v = removeBoolean(local_k);
    return containsKey ? Boolean.valueOf(local_v) : null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObject2BooleanFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */