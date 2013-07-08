package org.apache.commons.lang3.concurrent;

import java.util.concurrent.atomic.AtomicReference;

public abstract class AtomicInitializer<T>
  implements ConcurrentInitializer<T>
{
  private final AtomicReference<T> reference = new AtomicReference();
  
  public T get()
    throws ConcurrentException
  {
    T result = this.reference.get();
    if (result == null)
    {
      result = initialize();
      if (!this.reference.compareAndSet(null, result)) {
        result = this.reference.get();
      }
    }
    return result;
  }
  
  protected abstract T initialize()
    throws ConcurrentException;
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.concurrent.AtomicInitializer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */