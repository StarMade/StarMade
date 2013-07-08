package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.Function;

public abstract interface Reference2DoubleFunction<K>
  extends Function<K, Double>
{
  public abstract double put(K paramK, double paramDouble);
  
  public abstract double getDouble(Object paramObject);
  
  public abstract double removeDouble(Object paramObject);
  
  public abstract void defaultReturnValue(double paramDouble);
  
  public abstract double defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2DoubleFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */