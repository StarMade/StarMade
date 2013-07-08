package it.unimi.dsi.fastutil.chars;

import java.io.Serializable;

public abstract class AbstractChar2LongFunction
  implements Char2LongFunction, Serializable
{
  public static final long serialVersionUID = -4940583368468432370L;
  protected long defRetValue;
  
  public void defaultReturnValue(long local_rv)
  {
    this.defRetValue = local_rv;
  }
  
  public long defaultReturnValue()
  {
    return this.defRetValue;
  }
  
  public long put(char key, long value)
  {
    throw new UnsupportedOperationException();
  }
  
  public long remove(char key)
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
  
  public Long get(Object local_ok)
  {
    char local_k = ((Character)local_ok).charValue();
    return containsKey(local_k) ? Long.valueOf(get(local_k)) : null;
  }
  
  public Long put(Character local_ok, Long local_ov)
  {
    char local_k = local_ok.charValue();
    boolean containsKey = containsKey(local_k);
    long local_v = put(local_k, local_ov.longValue());
    return containsKey ? Long.valueOf(local_v) : null;
  }
  
  public Long remove(Object local_ok)
  {
    char local_k = ((Character)local_ok).charValue();
    boolean containsKey = containsKey(local_k);
    long local_v = remove(local_k);
    return containsKey ? Long.valueOf(local_v) : null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractChar2LongFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */