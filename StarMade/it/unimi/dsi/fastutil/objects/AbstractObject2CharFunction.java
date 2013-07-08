package it.unimi.dsi.fastutil.objects;

import java.io.Serializable;

public abstract class AbstractObject2CharFunction<K>
  implements Object2CharFunction<K>, Serializable
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
  
  public char put(K key, char value)
  {
    throw new UnsupportedOperationException();
  }
  
  public char removeChar(Object key)
  {
    throw new UnsupportedOperationException();
  }
  
  public void clear()
  {
    throw new UnsupportedOperationException();
  }
  
  public Character get(Object local_ok)
  {
    Object local_k = local_ok;
    return containsKey(local_k) ? Character.valueOf(getChar(local_k)) : null;
  }
  
  public Character put(K local_ok, Character local_ov)
  {
    K local_k = local_ok;
    boolean containsKey = containsKey(local_k);
    char local_v = put(local_k, local_ov.charValue());
    return containsKey ? Character.valueOf(local_v) : null;
  }
  
  public Character remove(Object local_ok)
  {
    Object local_k = local_ok;
    boolean containsKey = containsKey(local_k);
    char local_v = removeChar(local_k);
    return containsKey ? Character.valueOf(local_v) : null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObject2CharFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */