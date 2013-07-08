package it.unimi.dsi.fastutil.longs;

public abstract class AbstractLongIterator
  implements LongIterator
{
  public long nextLong()
  {
    return next().longValue();
  }
  
  public Long next()
  {
    return Long.valueOf(nextLong());
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException();
  }
  
  public int skip(int local_n)
  {
    int local_i = local_n;
    while ((local_i-- != 0) && (hasNext())) {
      nextLong();
    }
    return local_n - local_i - 1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLongIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */