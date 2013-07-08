package it.unimi.dsi.fastutil.objects;

public abstract class AbstractObjectListIterator<K>
  extends AbstractObjectBidirectionalIterator<K>
  implements ObjectListIterator<K>
{
  public void set(K local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public void add(K local_k)
  {
    throw new UnsupportedOperationException();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObjectListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */