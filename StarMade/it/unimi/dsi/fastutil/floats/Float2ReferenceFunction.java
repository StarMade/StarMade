package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.Function;

public abstract interface Float2ReferenceFunction<V>
  extends Function<Float, V>
{
  public abstract V put(float paramFloat, V paramV);
  
  public abstract V get(float paramFloat);
  
  public abstract V remove(float paramFloat);
  
  public abstract boolean containsKey(float paramFloat);
  
  public abstract void defaultReturnValue(V paramV);
  
  public abstract V defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2ReferenceFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */