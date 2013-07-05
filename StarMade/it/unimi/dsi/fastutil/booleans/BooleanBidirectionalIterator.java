package it.unimi.dsi.fastutil.booleans;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;

public abstract interface BooleanBidirectionalIterator extends BooleanIterator, ObjectBidirectionalIterator<Boolean>
{
  public abstract boolean previousBoolean();

  public abstract int back(int paramInt);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.BooleanBidirectionalIterator
 * JD-Core Version:    0.6.2
 */