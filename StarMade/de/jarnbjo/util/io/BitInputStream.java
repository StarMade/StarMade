package de.jarnbjo.util.io;

import java.io.IOException;

public abstract interface BitInputStream
{
  public static final int LITTLE_ENDIAN = 0;
  public static final int BIG_ENDIAN = 1;
  
  public abstract boolean getBit()
    throws IOException;
  
  public abstract int getInt(int paramInt)
    throws IOException;
  
  public abstract int getSignedInt(int paramInt)
    throws IOException;
  
  public abstract int getInt(HuffmanNode paramHuffmanNode)
    throws IOException;
  
  public abstract int readSignedRice(int paramInt)
    throws IOException;
  
  public abstract void readSignedRice(int paramInt1, int[] paramArrayOfInt, int paramInt2, int paramInt3)
    throws IOException;
  
  public abstract long getLong(int paramInt)
    throws IOException;
  
  public abstract void align();
  
  public abstract void setEndian(int paramInt);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     de.jarnbjo.util.io.BitInputStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */