/*  1:   */package com.google.code.tempusfugit;
/*  2:   */
/*  3:   */import java.lang.reflect.Constructor;
/*  4:   */import java.util.concurrent.Callable;
/*  5:   */
/* 22:   */public class ExceptionWrapper
/* 23:   */{
/* 24:   */  public static <V> V wrapAsRuntimeException(Callable<V> callable)
/* 25:   */    throws RuntimeException
/* 26:   */  {
/* 27:   */    try
/* 28:   */    {
/* 29:29 */      return callable.call();
/* 30:   */    } catch (Exception e) {
/* 31:31 */      throw new RuntimeException(e);
/* 32:   */    }
/* 33:   */  }
/* 34:   */  
/* 35:   */  public static <V, E extends Exception> V wrapAnyException(Callable<V> callable, WithException<E> wrapped) throws Exception {
/* 36:   */    try {
/* 37:37 */      return callable.call();
/* 38:   */    } catch (Throwable e) {
/* 39:39 */      throw ExceptionFactory.newException(wrapped, e).create();
/* 40:   */    }
/* 41:   */  }
/* 42:   */  
/* 43:   */  static class ExceptionFactory<E extends Exception> implements Factory<E> {
/* 44:   */    private final Class wrapped;
/* 45:   */    private final Throwable throwable;
/* 46:   */    
/* 47:   */    static <E extends Exception> ExceptionFactory<E> newException(WithException<E> wrapped, Throwable throwable) {
/* 48:48 */      return new ExceptionFactory(wrapped.getType(), throwable);
/* 49:   */    }
/* 50:   */    
/* 51:   */    private ExceptionFactory(Class<E> wrapped, Throwable throwable) {
/* 52:52 */      this.wrapped = wrapped;
/* 53:53 */      this.throwable = throwable;
/* 54:   */    }
/* 55:   */    
/* 56:   */    public E create() throws FactoryException
/* 57:   */    {
/* 58:   */      try {
/* 59:59 */        Constructor<E> constructor = this.wrapped.getConstructor(new Class[] { Throwable.class });
/* 60:60 */        return (Exception)constructor.newInstance(new Object[] { this.throwable });
/* 61:   */      } catch (Exception e) {
/* 62:62 */        throw new FactoryException(e);
/* 63:   */      }
/* 64:   */    }
/* 65:   */  }
/* 66:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.ExceptionWrapper
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */