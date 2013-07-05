package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.Function;

public abstract interface Int2ObjectFunction<V> extends Function<Integer, V>
{
  public abstract V put(int paramInt, V paramV);

  public abstract V get(int paramInt);

  public abstract V remove(int paramInt);

  public abstract boolean containsKey(int paramInt);

  public abstract void defaultReturnValue(V paramV);

  public abstract V defaultReturnValue();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2ObjectFunction
 * JD-Core Version:    0.6.2
 */