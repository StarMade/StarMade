package it.unimi.dsi.fastutil.bytes;

public abstract class AbstractByteBigListIterator
  extends AbstractByteBidirectionalIterator
  implements ByteBigListIterator
{
  public void set(Byte local_ok)
  {
    set(local_ok.byteValue());
  }
  
  public void add(Byte local_ok)
  {
    add(local_ok.byteValue());
  }
  
  public void set(byte local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public void add(byte local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public long skip(long local_n)
  {
    long local_i = local_n;
    while ((local_i-- != 0L) && (hasNext())) {
      nextByte();
    }
    return local_n - local_i - 1L;
  }
  
  public long back(long local_n)
  {
    long local_i = local_n;
    while ((local_i-- != 0L) && (hasPrevious())) {
      previousByte();
    }
    return local_n - local_i - 1L;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByteBigListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */