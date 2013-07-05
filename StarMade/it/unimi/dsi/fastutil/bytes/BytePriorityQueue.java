package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.PriorityQueue;

public abstract interface BytePriorityQueue extends PriorityQueue<Byte>
{
  public abstract void enqueue(byte paramByte);

  public abstract byte dequeueByte();

  public abstract byte firstByte();

  public abstract byte lastByte();

  public abstract ByteComparator comparator();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.BytePriorityQueue
 * JD-Core Version:    0.6.2
 */