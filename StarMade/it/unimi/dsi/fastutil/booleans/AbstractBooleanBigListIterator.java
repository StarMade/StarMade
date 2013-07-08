package it.unimi.dsi.fastutil.booleans;

public abstract class AbstractBooleanBigListIterator
  extends AbstractBooleanBidirectionalIterator
  implements BooleanBigListIterator
{
  public void set(Boolean local_ok)
  {
    set(local_ok.booleanValue());
  }
  
  public void add(Boolean local_ok)
  {
    add(local_ok.booleanValue());
  }
  
  public void set(boolean local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public void add(boolean local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public long skip(long local_n)
  {
    long local_i = local_n;
    while ((local_i-- != 0L) && (hasNext())) {
      nextBoolean();
    }
    return local_n - local_i - 1L;
  }
  
  public long back(long local_n)
  {
    long local_i = local_n;
    while ((local_i-- != 0L) && (hasPrevious())) {
      previousBoolean();
    }
    return local_n - local_i - 1L;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.AbstractBooleanBigListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */