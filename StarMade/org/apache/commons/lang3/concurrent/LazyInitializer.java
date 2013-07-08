package org.apache.commons.lang3.concurrent;

public abstract class LazyInitializer<T>
  implements ConcurrentInitializer<T>
{
  private volatile T object;
  
  public T get()
    throws ConcurrentException
  {
    T result = this.object;
    if (result == null) {
      synchronized (this)
      {
        result = this.object;
        if (result == null) {
          this.object = (result = initialize());
        }
      }
    }
    return result;
  }
  
  protected abstract T initialize()
    throws ConcurrentException;
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.concurrent.LazyInitializer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */