package it.unimi.dsi.fastutil.shorts;

public abstract class AbstractShortBigListIterator
  extends AbstractShortBidirectionalIterator
  implements ShortBigListIterator
{
  public void set(Short local_ok)
  {
    set(local_ok.shortValue());
  }
  
  public void add(Short local_ok)
  {
    add(local_ok.shortValue());
  }
  
  public void set(short local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public void add(short local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public long skip(long local_n)
  {
    long local_i = local_n;
    while ((local_i-- != 0L) && (hasNext())) {
      nextShort();
    }
    return local_n - local_i - 1L;
  }
  
  public long back(long local_n)
  {
    long local_i = local_n;
    while ((local_i-- != 0L) && (hasPrevious())) {
      previousShort();
    }
    return local_n - local_i - 1L;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShortBigListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */