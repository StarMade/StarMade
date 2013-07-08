package it.unimi.dsi.fastutil.booleans;

import it.unimi.dsi.fastutil.Stack;

public abstract interface BooleanStack
  extends Stack<Boolean>
{
  public abstract void push(boolean paramBoolean);
  
  public abstract boolean popBoolean();
  
  public abstract boolean topBoolean();
  
  public abstract boolean peekBoolean(int paramInt);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.BooleanStack
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */