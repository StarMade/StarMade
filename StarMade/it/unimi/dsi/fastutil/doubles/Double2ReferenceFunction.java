package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.Function;

public abstract interface Double2ReferenceFunction<V> extends Function<Double, V>
{
  public abstract V put(double paramDouble, V paramV);

  public abstract V get(double paramDouble);

  public abstract V remove(double paramDouble);

  public abstract boolean containsKey(double paramDouble);

  public abstract void defaultReturnValue(V paramV);

  public abstract V defaultReturnValue();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2ReferenceFunction
 * JD-Core Version:    0.6.2
 */