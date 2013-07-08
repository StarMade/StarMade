package it.unimi.dsi.fastutil.doubles;

public abstract class AbstractDoubleBigListIterator
  extends AbstractDoubleBidirectionalIterator
  implements DoubleBigListIterator
{
  public void set(Double local_ok)
  {
    set(local_ok.doubleValue());
  }
  
  public void add(Double local_ok)
  {
    add(local_ok.doubleValue());
  }
  
  public void set(double local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public void add(double local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public long skip(long local_n)
  {
    long local_i = local_n;
    while ((local_i-- != 0L) && (hasNext())) {
      nextDouble();
    }
    return local_n - local_i - 1L;
  }
  
  public long back(long local_n)
  {
    long local_i = local_n;
    while ((local_i-- != 0L) && (hasPrevious())) {
      previousDouble();
    }
    return local_n - local_i - 1L;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDoubleBigListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */