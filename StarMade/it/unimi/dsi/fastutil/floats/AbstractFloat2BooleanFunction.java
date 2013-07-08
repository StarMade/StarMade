package it.unimi.dsi.fastutil.floats;

import java.io.Serializable;

public abstract class AbstractFloat2BooleanFunction
  implements Float2BooleanFunction, Serializable
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
  
  public boolean put(float key, boolean value)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean remove(float key)
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
  
  public Boolean get(Object local_ok)
  {
    float local_k = ((Float)local_ok).floatValue();
    return containsKey(local_k) ? Boolean.valueOf(get(local_k)) : null;
  }
  
  public Boolean put(Float local_ok, Boolean local_ov)
  {
    float local_k = local_ok.floatValue();
    boolean containsKey = containsKey(local_k);
    boolean local_v = put(local_k, local_ov.booleanValue());
    return containsKey ? Boolean.valueOf(local_v) : null;
  }
  
  public Boolean remove(Object local_ok)
  {
    float local_k = ((Float)local_ok).floatValue();
    boolean containsKey = containsKey(local_k);
    boolean local_v = remove(local_k);
    return containsKey ? Boolean.valueOf(local_v) : null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloat2BooleanFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */