package it.unimi.dsi.fastutil.chars;

public abstract interface CharHash
{
  public static abstract interface Strategy
  {
    public abstract int hashCode(char paramChar);
    
    public abstract boolean equals(char paramChar1, char paramChar2);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharHash
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */