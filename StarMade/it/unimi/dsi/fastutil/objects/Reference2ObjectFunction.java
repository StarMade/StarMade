package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.Function;

public abstract interface Reference2ObjectFunction<K, V> extends Function<K, V>
{
  public abstract void defaultReturnValue(V paramV);

  public abstract V defaultReturnValue();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2ObjectFunction
 * JD-Core Version:    0.6.2
 */