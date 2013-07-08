package it.unimi.dsi.fastutil.chars;

import java.util.ListIterator;

public abstract interface CharListIterator
  extends ListIterator<Character>, CharBidirectionalIterator
{
  public abstract void set(char paramChar);
  
  public abstract void add(char paramChar);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */