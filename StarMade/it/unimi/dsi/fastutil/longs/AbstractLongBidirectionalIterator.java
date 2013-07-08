package it.unimi.dsi.fastutil.longs;

public abstract class AbstractLongBidirectionalIterator
  extends AbstractLongIterator
  implements LongBidirectionalIterator
{
  public long previousLong()
  {
    return previous().longValue();
  }
  
  public Long previous()
  {
    return Long.valueOf(previousLong());
  }
  
  public int back(int local_n)
  {
    int local_i = local_n;
    while ((local_i-- != 0) && (hasPrevious())) {
      previousLong();
    }
    return local_n - local_i - 1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLongBidirectionalIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */