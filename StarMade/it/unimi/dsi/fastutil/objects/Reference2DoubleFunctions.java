package it.unimi.dsi.fastutil.objects;

import java.io.Serializable;

public class Reference2DoubleFunctions
{
  public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();
  
  public static <K> Reference2DoubleFunction<K> singleton(K key, double value)
  {
    return new Singleton(key, value);
  }
  
  public static <K> Reference2DoubleFunction<K> singleton(K key, Double value)
  {
    return new Singleton(key, value.doubleValue());
  }
  
  public static <K> Reference2DoubleFunction<K> synchronize(Reference2DoubleFunction<K> local_f)
  {
    return new SynchronizedFunction(local_f);
  }
  
  public static <K> Reference2DoubleFunction<K> synchronize(Reference2DoubleFunction<K> local_f, Object sync)
  {
    return new SynchronizedFunction(local_f, sync);
  }
  
  public static <K> Reference2DoubleFunction<K> unmodifiable(Reference2DoubleFunction<K> local_f)
  {
    return new UnmodifiableFunction(local_f);
  }
  
  public static class UnmodifiableFunction<K>
    extends AbstractReference2DoubleFunction<K>
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Reference2DoubleFunction<K> function;
    
    protected UnmodifiableFunction(Reference2DoubleFunction<K> local_f)
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
    
    public double defaultReturnValue()
    {
      return this.function.defaultReturnValue();
    }
    
    public void defaultReturnValue(double defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public double put(K local_k, double local_v)
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
    
    public double removeDouble(Object local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public double getDouble(Object local_k)
    {
      return this.function.getDouble(local_k);
    }
  }
  
  public static class SynchronizedFunction<K>
    extends AbstractReference2DoubleFunction<K>
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Reference2DoubleFunction<K> function;
    protected final Object sync;
    
    protected SynchronizedFunction(Reference2DoubleFunction<K> local_f, Object sync)
    {
      if (local_f == null) {
        throw new NullPointerException();
      }
      this.function = local_f;
      this.sync = sync;
    }
    
    protected SynchronizedFunction(Reference2DoubleFunction<K> local_f)
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
    
    public double defaultReturnValue()
    {
      synchronized (this.sync)
      {
        return this.function.defaultReturnValue();
      }
    }
    
    public void defaultReturnValue(double defRetValue)
    {
      synchronized (this.sync)
      {
        this.function.defaultReturnValue(defRetValue);
      }
    }
    
    public double put(K local_k, double local_v)
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
    
    public Double put(K local_k, Double local_v)
    {
      synchronized (this.sync)
      {
        return (Double)this.function.put(local_k, local_v);
      }
    }
    
    public Double get(Object local_k)
    {
      synchronized (this.sync)
      {
        return (Double)this.function.get(local_k);
      }
    }
    
    public Double remove(Object local_k)
    {
      synchronized (this.sync)
      {
        return (Double)this.function.remove(local_k);
      }
    }
    
    public double removeDouble(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.function.removeDouble(local_k);
      }
    }
    
    public double getDouble(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.function.getDouble(local_k);
      }
    }
  }
  
  public static class Singleton<K>
    extends AbstractReference2DoubleFunction<K>
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final K key;
    protected final double value;
    
    protected Singleton(K key, double value)
    {
      this.key = key;
      this.value = value;
    }
    
    public boolean containsKey(Object local_k)
    {
      return this.key == local_k;
    }
    
    public double getDouble(Object local_k)
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
    extends AbstractReference2DoubleFunction<K>
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public double getDouble(Object local_k)
    {
      return 0.0D;
    }
    
    public boolean containsKey(Object local_k)
    {
      return false;
    }
    
    public double defaultReturnValue()
    {
      return 0.0D;
    }
    
    public void defaultReturnValue(double defRetValue)
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
      return Reference2DoubleFunctions.EMPTY_FUNCTION;
    }
    
    public Object clone()
    {
      return Reference2DoubleFunctions.EMPTY_FUNCTION;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2DoubleFunctions
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */