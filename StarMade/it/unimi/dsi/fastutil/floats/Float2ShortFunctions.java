package it.unimi.dsi.fastutil.floats;

import java.io.Serializable;

public class Float2ShortFunctions
{
  public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();
  
  public static Float2ShortFunction singleton(float key, short value)
  {
    return new Singleton(key, value);
  }
  
  public static Float2ShortFunction singleton(Float key, Short value)
  {
    return new Singleton(key.floatValue(), value.shortValue());
  }
  
  public static Float2ShortFunction synchronize(Float2ShortFunction local_f)
  {
    return new SynchronizedFunction(local_f);
  }
  
  public static Float2ShortFunction synchronize(Float2ShortFunction local_f, Object sync)
  {
    return new SynchronizedFunction(local_f, sync);
  }
  
  public static Float2ShortFunction unmodifiable(Float2ShortFunction local_f)
  {
    return new UnmodifiableFunction(local_f);
  }
  
  public static class UnmodifiableFunction
    extends AbstractFloat2ShortFunction
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Float2ShortFunction function;
    
    protected UnmodifiableFunction(Float2ShortFunction local_f)
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
    
    public short defaultReturnValue()
    {
      return this.function.defaultReturnValue();
    }
    
    public void defaultReturnValue(short defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public short put(float local_k, short local_v)
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
    
    public short remove(float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public short get(float local_k)
    {
      return this.function.get(local_k);
    }
    
    public boolean containsKey(Object local_ok)
    {
      return this.function.containsKey(local_ok);
    }
  }
  
  public static class SynchronizedFunction
    extends AbstractFloat2ShortFunction
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Float2ShortFunction function;
    protected final Object sync;
    
    protected SynchronizedFunction(Float2ShortFunction local_f, Object sync)
    {
      if (local_f == null) {
        throw new NullPointerException();
      }
      this.function = local_f;
      this.sync = sync;
    }
    
    protected SynchronizedFunction(Float2ShortFunction local_f)
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
    
    public short put(float local_k, short local_v)
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
    
    public Short put(Float local_k, Short local_v)
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
    
    public short remove(float local_k)
    {
      synchronized (this.sync)
      {
        return this.function.remove(local_k);
      }
    }
    
    public short get(float local_k)
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
    extends AbstractFloat2ShortFunction
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final float key;
    protected final short value;
    
    protected Singleton(float key, short value)
    {
      this.key = key;
      this.value = value;
    }
    
    public boolean containsKey(float local_k)
    {
      return this.key == local_k;
    }
    
    public short get(float local_k)
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
    extends AbstractFloat2ShortFunction
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public short get(float local_k)
    {
      return 0;
    }
    
    public boolean containsKey(float local_k)
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
      return Float2ShortFunctions.EMPTY_FUNCTION;
    }
    
    public Object clone()
    {
      return Float2ShortFunctions.EMPTY_FUNCTION;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2ShortFunctions
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */