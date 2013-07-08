package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.BigList;

public abstract interface CharBigList
  extends BigList<Character>, CharCollection, Comparable<BigList<? extends Character>>
{
  public abstract CharBigListIterator iterator();
  
  public abstract CharBigListIterator listIterator();
  
  public abstract CharBigListIterator listIterator(long paramLong);
  
  public abstract CharBigList subList(long paramLong1, long paramLong2);
  
  public abstract void getElements(long paramLong1, char[][] paramArrayOfChar, long paramLong2, long paramLong3);
  
  public abstract void removeElements(long paramLong1, long paramLong2);
  
  public abstract void addElements(long paramLong, char[][] paramArrayOfChar);
  
  public abstract void addElements(long paramLong1, char[][] paramArrayOfChar, long paramLong2, long paramLong3);
  
  public abstract void add(long paramLong, char paramChar);
  
  public abstract boolean addAll(long paramLong, CharCollection paramCharCollection);
  
  public abstract boolean addAll(long paramLong, CharBigList paramCharBigList);
  
  public abstract boolean addAll(CharBigList paramCharBigList);
  
  public abstract char getChar(long paramLong);
  
  public abstract long indexOf(char paramChar);
  
  public abstract long lastIndexOf(char paramChar);
  
  public abstract char removeChar(long paramLong);
  
  public abstract char set(long paramLong, char paramChar);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharBigList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */