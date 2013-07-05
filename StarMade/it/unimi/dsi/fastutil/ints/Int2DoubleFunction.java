package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.Function;

public abstract interface Int2DoubleFunction extends Function<Integer, Double>
{
  public abstract double put(int paramInt, double paramDouble);

  public abstract double get(int paramInt);

  public abstract double remove(int paramInt);

  public abstract boolean containsKey(int paramInt);

  public abstract void defaultReturnValue(double paramDouble);

  public abstract double defaultReturnValue();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2DoubleFunction
 * JD-Core Version:    0.6.2
 */