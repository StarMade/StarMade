package it.unimi.dsi.fastutil.longs;

import java.io.Serializable;

public abstract class AbstractLong2CharFunction
  implements Long2CharFunction, Serializable
{
  public static final long serialVersionUID = -4940583368468432370L;
  protected char defRetValue;
  
  public void defaultReturnValue(char local_rv)
  {
    this.defRetValue = local_rv;
  }
  
  public char defaultReturnValue()
  {
    return this.defRetValue;
  }
  
  public char put(long key, char value)
  {
    throw new UnsupportedOperationException();
  }
  
  public char remove(long key)
  {
    throw new UnsupportedOperationException();
  }
  
  public void clear()
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean containsKey(Object local_ok)
  {
    return containsKey(((Long)local_ok).longValue());
  }
  
  public Character get(Object local_ok)
  {
    long local_k = ((Long)local_ok).longValue();
    return containsKey(local_k) ? Character.valueOf(get(local_k)) : null;
  }
  
  public Character put(Long local_ok, Character local_ov)
  {
    long local_k = local_ok.longValue();
    boolean containsKey = containsKey(local_k);
    char local_v = put(local_k, local_ov.charValue());
    return containsKey ? Character.valueOf(local_v) : null;
  }
  
  public Character remove(Object local_ok)
  {
    long local_k = ((Long)local_ok).longValue();
    boolean containsKey = containsKey(local_k);
    char local_v = remove(local_k);
    return containsKey ? Character.valueOf(local_v) : null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLong2CharFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */