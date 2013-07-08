package it.unimi.dsi.fastutil.floats;

import java.io.Serializable;

public abstract class AbstractFloat2DoubleFunction
  implements Float2DoubleFunction, Serializable
{
  public static final long serialVersionUID = -4940583368468432370L;
  protected double defRetValue;
  
  public void defaultReturnValue(double local_rv)
  {
    this.defRetValue = local_rv;
  }
  
  public double defaultReturnValue()
  {
    return this.defRetValue;
  }
  
  public double put(float key, double value)
  {
    throw new UnsupportedOperationException();
  }
  
  public double remove(float key)
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
  
  public Double get(Object local_ok)
  {
    float local_k = ((Float)local_ok).floatValue();
    return containsKey(local_k) ? Double.valueOf(get(local_k)) : null;
  }
  
  public Double put(Float local_ok, Double local_ov)
  {
    float local_k = local_ok.floatValue();
    boolean containsKey = containsKey(local_k);
    double local_v = put(local_k, local_ov.doubleValue());
    return containsKey ? Double.valueOf(local_v) : null;
  }
  
  public Double remove(Object local_ok)
  {
    float local_k = ((Float)local_ok).floatValue();
    boolean containsKey = containsKey(local_k);
    double local_v = remove(local_k);
    return containsKey ? Double.valueOf(local_v) : null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloat2DoubleFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */