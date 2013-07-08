package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.Function;

public abstract interface Reference2IntFunction<K>
  extends Function<K, Integer>
{
  public abstract int put(K paramK, int paramInt);
  
  public abstract int getInt(Object paramObject);
  
  public abstract int removeInt(Object paramObject);
  
  public abstract void defaultReturnValue(int paramInt);
  
  public abstract int defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2IntFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */