package it.unimi.dsi.fastutil.bytes;

import java.util.Iterator;

public abstract interface ByteIterator
  extends Iterator<Byte>
{
  public abstract byte nextByte();
  
  public abstract int skip(int paramInt);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */