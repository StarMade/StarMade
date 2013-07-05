package it.unimi.dsi.fastutil.shorts;

public abstract interface ShortHash
{
  public static abstract interface Strategy
  {
    public abstract int hashCode(short paramShort);

    public abstract boolean equals(short paramShort1, short paramShort2);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortHash
 * JD-Core Version:    0.6.2
 */