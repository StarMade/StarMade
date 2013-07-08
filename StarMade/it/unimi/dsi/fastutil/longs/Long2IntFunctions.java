package it.unimi.dsi.fastutil.longs;

import java.io.Serializable;

public class Long2IntFunctions
{
  public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();
  
  public static Long2IntFunction singleton(long key, int value)
  {
    return new Singleton(key, value);
  }
  
  public static Long2IntFunction singleton(Long key, Integer value)
  {
    return new Singleton(key.longValue(), value.intValue());
  }
  
  public static Long2IntFunction synchronize(Long2IntFunction local_f)
  {
    return new SynchronizedFunction(local_f);
  }
  
  public static Long2IntFunction synchronize(Long2IntFunction local_f, Object sync)
  {
    return new SynchronizedFunction(local_f, sync);
  }
  
  public static Long2IntFunction unmodifiable(Long2IntFunction local_f)
  {
    return new UnmodifiableFunction(local_f);
  }
  
  public static class UnmodifiableFunction
    extends AbstractLong2IntFunction
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Long2IntFunction function;
    
    protected UnmodifiableFunction(Long2IntFunction local_f)
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
    
    public boolean containsKey(long local_k)
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
    
    public int put(long local_k, int local_v)
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
    
    public int remove(long local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public int get(long local_k)
    {
      return this.function.get(local_k);
    }
    
    public boolean containsKey(Object local_ok)
    {
      return this.function.containsKey(local_ok);
    }
  }
  
  public static class SynchronizedFunction
    extends AbstractLong2IntFunction
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Long2IntFunction function;
    protected final Object sync;
    
    protected SynchronizedFunction(Long2IntFunction local_f, Object sync)
    {
      if (local_f == null) {
        throw new NullPointerException();
      }
      this.function = local_f;
      this.sync = sync;
    }
    
    protected SynchronizedFunction(Long2IntFunction local_f)
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
    
    public boolean containsKey(long local_k)
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
    
    public int put(long local_k, int local_v)
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
    
    public Integer put(Long local_k, Integer local_v)
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
    
    public int remove(long local_k)
    {
      synchronized (this.sync)
      {
        return this.function.remove(local_k);
      }
    }
    
    public int get(long local_k)
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
    extends AbstractLong2IntFunction
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final long key;
    protected final int value;
    
    protected Singleton(long key, int value)
    {
      this.key = key;
      this.value = value;
    }
    
    public boolean containsKey(long local_k)
    {
      return this.key == local_k;
    }
    
    public int get(long local_k)
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
    extends AbstractLong2IntFunction
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public int get(long local_k)
    {
      return 0;
    }
    
    public boolean containsKey(long local_k)
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
    
    public Integer get(Object local_k)
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
      return Long2IntFunctions.EMPTY_FUNCTION;
    }
    
    public Object clone()
    {
      return Long2IntFunctions.EMPTY_FUNCTION;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2IntFunctions
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */