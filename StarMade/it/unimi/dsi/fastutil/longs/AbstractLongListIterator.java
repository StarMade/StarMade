package it.unimi.dsi.fastutil.longs;

public abstract class AbstractLongListIterator
  extends AbstractLongBidirectionalIterator
  implements LongListIterator
{
  public void set(Long local_ok)
  {
    set(local_ok.longValue());
  }
  
  public void add(Long local_ok)
  {
    add(local_ok.longValue());
  }
  
  public void set(long local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public void add(long local_k)
  {
    throw new UnsupportedOperationException();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLongListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */