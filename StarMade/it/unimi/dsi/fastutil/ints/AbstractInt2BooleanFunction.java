package it.unimi.dsi.fastutil.ints;

import java.io.Serializable;

public abstract class AbstractInt2BooleanFunction
  implements Int2BooleanFunction, Serializable
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
  
  public boolean put(int key, boolean value)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean remove(int key)
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
  
  public Boolean get(Object local_ok)
  {
    int local_k = ((Integer)local_ok).intValue();
    return containsKey(local_k) ? Boolean.valueOf(get(local_k)) : null;
  }
  
  public Boolean put(Integer local_ok, Boolean local_ov)
  {
    int local_k = local_ok.intValue();
    boolean containsKey = containsKey(local_k);
    boolean local_v = put(local_k, local_ov.booleanValue());
    return containsKey ? Boolean.valueOf(local_v) : null;
  }
  
  public Boolean remove(Object local_ok)
  {
    int local_k = ((Integer)local_ok).intValue();
    boolean containsKey = containsKey(local_k);
    boolean local_v = remove(local_k);
    return containsKey ? Boolean.valueOf(local_v) : null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractInt2BooleanFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */