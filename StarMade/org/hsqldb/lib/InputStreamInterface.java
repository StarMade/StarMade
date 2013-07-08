package org.hsqldb.lib;

import java.io.IOException;

public abstract interface InputStreamInterface
{
  public abstract int read()
    throws IOException;
  
  public abstract int read(byte[] paramArrayOfByte)
    throws IOException;
  
  public abstract int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException;
  
  public abstract long skip(long paramLong)
    throws IOException;
  
  public abstract int available()
    throws IOException;
  
  public abstract void close()
    throws IOException;
  
  public abstract void setSizeLimit(long paramLong);
  
  public abstract long getSizeLimit();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.InputStreamInterface
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */