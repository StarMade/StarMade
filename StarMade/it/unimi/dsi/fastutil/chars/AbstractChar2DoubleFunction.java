package it.unimi.dsi.fastutil.chars;

import java.io.Serializable;

public abstract class AbstractChar2DoubleFunction
  implements Char2DoubleFunction, Serializable
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
  
  public double put(char key, double value)
  {
    throw new UnsupportedOperationException();
  }
  
  public double remove(char key)
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
  
  public Double get(Object local_ok)
  {
    char local_k = ((Character)local_ok).charValue();
    return containsKey(local_k) ? Double.valueOf(get(local_k)) : null;
  }
  
  public Double put(Character local_ok, Double local_ov)
  {
    char local_k = local_ok.charValue();
    boolean containsKey = containsKey(local_k);
    double local_v = put(local_k, local_ov.doubleValue());
    return containsKey ? Double.valueOf(local_v) : null;
  }
  
  public Double remove(Object local_ok)
  {
    char local_k = ((Character)local_ok).charValue();
    boolean containsKey = containsKey(local_k);
    double local_v = remove(local_k);
    return containsKey ? Double.valueOf(local_v) : null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractChar2DoubleFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */