package it.unimi.dsi.fastutil.objects;

import java.io.Serializable;

public class Object2IntFunctions
{
  public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();
  
  public static <K> Object2IntFunction<K> singleton(K key, int value)
  {
    return new Singleton(key, value);
  }
  
  public static <K> Object2IntFunction<K> singleton(K key, Integer value)
  {
    return new Singleton(key, value.intValue());
  }
  
  public static <K> Object2IntFunction<K> synchronize(Object2IntFunction<K> local_f)
  {
    return new SynchronizedFunction(local_f);
  }
  
  public static <K> Object2IntFunction<K> synchronize(Object2IntFunction<K> local_f, Object sync)
  {
    return new SynchronizedFunction(local_f, sync);
  }
  
  public static <K> Object2IntFunction<K> unmodifiable(Object2IntFunction<K> local_f)
  {
    return new UnmodifiableFunction(local_f);
  }
  
  public static class UnmodifiableFunction<K>
    extends AbstractObject2IntFunction<K>
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Object2IntFunction<K> function;
    
    protected UnmodifiableFunction(Object2IntFunction<K> local_f)
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
    
    public boolean containsKey(Object local_k)
    {
      return this.function.containsKey(local_k);
    }
    
    public int defaultReturnValue()
    {
      return this.function.defaultReturnValue();
    }
    
    public void defaultReturnValue(int defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public int put(K local_k, int local_v)
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
    
    public int removeInt(Object local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public int getInt(Object local_k)
    {
      return this.function.getInt(local_k);
    }
  }
  
  public static class SynchronizedFunction<K>
    extends AbstractObject2IntFunction<K>
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Object2IntFunction<K> function;
    protected final Object sync;
    
    protected SynchronizedFunction(Object2IntFunction<K> local_f, Object sync)
    {
      if (local_f == null) {
        throw new NullPointerException();
      }
      this.function = local_f;
      this.sync = sync;
    }
    
    protected SynchronizedFunction(Object2IntFunction<K> local_f)
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
    
    public boolean containsKey(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.function.containsKey(local_k);
      }
    }
    
    public int defaultReturnValue()
    {
      synchronized (this.sync)
      {
        return this.function.defaultReturnValue();
      }
    }
    
    public void defaultReturnValue(int defRetValue)
    {
      synchronized (this.sync)
      {
        this.function.defaultReturnValue(defRetValue);
      }
    }
    
    public int put(K local_k, int local_v)
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
    
    public Integer put(K local_k, Integer local_v)
    {
      synchronized (this.sync)
      {
        return (Integer)this.function.put(local_k, local_v);
      }
    }
    
    public Integer get(Object local_k)
    {
      synchronized (this.sync)
      {
        return (Integer)this.function.get(local_k);
      }
    }
    
    public Integer remove(Object local_k)
    {
      synchronized (this.sync)
      {
        return (Integer)this.function.remove(local_k);
      }
    }
    
    public int removeInt(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.function.removeInt(local_k);
      }
    }
    
    public int getInt(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.function.getInt(local_k);
      }
    }
  }
  
  public static class Singleton<K>
    extends AbstractObject2IntFunction<K>
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final K key;
    protected final int value;
    
    protected Singleton(K key, int value)
    {
      this.key = key;
      this.value = value;
    }
    
    public boolean containsKey(Object local_k)
    {
      return this.key == null ? false : local_k == null ? true : this.key.equals(local_k);
    }
    
    public int getInt(Object local_k)
    {
      if (this.key == null ? local_k == null : this.key.equals(local_k)) {
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
  
  public static class EmptyFunction<K>
    extends AbstractObject2IntFunction<K>
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public int getInt(Object local_k)
    {
      return 0;
    }
    
    public boolean containsKey(Object local_k)
    {
      return false;
    }
    
    public int defaultReturnValue()
    {
      return 0;
    }
    
    public void defaultReturnValue(int defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public int size()
    {
      return 0;
    }
    
    public void clear() {}
    
    private Object readResolve()
    {
      return Object2IntFunctions.EMPTY_FUNCTION;
    }
    
    public Object clone()
    {
      return Object2IntFunctions.EMPTY_FUNCTION;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2IntFunctions
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */