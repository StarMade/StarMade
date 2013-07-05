package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.Function;

public abstract interface Object2ReferenceFunction<K, V> extends Function<K, V>
{
  public abstract void defaultReturnValue(V paramV);

  public abstract V defaultReturnValue();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2ReferenceFunction
 * JD-Core Version:    0.6.2
 */