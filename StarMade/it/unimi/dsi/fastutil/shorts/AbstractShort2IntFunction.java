package it.unimi.dsi.fastutil.shorts;

import java.io.Serializable;

public abstract class AbstractShort2IntFunction
  implements Short2IntFunction, Serializable
{
  public static final long serialVersionUID = -4940583368468432370L;
  protected int defRetValue;
  
  public void defaultReturnValue(int local_rv)
  {
    this.defRetValue = local_rv;
  }
  
  public int defaultReturnValue()
  {
    return this.defRetValue;
  }
  
  public int put(short key, int value)
  {
    throw new UnsupportedOperationException();
  }
  
  public int remove(short key)
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
  
  public Integer get(Object local_ok)
  {
    short local_k = ((Short)local_ok).shortValue();
    return containsKey(local_k) ? Integer.valueOf(get(local_k)) : null;
  }
  
  public Integer put(Short local_ok, Integer local_ov)
  {
    short local_k = local_ok.shortValue();
    boolean containsKey = containsKey(local_k);
    int local_v = put(local_k, local_ov.intValue());
    return containsKey ? Integer.valueOf(local_v) : null;
  }
  
  public Integer remove(Object local_ok)
  {
    short local_k = ((Short)local_ok).shortValue();
    boolean containsKey = containsKey(local_k);
    int local_v = remove(local_k);
    return containsKey ? Integer.valueOf(local_v) : null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShort2IntFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */