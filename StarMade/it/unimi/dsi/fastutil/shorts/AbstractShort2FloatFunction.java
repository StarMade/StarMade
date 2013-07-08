package it.unimi.dsi.fastutil.shorts;

import java.io.Serializable;

public abstract class AbstractShort2FloatFunction
  implements Short2FloatFunction, Serializable
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
  
  public float put(short key, float value)
  {
    throw new UnsupportedOperationException();
  }
  
  public float remove(short key)
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
  
  public Float get(Object local_ok)
  {
    short local_k = ((Short)local_ok).shortValue();
    return containsKey(local_k) ? Float.valueOf(get(local_k)) : null;
  }
  
  public Float put(Short local_ok, Float local_ov)
  {
    short local_k = local_ok.shortValue();
    boolean containsKey = containsKey(local_k);
    float local_v = put(local_k, local_ov.floatValue());
    return containsKey ? Float.valueOf(local_v) : null;
  }
  
  public Float remove(Object local_ok)
  {
    short local_k = ((Short)local_ok).shortValue();
    boolean containsKey = containsKey(local_k);
    float local_v = remove(local_k);
    return containsKey ? Float.valueOf(local_v) : null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShort2FloatFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */