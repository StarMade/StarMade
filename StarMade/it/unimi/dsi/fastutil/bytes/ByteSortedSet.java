package it.unimi.dsi.fastutil.bytes;

import java.util.SortedSet;

public abstract interface ByteSortedSet
  extends ByteSet, SortedSet<Byte>
{
  public abstract ByteBidirectionalIterator iterator(byte paramByte);
  
  @Deprecated
  public abstract ByteBidirectionalIterator byteIterator();
  
  public abstract ByteBidirectionalIterator iterator();
  
  public abstract ByteSortedSet subSet(Byte paramByte1, Byte paramByte2);
  
  public abstract ByteSortedSet headSet(Byte paramByte);
  
  public abstract ByteSortedSet tailSet(Byte paramByte);
  
  public abstract ByteComparator comparator();
  
  public abstract ByteSortedSet subSet(byte paramByte1, byte paramByte2);
  
  public abstract ByteSortedSet headSet(byte paramByte);
  
  public abstract ByteSortedSet tailSet(byte paramByte);
  
  public abstract byte firstByte();
  
  public abstract byte lastByte();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteSortedSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */