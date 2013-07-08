package it.unimi.dsi.fastutil.objects;

import java.io.Serializable;

public abstract class AbstractObject2FloatFunction<K>
  implements Object2FloatFunction<K>, Serializable
{
  public static final long serialVersionUID = -4940583368468432370L;
  protected float defRetValue;
  
  public void defaultReturnValue(float local_rv)
  {
    this.defRetValue = local_rv;
  }
  
  public float defaultReturnValue()
  {
    return this.defRetValue;
  }
  
  public float put(K key, float value)
  {
    throw new UnsupportedOperationException();
  }
  
  public float removeFloat(Object key)
  {
    throw new UnsupportedOperationException();
  }
  
  public void clear()
  {
    throw new UnsupportedOperationException();
  }
  
  public Float get(Object local_ok)
  {
    Object local_k = local_ok;
    return containsKey(local_k) ? Float.valueOf(getFloat(local_k)) : null;
  }
  
  public Float put(K local_ok, Float local_ov)
  {
    K local_k = local_ok;
    boolean containsKey = containsKey(local_k);
    float local_v = put(local_k, local_ov.floatValue());
    return containsKey ? Float.valueOf(local_v) : null;
  }
  
  public Float remove(Object local_ok)
  {
    Object local_k = local_ok;
    boolean containsKey = containsKey(local_k);
    float local_v = removeFloat(local_k);
    return containsKey ? Float.valueOf(local_v) : null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObject2FloatFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */