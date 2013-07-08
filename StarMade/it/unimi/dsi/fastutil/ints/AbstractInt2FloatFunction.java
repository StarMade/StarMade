package it.unimi.dsi.fastutil.ints;

import java.io.Serializable;

public abstract class AbstractInt2FloatFunction
  implements Int2FloatFunction, Serializable
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
  
  public float put(int key, float value)
  {
    throw new UnsupportedOperationException();
  }
  
  public float remove(int key)
  {
    throw new UnsupportedOperationException();
  }
  
  public void clear()
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean containsKey(Object local_ok)
  {
    return containsKey(((Integer)local_ok).intValue());
  }
  
  public Float get(Object local_ok)
  {
    int local_k = ((Integer)local_ok).intValue();
    return containsKey(local_k) ? Float.valueOf(get(local_k)) : null;
  }
  
  public Float put(Integer local_ok, Float local_ov)
  {
    int local_k = local_ok.intValue();
    boolean containsKey = containsKey(local_k);
    float local_v = put(local_k, local_ov.floatValue());
    return containsKey ? Float.valueOf(local_v) : null;
  }
  
  public Float remove(Object local_ok)
  {
    int local_k = ((Integer)local_ok).intValue();
    boolean containsKey = containsKey(local_k);
    float local_v = remove(local_k);
    return containsKey ? Float.valueOf(local_v) : null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractInt2FloatFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */