package org.apache.commons.lang3.concurrent;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ConcurrentUtils
{
  public static ConcurrentException extractCause(ExecutionException local_ex)
  {
    if ((local_ex == null) || (local_ex.getCause() == null)) {
      return null;
    }
    throwCause(local_ex);
    return new ConcurrentException(local_ex.getMessage(), local_ex.getCause());
  }
  
  public static ConcurrentRuntimeException extractCauseUnchecked(ExecutionException local_ex)
  {
    if ((local_ex == null) || (local_ex.getCause() == null)) {
      return null;
    }
    throwCause(local_ex);
    return new ConcurrentRuntimeException(local_ex.getMessage(), local_ex.getCause());
  }
  
  public static void handleCause(ExecutionException local_ex)
    throws ConcurrentException
  {
    ConcurrentException cex = extractCause(local_ex);
    if (cex != null) {
      throw cex;
    }
  }
  
  public static void handleCauseUnchecked(ExecutionException local_ex)
  {
    ConcurrentRuntimeException crex = extractCauseUnchecked(local_ex);
    if (crex != null) {
      throw crex;
    }
  }
  
  static Throwable checkedException(Throwable local_ex)
  {
    if ((local_ex != null) && (!(local_ex instanceof RuntimeException)) && (!(local_ex instanceof Error))) {
      return local_ex;
    }
    throw new IllegalArgumentException("Not a checked exception: " + local_ex);
  }
  
  private static void throwCause(ExecutionException local_ex)
  {
    if ((local_ex.getCause() instanceof RuntimeException)) {
      throw ((RuntimeException)local_ex.getCause());
    }
    if ((local_ex.getCause() instanceof Error)) {
      throw ((Error)local_ex.getCause());
    }
  }
  
  public static <T> T initialize(ConcurrentInitializer<T> initializer)
    throws ConcurrentException
  {
    return initializer != null ? initializer.get() : null;
  }
  
  public static <T> T initializeUnchecked(ConcurrentInitializer<T> initializer)
  {
    try
    {
      return initialize(initializer);
    }
    catch (ConcurrentException cex)
    {
      throw new ConcurrentRuntimeException(cex.getCause());
    }
  }
  
  public static <K, V> V putIfAbsent(ConcurrentMap<K, V> map, K key, V value)
  {
    if (map == null) {
      return null;
    }
    V result = map.putIfAbsent(key, value);
    return result != null ? result : value;
  }
  
  public static <K, V> V createIfAbsent(ConcurrentMap<K, V> map, K key, ConcurrentInitializer<V> init)
    throws ConcurrentException
  {
    if ((map == null) || (init == null)) {
      return null;
    }
    V value = map.get(key);
    if (value == null) {
      return putIfAbsent(map, key, init.get());
    }
    return value;
  }
  
  public static <K, V> V createIfAbsentUnchecked(ConcurrentMap<K, V> map, K key, ConcurrentInitializer<V> init)
  {
    try
    {
      return createIfAbsent(map, key, init);
    }
    catch (ConcurrentException cex)
    {
      throw new ConcurrentRuntimeException(cex.getCause());
    }
  }
  
  public static <T> Future<T> constantFuture(T value)
  {
    return new ConstantFuture(value);
  }
  
  static final class ConstantFuture<T>
    implements Future<T>
  {
    private final T value;
    
    ConstantFuture(T value)
    {
      this.value = value;
    }
    
    public boolean isDone()
    {
      return true;
    }
    
    public T get()
    {
      return this.value;
    }
    
    public T get(long timeout, TimeUnit unit)
    {
      return this.value;
    }
    
    public boolean isCancelled()
    {
      return false;
    }
    
    public boolean cancel(boolean mayInterruptIfRunning)
    {
      return false;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.concurrent.ConcurrentUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */