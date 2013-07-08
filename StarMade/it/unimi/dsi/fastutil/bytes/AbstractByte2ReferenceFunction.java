package it.unimi.dsi.fastutil.bytes;

import java.io.Serializable;

public abstract class AbstractByte2ReferenceFunction<V>
  implements Byte2ReferenceFunction<V>, Serializable
{
  public static final long serialVersionUID = -4940583368468432370L;
  protected V defRetValue;
  
  public void defaultReturnValue(V local_rv)
  {
    this.defRetValue = local_rv;
  }
  
  public V defaultReturnValue()
  {
    return this.defRetValue;
  }
  
  public V put(byte key, V value)
  {
    throw new UnsupportedOperationException();
  }
  
  public V remove(byte key)
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
  
  public V get(Object local_ok)
  {
    byte local_k = ((Byte)local_ok).byteValue();
    return containsKey(local_k) ? get(local_k) : null;
  }
  
  public V put(Byte local_ok, V local_ov)
  {
    byte local_k = local_ok.byteValue();
    boolean containsKey = containsKey(local_k);
    V local_v = put(local_k, local_ov);
    return containsKey ? local_v : null;
  }
  
  public V remove(Object local_ok)
  {
    byte local_k = ((Byte)local_ok).byteValue();
    boolean containsKey = containsKey(local_k);
    V local_v = remove(local_k);
    return containsKey ? local_v : null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByte2ReferenceFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */