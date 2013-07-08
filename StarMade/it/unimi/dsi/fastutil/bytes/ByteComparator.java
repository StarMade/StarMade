package it.unimi.dsi.fastutil.bytes;

import java.util.Comparator;

public abstract interface ByteComparator
  extends Comparator<Byte>
{
  public abstract int compare(byte paramByte1, byte paramByte2);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteComparator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */