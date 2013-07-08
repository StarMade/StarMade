package it.unimi.dsi.fastutil.ints;

public abstract class AbstractIntSortedSet
  extends AbstractIntSet
  implements IntSortedSet
{
  public IntSortedSet headSet(Integer local_to)
  {
    return headSet(local_to.intValue());
  }
  
  public IntSortedSet tailSet(Integer from)
  {
    return tailSet(from.intValue());
  }
  
  public IntSortedSet subSet(Integer from, Integer local_to)
  {
    return subSet(from.intValue(), local_to.intValue());
  }
  
  public Integer first()
  {
    return Integer.valueOf(firstInt());
  }
  
  public Integer last()
  {
    return Integer.valueOf(lastInt());
  }
  
  @Deprecated
  public IntBidirectionalIterator intIterator()
  {
    return iterator();
  }
  
  public abstract IntBidirectionalIterator iterator();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractIntSortedSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */