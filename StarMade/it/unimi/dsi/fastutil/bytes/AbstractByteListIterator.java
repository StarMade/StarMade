package it.unimi.dsi.fastutil.bytes;

public abstract class AbstractByteListIterator
  extends AbstractByteBidirectionalIterator
  implements ByteListIterator
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
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByteListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */