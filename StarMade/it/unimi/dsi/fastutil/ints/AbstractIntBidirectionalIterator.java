package it.unimi.dsi.fastutil.ints;

public abstract class AbstractIntBidirectionalIterator
  extends AbstractIntIterator
  implements IntBidirectionalIterator
{
  public int previousInt()
  {
    return previous().intValue();
  }
  
  public Integer previous()
  {
    return Integer.valueOf(previousInt());
  }
  
  public int back(int local_n)
  {
    int local_i = local_n;
    while ((local_i-- != 0) && (hasPrevious())) {
      previousInt();
    }
    return local_n - local_i - 1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractIntBidirectionalIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */