package it.unimi.dsi.fastutil.doubles;

import java.io.Serializable;

public class Double2BooleanFunctions
{
  public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();
  
  public static Double2BooleanFunction singleton(double key, boolean value)
  {
    return new Singleton(key, value);
  }
  
  public static Double2BooleanFunction singleton(Double key, Boolean value)
  {
    return new Singleton(key.doubleValue(), value.booleanValue());
  }
  
  public static Double2BooleanFunction synchronize(Double2BooleanFunction local_f)
  {
    return new SynchronizedFunction(local_f);
  }
  
  public static Double2BooleanFunction synchronize(Double2BooleanFunction local_f, Object sync)
  {
    return new SynchronizedFunction(local_f, sync);
  }
  
  public static Double2BooleanFunction unmodifiable(Double2BooleanFunction local_f)
  {
    return new UnmodifiableFunction(local_f);
  }
  
  public static class UnmodifiableFunction
    extends AbstractDouble2BooleanFunction
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Double2BooleanFunction function;
    
    protected UnmodifiableFunction(Double2BooleanFunction local_f)
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
    
    public boolean defaultReturnValue()
    {
      return this.function.defaultReturnValue();
    }
    
    public void defaultReturnValue(boolean defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean put(double local_k, boolean local_v)
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
    
    public boolean remove(double local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean get(double local_k)
    {
      return this.function.get(local_k);
    }
    
    public boolean containsKey(Object local_ok)
    {
      return this.function.containsKey(local_ok);
    }
  }
  
  public static class SynchronizedFunction
    extends AbstractDouble2BooleanFunction
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Double2BooleanFunction function;
    protected final Object sync;
    
    protected SynchronizedFunction(Double2BooleanFunction local_f, Object sync)
    {
      if (local_f == null) {
        throw new NullPointerException();
      }
      this.function = local_f;
      this.sync = sync;
    }
    
    protected SynchronizedFunction(Double2BooleanFunction local_f)
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
    
    public boolean defaultReturnValue()
    {
      synchronized (this.sync)
      {
        return this.function.defaultReturnValue();
      }
    }
    
    public void defaultReturnValue(boolean defRetValue)
    {
      synchronized (this.sync)
      {
        this.function.defaultReturnValue(defRetValue);
      }
    }
    
    public boolean put(double local_k, boolean local_v)
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
    
    public Boolean put(Double local_k, Boolean local_v)
    {
      synchronized (this.sync)
      {
        return (Boolean)this.function.put(local_k, local_v);
      }
    }
    
    public Boolean get(Object local_k)
    {
      synchronized (this.sync)
      {
        return (Boolean)this.function.get(local_k);
      }
    }
    
    public Boolean remove(Object local_k)
    {
      synchronized (this.sync)
      {
        return (Boolean)this.function.remove(local_k);
      }
    }
    
    public boolean remove(double local_k)
    {
      synchronized (this.sync)
      {
        return this.function.remove(local_k);
      }
    }
    
    public boolean get(double local_k)
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
    extends AbstractDouble2BooleanFunction
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final double key;
    protected final boolean value;
    
    protected Singleton(double key, boolean value)
    {
      this.key = key;
      this.value = value;
    }
    
    public boolean containsKey(double local_k)
    {
      return this.key == local_k;
    }
    
    public boolean get(double local_k)
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
    extends AbstractDouble2BooleanFunction
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean get(double local_k)
    {
      return false;
    }
    
    public boolean containsKey(double local_k)
    {
      return false;
    }
    
    public boolean defaultReturnValue()
    {
      return false;
    }
    
    public void defaultReturnValue(boolean defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public Boolean get(Object local_k)
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
      return Double2BooleanFunctions.EMPTY_FUNCTION;
    }
    
    public Object clone()
    {
      return Double2BooleanFunctions.EMPTY_FUNCTION;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2BooleanFunctions
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */