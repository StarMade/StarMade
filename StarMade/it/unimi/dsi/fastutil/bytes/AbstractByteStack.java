package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.AbstractStack;

public abstract class AbstractByteStack
  extends AbstractStack<Byte>
  implements ByteStack
{
  public void push(Byte local_o)
  {
    push(local_o.byteValue());
  }
  
  public Byte pop()
  {
    return Byte.valueOf(popByte());
  }
  
  public Byte top()
  {
    return Byte.valueOf(topByte());
  }
  
  public Byte peek(int local_i)
  {
    return Byte.valueOf(peekByte(local_i));
  }
  
  public void push(byte local_k)
  {
    push(Byte.valueOf(local_k));
  }
  
  public byte popByte()
  {
    return pop().byteValue();
  }
  
  public byte topByte()
  {
    return top().byteValue();
  }
  
  public byte peekByte(int local_i)
  {
    return peek(local_i).byteValue();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByteStack
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */