package it.unimi.dsi.fastutil.doubles;

public abstract class AbstractDoubleSortedSet
  extends AbstractDoubleSet
  implements DoubleSortedSet
{
  public DoubleSortedSet headSet(Double local_to)
  {
    return headSet(local_to.doubleValue());
  }
  
  public DoubleSortedSet tailSet(Double from)
  {
    return tailSet(from.doubleValue());
  }
  
  public DoubleSortedSet subSet(Double from, Double local_to)
  {
    return subSet(from.doubleValue(), local_to.doubleValue());
  }
  
  public Double first()
  {
    return Double.valueOf(firstDouble());
  }
  
  public Double last()
  {
    return Double.valueOf(lastDouble());
  }
  
  @Deprecated
  public DoubleBidirectionalIterator doubleIterator()
  {
    return iterator();
  }
  
  public abstract DoubleBidirectionalIterator iterator();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDoubleSortedSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */