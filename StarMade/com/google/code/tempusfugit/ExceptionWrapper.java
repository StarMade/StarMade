package com.google.code.tempusfugit;

import java.lang.reflect.Constructor;
import java.util.concurrent.Callable;

public class ExceptionWrapper
{
  public static <V> V wrapAsRuntimeException(Callable<V> callable)
    throws RuntimeException
  {
    try
    {
      return callable.call();
    }
    catch (Exception local_e)
    {
      throw new RuntimeException(local_e);
    }
  }
  
  public static <V, E extends Exception> V wrapAnyException(Callable<V> callable, WithException<E> wrapped)
    throws Exception
  {
    try
    {
      return callable.call();
    }
    catch (Throwable local_e)
    {
      throw ExceptionFactory.newException(wrapped, local_e).create();
    }
  }
  
  static class ExceptionFactory<E extends Exception>
    implements Factory<E>
  {
    private final Class wrapped;
    private final Throwable throwable;
    
    static <E extends Exception> ExceptionFactory<E> newException(WithException<E> wrapped, Throwable throwable)
    {
      return new ExceptionFactory(wrapped.getType(), throwable);
    }
    
    private ExceptionFactory(Class<E> wrapped, Throwable throwable)
    {
      this.wrapped = wrapped;
      this.throwable = throwable;
    }
    
    public E create()
      throws FactoryException
    {
      try
      {
        Constructor<E> constructor = this.wrapped.getConstructor(new Class[] { Throwable.class });
        return (Exception)constructor.newInstance(new Object[] { this.throwable });
      }
      catch (Exception constructor)
      {
        throw new FactoryException(constructor);
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.google.code.tempusfugit.ExceptionWrapper
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */