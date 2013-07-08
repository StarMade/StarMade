package it.unimi.dsi.fastutil.bytes;

import java.util.ListIterator;

public abstract interface ByteListIterator
  extends ListIterator<Byte>, ByteBidirectionalIterator
{
  public abstract void set(byte paramByte);
  
  public abstract void add(byte paramByte);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */