package it.unimi.dsi.fastutil.shorts;

import java.io.Serializable;

public class Short2BooleanFunctions
{
  public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();
  
  public static Short2BooleanFunction singleton(short key, boolean value)
  {
    return new Singleton(key, value);
  }
  
  public static Short2BooleanFunction singleton(Short key, Boolean value)
  {
    return new Singleton(key.shortValue(), value.booleanValue());
  }
  
  public static Short2BooleanFunction synchronize(Short2BooleanFunction local_f)
  {
    return new SynchronizedFunction(local_f);
  }
  
  public static Short2BooleanFunction synchronize(Short2BooleanFunction local_f, Object sync)
  {
    return new SynchronizedFunction(local_f, sync);
  }
  
  public static Short2BooleanFunction unmodifiable(Short2BooleanFunction local_f)
  {
    return new UnmodifiableFunction(local_f);
  }
  
  public static class UnmodifiableFunction
    extends AbstractShort2BooleanFunction
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Short2BooleanFunction function;
    
    protected UnmodifiableFunction(Short2BooleanFunction local_f)
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
    
    public boolean defaultReturnValue()
    {
      return this.function.defaultReturnValue();
    }
    
    public void defaultReturnValue(boolean defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean put(short local_k, boolean local_v)
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
    
    public boolean remove(short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean get(short local_k)
    {
      return this.function.get(local_k);
    }
    
    public boolean containsKey(Object local_ok)
    {
      return this.function.containsKey(local_ok);
    }
  }
  
  public static class SynchronizedFunction
    extends AbstractShort2BooleanFunction
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Short2BooleanFunction function;
    protected final Object sync;
    
    protected SynchronizedFunction(Short2BooleanFunction local_f, Object sync)
    {
      if (local_f == null) {
        throw new NullPointerException();
      }
      this.function = local_f;
      this.sync = sync;
    }
    
    protected SynchronizedFunction(Short2BooleanFunction local_f)
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
    
    public boolean put(short local_k, boolean local_v)
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
    
    public Boolean put(Short local_k, Boolean local_v)
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
    
    public boolean remove(short local_k)
    {
      synchronized (this.sync)
      {
        return this.function.remove(local_k);
      }
    }
    
    public boolean get(short local_k)
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
    extends AbstractShort2BooleanFunction
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final short key;
    protected final boolean value;
    
    protected Singleton(short key, boolean value)
    {
      this.key = key;
      this.value = value;
    }
    
    public boolean containsKey(short local_k)
    {
      return this.key == local_k;
    }
    
    public boolean get(short local_k)
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
    extends AbstractShort2BooleanFunction
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean get(short local_k)
    {
      return false;
    }
    
    public boolean containsKey(short local_k)
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
      return Short2BooleanFunctions.EMPTY_FUNCTION;
    }
    
    public Object clone()
    {
      return Short2BooleanFunctions.EMPTY_FUNCTION;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2BooleanFunctions
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */