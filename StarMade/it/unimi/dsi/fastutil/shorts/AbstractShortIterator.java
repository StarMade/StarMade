package it.unimi.dsi.fastutil.shorts;

public abstract class AbstractShortIterator
  implements ShortIterator
{
  public short nextShort()
  {
    return next().shortValue();
  }
  
  public Short next()
  {
    return Short.valueOf(nextShort());
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException();
  }
  
  public int skip(int local_n)
  {
    int local_i = local_n;
    while ((local_i-- != 0) && (hasNext())) {
      nextShort();
    }
    return local_n - local_i - 1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShortIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */