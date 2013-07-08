package it.unimi.dsi.fastutil.longs;

public abstract class AbstractLongSortedSet
  extends AbstractLongSet
  implements LongSortedSet
{
  public LongSortedSet headSet(Long local_to)
  {
    return headSet(local_to.longValue());
  }
  
  public LongSortedSet tailSet(Long from)
  {
    return tailSet(from.longValue());
  }
  
  public LongSortedSet subSet(Long from, Long local_to)
  {
    return subSet(from.longValue(), local_to.longValue());
  }
  
  public Long first()
  {
    return Long.valueOf(firstLong());
  }
  
  public Long last()
  {
    return Long.valueOf(lastLong());
  }
  
  @Deprecated
  public LongBidirectionalIterator longIterator()
  {
    return iterator();
  }
  
  public abstract LongBidirectionalIterator iterator();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLongSortedSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */