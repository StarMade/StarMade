package it.unimi.dsi.fastutil.chars;

import java.util.Collection;

public abstract interface CharCollection extends Collection<Character>, CharIterable
{
  public abstract CharIterator iterator();

  @Deprecated
  public abstract CharIterator charIterator();

  public abstract <T> T[] toArray(T[] paramArrayOfT);

  public abstract boolean contains(char paramChar);

  public abstract char[] toCharArray();

  public abstract char[] toCharArray(char[] paramArrayOfChar);

  public abstract char[] toArray(char[] paramArrayOfChar);

  public abstract boolean add(char paramChar);

  public abstract boolean rem(char paramChar);

  public abstract boolean addAll(CharCollection paramCharCollection);

  public abstract boolean containsAll(CharCollection paramCharCollection);

  public abstract boolean removeAll(CharCollection paramCharCollection);

  public abstract boolean retainAll(CharCollection paramCharCollection);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharCollection
 * JD-Core Version:    0.6.2
 */