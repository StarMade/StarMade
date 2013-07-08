package it.unimi.dsi.fastutil.shorts;

import java.io.Serializable;

public class Short2ShortFunctions
{
  public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();
  
  public static Short2ShortFunction singleton(short key, short value)
  {
    return new Singleton(key, value);
  }
  
  public static Short2ShortFunction singleton(Short key, Short value)
  {
    return new Singleton(key.shortValue(), value.shortValue());
  }
  
  public static Short2ShortFunction synchronize(Short2ShortFunction local_f)
  {
    return new SynchronizedFunction(local_f);
  }
  
  public static Short2ShortFunction synchronize(Short2ShortFunction local_f, Object sync)
  {
    return new SynchronizedFunction(local_f, sync);
  }
  
  public static Short2ShortFunction unmodifiable(Short2ShortFunction local_f)
  {
    return new UnmodifiableFunction(local_f);
  }
  
  public static class UnmodifiableFunction
    extends AbstractShort2ShortFunction
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Short2ShortFunction function;
    
    protected UnmodifiableFunction(Short2ShortFunction local_f)
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
    
    public short defaultReturnValue()
    {
      return this.function.defaultReturnValue();
    }
    
    public void defaultReturnValue(short defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public short put(short local_k, short local_v)
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
    
    public short remove(short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public short get(short local_k)
    {
      return this.function.get(local_k);
    }
    
    public boolean containsKey(Object local_ok)
    {
      return this.function.containsKey(local_ok);
    }
  }
  
  public static class SynchronizedFunction
    extends AbstractShort2ShortFunction
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Short2ShortFunction function;
    protected final Object sync;
    
    protected SynchronizedFunction(Short2ShortFunction local_f, Object sync)
    {
      if (local_f == null) {
        throw new NullPointerException();
      }
      this.function = local_f;
      this.sync = sync;
    }
    
    protected SynchronizedFunction(Short2ShortFunction local_f)
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
    
    public short defaultReturnValue()
    {
      synchronized (this.sync)
      {
        return this.function.defaultReturnValue();
      }
    }
    
    public void defaultReturnValue(short defRetValue)
    {
      synchronized (this.sync)
      {
        this.function.defaultReturnValue(defRetValue);
      }
    }
    
    public short put(short local_k, short local_v)
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
    
    public Short put(Short local_k, Short local_v)
    {
      synchronized (this.sync)
      {
        return (Short)this.function.put(local_k, local_v);
      }
    }
    
    public Short get(Object local_k)
    {
      synchronized (this.sync)
      {
        return (Short)this.function.get(local_k);
      }
    }
    
    public Short remove(Object local_k)
    {
      synchronized (this.sync)
      {
        return (Short)this.function.remove(local_k);
      }
    }
    
    public short remove(short local_k)
    {
      synchronized (this.sync)
      {
        return this.function.remove(local_k);
      }
    }
    
    public short get(short local_k)
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
    extends AbstractShort2ShortFunction
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final short key;
    protected final short value;
    
    protected Singleton(short key, short value)
    {
      this.key = key;
      this.value = value;
    }
    
    public boolean containsKey(short local_k)
    {
      return this.key == local_k;
    }
    
    public short get(short local_k)
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
    extends AbstractShort2ShortFunction
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public short get(short local_k)
    {
      return 0;
    }
    
    public boolean containsKey(short local_k)
    {
      return false;
    }
    
    public short defaultReturnValue()
    {
      return 0;
    }
    
    public void defaultReturnValue(short defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public Short get(Object local_k)
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
      return Short2ShortFunctions.EMPTY_FUNCTION;
    }
    
    public Object clone()
    {
      return Short2ShortFunctions.EMPTY_FUNCTION;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2ShortFunctions
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */