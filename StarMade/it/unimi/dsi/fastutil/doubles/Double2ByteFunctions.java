package it.unimi.dsi.fastutil.doubles;

import java.io.Serializable;

public class Double2ByteFunctions
{
  public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();
  
  public static Double2ByteFunction singleton(double key, byte value)
  {
    return new Singleton(key, value);
  }
  
  public static Double2ByteFunction singleton(Double key, Byte value)
  {
    return new Singleton(key.doubleValue(), value.byteValue());
  }
  
  public static Double2ByteFunction synchronize(Double2ByteFunction local_f)
  {
    return new SynchronizedFunction(local_f);
  }
  
  public static Double2ByteFunction synchronize(Double2ByteFunction local_f, Object sync)
  {
    return new SynchronizedFunction(local_f, sync);
  }
  
  public static Double2ByteFunction unmodifiable(Double2ByteFunction local_f)
  {
    return new UnmodifiableFunction(local_f);
  }
  
  public static class UnmodifiableFunction
    extends AbstractDouble2ByteFunction
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Double2ByteFunction function;
    
    protected UnmodifiableFunction(Double2ByteFunction local_f)
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
    
    public byte defaultReturnValue()
    {
      return this.function.defaultReturnValue();
    }
    
    public void defaultReturnValue(byte defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public byte put(double local_k, byte local_v)
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
    
    public byte remove(double local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public byte get(double local_k)
    {
      return this.function.get(local_k);
    }
    
    public boolean containsKey(Object local_ok)
    {
      return this.function.containsKey(local_ok);
    }
  }
  
  public static class SynchronizedFunction
    extends AbstractDouble2ByteFunction
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Double2ByteFunction function;
    protected final Object sync;
    
    protected SynchronizedFunction(Double2ByteFunction local_f, Object sync)
    {
      if (local_f == null) {
        throw new NullPointerException();
      }
      this.function = local_f;
      this.sync = sync;
    }
    
    protected SynchronizedFunction(Double2ByteFunction local_f)
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
    
    public byte defaultReturnValue()
    {
      synchronized (this.sync)
      {
        return this.function.defaultReturnValue();
      }
    }
    
    public void defaultReturnValue(byte defRetValue)
    {
      synchronized (this.sync)
      {
        this.function.defaultReturnValue(defRetValue);
      }
    }
    
    public byte put(double local_k, byte local_v)
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
    
    public Byte put(Double local_k, Byte local_v)
    {
      synchronized (this.sync)
      {
        return (Byte)this.function.put(local_k, local_v);
      }
    }
    
    public Byte get(Object local_k)
    {
      synchronized (this.sync)
      {
        return (Byte)this.function.get(local_k);
      }
    }
    
    public Byte remove(Object local_k)
    {
      synchronized (this.sync)
      {
        return (Byte)this.function.remove(local_k);
      }
    }
    
    public byte remove(double local_k)
    {
      synchronized (this.sync)
      {
        return this.function.remove(local_k);
      }
    }
    
    public byte get(double local_k)
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
    extends AbstractDouble2ByteFunction
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final double key;
    protected final byte value;
    
    protected Singleton(double key, byte value)
    {
      this.key = key;
      this.value = value;
    }
    
    public boolean containsKey(double local_k)
    {
      return this.key == local_k;
    }
    
    public byte get(double local_k)
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
    extends AbstractDouble2ByteFunction
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public byte get(double local_k)
    {
      return 0;
    }
    
    public boolean containsKey(double local_k)
    {
      return false;
    }
    
    public byte defaultReturnValue()
    {
      return 0;
    }
    
    public void defaultReturnValue(byte defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public Byte get(Object local_k)
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
      return Double2ByteFunctions.EMPTY_FUNCTION;
    }
    
    public Object clone()
    {
      return Double2ByteFunctions.EMPTY_FUNCTION;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2ByteFunctions
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */