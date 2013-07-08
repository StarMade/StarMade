package it.unimi.dsi.fastutil.bytes;

public abstract interface ByteHash
{
  public static abstract interface Strategy
  {
    public abstract int hashCode(byte paramByte);
    
    public abstract boolean equals(byte paramByte1, byte paramByte2);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteHash
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */