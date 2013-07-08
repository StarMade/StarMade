package it.unimi.dsi.fastutil.bytes;

import java.io.Serializable;

public class Byte2BooleanFunctions
{
  public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();
  
  public static Byte2BooleanFunction singleton(byte key, boolean value)
  {
    return new Singleton(key, value);
  }
  
  public static Byte2BooleanFunction singleton(Byte key, Boolean value)
  {
    return new Singleton(key.byteValue(), value.booleanValue());
  }
  
  public static Byte2BooleanFunction synchronize(Byte2BooleanFunction local_f)
  {
    return new SynchronizedFunction(local_f);
  }
  
  public static Byte2BooleanFunction synchronize(Byte2BooleanFunction local_f, Object sync)
  {
    return new SynchronizedFunction(local_f, sync);
  }
  
  public static Byte2BooleanFunction unmodifiable(Byte2BooleanFunction local_f)
  {
    return new UnmodifiableFunction(local_f);
  }
  
  public static class UnmodifiableFunction
    extends AbstractByte2BooleanFunction
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Byte2BooleanFunction function;
    
    protected UnmodifiableFunction(Byte2BooleanFunction local_f)
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
    
    public boolean defaultReturnValue()
    {
      return this.function.defaultReturnValue();
    }
    
    public void defaultReturnValue(boolean defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean put(byte local_k, boolean local_v)
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
    
    public boolean remove(byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean get(byte local_k)
    {
      return this.function.get(local_k);
    }
    
    public boolean containsKey(Object local_ok)
    {
      return this.function.containsKey(local_ok);
    }
  }
  
  public static class SynchronizedFunction
    extends AbstractByte2BooleanFunction
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Byte2BooleanFunction function;
    protected final Object sync;
    
    protected SynchronizedFunction(Byte2BooleanFunction local_f, Object sync)
    {
      if (local_f == null) {
        throw new NullPointerException();
      }
      this.function = local_f;
      this.sync = sync;
    }
    
    protected SynchronizedFunction(Byte2BooleanFunction local_f)
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
    
    public boolean put(byte local_k, boolean local_v)
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
    
    public Boolean put(Byte local_k, Boolean local_v)
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
    
    public boolean remove(byte local_k)
    {
      synchronized (this.sync)
      {
        return this.function.remove(local_k);
      }
    }
    
    public boolean get(byte local_k)
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
    extends AbstractByte2BooleanFunction
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final byte key;
    protected final boolean value;
    
    protected Singleton(byte key, boolean value)
    {
      this.key = key;
      this.value = value;
    }
    
    public boolean containsKey(byte local_k)
    {
      return this.key == local_k;
    }
    
    public boolean get(byte local_k)
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
    extends AbstractByte2BooleanFunction
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean get(byte local_k)
    {
      return false;
    }
    
    public boolean containsKey(byte local_k)
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
      return Byte2BooleanFunctions.EMPTY_FUNCTION;
    }
    
    public Object clone()
    {
      return Byte2BooleanFunctions.EMPTY_FUNCTION;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2BooleanFunctions
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */