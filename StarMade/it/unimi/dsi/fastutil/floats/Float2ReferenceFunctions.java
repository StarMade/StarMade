package it.unimi.dsi.fastutil.floats;

import java.io.Serializable;

public class Float2ReferenceFunctions
{
  public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();
  
  public static <V> Float2ReferenceFunction<V> singleton(float key, V value)
  {
    return new Singleton(key, value);
  }
  
  public static <V> Float2ReferenceFunction<V> singleton(Float key, V value)
  {
    return new Singleton(key.floatValue(), value);
  }
  
  public static <V> Float2ReferenceFunction<V> synchronize(Float2ReferenceFunction<V> local_f)
  {
    return new SynchronizedFunction(local_f);
  }
  
  public static <V> Float2ReferenceFunction<V> synchronize(Float2ReferenceFunction<V> local_f, Object sync)
  {
    return new SynchronizedFunction(local_f, sync);
  }
  
  public static <V> Float2ReferenceFunction<V> unmodifiable(Float2ReferenceFunction<V> local_f)
  {
    return new UnmodifiableFunction(local_f);
  }
  
  public static class UnmodifiableFunction<V>
    extends AbstractFloat2ReferenceFunction<V>
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Float2ReferenceFunction<V> function;
    
    protected UnmodifiableFunction(Float2ReferenceFunction<V> local_f)
    {
      if (local_f == null) {
        throw new NullPointerException();
      }
      this.function = local_f;
    }
    
    public int size()
    {
      return this.function.size();
    }
    
    public boolean containsKey(float local_k)
    {
      return this.function.containsKey(local_k);
    }
    
    public V defaultReturnValue()
    {
      return this.function.defaultReturnValue();
    }
    
    public void defaultReturnValue(V defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public V put(float local_k, V local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void clear()
    {
      throw new UnsupportedOperationException();
    }
    
    public String toString()
    {
      return this.function.toString();
    }
    
    public V remove(float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public V get(float local_k)
    {
      return this.function.get(local_k);
    }
    
    public boolean containsKey(Object local_ok)
    {
      return this.function.containsKey(local_ok);
    }
    
    public V remove(Object local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public V get(Object local_k)
    {
      return this.function.get(local_k);
    }
  }
  
  public static class SynchronizedFunction<V>
    extends AbstractFloat2ReferenceFunction<V>
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Float2ReferenceFunction<V> function;
    protected final Object sync;
    
    protected SynchronizedFunction(Float2ReferenceFunction<V> local_f, Object sync)
    {
      if (local_f == null) {
        throw new NullPointerException();
      }
      this.function = local_f;
      this.sync = sync;
    }
    
    protected SynchronizedFunction(Float2ReferenceFunction<V> local_f)
    {
      if (local_f == null) {
        throw new NullPointerException();
      }
      this.function = local_f;
      this.sync = this;
    }
    
    public int size()
    {
      synchronized (this.sync)
      {
        return this.function.size();
      }
    }
    
    public boolean containsKey(float local_k)
    {
      synchronized (this.sync)
      {
        return this.function.containsKey(local_k);
      }
    }
    
    public V defaultReturnValue()
    {
      synchronized (this.sync)
      {
        return this.function.defaultReturnValue();
      }
    }
    
    public void defaultReturnValue(V defRetValue)
    {
      synchronized (this.sync)
      {
        this.function.defaultReturnValue(defRetValue);
      }
    }
    
    public V put(float local_k, V local_v)
    {
      synchronized (this.sync)
      {
        return this.function.put(local_k, local_v);
      }
    }
    
    public void clear()
    {
      synchronized (this.sync)
      {
        this.function.clear();
      }
    }
    
    public String toString()
    {
      synchronized (this.sync)
      {
        return this.function.toString();
      }
    }
    
    public V put(Float local_k, V local_v)
    {
      synchronized (this.sync)
      {
        return this.function.put(local_k, local_v);
      }
    }
    
    public V get(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.function.get(local_k);
      }
    }
    
    public V remove(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.function.remove(local_k);
      }
    }
    
    public V remove(float local_k)
    {
      synchronized (this.sync)
      {
        return this.function.remove(local_k);
      }
    }
    
    public V get(float local_k)
    {
      synchronized (this.sync)
      {
        return this.function.get(local_k);
      }
    }
    
    public boolean containsKey(Object local_ok)
    {
      synchronized (this.sync)
      {
        return this.function.containsKey(local_ok);
      }
    }
  }
  
  public static class Singleton<V>
    extends AbstractFloat2ReferenceFunction<V>
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final float key;
    protected final V value;
    
    protected Singleton(float key, V value)
    {
      this.key = key;
      this.value = value;
    }
    
    public boolean containsKey(float local_k)
    {
      return this.key == local_k;
    }
    
    public V get(float local_k)
    {
      if (this.key == local_k) {
        return this.value;
      }
      return this.defRetValue;
    }
    
    public int size()
    {
      return 1;
    }
    
    public Object clone()
    {
      return this;
    }
  }
  
  public static class EmptyFunction<V>
    extends AbstractFloat2ReferenceFunction<V>
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public V get(float local_k)
    {
      return null;
    }
    
    public boolean containsKey(float local_k)
    {
      return false;
    }
    
    public V defaultReturnValue()
    {
      return null;
    }
    
    public void defaultReturnValue(V defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public V get(Object local_k)
    {
      return null;
    }
    
    public int size()
    {
      return 0;
    }
    
    public void clear() {}
    
    private Object readResolve()
    {
      return Float2ReferenceFunctions.EMPTY_FUNCTION;
    }
    
    public Object clone()
    {
      return Float2ReferenceFunctions.EMPTY_FUNCTION;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2ReferenceFunctions
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */