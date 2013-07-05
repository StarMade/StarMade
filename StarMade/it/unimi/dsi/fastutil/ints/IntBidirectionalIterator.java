package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;

public abstract interface IntBidirectionalIterator extends IntIterator, ObjectBidirectionalIterator<Integer>
{
  public abstract int previousInt();

  public abstract int back(int paramInt);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntBidirectionalIterator
 * JD-Core Version:    0.6.2
 */