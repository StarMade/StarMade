package it.unimi.dsi.fastutil.shorts;

public abstract class AbstractShortListIterator
  extends AbstractShortBidirectionalIterator
  implements ShortListIterator
{
  public void set(Short local_ok)
  {
    set(local_ok.shortValue());
  }
  
  public void add(Short local_ok)
  {
    add(local_ok.shortValue());
  }
  
  public void set(short local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public void add(short local_k)
  {
    throw new UnsupportedOperationException();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShortListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */