package it.unimi.dsi.fastutil.ints;

import java.io.Serializable;

public abstract class AbstractInt2CharFunction
  implements Int2CharFunction, Serializable
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
  
  public char put(int key, char value)
  {
    throw new UnsupportedOperationException();
  }
  
  public char remove(int key)
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
  
  public Character get(Object local_ok)
  {
    int local_k = ((Integer)local_ok).intValue();
    return containsKey(local_k) ? Character.valueOf(get(local_k)) : null;
  }
  
  public Character put(Integer local_ok, Character local_ov)
  {
    int local_k = local_ok.intValue();
    boolean containsKey = containsKey(local_k);
    char local_v = put(local_k, local_ov.charValue());
    return containsKey ? Character.valueOf(local_v) : null;
  }
  
  public Character remove(Object local_ok)
  {
    int local_k = ((Integer)local_ok).intValue();
    boolean containsKey = containsKey(local_k);
    char local_v = remove(local_k);
    return containsKey ? Character.valueOf(local_v) : null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractInt2CharFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */