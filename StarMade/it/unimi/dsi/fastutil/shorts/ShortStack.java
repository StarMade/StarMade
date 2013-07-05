package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.Stack;

public abstract interface ShortStack extends Stack<Short>
{
  public abstract void push(short paramShort);

  public abstract short popShort();

  public abstract short topShort();

  public abstract short peekShort(int paramInt);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortStack
 * JD-Core Version:    0.6.2
 */