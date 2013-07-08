package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.Function;

public abstract interface Object2CharFunction<K>
  extends Function<K, Character>
{
  public abstract char put(K paramK, char paramChar);
  
  public abstract char getChar(Object paramObject);
  
  public abstract char removeChar(Object paramObject);
  
  public abstract void defaultReturnValue(char paramChar);
  
  public abstract char defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2CharFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */