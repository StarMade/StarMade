package it.unimi.dsi.fastutil.bytes;

public abstract class AbstractByteSortedSet
  extends AbstractByteSet
  implements ByteSortedSet
{
  public ByteSortedSet headSet(Byte local_to)
  {
    return headSet(local_to.byteValue());
  }
  
  public ByteSortedSet tailSet(Byte from)
  {
    return tailSet(from.byteValue());
  }
  
  public ByteSortedSet subSet(Byte from, Byte local_to)
  {
    return subSet(from.byteValue(), local_to.byteValue());
  }
  
  public Byte first()
  {
    return Byte.valueOf(firstByte());
  }
  
  public Byte last()
  {
    return Byte.valueOf(lastByte());
  }
  
  @Deprecated
  public ByteBidirectionalIterator byteIterator()
  {
    return iterator();
  }
  
  public abstract ByteBidirectionalIterator iterator();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByteSortedSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */