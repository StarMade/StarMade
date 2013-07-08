package it.unimi.dsi.fastutil.chars;

import java.io.Serializable;

public abstract class AbstractChar2BooleanFunction
  implements Char2BooleanFunction, Serializable
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
  
  public boolean put(char key, boolean value)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean remove(char key)
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
  
  public Boolean get(Object local_ok)
  {
    char local_k = ((Character)local_ok).charValue();
    return containsKey(local_k) ? Boolean.valueOf(get(local_k)) : null;
  }
  
  public Boolean put(Character local_ok, Boolean local_ov)
  {
    char local_k = local_ok.charValue();
    boolean containsKey = containsKey(local_k);
    boolean local_v = put(local_k, local_ov.booleanValue());
    return containsKey ? Boolean.valueOf(local_v) : null;
  }
  
  public Boolean remove(Object local_ok)
  {
    char local_k = ((Character)local_ok).charValue();
    boolean containsKey = containsKey(local_k);
    boolean local_v = remove(local_k);
    return containsKey ? Boolean.valueOf(local_v) : null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractChar2BooleanFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */