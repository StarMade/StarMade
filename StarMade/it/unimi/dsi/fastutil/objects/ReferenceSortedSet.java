package it.unimi.dsi.fastutil.objects;

import java.util.SortedSet;

public abstract interface ReferenceSortedSet<K>
  extends ReferenceSet<K>, SortedSet<K>
{
  public abstract ObjectBidirectionalIterator<K> iterator(K paramK);
  
  @Deprecated
  public abstract ObjectBidirectionalIterator<K> objectIterator();
  
  public abstract ObjectBidirectionalIterator<K> iterator();
  
  public abstract ReferenceSortedSet<K> subSet(K paramK1, K paramK2);
  
  public abstract ReferenceSortedSet<K> headSet(K paramK);
  
  public abstract ReferenceSortedSet<K> tailSet(K paramK);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ReferenceSortedSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */