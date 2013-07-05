package it.unimi.dsi.fastutil.ints;

import java.util.ListIterator;

public abstract interface IntListIterator extends ListIterator<Integer>, IntBidirectionalIterator
{
  public abstract void set(int paramInt);

  public abstract void add(int paramInt);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntListIterator
 * JD-Core Version:    0.6.2
 */