package it.unimi.dsi.fastutil.doubles;

public abstract class AbstractDoubleBidirectionalIterator
  extends AbstractDoubleIterator
  implements DoubleBidirectionalIterator
{
  public double previousDouble()
  {
    return previous().doubleValue();
  }
  
  public Double previous()
  {
    return Double.valueOf(previousDouble());
  }
  
  public int back(int local_n)
  {
    int local_i = local_n;
    while ((local_i-- != 0) && (hasPrevious())) {
      previousDouble();
    }
    return local_n - local_i - 1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDoubleBidirectionalIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */