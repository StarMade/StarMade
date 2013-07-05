package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.Function;

public abstract interface Float2BooleanFunction extends Function<Float, Boolean>
{
  public abstract boolean put(float paramFloat, boolean paramBoolean);

  public abstract boolean get(float paramFloat);

  public abstract boolean remove(float paramFloat);

  public abstract boolean containsKey(float paramFloat);

  public abstract void defaultReturnValue(boolean paramBoolean);

  public abstract boolean defaultReturnValue();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2BooleanFunction
 * JD-Core Version:    0.6.2
 */