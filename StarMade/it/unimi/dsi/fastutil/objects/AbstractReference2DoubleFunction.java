package it.unimi.dsi.fastutil.objects;

import java.io.Serializable;

public abstract class AbstractReference2DoubleFunction<K>
  implements Reference2DoubleFunction<K>, Serializable
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
  
  public double put(K key, double value)
  {
    throw new UnsupportedOperationException();
  }
  
  public double removeDouble(Object key)
  {
    throw new UnsupportedOperationException();
  }
  
  public void clear()
  {
    throw new UnsupportedOperationException();
  }
  
  public Double get(Object local_ok)
  {
    Object local_k = local_ok;
    return containsKey(local_k) ? Double.valueOf(getDouble(local_k)) : null;
  }
  
  public Double put(K local_ok, Double local_ov)
  {
    K local_k = local_ok;
    boolean containsKey = containsKey(local_k);
    double local_v = put(local_k, local_ov.doubleValue());
    return containsKey ? Double.valueOf(local_v) : null;
  }
  
  public Double remove(Object local_ok)
  {
    Object local_k = local_ok;
    boolean containsKey = containsKey(local_k);
    double local_v = removeDouble(local_k);
    return containsKey ? Double.valueOf(local_v) : null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReference2DoubleFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */