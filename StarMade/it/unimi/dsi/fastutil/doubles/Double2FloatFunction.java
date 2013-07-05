package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.Function;

public abstract interface Double2FloatFunction extends Function<Double, Float>
{
  public abstract float put(double paramDouble, float paramFloat);

  public abstract float get(double paramDouble);

  public abstract float remove(double paramDouble);

  public abstract boolean containsKey(double paramDouble);

  public abstract void defaultReturnValue(float paramFloat);

  public abstract float defaultReturnValue();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2FloatFunction
 * JD-Core Version:    0.6.2
 */