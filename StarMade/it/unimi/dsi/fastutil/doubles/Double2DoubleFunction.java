package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.Function;

public abstract interface Double2DoubleFunction extends Function<Double, Double>
{
  public abstract double put(double paramDouble1, double paramDouble2);

  public abstract double get(double paramDouble);

  public abstract double remove(double paramDouble);

  public abstract boolean containsKey(double paramDouble);

  public abstract void defaultReturnValue(double paramDouble);

  public abstract double defaultReturnValue();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2DoubleFunction
 * JD-Core Version:    0.6.2
 */