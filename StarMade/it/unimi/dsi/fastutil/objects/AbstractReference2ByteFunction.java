package it.unimi.dsi.fastutil.objects;

import java.io.Serializable;

public abstract class AbstractReference2ByteFunction<K>
  implements Reference2ByteFunction<K>, Serializable
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
  
  public byte put(K key, byte value)
  {
    throw new UnsupportedOperationException();
  }
  
  public byte removeByte(Object key)
  {
    throw new UnsupportedOperationException();
  }
  
  public void clear()
  {
    throw new UnsupportedOperationException();
  }
  
  public Byte get(Object local_ok)
  {
    Object local_k = local_ok;
    return containsKey(local_k) ? Byte.valueOf(getByte(local_k)) : null;
  }
  
  public Byte put(K local_ok, Byte local_ov)
  {
    K local_k = local_ok;
    boolean containsKey = containsKey(local_k);
    byte local_v = put(local_k, local_ov.byteValue());
    return containsKey ? Byte.valueOf(local_v) : null;
  }
  
  public Byte remove(Object local_ok)
  {
    Object local_k = local_ok;
    boolean containsKey = containsKey(local_k);
    byte local_v = removeByte(local_k);
    return containsKey ? Byte.valueOf(local_v) : null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReference2ByteFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */