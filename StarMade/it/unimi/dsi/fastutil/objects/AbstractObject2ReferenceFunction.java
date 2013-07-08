package it.unimi.dsi.fastutil.objects;

import java.io.Serializable;

public abstract class AbstractObject2ReferenceFunction<K, V>
  implements Object2ReferenceFunction<K, V>, Serializable
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
  
  public V put(K key, V value)
  {
    throw new UnsupportedOperationException();
  }
  
  public V remove(Object key)
  {
    throw new UnsupportedOperationException();
  }
  
  public void clear()
  {
    throw new UnsupportedOperationException();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObject2ReferenceFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */