package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.BidirectionalIterator;

public abstract interface ObjectBidirectionalIterator<K>
  extends ObjectIterator<K>, BidirectionalIterator<K>
{
  public abstract int back(int paramInt);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */