package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;

public abstract interface CharBidirectionalIterator
  extends CharIterator, ObjectBidirectionalIterator<Character>
{
  public abstract char previousChar();
  
  public abstract int back(int paramInt);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharBidirectionalIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */