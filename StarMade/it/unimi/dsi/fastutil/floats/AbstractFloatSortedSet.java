package it.unimi.dsi.fastutil.floats;

public abstract class AbstractFloatSortedSet
  extends AbstractFloatSet
  implements FloatSortedSet
{
  public FloatSortedSet headSet(Float local_to)
  {
    return headSet(local_to.floatValue());
  }
  
  public FloatSortedSet tailSet(Float from)
  {
    return tailSet(from.floatValue());
  }
  
  public FloatSortedSet subSet(Float from, Float local_to)
  {
    return subSet(from.floatValue(), local_to.floatValue());
  }
  
  public Float first()
  {
    return Float.valueOf(firstFloat());
  }
  
  public Float last()
  {
    return Float.valueOf(lastFloat());
  }
  
  @Deprecated
  public FloatBidirectionalIterator floatIterator()
  {
    return iterator();
  }
  
  public abstract FloatBidirectionalIterator iterator();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloatSortedSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */