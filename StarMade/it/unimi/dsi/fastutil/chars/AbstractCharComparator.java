package it.unimi.dsi.fastutil.chars;

public abstract class AbstractCharComparator
  implements CharComparator
{
  public int compare(Character ok1, Character ok2)
  {
    return compare(ok1.charValue(), ok2.charValue());
  }
  
  public abstract int compare(char paramChar1, char paramChar2);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractCharComparator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */