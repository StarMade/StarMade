package it.unimi.dsi.fastutil.floats;

public abstract class AbstractFloatComparator
  implements FloatComparator
{
  public int compare(Float ok1, Float ok2)
  {
    return compare(ok1.floatValue(), ok2.floatValue());
  }
  
  public abstract int compare(float paramFloat1, float paramFloat2);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloatComparator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */