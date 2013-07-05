package it.unimi.dsi.fastutil.chars;

import java.util.Iterator;

public abstract interface CharIterator extends Iterator<Character>
{
  public abstract char nextChar();

  public abstract int skip(int paramInt);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharIterator
 * JD-Core Version:    0.6.2
 */