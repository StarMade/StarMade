package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.Function;

public abstract interface Int2BooleanFunction extends Function<Integer, Boolean>
{
  public abstract boolean put(int paramInt, boolean paramBoolean);

  public abstract boolean get(int paramInt);

  public abstract boolean remove(int paramInt);

  public abstract boolean containsKey(int paramInt);

  public abstract void defaultReturnValue(boolean paramBoolean);

  public abstract boolean defaultReturnValue();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2BooleanFunction
 * JD-Core Version:    0.6.2
 */