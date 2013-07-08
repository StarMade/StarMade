package it.unimi.dsi.fastutil.ints;

public abstract class AbstractIntIterator
  implements IntIterator
{
  public int nextInt()
  {
    return next().intValue();
  }
  
  public Integer next()
  {
    return Integer.valueOf(nextInt());
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException();
  }
  
  public int skip(int local_n)
  {
    int local_i = local_n;
    while ((local_i-- != 0) && (hasNext())) {
      nextInt();
    }
    return local_n - local_i - 1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractIntIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */