package it.unimi.dsi.fastutil.floats;

import java.io.Serializable;

public abstract class AbstractFloat2FloatFunction
  implements Float2FloatFunction, Serializable
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
  
  public float put(float key, float value)
  {
    throw new UnsupportedOperationException();
  }
  
  public float remove(float key)
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
  
  public Float get(Object local_ok)
  {
    float local_k = ((Float)local_ok).floatValue();
    return containsKey(local_k) ? Float.valueOf(get(local_k)) : null;
  }
  
  public Float put(Float local_ok, Float local_ov)
  {
    float local_k = local_ok.floatValue();
    boolean containsKey = containsKey(local_k);
    float local_v = put(local_k, local_ov.floatValue());
    return containsKey ? Float.valueOf(local_v) : null;
  }
  
  public Float remove(Object local_ok)
  {
    float local_k = ((Float)local_ok).floatValue();
    boolean containsKey = containsKey(local_k);
    float local_v = remove(local_k);
    return containsKey ? Float.valueOf(local_v) : null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloat2FloatFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */