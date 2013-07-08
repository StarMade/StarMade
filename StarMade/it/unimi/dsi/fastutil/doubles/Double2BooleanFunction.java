package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.Function;

public abstract interface Double2BooleanFunction
  extends Function<Double, Boolean>
{
  public abstract boolean put(double paramDouble, boolean paramBoolean);
  
  public abstract boolean get(double paramDouble);
  
  public abstract boolean remove(double paramDouble);
  
  public abstract boolean containsKey(double paramDouble);
  
  public abstract void defaultReturnValue(boolean paramBoolean);
  
  public abstract boolean defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2BooleanFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */