package com.google.code.tempusfugit;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CompositeFactory<T>
  implements Factory<T>
{
  private final List<Factory<T>> factories;
  
  public CompositeFactory(Factory<T>... factories)
  {
    this.factories = Arrays.asList(factories);
  }
  
  public T create()
    throws FactoryException
  {
    Iterator local_i$ = this.factories.iterator();
    while (local_i$.hasNext())
    {
      Factory<T> factory = (Factory)local_i$.next();
      try
      {
        return factory.create();
      }
      catch (FactoryException local_e) {}
    }
    throw new FactoryException("unable to create any objects from factories");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.google.code.tempusfugit.CompositeFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */