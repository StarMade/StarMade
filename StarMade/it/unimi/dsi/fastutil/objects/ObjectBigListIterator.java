package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.BigListIterator;

public abstract interface ObjectBigListIterator<K>
  extends ObjectBidirectionalIterator<K>, BigListIterator<K>
{
  public abstract void set(K paramK);
  
  public abstract void add(K paramK);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectBigListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */