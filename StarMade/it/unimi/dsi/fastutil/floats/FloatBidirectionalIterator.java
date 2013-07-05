package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;

public abstract interface FloatBidirectionalIterator extends FloatIterator, ObjectBidirectionalIterator<Float>
{
  public abstract float previousFloat();

  public abstract int back(int paramInt);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatBidirectionalIterator
 * JD-Core Version:    0.6.2
 */