package it.unimi.dsi.fastutil.ints;

public abstract class AbstractIntBigListIterator
  extends AbstractIntBidirectionalIterator
  implements IntBigListIterator
{
  public void set(Integer local_ok)
  {
    set(local_ok.intValue());
  }
  
  public void add(Integer local_ok)
  {
    add(local_ok.intValue());
  }
  
  public void set(int local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public void add(int local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public long skip(long local_n)
  {
    long local_i = local_n;
    while ((local_i-- != 0L) && (hasNext())) {
      nextInt();
    }
    return local_n - local_i - 1L;
  }
  
  public long back(long local_n)
  {
    long local_i = local_n;
    while ((local_i-- != 0L) && (hasPrevious())) {
      previousInt();
    }
    return local_n - local_i - 1L;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractIntBigListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */