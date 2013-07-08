package it.unimi.dsi.fastutil.objects;

import java.io.Serializable;

public class Reference2LongFunctions
{
  public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();
  
  public static <K> Reference2LongFunction<K> singleton(K key, long value)
  {
    return new Singleton(key, value);
  }
  
  public static <K> Reference2LongFunction<K> singleton(K key, Long value)
  {
    return new Singleton(key, value.longValue());
  }
  
  public static <K> Reference2LongFunction<K> synchronize(Reference2LongFunction<K> local_f)
  {
    return new SynchronizedFunction(local_f);
  }
  
  public static <K> Reference2LongFunction<K> synchronize(Reference2LongFunction<K> local_f, Object sync)
  {
    return new SynchronizedFunction(local_f, sync);
  }
  
  public static <K> Reference2LongFunction<K> unmodifiable(Reference2LongFunction<K> local_f)
  {
    return new UnmodifiableFunction(local_f);
  }
  
  public static class UnmodifiableFunction<K>
    extends AbstractReference2LongFunction<K>
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Reference2LongFunction<K> function;
    
    protected UnmodifiableFunction(Reference2LongFunction<K> local_f)
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
    
    public long defaultReturnValue()
    {
      return this.function.defaultReturnValue();
    }
    
    public void defaultReturnValue(long defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public long put(K local_k, long local_v)
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
    
    public long removeLong(Object local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public long getLong(Object local_k)
    {
      return this.function.getLong(local_k);
    }
  }
  
  public static class SynchronizedFunction<K>
    extends AbstractReference2LongFunction<K>
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Reference2LongFunction<K> function;
    protected final Object sync;
    
    protected SynchronizedFunction(Reference2LongFunction<K> local_f, Object sync)
    {
      if (local_f == null) {
        throw new NullPointerException();
      }
      this.function = local_f;
      this.sync = sync;
    }
    
    protected SynchronizedFunction(Reference2LongFunction<K> local_f)
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
    
    public long defaultReturnValue()
    {
      synchronized (this.sync)
      {
        return this.function.defaultReturnValue();
      }
    }
    
    public void defaultReturnValue(long defRetValue)
    {
      synchronized (this.sync)
      {
        this.function.defaultReturnValue(defRetValue);
      }
    }
    
    public long put(K local_k, long local_v)
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
    
    public Long put(K local_k, Long local_v)
    {
      synchronized (this.sync)
      {
        return (Long)this.function.put(local_k, local_v);
      }
    }
    
    public Long get(Object local_k)
    {
      synchronized (this.sync)
      {
        return (Long)this.function.get(local_k);
      }
    }
    
    public Long remove(Object local_k)
    {
      synchronized (this.sync)
      {
        return (Long)this.function.remove(local_k);
      }
    }
    
    public long removeLong(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.function.removeLong(local_k);
      }
    }
    
    public long getLong(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.function.getLong(local_k);
      }
    }
  }
  
  public static class Singleton<K>
    extends AbstractReference2LongFunction<K>
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final K key;
    protected final long value;
    
    protected Singleton(K key, long value)
    {
      this.key = key;
      this.value = value;
    }
    
    public boolean containsKey(Object local_k)
    {
      return this.key == local_k;
    }
    
    public long getLong(Object local_k)
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
  
  public static class EmptyFunction<K>
    extends AbstractReference2LongFunction<K>
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public long getLong(Object local_k)
    {
      return 0L;
    }
    
    public boolean containsKey(Object local_k)
    {
      return false;
    }
    
    public long defaultReturnValue()
    {
      return 0L;
    }
    
    public void defaultReturnValue(long defRetValue)
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
      return Reference2LongFunctions.EMPTY_FUNCTION;
    }
    
    public Object clone()
    {
      return Reference2LongFunctions.EMPTY_FUNCTION;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2LongFunctions
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */