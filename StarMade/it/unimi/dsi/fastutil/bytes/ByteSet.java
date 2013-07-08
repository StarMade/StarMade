package it.unimi.dsi.fastutil.bytes;

import java.util.Set;

public abstract interface ByteSet
  extends ByteCollection, Set<Byte>
{
  public abstract ByteIterator iterator();
  
  public abstract boolean remove(byte paramByte);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */