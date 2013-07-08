package it.unimi.dsi.fastutil.shorts;

import java.io.Serializable;

public abstract class AbstractShort2CharFunction
  implements Short2CharFunction, Serializable
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
  
  public char put(short key, char value)
  {
    throw new UnsupportedOperationException();
  }
  
  public char remove(short key)
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
  
  public Character get(Object local_ok)
  {
    short local_k = ((Short)local_ok).shortValue();
    return containsKey(local_k) ? Character.valueOf(get(local_k)) : null;
  }
  
  public Character put(Short local_ok, Character local_ov)
  {
    short local_k = local_ok.shortValue();
    boolean containsKey = containsKey(local_k);
    char local_v = put(local_k, local_ov.charValue());
    return containsKey ? Character.valueOf(local_v) : null;
  }
  
  public Character remove(Object local_ok)
  {
    short local_k = ((Short)local_ok).shortValue();
    boolean containsKey = containsKey(local_k);
    char local_v = remove(local_k);
    return containsKey ? Character.valueOf(local_v) : null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShort2CharFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */