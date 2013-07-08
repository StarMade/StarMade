package it.unimi.dsi.fastutil.objects;

public abstract class AbstractObjectBidirectionalIterator<K>
  extends AbstractObjectIterator<K>
  implements ObjectBidirectionalIterator<K>
{
  public int back(int local_n)
  {
    int local_i = local_n;
    while ((local_i-- != 0) && (hasPrevious())) {
      previous();
    }
    return local_n - local_i - 1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObjectBidirectionalIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */