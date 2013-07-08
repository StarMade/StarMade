package it.unimi.dsi.fastutil.shorts;

import java.io.Serializable;

public class Short2IntFunctions
{
  public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();
  
  public static Short2IntFunction singleton(short key, int value)
  {
    return new Singleton(key, value);
  }
  
  public static Short2IntFunction singleton(Short key, Integer value)
  {
    return new Singleton(key.shortValue(), value.intValue());
  }
  
  public static Short2IntFunction synchronize(Short2IntFunction local_f)
  {
    return new SynchronizedFunction(local_f);
  }
  
  public static Short2IntFunction synchronize(Short2IntFunction local_f, Object sync)
  {
    return new SynchronizedFunction(local_f, sync);
  }
  
  public static Short2IntFunction unmodifiable(Short2IntFunction local_f)
  {
    return new UnmodifiableFunction(local_f);
  }
  
  public static class UnmodifiableFunction
    extends AbstractShort2IntFunction
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Short2IntFunction function;
    
    protected UnmodifiableFunction(Short2IntFunction local_f)
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
    
    public boolean containsKey(short local_k)
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
    
    public int put(short local_k, int local_v)
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
    
    public int remove(short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public int get(short local_k)
    {
      return this.function.get(local_k);
    }
    
    public boolean containsKey(Object local_ok)
    {
      return this.function.containsKey(local_ok);
    }
  }
  
  public static class SynchronizedFunction
    extends AbstractShort2IntFunction
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Short2IntFunction function;
    protected final Object sync;
    
    protected SynchronizedFunction(Short2IntFunction local_f, Object sync)
    {
      if (local_f == null) {
        throw new NullPointerException();
      }
      this.function = local_f;
      this.sync = sync;
    }
    
    protected SynchronizedFunction(Short2IntFunction local_f)
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
    
    public boolean containsKey(short local_k)
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
    
    public int put(short local_k, int local_v)
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
    
    public Integer put(Short local_k, Integer local_v)
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
    
    public int remove(short local_k)
    {
      synchronized (this.sync)
      {
        return this.function.remove(local_k);
      }
    }
    
    public int get(short local_k)
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
    extends AbstractShort2IntFunction
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final short key;
    protected final int value;
    
    protected Singleton(short key, int value)
    {
      this.key = key;
      this.value = value;
    }
    
    public boolean containsKey(short local_k)
    {
      return this.key == local_k;
    }
    
    public int get(short local_k)
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
    extends AbstractShort2IntFunction
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public int get(short local_k)
    {
      return 0;
    }
    
    public boolean containsKey(short local_k)
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
      return Short2IntFunctions.EMPTY_FUNCTION;
    }
    
    public Object clone()
    {
      return Short2IntFunctions.EMPTY_FUNCTION;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2IntFunctions
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */