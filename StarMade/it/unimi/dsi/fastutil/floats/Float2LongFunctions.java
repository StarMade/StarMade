package it.unimi.dsi.fastutil.floats;

import java.io.Serializable;

public class Float2LongFunctions
{
  public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();
  
  public static Float2LongFunction singleton(float key, long value)
  {
    return new Singleton(key, value);
  }
  
  public static Float2LongFunction singleton(Float key, Long value)
  {
    return new Singleton(key.floatValue(), value.longValue());
  }
  
  public static Float2LongFunction synchronize(Float2LongFunction local_f)
  {
    return new SynchronizedFunction(local_f);
  }
  
  public static Float2LongFunction synchronize(Float2LongFunction local_f, Object sync)
  {
    return new SynchronizedFunction(local_f, sync);
  }
  
  public static Float2LongFunction unmodifiable(Float2LongFunction local_f)
  {
    return new UnmodifiableFunction(local_f);
  }
  
  public static class UnmodifiableFunction
    extends AbstractFloat2LongFunction
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Float2LongFunction function;
    
    protected UnmodifiableFunction(Float2LongFunction local_f)
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
    
    public long defaultReturnValue()
    {
      return this.function.defaultReturnValue();
    }
    
    public void defaultReturnValue(long defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public long put(float local_k, long local_v)
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
    
    public long remove(float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public long get(float local_k)
    {
      return this.function.get(local_k);
    }
    
    public boolean containsKey(Object local_ok)
    {
      return this.function.containsKey(local_ok);
    }
  }
  
  public static class SynchronizedFunction
    extends AbstractFloat2LongFunction
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Float2LongFunction function;
    protected final Object sync;
    
    protected SynchronizedFunction(Float2LongFunction local_f, Object sync)
    {
      if (local_f == null) {
        throw new NullPointerException();
      }
      this.function = local_f;
      this.sync = sync;
    }
    
    protected SynchronizedFunction(Float2LongFunction local_f)
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
    
    public long put(float local_k, long local_v)
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
    
    public Long put(Float local_k, Long local_v)
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
    
    public long remove(float local_k)
    {
      synchronized (this.sync)
      {
        return this.function.remove(local_k);
      }
    }
    
    public long get(float local_k)
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
  
  public static class Singleton
    extends AbstractFloat2LongFunction
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final float key;
    protected final long value;
    
    protected Singleton(float key, long value)
    {
      this.key = key;
      this.value = value;
    }
    
    public boolean containsKey(float local_k)
    {
      return this.key == local_k;
    }
    
    public long get(float local_k)
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
  
  public static class EmptyFunction
    extends AbstractFloat2LongFunction
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public long get(float local_k)
    {
      return 0L;
    }
    
    public boolean containsKey(float local_k)
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
    
    public Long get(Object local_k)
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
      return Float2LongFunctions.EMPTY_FUNCTION;
    }
    
    public Object clone()
    {
      return Float2LongFunctions.EMPTY_FUNCTION;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2LongFunctions
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */