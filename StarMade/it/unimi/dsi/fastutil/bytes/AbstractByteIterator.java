package it.unimi.dsi.fastutil.bytes;

public abstract class AbstractByteIterator
  implements ByteIterator
{
  public byte nextByte()
  {
    return next().byteValue();
  }
  
  public Byte next()
  {
    return Byte.valueOf(nextByte());
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException();
  }
  
  public int skip(int local_n)
  {
    int local_i = local_n;
    while ((local_i-- != 0) && (hasNext())) {
      nextByte();
    }
    return local_n - local_i - 1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByteIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */