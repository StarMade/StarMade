package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.Function;

public abstract interface Reference2BooleanFunction<K> extends Function<K, Boolean>
{
  public abstract boolean put(K paramK, boolean paramBoolean);

  public abstract boolean getBoolean(Object paramObject);

  public abstract boolean removeBoolean(Object paramObject);

  public abstract void defaultReturnValue(boolean paramBoolean);

  public abstract boolean defaultReturnValue();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2BooleanFunction
 * JD-Core Version:    0.6.2
 */