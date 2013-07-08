package it.unimi.dsi.fastutil.objects;

public abstract class AbstractObjectIterator<K>
  implements ObjectIterator<K>
{
  public void remove()
  {
    throw new UnsupportedOperationException();
  }
  
  public int skip(int local_n)
  {
    int local_i = local_n;
    while ((local_i-- != 0) && (hasNext())) {
      next();
    }
    return local_n - local_i - 1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObjectIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */