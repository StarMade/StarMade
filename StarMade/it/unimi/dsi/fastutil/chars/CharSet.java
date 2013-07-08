package it.unimi.dsi.fastutil.chars;

import java.util.Set;

public abstract interface CharSet
  extends CharCollection, Set<Character>
{
  public abstract CharIterator iterator();
  
  public abstract boolean remove(char paramChar);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */