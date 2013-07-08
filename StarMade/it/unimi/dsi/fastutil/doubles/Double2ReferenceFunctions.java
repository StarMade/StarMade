package it.unimi.dsi.fastutil.doubles;

import java.io.Serializable;

public class Double2ReferenceFunctions
{
  public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();
  
  public static <V> Double2ReferenceFunction<V> singleton(double key, V value)
  {
    return new Singleton(key, value);
  }
  
  public static <V> Double2ReferenceFunction<V> singleton(Double key, V value)
  {
    return new Singleton(key.doubleValue(), value);
  }
  
  public static <V> Double2ReferenceFunction<V> synchronize(Double2ReferenceFunction<V> local_f)
  {
    return new SynchronizedFunction(local_f);
  }
  
  public static <V> Double2ReferenceFunction<V> synchronize(Double2ReferenceFunction<V> local_f, Object sync)
  {
    return new SynchronizedFunction(local_f, sync);
  }
  
  public static <V> Double2ReferenceFunction<V> unmodifiable(Double2ReferenceFunction<V> local_f)
  {
    return new UnmodifiableFunction(local_f);
  }
  
  public static class UnmodifiableFunction<V>
    extends AbstractDouble2ReferenceFunction<V>
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Double2ReferenceFunction<V> function;
    
    protected UnmodifiableFunction(Double2ReferenceFunction<V> local_f)
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
    
    public boolean containsKey(double local_k)
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
    
    public V put(double local_k, V local_v)
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
    
    public V remove(double local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public V get(double local_k)
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
    extends AbstractDouble2ReferenceFunction<V>
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Double2ReferenceFunction<V> function;
    protected final Object sync;
    
    protected SynchronizedFunction(Double2ReferenceFunction<V> local_f, Object sync)
    {
      if (local_f == null) {
        throw new NullPointerException();
      }
      this.function = local_f;
      this.sync = sync;
    }
    
    protected SynchronizedFunction(Double2ReferenceFunction<V> local_f)
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
    
    public boolean containsKey(double local_k)
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
    
    public V put(double local_k, V local_v)
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
    
    public V put(Double local_k, V local_v)
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
    
    public V remove(double local_k)
    {
      synchronized (this.sync)
      {
        return this.function.remove(local_k);
      }
    }
    
    public V get(double local_k)
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
    extends AbstractDouble2ReferenceFunction<V>
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final double key;
    protected final V value;
    
    protected Singleton(double key, V value)
    {
      this.key = key;
      this.value = value;
    }
    
    public boolean containsKey(double local_k)
    {
      return this.key == local_k;
    }
    
    public V get(double local_k)
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
    extends AbstractDouble2ReferenceFunction<V>
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public V get(double local_k)
    {
      return null;
    }
    
    public boolean containsKey(double local_k)
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
      return Double2ReferenceFunctions.EMPTY_FUNCTION;
    }
    
    public Object clone()
    {
      return Double2ReferenceFunctions.EMPTY_FUNCTION;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2ReferenceFunctions
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */