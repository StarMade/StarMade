package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.Function;

public abstract interface Double2ShortFunction extends Function<Double, Short>
{
  public abstract short put(double paramDouble, short paramShort);

  public abstract short get(double paramDouble);

  public abstract short remove(double paramDouble);

  public abstract boolean containsKey(double paramDouble);

  public abstract void defaultReturnValue(short paramShort);

  public abstract short defaultReturnValue();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2ShortFunction
 * JD-Core Version:    0.6.2
 */