package it.unimi.dsi.fastutil.floats;

public abstract class AbstractFloatBigListIterator
  extends AbstractFloatBidirectionalIterator
  implements FloatBigListIterator
{
  public void set(Float local_ok)
  {
    set(local_ok.floatValue());
  }
  
  public void add(Float local_ok)
  {
    add(local_ok.floatValue());
  }
  
  public void set(float local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public void add(float local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public long skip(long local_n)
  {
    long local_i = local_n;
    while ((local_i-- != 0L) && (hasNext())) {
      nextFloat();
    }
    return local_n - local_i - 1L;
  }
  
  public long back(long local_n)
  {
    long local_i = local_n;
    while ((local_i-- != 0L) && (hasPrevious())) {
      previousFloat();
    }
    return local_n - local_i - 1L;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloatBigListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */