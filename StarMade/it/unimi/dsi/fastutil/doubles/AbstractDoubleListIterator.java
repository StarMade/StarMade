package it.unimi.dsi.fastutil.doubles;

public abstract class AbstractDoubleListIterator
  extends AbstractDoubleBidirectionalIterator
  implements DoubleListIterator
{
  public void set(Double local_ok)
  {
    set(local_ok.doubleValue());
  }
  
  public void add(Double local_ok)
  {
    add(local_ok.doubleValue());
  }
  
  public void set(double local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public void add(double local_k)
  {
    throw new UnsupportedOperationException();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDoubleListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */