package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.BigListIterator;

public abstract interface FloatBigListIterator extends FloatBidirectionalIterator, BigListIterator<Float>
{
  public abstract void set(float paramFloat);

  public abstract void add(float paramFloat);

  public abstract void set(Float paramFloat);

  public abstract void add(Float paramFloat);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatBigListIterator
 * JD-Core Version:    0.6.2
 */