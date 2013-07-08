package org.hsqldb.persist;

public abstract interface LobStore
{
  public abstract byte[] getBlockBytes(int paramInt1, int paramInt2);
  
  public abstract void setBlockBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2);
  
  public abstract void setBlockBytes(byte[] paramArrayOfByte, long paramLong, int paramInt1, int paramInt2);
  
  public abstract int getBlockSize();
  
  public abstract void close();
  
  public abstract void synch();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.persist.LobStore
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */