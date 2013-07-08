package it.unimi.dsi.fastutil.ints;

public abstract interface IntHash
{
  public static abstract interface Strategy
  {
    public abstract int hashCode(int paramInt);
    
    public abstract boolean equals(int paramInt1, int paramInt2);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntHash
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */