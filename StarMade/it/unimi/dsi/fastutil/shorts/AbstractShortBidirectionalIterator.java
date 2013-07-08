package it.unimi.dsi.fastutil.shorts;

public abstract class AbstractShortBidirectionalIterator
  extends AbstractShortIterator
  implements ShortBidirectionalIterator
{
  public short previousShort()
  {
    return previous().shortValue();
  }
  
  public Short previous()
  {
    return Short.valueOf(previousShort());
  }
  
  public int back(int local_n)
  {
    int local_i = local_n;
    while ((local_i-- != 0) && (hasPrevious())) {
      previousShort();
    }
    return local_n - local_i - 1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShortBidirectionalIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */