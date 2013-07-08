package org.hsqldb.rowio;

import java.io.IOException;
import org.hsqldb.types.Type;

public abstract interface RowInputInterface
{
  public abstract long getPos();
  
  public abstract int getSize();
  
  public abstract int readType()
    throws IOException;
  
  public abstract String readString()
    throws IOException;
  
  public abstract byte readByte()
    throws IOException;
  
  public abstract short readShort()
    throws IOException;
  
  public abstract int readInt()
    throws IOException;
  
  public abstract long readLong()
    throws IOException;
  
  public abstract Object[] readData(Type[] paramArrayOfType)
    throws IOException;
  
  public abstract void resetRow(long paramLong, int paramInt)
    throws IOException;
  
  public abstract byte[] getBuffer();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.rowio.RowInputInterface
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */