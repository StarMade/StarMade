package it.unimi.dsi.fastutil.ints;

import java.io.Serializable;

public class Int2BooleanFunctions
{
  public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();
  
  public static Int2BooleanFunction singleton(int key, boolean value)
  {
    return new Singleton(key, value);
  }
  
  public static Int2BooleanFunction singleton(Integer key, Boolean value)
  {
    return new Singleton(key.intValue(), value.booleanValue());
  }
  
  public static Int2BooleanFunction synchronize(Int2BooleanFunction local_f)
  {
    return new SynchronizedFunction(local_f);
  }
  
  public static Int2BooleanFunction synchronize(Int2BooleanFunction local_f, Object sync)
  {
    return new SynchronizedFunction(local_f, sync);
  }
  
  public static Int2BooleanFunction unmodifiable(Int2BooleanFunction local_f)
  {
    return new UnmodifiableFunction(local_f);
  }
  
  public static class UnmodifiableFunction
    extends AbstractInt2BooleanFunction
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Int2BooleanFunction function;
    
    protected UnmodifiableFunction(Int2BooleanFunction local_f)
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
    
    public boolean containsKey(int local_k)
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
    
    public boolean put(int local_k, boolean local_v)
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
    
    public boolean remove(int local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean get(int local_k)
    {
      return this.function.get(local_k);
    }
    
    public boolean containsKey(Object local_ok)
    {
      return this.function.containsKey(local_ok);
    }
  }
  
  public static class SynchronizedFunction
    extends AbstractInt2BooleanFunction
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Int2BooleanFunction function;
    protected final Object sync;
    
    protected SynchronizedFunction(Int2BooleanFunction local_f, Object sync)
    {
      if (local_f == null) {
        throw new NullPointerException();
      }
      this.function = local_f;
      this.sync = sync;
    }
    
    protected SynchronizedFunction(Int2BooleanFunction local_f)
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
    
    public boolean containsKey(int local_k)
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
    
    public boolean put(int local_k, boolean local_v)
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
    
    public Boolean put(Integer local_k, Boolean local_v)
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
    
    public boolean remove(int local_k)
    {
      synchronized (this.sync)
      {
        return this.function.remove(local_k);
      }
    }
    
    public boolean get(int local_k)
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
    extends AbstractInt2BooleanFunction
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final int key;
    protected final boolean value;
    
    protected Singleton(int key, boolean value)
    {
      this.key = key;
      this.value = value;
    }
    
    public boolean containsKey(int local_k)
    {
      return this.key == local_k;
    }
    
    public boolean get(int local_k)
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
    extends AbstractInt2BooleanFunction
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean get(int local_k)
    {
      return false;
    }
    
    public boolean containsKey(int local_k)
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
      return Int2BooleanFunctions.EMPTY_FUNCTION;
    }
    
    public Object clone()
    {
      return Int2BooleanFunctions.EMPTY_FUNCTION;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2BooleanFunctions
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */