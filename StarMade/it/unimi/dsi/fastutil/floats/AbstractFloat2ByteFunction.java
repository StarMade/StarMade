package it.unimi.dsi.fastutil.floats;

import java.io.Serializable;

public abstract class AbstractFloat2ByteFunction
  implements Float2ByteFunction, Serializable
{
  public static final long serialVersionUID = -4940583368468432370L;
  protected byte defRetValue;
  
  public void defaultReturnValue(byte local_rv)
  {
    this.defRetValue = local_rv;
  }
  
  public byte defaultReturnValue()
  {
    return this.defRetValue;
  }
  
  public byte put(float key, byte value)
  {
    throw new UnsupportedOperationException();
  }
  
  public byte remove(float key)
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
  
  public Byte get(Object local_ok)
  {
    float local_k = ((Float)local_ok).floatValue();
    return containsKey(local_k) ? Byte.valueOf(get(local_k)) : null;
  }
  
  public Byte put(Float local_ok, Byte local_ov)
  {
    float local_k = local_ok.floatValue();
    boolean containsKey = containsKey(local_k);
    byte local_v = put(local_k, local_ov.byteValue());
    return containsKey ? Byte.valueOf(local_v) : null;
  }
  
  public Byte remove(Object local_ok)
  {
    float local_k = ((Float)local_ok).floatValue();
    boolean containsKey = containsKey(local_k);
    byte local_v = remove(local_k);
    return containsKey ? Byte.valueOf(local_v) : null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloat2ByteFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */