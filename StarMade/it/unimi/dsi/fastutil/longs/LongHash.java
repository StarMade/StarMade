package it.unimi.dsi.fastutil.longs;

public abstract interface LongHash
{
  public static abstract interface Strategy
  {
    public abstract int hashCode(long paramLong);
    
    public abstract boolean equals(long paramLong1, long paramLong2);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongHash
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */