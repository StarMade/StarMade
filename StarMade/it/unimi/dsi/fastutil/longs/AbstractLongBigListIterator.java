package it.unimi.dsi.fastutil.longs;

public abstract class AbstractLongBigListIterator
  extends AbstractLongBidirectionalIterator
  implements LongBigListIterator
{
  public void set(Long local_ok)
  {
    set(local_ok.longValue());
  }
  
  public void add(Long local_ok)
  {
    add(local_ok.longValue());
  }
  
  public void set(long local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public void add(long local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public long skip(long local_n)
  {
    long local_i = local_n;
    while ((local_i-- != 0L) && (hasNext())) {
      nextLong();
    }
    return local_n - local_i - 1L;
  }
  
  public long back(long local_n)
  {
    long local_i = local_n;
    while ((local_i-- != 0L) && (hasPrevious())) {
      previousLong();
    }
    return local_n - local_i - 1L;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLongBigListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */