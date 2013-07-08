package it.unimi.dsi.fastutil.objects;

public abstract class AbstractObjectSortedSet<K>
  extends AbstractObjectSet<K>
  implements ObjectSortedSet<K>
{
  @Deprecated
  public ObjectBidirectionalIterator<K> objectIterator()
  {
    return iterator();
  }
  
  public abstract ObjectBidirectionalIterator<K> iterator();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObjectSortedSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */