package it.unimi.dsi.fastutil.chars;

public abstract interface CharHash
{
  public static abstract interface Strategy
  {
    public abstract int hashCode(char paramChar);

    public abstract boolean equals(char paramChar1, char paramChar2);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharHash
 * JD-Core Version:    0.6.2
 */