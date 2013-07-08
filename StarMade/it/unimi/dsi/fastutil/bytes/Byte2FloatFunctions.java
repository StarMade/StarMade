package it.unimi.dsi.fastutil.bytes;

import java.io.Serializable;

public class Byte2FloatFunctions
{
  public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();
  
  public static Byte2FloatFunction singleton(byte key, float value)
  {
    return new Singleton(key, value);
  }
  
  public static Byte2FloatFunction singleton(Byte key, Float value)
  {
    return new Singleton(key.byteValue(), value.floatValue());
  }
  
  public static Byte2FloatFunction synchronize(Byte2FloatFunction local_f)
  {
    return new SynchronizedFunction(local_f);
  }
  
  public static Byte2FloatFunction synchronize(Byte2FloatFunction local_f, Object sync)
  {
    return new SynchronizedFunction(local_f, sync);
  }
  
  public static Byte2FloatFunction unmodifiable(Byte2FloatFunction local_f)
  {
    return new UnmodifiableFunction(local_f);
  }
  
  public static class UnmodifiableFunction
    extends AbstractByte2FloatFunction
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Byte2FloatFunction function;
    
    protected UnmodifiableFunction(Byte2FloatFunction local_f)
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
    
    public float defaultReturnValue()
    {
      return this.function.defaultReturnValue();
    }
    
    public void defaultReturnValue(float defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public float put(byte local_k, float local_v)
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
    
    public float remove(byte local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public float get(byte local_k)
    {
      return this.function.get(local_k);
    }
    
    public boolean containsKey(Object local_ok)
    {
      return this.function.containsKey(local_ok);
    }
  }
  
  public static class SynchronizedFunction
    extends AbstractByte2FloatFunction
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Byte2FloatFunction function;
    protected final Object sync;
    
    protected SynchronizedFunction(Byte2FloatFunction local_f, Object sync)
    {
      if (local_f == null) {
        throw new NullPointerException();
      }
      this.function = local_f;
      this.sync = sync;
    }
    
    protected SynchronizedFunction(Byte2FloatFunction local_f)
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
    
    public float defaultReturnValue()
    {
      synchronized (this.sync)
      {
        return this.function.defaultReturnValue();
      }
    }
    
    public void defaultReturnValue(float defRetValue)
    {
      synchronized (this.sync)
      {
        this.function.defaultReturnValue(defRetValue);
      }
    }
    
    public float put(byte local_k, float local_v)
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
    
    public Float put(Byte local_k, Float local_v)
    {
      synchronized (this.sync)
      {
        return (Float)this.function.put(local_k, local_v);
      }
    }
    
    public Float get(Object local_k)
    {
      synchronized (this.sync)
      {
        return (Float)this.function.get(local_k);
      }
    }
    
    public Float remove(Object local_k)
    {
      synchronized (this.sync)
      {
        return (Float)this.function.remove(local_k);
      }
    }
    
    public float remove(byte local_k)
    {
      synchronized (this.sync)
      {
        return this.function.remove(local_k);
      }
    }
    
    public float get(byte local_k)
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
    extends AbstractByte2FloatFunction
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final byte key;
    protected final float value;
    
    protected Singleton(byte key, float value)
    {
      this.key = key;
      this.value = value;
    }
    
    public boolean containsKey(byte local_k)
    {
      return this.key == local_k;
    }
    
    public float get(byte local_k)
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
    extends AbstractByte2FloatFunction
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public float get(byte local_k)
    {
      return 0.0F;
    }
    
    public boolean containsKey(byte local_k)
    {
      return false;
    }
    
    public float defaultReturnValue()
    {
      return 0.0F;
    }
    
    public void defaultReturnValue(float defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public Float get(Object local_k)
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
      return Byte2FloatFunctions.EMPTY_FUNCTION;
    }
    
    public Object clone()
    {
      return Byte2FloatFunctions.EMPTY_FUNCTION;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2FloatFunctions
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */