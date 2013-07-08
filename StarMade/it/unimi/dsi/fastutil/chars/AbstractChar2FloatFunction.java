package it.unimi.dsi.fastutil.chars;

import java.io.Serializable;

public abstract class AbstractChar2FloatFunction
  implements Char2FloatFunction, Serializable
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
  
  public float put(char key, float value)
  {
    throw new UnsupportedOperationException();
  }
  
  public float remove(char key)
  {
    throw new UnsupportedOperationException();
  }
  
  public void clear()
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean containsKey(Object local_ok)
  {
    return containsKey(((Character)local_ok).charValue());
  }
  
  public Float get(Object local_ok)
  {
    char local_k = ((Character)local_ok).charValue();
    return containsKey(local_k) ? Float.valueOf(get(local_k)) : null;
  }
  
  public Float put(Character local_ok, Float local_ov)
  {
    char local_k = local_ok.charValue();
    boolean containsKey = containsKey(local_k);
    float local_v = put(local_k, local_ov.floatValue());
    return containsKey ? Float.valueOf(local_v) : null;
  }
  
  public Float remove(Object local_ok)
  {
    char local_k = ((Character)local_ok).charValue();
    boolean containsKey = containsKey(local_k);
    float local_v = remove(local_k);
    return containsKey ? Float.valueOf(local_v) : null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractChar2FloatFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */