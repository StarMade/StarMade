package it.unimi.dsi.fastutil.bytes;

import java.io.Serializable;

public abstract class AbstractByte2BooleanFunction
  implements Byte2BooleanFunction, Serializable
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
  
  public boolean put(byte key, boolean value)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean remove(byte key)
  {
    throw new UnsupportedOperationException();
  }
  
  public void clear()
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean containsKey(Object local_ok)
  {
    return containsKey(((Byte)local_ok).byteValue());
  }
  
  public Boolean get(Object local_ok)
  {
    byte local_k = ((Byte)local_ok).byteValue();
    return containsKey(local_k) ? Boolean.valueOf(get(local_k)) : null;
  }
  
  public Boolean put(Byte local_ok, Boolean local_ov)
  {
    byte local_k = local_ok.byteValue();
    boolean containsKey = containsKey(local_k);
    boolean local_v = put(local_k, local_ov.booleanValue());
    return containsKey ? Boolean.valueOf(local_v) : null;
  }
  
  public Boolean remove(Object local_ok)
  {
    byte local_k = ((Byte)local_ok).byteValue();
    boolean containsKey = containsKey(local_k);
    boolean local_v = remove(local_k);
    return containsKey ? Boolean.valueOf(local_v) : null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByte2BooleanFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */