package it.unimi.dsi.fastutil.doubles;

public abstract class AbstractDoubleIterator
  implements DoubleIterator
{
  public double nextDouble()
  {
    return next().doubleValue();
  }
  
  public Double next()
  {
    return Double.valueOf(nextDouble());
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException();
  }
  
  public int skip(int local_n)
  {
    int local_i = local_n;
    while ((local_i-- != 0) && (hasNext())) {
      nextDouble();
    }
    return local_n - local_i - 1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDoubleIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */