package it.unimi.dsi.fastutil.objects;

import java.io.Serializable;

public class Object2FloatFunctions
{
  public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();
  
  public static <K> Object2FloatFunction<K> singleton(K key, float value)
  {
    return new Singleton(key, value);
  }
  
  public static <K> Object2FloatFunction<K> singleton(K key, Float value)
  {
    return new Singleton(key, value.floatValue());
  }
  
  public static <K> Object2FloatFunction<K> synchronize(Object2FloatFunction<K> local_f)
  {
    return new SynchronizedFunction(local_f);
  }
  
  public static <K> Object2FloatFunction<K> synchronize(Object2FloatFunction<K> local_f, Object sync)
  {
    return new SynchronizedFunction(local_f, sync);
  }
  
  public static <K> Object2FloatFunction<K> unmodifiable(Object2FloatFunction<K> local_f)
  {
    return new UnmodifiableFunction(local_f);
  }
  
  public static class UnmodifiableFunction<K>
    extends AbstractObject2FloatFunction<K>
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Object2FloatFunction<K> function;
    
    protected UnmodifiableFunction(Object2FloatFunction<K> local_f)
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
    
    public float defaultReturnValue()
    {
      return this.function.defaultReturnValue();
    }
    
    public void defaultReturnValue(float defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public float put(K local_k, float local_v)
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
    
    public float removeFloat(Object local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public float getFloat(Object local_k)
    {
      return this.function.getFloat(local_k);
    }
  }
  
  public static class SynchronizedFunction<K>
    extends AbstractObject2FloatFunction<K>
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Object2FloatFunction<K> function;
    protected final Object sync;
    
    protected SynchronizedFunction(Object2FloatFunction<K> local_f, Object sync)
    {
      if (local_f == null) {
        throw new NullPointerException();
      }
      this.function = local_f;
      this.sync = sync;
    }
    
    protected SynchronizedFunction(Object2FloatFunction<K> local_f)
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
    
    public float put(K local_k, float local_v)
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
    
    public Float put(K local_k, Float local_v)
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
    
    public float removeFloat(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.function.removeFloat(local_k);
      }
    }
    
    public float getFloat(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.function.getFloat(local_k);
      }
    }
  }
  
  public static class Singleton<K>
    extends AbstractObject2FloatFunction<K>
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final K key;
    protected final float value;
    
    protected Singleton(K key, float value)
    {
      this.key = key;
      this.value = value;
    }
    
    public boolean containsKey(Object local_k)
    {
      return this.key == null ? false : local_k == null ? true : this.key.equals(local_k);
    }
    
    public float getFloat(Object local_k)
    {
      if (this.key == null ? local_k == null : this.key.equals(local_k)) {
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
    extends AbstractObject2FloatFunction<K>
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public float getFloat(Object local_k)
    {
      return 0.0F;
    }
    
    public boolean containsKey(Object local_k)
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
    
    public int size()
    {
      return 0;
    }
    
    public void clear() {}
    
    private Object readResolve()
    {
      return Object2FloatFunctions.EMPTY_FUNCTION;
    }
    
    public Object clone()
    {
      return Object2FloatFunctions.EMPTY_FUNCTION;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2FloatFunctions
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */