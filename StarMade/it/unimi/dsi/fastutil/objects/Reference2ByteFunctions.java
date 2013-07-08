package it.unimi.dsi.fastutil.objects;

import java.io.Serializable;

public class Reference2ByteFunctions
{
  public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();
  
  public static <K> Reference2ByteFunction<K> singleton(K key, byte value)
  {
    return new Singleton(key, value);
  }
  
  public static <K> Reference2ByteFunction<K> singleton(K key, Byte value)
  {
    return new Singleton(key, value.byteValue());
  }
  
  public static <K> Reference2ByteFunction<K> synchronize(Reference2ByteFunction<K> local_f)
  {
    return new SynchronizedFunction(local_f);
  }
  
  public static <K> Reference2ByteFunction<K> synchronize(Reference2ByteFunction<K> local_f, Object sync)
  {
    return new SynchronizedFunction(local_f, sync);
  }
  
  public static <K> Reference2ByteFunction<K> unmodifiable(Reference2ByteFunction<K> local_f)
  {
    return new UnmodifiableFunction(local_f);
  }
  
  public static class UnmodifiableFunction<K>
    extends AbstractReference2ByteFunction<K>
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Reference2ByteFunction<K> function;
    
    protected UnmodifiableFunction(Reference2ByteFunction<K> local_f)
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
    
    public boolean containsKey(Object local_k)
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
    
    public byte put(K local_k, byte local_v)
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
    
    public byte removeByte(Object local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public byte getByte(Object local_k)
    {
      return this.function.getByte(local_k);
    }
  }
  
  public static class SynchronizedFunction<K>
    extends AbstractReference2ByteFunction<K>
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Reference2ByteFunction<K> function;
    protected final Object sync;
    
    protected SynchronizedFunction(Reference2ByteFunction<K> local_f, Object sync)
    {
      if (local_f == null) {
        throw new NullPointerException();
      }
      this.function = local_f;
      this.sync = sync;
    }
    
    protected SynchronizedFunction(Reference2ByteFunction<K> local_f)
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
    
    public boolean containsKey(Object local_k)
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
    
    public byte put(K local_k, byte local_v)
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
    
    public Byte put(K local_k, Byte local_v)
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
    
    public byte removeByte(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.function.removeByte(local_k);
      }
    }
    
    public byte getByte(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.function.getByte(local_k);
      }
    }
  }
  
  public static class Singleton<K>
    extends AbstractReference2ByteFunction<K>
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final K key;
    protected final byte value;
    
    protected Singleton(K key, byte value)
    {
      this.key = key;
      this.value = value;
    }
    
    public boolean containsKey(Object local_k)
    {
      return this.key == local_k;
    }
    
    public byte getByte(Object local_k)
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
  
  public static class EmptyFunction<K>
    extends AbstractReference2ByteFunction<K>
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public byte getByte(Object local_k)
    {
      return 0;
    }
    
    public boolean containsKey(Object local_k)
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
    
    public int size()
    {
      return 0;
    }
    
    public void clear() {}
    
    private Object readResolve()
    {
      return Reference2ByteFunctions.EMPTY_FUNCTION;
    }
    
    public Object clone()
    {
      return Reference2ByteFunctions.EMPTY_FUNCTION;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2ByteFunctions
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */