package it.unimi.dsi.fastutil.shorts;

import java.io.Serializable;

public class Short2DoubleFunctions
{
  public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();
  
  public static Short2DoubleFunction singleton(short key, double value)
  {
    return new Singleton(key, value);
  }
  
  public static Short2DoubleFunction singleton(Short key, Double value)
  {
    return new Singleton(key.shortValue(), value.doubleValue());
  }
  
  public static Short2DoubleFunction synchronize(Short2DoubleFunction local_f)
  {
    return new SynchronizedFunction(local_f);
  }
  
  public static Short2DoubleFunction synchronize(Short2DoubleFunction local_f, Object sync)
  {
    return new SynchronizedFunction(local_f, sync);
  }
  
  public static Short2DoubleFunction unmodifiable(Short2DoubleFunction local_f)
  {
    return new UnmodifiableFunction(local_f);
  }
  
  public static class UnmodifiableFunction
    extends AbstractShort2DoubleFunction
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Short2DoubleFunction function;
    
    protected UnmodifiableFunction(Short2DoubleFunction local_f)
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
    
    public double defaultReturnValue()
    {
      return this.function.defaultReturnValue();
    }
    
    public void defaultReturnValue(double defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public double put(short local_k, double local_v)
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
    
    public double remove(short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public double get(short local_k)
    {
      return this.function.get(local_k);
    }
    
    public boolean containsKey(Object local_ok)
    {
      return this.function.containsKey(local_ok);
    }
  }
  
  public static class SynchronizedFunction
    extends AbstractShort2DoubleFunction
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Short2DoubleFunction function;
    protected final Object sync;
    
    protected SynchronizedFunction(Short2DoubleFunction local_f, Object sync)
    {
      if (local_f == null) {
        throw new NullPointerException();
      }
      this.function = local_f;
      this.sync = sync;
    }
    
    protected SynchronizedFunction(Short2DoubleFunction local_f)
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
    
    public double defaultReturnValue()
    {
      synchronized (this.sync)
      {
        return this.function.defaultReturnValue();
      }
    }
    
    public void defaultReturnValue(double defRetValue)
    {
      synchronized (this.sync)
      {
        this.function.defaultReturnValue(defRetValue);
      }
    }
    
    public double put(short local_k, double local_v)
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
    
    public Double put(Short local_k, Double local_v)
    {
      synchronized (this.sync)
      {
        return (Double)this.function.put(local_k, local_v);
      }
    }
    
    public Double get(Object local_k)
    {
      synchronized (this.sync)
      {
        return (Double)this.function.get(local_k);
      }
    }
    
    public Double remove(Object local_k)
    {
      synchronized (this.sync)
      {
        return (Double)this.function.remove(local_k);
      }
    }
    
    public double remove(short local_k)
    {
      synchronized (this.sync)
      {
        return this.function.remove(local_k);
      }
    }
    
    public double get(short local_k)
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
    extends AbstractShort2DoubleFunction
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final short key;
    protected final double value;
    
    protected Singleton(short key, double value)
    {
      this.key = key;
      this.value = value;
    }
    
    public boolean containsKey(short local_k)
    {
      return this.key == local_k;
    }
    
    public double get(short local_k)
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
    extends AbstractShort2DoubleFunction
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public double get(short local_k)
    {
      return 0.0D;
    }
    
    public boolean containsKey(short local_k)
    {
      return false;
    }
    
    public double defaultReturnValue()
    {
      return 0.0D;
    }
    
    public void defaultReturnValue(double defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public Double get(Object local_k)
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
      return Short2DoubleFunctions.EMPTY_FUNCTION;
    }
    
    public Object clone()
    {
      return Short2DoubleFunctions.EMPTY_FUNCTION;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2DoubleFunctions
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */