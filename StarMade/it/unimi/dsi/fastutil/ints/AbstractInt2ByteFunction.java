package it.unimi.dsi.fastutil.ints;

import java.io.Serializable;

public abstract class AbstractInt2ByteFunction
  implements Int2ByteFunction, Serializable
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
  
  public byte put(int key, byte value)
  {
    throw new UnsupportedOperationException();
  }
  
  public byte remove(int key)
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
  
  public Byte get(Object local_ok)
  {
    int local_k = ((Integer)local_ok).intValue();
    return containsKey(local_k) ? Byte.valueOf(get(local_k)) : null;
  }
  
  public Byte put(Integer local_ok, Byte local_ov)
  {
    int local_k = local_ok.intValue();
    boolean containsKey = containsKey(local_k);
    byte local_v = put(local_k, local_ov.byteValue());
    return containsKey ? Byte.valueOf(local_v) : null;
  }
  
  public Byte remove(Object local_ok)
  {
    int local_k = ((Integer)local_ok).intValue();
    boolean containsKey = containsKey(local_k);
    byte local_v = remove(local_k);
    return containsKey ? Byte.valueOf(local_v) : null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractInt2ByteFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */