package it.unimi.dsi.fastutil.objects;

import java.io.Serializable;

public class Reference2CharFunctions
{
  public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();
  
  public static <K> Reference2CharFunction<K> singleton(K key, char value)
  {
    return new Singleton(key, value);
  }
  
  public static <K> Reference2CharFunction<K> singleton(K key, Character value)
  {
    return new Singleton(key, value.charValue());
  }
  
  public static <K> Reference2CharFunction<K> synchronize(Reference2CharFunction<K> local_f)
  {
    return new SynchronizedFunction(local_f);
  }
  
  public static <K> Reference2CharFunction<K> synchronize(Reference2CharFunction<K> local_f, Object sync)
  {
    return new SynchronizedFunction(local_f, sync);
  }
  
  public static <K> Reference2CharFunction<K> unmodifiable(Reference2CharFunction<K> local_f)
  {
    return new UnmodifiableFunction(local_f);
  }
  
  public static class UnmodifiableFunction<K>
    extends AbstractReference2CharFunction<K>
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Reference2CharFunction<K> function;
    
    protected UnmodifiableFunction(Reference2CharFunction<K> local_f)
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
    
    public char defaultReturnValue()
    {
      return this.function.defaultReturnValue();
    }
    
    public void defaultReturnValue(char defRetValue)
    {
      throw new UnsupportedOperationException();
    }
    
    public char put(K local_k, char local_v)
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
    
    public char removeChar(Object local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public char getChar(Object local_k)
    {
      return this.function.getChar(local_k);
    }
  }
  
  public static class SynchronizedFunction<K>
    extends AbstractReference2CharFunction<K>
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Reference2CharFunction<K> function;
    protected final Object sync;
    
    protected SynchronizedFunction(Reference2CharFunction<K> local_f, Object sync)
    {
      if (local_f == null) {
        throw new NullPointerException();
      }
      this.function = local_f;
      this.sync = sync;
    }
    
    protected SynchronizedFunction(Reference2CharFunction<K> local_f)
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
    
    public char defaultReturnValue()
    {
      synchronized (this.sync)
      {
        return this.function.defaultReturnValue();
      }
    }
    
    public void defaultReturnValue(char defRetValue)
    {
      synchronized (this.sync)
      {
        this.function.defaultReturnValue(defRetValue);
      }
    }
    
    public char put(K local_k, char local_v)
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
    
    public Character put(K local_k, Character local_v)
    {
      synchronized (this.sync)
      {
        return (Character)this.function.put(local_k, local_v);
      }
    }
    
    public Character get(Object local_k)
    {
      synchronized (this.sync)
      {
        return (Character)this.function.get(local_k);
      }
    }
    
    public Character remove(Object local_k)
    {
      synchronized (this.sync)
      {
        return (Character)this.function.remove(local_k);
      }
    }
    
    public char removeChar(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.function.removeChar(local_k);
      }
    }
    
    public char getChar(Object local_k)
    {
      synchronized (this.sync)
      {
        return this.function.getChar(local_k);
      }
    }
  }
  
  public static class Singleton<K>
    extends AbstractReference2CharFunction<K>
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final K key;
    protected final char value;
    
    protected Singleton(K key, char value)
    {
      this.key = key;
      this.value = value;
    }
    
    public boolean containsKey(Object local_k)
    {
      return this.key == local_k;
    }
    
    public char getChar(Object local_k)
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
    extends AbstractReference2CharFunction<K>
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public char getChar(Object local_k)
    {
      return '\000';
    }
    
    public boolean containsKey(Object local_k)
    {
      return false;
    }
    
    public char defaultReturnValue()
    {
      return '\000';
    }
    
    public void defaultReturnValue(char defRetValue)
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
      return Reference2CharFunctions.EMPTY_FUNCTION;
    }
    
    public Object clone()
    {
      return Reference2CharFunctions.EMPTY_FUNCTION;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2CharFunctions
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */