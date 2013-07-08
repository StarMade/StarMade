package it.unimi.dsi.fastutil.shorts;

public abstract class AbstractShortSortedSet
  extends AbstractShortSet
  implements ShortSortedSet
{
  public ShortSortedSet headSet(Short local_to)
  {
    return headSet(local_to.shortValue());
  }
  
  public ShortSortedSet tailSet(Short from)
  {
    return tailSet(from.shortValue());
  }
  
  public ShortSortedSet subSet(Short from, Short local_to)
  {
    return subSet(from.shortValue(), local_to.shortValue());
  }
  
  public Short first()
  {
    return Short.valueOf(firstShort());
  }
  
  public Short last()
  {
    return Short.valueOf(lastShort());
  }
  
  @Deprecated
  public ShortBidirectionalIterator shortIterator()
  {
    return iterator();
  }
  
  public abstract ShortBidirectionalIterator iterator();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShortSortedSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */