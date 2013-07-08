package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.AbstractPriorityQueue;

public abstract class AbstractBytePriorityQueue
  extends AbstractPriorityQueue<Byte>
  implements BytePriorityQueue
{
  public void enqueue(Byte local_x)
  {
    enqueue(local_x.byteValue());
  }
  
  public Byte dequeue()
  {
    return Byte.valueOf(dequeueByte());
  }
  
  public Byte first()
  {
    return Byte.valueOf(firstByte());
  }
  
  public Byte last()
  {
    return Byte.valueOf(lastByte());
  }
  
  public byte lastByte()
  {
    throw new UnsupportedOperationException();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractBytePriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */