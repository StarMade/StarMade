package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.Function;

public abstract interface Object2BooleanFunction<K>
  extends Function<K, Boolean>
{
  public abstract boolean put(K paramK, boolean paramBoolean);
  
  public abstract boolean getBoolean(Object paramObject);
  
  public abstract boolean removeBoolean(Object paramObject);
  
  public abstract void defaultReturnValue(boolean paramBoolean);
  
  public abstract boolean defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2BooleanFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */