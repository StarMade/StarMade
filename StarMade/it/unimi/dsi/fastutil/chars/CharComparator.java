package it.unimi.dsi.fastutil.chars;

import java.util.Comparator;

public abstract interface CharComparator
  extends Comparator<Character>
{
  public abstract int compare(char paramChar1, char paramChar2);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharComparator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */