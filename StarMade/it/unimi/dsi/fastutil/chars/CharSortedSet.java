package it.unimi.dsi.fastutil.chars;

import java.util.SortedSet;

public abstract interface CharSortedSet
  extends CharSet, SortedSet<Character>
{
  public abstract CharBidirectionalIterator iterator(char paramChar);
  
  @Deprecated
  public abstract CharBidirectionalIterator charIterator();
  
  public abstract CharBidirectionalIterator iterator();
  
  public abstract CharSortedSet subSet(Character paramCharacter1, Character paramCharacter2);
  
  public abstract CharSortedSet headSet(Character paramCharacter);
  
  public abstract CharSortedSet tailSet(Character paramCharacter);
  
  public abstract CharComparator comparator();
  
  public abstract CharSortedSet subSet(char paramChar1, char paramChar2);
  
  public abstract CharSortedSet headSet(char paramChar);
  
  public abstract CharSortedSet tailSet(char paramChar);
  
  public abstract char firstChar();
  
  public abstract char lastChar();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharSortedSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */