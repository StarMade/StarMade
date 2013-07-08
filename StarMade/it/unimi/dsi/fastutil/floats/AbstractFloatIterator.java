package it.unimi.dsi.fastutil.floats;

public abstract class AbstractFloatIterator
  implements FloatIterator
{
  public float nextFloat()
  {
    return next().floatValue();
  }
  
  public Float next()
  {
    return Float.valueOf(nextFloat());
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException();
  }
  
  public int skip(int local_n)
  {
    int local_i = local_n;
    while ((local_i-- != 0) && (hasNext())) {
      nextFloat();
    }
    return local_n - local_i - 1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloatIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */