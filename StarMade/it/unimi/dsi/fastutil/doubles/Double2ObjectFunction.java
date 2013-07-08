package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.Function;

public abstract interface Double2ObjectFunction<V>
  extends Function<Double, V>
{
  public abstract V put(double paramDouble, V paramV);
  
  public abstract V get(double paramDouble);
  
  public abstract V remove(double paramDouble);
  
  public abstract boolean containsKey(double paramDouble);
  
  public abstract void defaultReturnValue(V paramV);
  
  public abstract V defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2ObjectFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */