package it.unimi.dsi.fastutil.booleans;

public abstract class AbstractBooleanBidirectionalIterator
  extends AbstractBooleanIterator
  implements BooleanBidirectionalIterator
{
  public boolean previousBoolean()
  {
    return previous().booleanValue();
  }
  
  public Boolean previous()
  {
    return Boolean.valueOf(previousBoolean());
  }
  
  public int back(int local_n)
  {
    int local_i = local_n;
    while ((local_i-- != 0) && (hasPrevious())) {
      previousBoolean();
    }
    return local_n - local_i - 1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.AbstractBooleanBidirectionalIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */