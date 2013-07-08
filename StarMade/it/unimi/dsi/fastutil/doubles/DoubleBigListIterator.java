package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.BigListIterator;

public abstract interface DoubleBigListIterator
  extends DoubleBidirectionalIterator, BigListIterator<Double>
{
  public abstract void set(double paramDouble);
  
  public abstract void add(double paramDouble);
  
  public abstract void set(Double paramDouble);
  
  public abstract void add(Double paramDouble);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleBigListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */