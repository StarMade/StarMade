package it.unimi.dsi.fastutil.chars;

import java.io.Serializable;

public class Char2CharFunctions
{
  public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();
  
  public static Char2CharFunction singleton(char key, char value)
  {
    return new Singleton(key, value);
  }
  
  public static Char2CharFunction singleton(Character key, Character value)
  {
    return new Singleton(key.charValue(), value.charValue());
  }
  
  public static Char2CharFunction synchronize(Char2CharFunction local_f)
  {
    return new SynchronizedFunction(local_f);
  }
  
  public static Char2CharFunction synchronize(Char2CharFunction local_f, Object sync)
  {
    return new SynchronizedFunction(local_f, sync);
  }
  
  public static Char2CharFunction unmodifiable(Char2CharFunction local_f)
  {
    return new UnmodifiableFunction(local_f);
  }
  
  public static class UnmodifiableFunction
    extends AbstractChar2CharFunction
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Char2CharFunction function;
    
    protected UnmodifiableFunction(Char2CharFunction local_f)
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
    
    public boolean containsKey(char local_k)
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
    
    public char put(char local_k, char local_v)
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
    
    public char remove(char local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public char get(char local_k)
    {
      return this.function.get(local_k);
    }
    
    public boolean containsKey(Object local_ok)
    {
      return this.function.containsKey(local_ok);
    }
  }
  
  public static class SynchronizedFunction
    extends AbstractChar2CharFunction
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final Char2CharFunction function;
    protected final Object sync;
    
    protected SynchronizedFunction(Char2CharFunction local_f, Object sync)
    {
      if (local_f == null) {
        throw new NullPointerException();
      }
      this.function = local_f;
      this.sync = sync;
    }
    
    protected SynchronizedFunction(Char2CharFunction local_f)
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
    
    public boolean containsKey(char local_k)
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
    
    public char put(char local_k, char local_v)
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
    
    public Character put(Character local_k, Character local_v)
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
    
    public char remove(char local_k)
    {
      synchronized (this.sync)
      {
        return this.function.remove(local_k);
      }
    }
    
    public char get(char local_k)
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
    extends AbstractChar2CharFunction
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    protected final char key;
    protected final char value;
    
    protected Singleton(char key, char value)
    {
      this.key = key;
      this.value = value;
    }
    
    public boolean containsKey(char local_k)
    {
      return this.key == local_k;
    }
    
    public char get(char local_k)
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
    extends AbstractChar2CharFunction
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public char get(char local_k)
    {
      return '\000';
    }
    
    public boolean containsKey(char local_k)
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
      return Char2CharFunctions.EMPTY_FUNCTION;
    }
    
    public Object clone()
    {
      return Char2CharFunctions.EMPTY_FUNCTION;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2CharFunctions
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */