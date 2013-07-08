package it.unimi.dsi.fastutil.floats;

public abstract interface FloatHash
{
  public static abstract interface Strategy
  {
    public abstract int hashCode(float paramFloat);
    
    public abstract boolean equals(float paramFloat1, float paramFloat2);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatHash
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */