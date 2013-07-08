package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.Stack;

public abstract interface ByteStack
  extends Stack<Byte>
{
  public abstract void push(byte paramByte);
  
  public abstract byte popByte();
  
  public abstract byte topByte();
  
  public abstract byte peekByte(int paramInt);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteStack
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */