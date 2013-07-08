package it.unimi.dsi.fastutil.chars;

import java.util.Iterator;

public abstract interface CharIterator
  extends Iterator<Character>
{
  public abstract char nextChar();
  
  public abstract int skip(int paramInt);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */