package it.unimi.dsi.fastutil.chars;

import java.util.List;

public abstract interface CharList
  extends List<Character>, Comparable<List<? extends Character>>, CharCollection
{
  public abstract CharListIterator iterator();
  
  @Deprecated
  public abstract CharListIterator charListIterator();
  
  @Deprecated
  public abstract CharListIterator charListIterator(int paramInt);
  
  public abstract CharListIterator listIterator();
  
  public abstract CharListIterator listIterator(int paramInt);
  
  @Deprecated
  public abstract CharList charSubList(int paramInt1, int paramInt2);
  
  public abstract CharList subList(int paramInt1, int paramInt2);
  
  public abstract void size(int paramInt);
  
  public abstract void getElements(int paramInt1, char[] paramArrayOfChar, int paramInt2, int paramInt3);
  
  public abstract void removeElements(int paramInt1, int paramInt2);
  
  public abstract void addElements(int paramInt, char[] paramArrayOfChar);
  
  public abstract void addElements(int paramInt1, char[] paramArrayOfChar, int paramInt2, int paramInt3);
  
  public abstract boolean add(char paramChar);
  
  public abstract void add(int paramInt, char paramChar);
  
  public abstract boolean addAll(int paramInt, CharCollection paramCharCollection);
  
  public abstract boolean addAll(int paramInt, CharList paramCharList);
  
  public abstract boolean addAll(CharList paramCharList);
  
  public abstract char getChar(int paramInt);
  
  public abstract int indexOf(char paramChar);
  
  public abstract int lastIndexOf(char paramChar);
  
  public abstract char removeChar(int paramInt);
  
  public abstract char set(int paramInt, char paramChar);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */