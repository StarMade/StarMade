package it.unimi.dsi.fastutil.bytes;

public abstract class AbstractByteBidirectionalIterator
  extends AbstractByteIterator
  implements ByteBidirectionalIterator
{
  public byte previousByte()
  {
    return previous().byteValue();
  }
  
  public Byte previous()
  {
    return Byte.valueOf(previousByte());
  }
  
  public int back(int local_n)
  {
    int local_i = local_n;
    while ((local_i-- != 0) && (hasPrevious())) {
      previousByte();
    }
    return local_n - local_i - 1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByteBidirectionalIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */