package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.Stack;

public abstract interface CharStack extends Stack<Character>
{
  public abstract void push(char paramChar);

  public abstract char popChar();

  public abstract char topChar();

  public abstract char peekChar(int paramInt);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharStack
 * JD-Core Version:    0.6.2
 */