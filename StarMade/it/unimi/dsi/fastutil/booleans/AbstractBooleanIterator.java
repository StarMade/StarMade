package it.unimi.dsi.fastutil.booleans;

public abstract class AbstractBooleanIterator
  implements BooleanIterator
{
  public boolean nextBoolean()
  {
    return next().booleanValue();
  }
  
  public Boolean next()
  {
    return Boolean.valueOf(nextBoolean());
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException();
  }
  
  public int skip(int local_n)
  {
    int local_i = local_n;
    while ((local_i-- != 0) && (hasNext())) {
      nextBoolean();
    }
    return local_n - local_i - 1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.AbstractBooleanIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */