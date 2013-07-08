package it.unimi.dsi.fastutil.ints;

import it.unimi.dsi.fastutil.Stack;

public abstract interface IntStack
  extends Stack<Integer>
{
  public abstract void push(int paramInt);
  
  public abstract int popInt();
  
  public abstract int topInt();
  
  public abstract int peekInt(int paramInt);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntStack
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */