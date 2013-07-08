package it.unimi.dsi.fastutil;

public abstract interface Stack<K>
{
  public abstract void push(K paramK);
  
  public abstract K pop();
  
  public abstract boolean isEmpty();
  
  public abstract K top();
  
  public abstract K peek(int paramInt);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.Stack
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */