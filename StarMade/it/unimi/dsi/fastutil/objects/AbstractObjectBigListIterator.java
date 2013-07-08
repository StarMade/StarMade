package it.unimi.dsi.fastutil.objects;

public abstract class AbstractObjectBigListIterator<K>
  extends AbstractObjectBidirectionalIterator<K>
  implements ObjectBigListIterator<K>
{
  public void set(K local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public void add(K local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public long skip(long local_n)
  {
    long local_i = local_n;
    while ((local_i-- != 0L) && (hasNext())) {
      next();
    }
    return local_n - local_i - 1L;
  }
  
  public long back(long local_n)
  {
    long local_i = local_n;
    while ((local_i-- != 0L) && (hasPrevious())) {
      previous();
    }
    return local_n - local_i - 1L;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObjectBigListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */