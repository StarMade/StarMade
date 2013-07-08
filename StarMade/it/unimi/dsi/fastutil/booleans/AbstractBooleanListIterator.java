package it.unimi.dsi.fastutil.booleans;

public abstract class AbstractBooleanListIterator
  extends AbstractBooleanBidirectionalIterator
  implements BooleanListIterator
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
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.AbstractBooleanListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */