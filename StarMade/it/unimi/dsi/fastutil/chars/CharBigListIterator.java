package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.BigListIterator;

public abstract interface CharBigListIterator
  extends CharBidirectionalIterator, BigListIterator<Character>
{
  public abstract void set(char paramChar);
  
  public abstract void add(char paramChar);
  
  public abstract void set(Character paramCharacter);
  
  public abstract void add(Character paramCharacter);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharBigListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */