package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.Function;

public abstract interface Double2IntFunction extends Function<Double, Integer>
{
  public abstract int put(double paramDouble, int paramInt);

  public abstract int get(double paramDouble);

  public abstract int remove(double paramDouble);

  public abstract boolean containsKey(double paramDouble);

  public abstract void defaultReturnValue(int paramInt);

  public abstract int defaultReturnValue();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2IntFunction
 * JD-Core Version:    0.6.2
 */