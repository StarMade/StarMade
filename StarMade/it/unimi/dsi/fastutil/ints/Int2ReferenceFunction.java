package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.Function;

public abstract interface Int2ReferenceFunction<V>
  extends Function<Integer, V>
{
  public abstract V put(int paramInt, V paramV);
  
  public abstract V get(int paramInt);
  
  public abstract V remove(int paramInt);
  
  public abstract boolean containsKey(int paramInt);
  
  public abstract void defaultReturnValue(V paramV);
  
  public abstract V defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2ReferenceFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */