package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.Stack;

public abstract interface IntStack extends Stack<Integer>
{
  public abstract void push(int paramInt);

  public abstract int popInt();

  public abstract int topInt();

  public abstract int peekInt(int paramInt);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntStack
 * JD-Core Version:    0.6.2
 */