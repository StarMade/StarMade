package org.apache.commons.lang3.concurrent;

public abstract interface ConcurrentInitializer<T>
{
  public abstract T get()
    throws ConcurrentException;
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.concurrent.ConcurrentInitializer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */