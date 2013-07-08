package it.unimi.dsi.fastutil.ints;

public abstract class AbstractIntListIterator
  extends AbstractIntBidirectionalIterator
  implements IntListIterator
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
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractIntListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */