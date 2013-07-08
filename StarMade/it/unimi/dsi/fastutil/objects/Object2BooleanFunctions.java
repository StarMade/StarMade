package it.unimi.dsi.fastutil.objects;

import java.io.Serializable;

public class Object2BooleanFunctions
{
  public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();
  
  public static <K> Object2BooleanFunction<K> singleton(K key, boolean value)
  {
    return new Singleton(key, value);
  }
  
  public static <K> Object2BooleanFunction<K> singleton(K key, Boolean value)
  {
    return new Singleton(key, value.booleanValue());
  }
  
  public static <K> Object2BooleanFunction<K> synchronize(Object2BooleanFunction<K> local_f)
  {
    return new SynchronizedFunction(local_f);
  }
  
  public static <K> Object2BooleanFunction<K> synchronize(Object2BooleanFunction<K> local_f, Object sync)
  {
    return new SynchronizedFunction(local_f, sync);
  }
  
  public static <K> Object2BooleanFunction<K> unmodifiable(Object2BooleanFunction<K> local_f)
  {
    return new UnmodifiableFunction(local_f);
  }
  
  public static class UnmodifiableFunction<K>
    extends AbstractObject2BooleanFunction<K>
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Object2BooleanFunction<K> function;
    
    protected UnmodifiableFunction(Object2BooleanFunction<K> local_f)
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
    
    public boolean defaultReturnValue()
    {
      return this.function.defaultReturnValue();
    }
    
    public void defaultReturnValue(boolean defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean put(K local_k, boolean local_v)
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
    
    public boolean removeBoolean(Object local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean getBoolean(Object local_k)
    {
      return this.function.getBoolean(local_k);
    }
  }
  
  public static class SynchronizedFunction<K>
    extends AbstractObject2BooleanFunction<K>
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Object2BooleanFunction<K> function;
    protected final Object sync;
    
    protected SynchronizedFunction(Object2BooleanFunction<K> local_f, Object sync)
    {
      if (local_f == null) {
        throw new NullPointerException();
      }
      this.function = local_f;
      this.sync = sync;
    }
    
    protected SynchronizedFunction(Object2BooleanFunction<K> local_f)
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
    
    public boolean put(K local_k, boolean local_v)
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
    
    public Boolean put(K local_k, Boolean local_v)
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
    
    public boolean removeBoolean(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.function.removeBoolean(local_k);
      }
    }
    
    public boolean getBoolean(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.function.getBoolean(local_k);
      }
    }
  }
  
  public static class Singleton<K>
    extends AbstractObject2BooleanFunction<K>
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final K key;
    protected final boolean value;
    
    protected Singleton(K key, boolean value)
    {
      this.key = key;
      this.value = value;
    }
    
    public boolean containsKey(Object local_k)
    {
      return this.key == null ? false : local_k == null ? true : this.key.equals(local_k);
    }
    
    public boolean getBoolean(Object local_k)
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
    extends AbstractObject2BooleanFunction<K>
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean getBoolean(Object local_k)
    {
      return false;
    }
    
    public boolean containsKey(Object local_k)
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
    
    public int size()
    {
      return 0;
    }
    
    public void clear() {}
    
    private Object readResolve()
    {
      return Object2BooleanFunctions.EMPTY_FUNCTION;
    }
    
    public Object clone()
    {
      return Object2BooleanFunctions.EMPTY_FUNCTION;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2BooleanFunctions
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */