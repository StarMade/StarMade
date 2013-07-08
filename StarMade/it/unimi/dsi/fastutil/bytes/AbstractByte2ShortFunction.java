package it.unimi.dsi.fastutil.bytes;

import java.io.Serializable;

public abstract class AbstractByte2ShortFunction
  implements Byte2ShortFunction, Serializable
{
  public static final long serialVersionUID = -4940583368468432370L;
  protected short defRetValue;
  
  public void defaultReturnValue(short local_rv)
  {
    this.defRetValue = local_rv;
  }
  
  public short defaultReturnValue()
  {
    return this.defRetValue;
  }
  
  public short put(byte key, short value)
  {
    throw new UnsupportedOperationException();
  }
  
  public short remove(byte key)
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
  
  public Short get(Object local_ok)
  {
    byte local_k = ((Byte)local_ok).byteValue();
    return containsKey(local_k) ? Short.valueOf(get(local_k)) : null;
  }
  
  public Short put(Byte local_ok, Short local_ov)
  {
    byte local_k = local_ok.byteValue();
    boolean containsKey = containsKey(local_k);
    short local_v = put(local_k, local_ov.shortValue());
    return containsKey ? Short.valueOf(local_v) : null;
  }
  
  public Short remove(Object local_ok)
  {
    byte local_k = ((Byte)local_ok).byteValue();
    boolean containsKey = containsKey(local_k);
    short local_v = remove(local_k);
    return containsKey ? Short.valueOf(local_v) : null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByte2ShortFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */