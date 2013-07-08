package it.unimi.dsi.fastutil.bytes;

import java.io.Serializable;

public class Byte2ByteFunctions
{
  public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();
  
  public static Byte2ByteFunction singleton(byte key, byte value)
  {
    return new Singleton(key, value);
  }
  
  public static Byte2ByteFunction singleton(Byte key, Byte value)
  {
    return new Singleton(key.byteValue(), value.byteValue());
  }
  
  public static Byte2ByteFunction synchronize(Byte2ByteFunction local_f)
  {
    return new SynchronizedFunction(local_f);
  }
  
  public static Byte2ByteFunction synchronize(Byte2ByteFunction local_f, Object sync)
  {
    return new SynchronizedFunction(local_f, sync);
  }
  
  public static Byte2ByteFunction unmodifiable(Byte2ByteFunction local_f)
  {
    return new UnmodifiableFunction(local_f);
  }
  
  public static class UnmodifiableFunction
    extends AbstractByte2ByteFunction
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Byte2ByteFunction function;
    
    protected UnmodifiableFunction(Byte2ByteFunction local_f)
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
    
    public byte defaultReturnValue()
    {
      return this.function.defaultReturnValue();
    }
    
    public void defaultReturnValue(byte defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public byte put(byte local_k, byte local_v)
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
    
    public byte remove(byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public byte get(byte local_k)
    {
      return this.function.get(local_k);
    }
    
    public boolean containsKey(Object local_ok)
    {
      return this.function.containsKey(local_ok);
    }
  }
  
  public static class SynchronizedFunction
    extends AbstractByte2ByteFunction
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Byte2ByteFunction function;
    protected final Object sync;
    
    protected SynchronizedFunction(Byte2ByteFunction local_f, Object sync)
    {
      if (local_f == null) {
        throw new NullPointerException();
      }
      this.function = local_f;
      this.sync = sync;
    }
    
    protected SynchronizedFunction(Byte2ByteFunction local_f)
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
    
    public byte put(byte local_k, byte local_v)
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
    
    public Byte put(Byte local_k, Byte local_v)
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
    
    public byte remove(byte local_k)
    {
      synchronized (this.sync)
      {
        return this.function.remove(local_k);
      }
    }
    
    public byte get(byte local_k)
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
    extends AbstractByte2ByteFunction
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final byte key;
    protected final byte value;
    
    protected Singleton(byte key, byte value)
    {
      this.key = key;
      this.value = value;
    }
    
    public boolean containsKey(byte local_k)
    {
      return this.key == local_k;
    }
    
    public byte get(byte local_k)
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
    extends AbstractByte2ByteFunction
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public byte get(byte local_k)
    {
      return 0;
    }
    
    public boolean containsKey(byte local_k)
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
      return Byte2ByteFunctions.EMPTY_FUNCTION;
    }
    
    public Object clone()
    {
      return Byte2ByteFunctions.EMPTY_FUNCTION;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2ByteFunctions
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */