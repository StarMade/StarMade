package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.Function;

public abstract interface Float2CharFunction extends Function<Float, Character>
{
  public abstract char put(float paramFloat, char paramChar);

  public abstract char get(float paramFloat);

  public abstract char remove(float paramFloat);

  public abstract boolean containsKey(float paramFloat);

  public abstract void defaultReturnValue(char paramChar);

  public abstract char defaultReturnValue();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2CharFunction
 * JD-Core Version:    0.6.2
 */