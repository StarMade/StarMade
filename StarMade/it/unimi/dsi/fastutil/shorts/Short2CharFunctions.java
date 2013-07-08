package it.unimi.dsi.fastutil.shorts;

import java.io.Serializable;

public class Short2CharFunctions
{
  public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();
  
  public static Short2CharFunction singleton(short key, char value)
  {
    return new Singleton(key, value);
  }
  
  public static Short2CharFunction singleton(Short key, Character value)
  {
    return new Singleton(key.shortValue(), value.charValue());
  }
  
  public static Short2CharFunction synchronize(Short2CharFunction local_f)
  {
    return new SynchronizedFunction(local_f);
  }
  
  public static Short2CharFunction synchronize(Short2CharFunction local_f, Object sync)
  {
    return new SynchronizedFunction(local_f, sync);
  }
  
  public static Short2CharFunction unmodifiable(Short2CharFunction local_f)
  {
    return new UnmodifiableFunction(local_f);
  }
  
  public static class UnmodifiableFunction
    extends AbstractShort2CharFunction
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Short2CharFunction function;
    
    protected UnmodifiableFunction(Short2CharFunction local_f)
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
    
    public boolean containsKey(short local_k)
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
    
    public char put(short local_k, char local_v)
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
    
    public char remove(short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public char get(short local_k)
    {
      return this.function.get(local_k);
    }
    
    public boolean containsKey(Object local_ok)
    {
      return this.function.containsKey(local_ok);
    }
  }
  
  public static class SynchronizedFunction
    extends AbstractShort2CharFunction
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Short2CharFunction function;
    protected final Object sync;
    
    protected SynchronizedFunction(Short2CharFunction local_f, Object sync)
    {
      if (local_f == null) {
        throw new NullPointerException();
      }
      this.function = local_f;
      this.sync = sync;
    }
    
    protected SynchronizedFunction(Short2CharFunction local_f)
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
    
    public boolean containsKey(short local_k)
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
    
    public char put(short local_k, char local_v)
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
    
    public Character put(Short local_k, Character local_v)
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
    
    public char remove(short local_k)
    {
      synchronized (this.sync)
      {
        return this.function.remove(local_k);
      }
    }
    
    public char get(short local_k)
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
    extends AbstractShort2CharFunction
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final short key;
    protected final char value;
    
    protected Singleton(short key, char value)
    {
      this.key = key;
      this.value = value;
    }
    
    public boolean containsKey(short local_k)
    {
      return this.key == local_k;
    }
    
    public char get(short local_k)
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
    extends AbstractShort2CharFunction
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public char get(short local_k)
    {
      return '\000';
    }
    
    public boolean containsKey(short local_k)
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
    
    public Character get(Object local_k)
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
      return Short2CharFunctions.EMPTY_FUNCTION;
    }
    
    public Object clone()
    {
      return Short2CharFunctions.EMPTY_FUNCTION;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2CharFunctions
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */