package it.unimi.dsi.fastutil.floats;

public abstract class AbstractFloatBidirectionalIterator
  extends AbstractFloatIterator
  implements FloatBidirectionalIterator
{
  public float previousFloat()
  {
    return previous().floatValue();
  }
  
  public Float previous()
  {
    return Float.valueOf(previousFloat());
  }
  
  public int back(int local_n)
  {
    int local_i = local_n;
    while ((local_i-- != 0) && (hasPrevious())) {
      previousFloat();
    }
    return local_n - local_i - 1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloatBidirectionalIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */