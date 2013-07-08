package it.unimi.dsi.fastutil.floats;

public abstract class AbstractFloatListIterator
  extends AbstractFloatBidirectionalIterator
  implements FloatListIterator
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
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloatListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */