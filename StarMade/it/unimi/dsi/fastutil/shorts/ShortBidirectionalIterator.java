package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;

public abstract interface ShortBidirectionalIterator extends ShortIterator, ObjectBidirectionalIterator<Short>
{
  public abstract short previousShort();

  public abstract int back(int paramInt);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortBidirectionalIterator
 * JD-Core Version:    0.6.2
 */