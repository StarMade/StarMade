package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;

public abstract interface ByteBidirectionalIterator
  extends ByteIterator, ObjectBidirectionalIterator<Byte>
{
  public abstract byte previousByte();
  
  public abstract int back(int paramInt);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteBidirectionalIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */