package it.unimi.dsi.fastutil.bytes;

import java.io.Serializable;

public class Byte2LongFunctions
{
  public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();
  
  public static Byte2LongFunction singleton(byte key, long value)
  {
    return new Singleton(key, value);
  }
  
  public static Byte2LongFunction singleton(Byte key, Long value)
  {
    return new Singleton(key.byteValue(), value.longValue());
  }
  
  public static Byte2LongFunction synchronize(Byte2LongFunction local_f)
  {
    return new SynchronizedFunction(local_f);
  }
  
  public static Byte2LongFunction synchronize(Byte2LongFunction local_f, Object sync)
  {
    return new SynchronizedFunction(local_f, sync);
  }
  
  public static Byte2LongFunction unmodifiable(Byte2LongFunction local_f)
  {
    return new UnmodifiableFunction(local_f);
  }
  
  public static class UnmodifiableFunction
    extends AbstractByte2LongFunction
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Byte2LongFunction function;
    
    protected UnmodifiableFunction(Byte2LongFunction local_f)
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
    
    public boolean containsKey(byte local_k)
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
    
    public long put(byte local_k, long local_v)
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
    
    public long remove(byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public long get(byte local_k)
    {
      return this.function.get(local_k);
    }
    
    public boolean containsKey(Object local_ok)
    {
      return this.function.containsKey(local_ok);
    }
  }
  
  public static class SynchronizedFunction
    extends AbstractByte2LongFunction
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Byte2LongFunction function;
    protected final Object sync;
    
    protected SynchronizedFunction(Byte2LongFunction local_f, Object sync)
    {
      if (local_f == null) {
        throw new NullPointerException();
      }
      this.function = local_f;
      this.sync = sync;
    }
    
    protected SynchronizedFunction(Byte2LongFunction local_f)
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
    
    public boolean containsKey(byte local_k)
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
    
    public long put(byte local_k, long local_v)
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
    
    public Long put(Byte local_k, Long local_v)
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
    
    public long remove(byte local_k)
    {
      synchronized (this.sync)
      {
        return this.function.remove(local_k);
      }
    }
    
    public long get(byte local_k)
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
    extends AbstractByte2LongFunction
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final byte key;
    protected final long value;
    
    protected Singleton(byte key, long value)
    {
      this.key = key;
      this.value = value;
    }
    
    public boolean containsKey(byte local_k)
    {
      return this.key == local_k;
    }
    
    public long get(byte local_k)
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
    extends AbstractByte2LongFunction
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public long get(byte local_k)
    {
      return 0L;
    }
    
    public boolean containsKey(byte local_k)
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
      return Byte2LongFunctions.EMPTY_FUNCTION;
    }
    
    public Object clone()
    {
      return Byte2LongFunctions.EMPTY_FUNCTION;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2LongFunctions
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */