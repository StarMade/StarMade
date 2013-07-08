package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.Function;

public abstract interface Short2BooleanFunction
  extends Function<Short, Boolean>
{
  public abstract boolean put(short paramShort, boolean paramBoolean);
  
  public abstract boolean get(short paramShort);
  
  public abstract boolean remove(short paramShort);
  
  public abstract boolean containsKey(short paramShort);
  
  public abstract void defaultReturnValue(boolean paramBoolean);
  
  public abstract boolean defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2BooleanFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */