package org.hsqldb.types;

import java.io.IOException;
import java.io.Reader;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;
import org.hsqldb.lib.java.JavaSystem;

public final class ClobInputStream
  extends Reader
{
  final ClobData clob;
  final long availableLength;
  long bufferOffset;
  long currentPosition;
  char[] buffer;
  boolean isClosed;
  int streamBlockSize;
  public final SessionInterface session;
  
  public ClobInputStream(SessionInterface paramSessionInterface, ClobData paramClobData, long paramLong1, long paramLong2)
  {
    long l = paramClobData.length(paramSessionInterface);
    this.session = paramSessionInterface;
    this.clob = paramClobData;
    this.availableLength = (paramLong1 + Math.min(paramLong2, l - paramLong1));
    this.currentPosition = paramLong1;
    this.streamBlockSize = paramSessionInterface.getStreamBlockSize();
  }
  
  public int read()
    throws IOException
  {
    checkClosed();
    if (this.currentPosition >= this.availableLength) {
      return -1;
    }
    if ((this.buffer == null) || (this.currentPosition >= this.bufferOffset + this.buffer.length)) {
      try
      {
        checkClosed();
        readIntoBuffer();
      }
      catch (Exception localException)
      {
        throw JavaSystem.toIOException(localException);
      }
    }
    int i = this.buffer[((int)(this.currentPosition - this.bufferOffset))];
    this.currentPosition += 1L;
    return i;
  }
  
  public int read(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    checkClosed();
    if (this.currentPosition == this.availableLength) {
      return -1;
    }
    if (this.currentPosition + paramInt2 > this.availableLength) {
      paramInt2 = (int)(this.availableLength - this.currentPosition);
    }
    for (int i = paramInt1; i < paramInt2; i++) {
      paramArrayOfChar[i] = ((char)read());
    }
    return paramInt2;
  }
  
  public long skip(long paramLong)
    throws IOException
  {
    checkClosed();
    if (paramLong <= 0L) {
      return 0L;
    }
    if (this.currentPosition + paramLong > this.availableLength) {
      paramLong = this.availableLength - this.currentPosition;
    }
    this.currentPosition += paramLong;
    return paramLong;
  }
  
  public int available()
  {
    long l = this.availableLength - this.currentPosition;
    if (l > 2147483647L) {
      return 2147483647;
    }
    return (int)l;
  }
  
  public void close()
    throws IOException
  {
    this.isClosed = true;
  }
  
  private void checkClosed()
    throws IOException
  {
    if (this.isClosed) {
      throw new IOException(Error.getMessage(3475));
    }
  }
  
  private void readIntoBuffer()
  {
    long l = this.availableLength - this.currentPosition;
    if ((l > 0L) || (l > this.streamBlockSize)) {
      l = this.streamBlockSize;
    }
    this.buffer = this.clob.getChars(this.session, this.currentPosition, (int)l);
    this.bufferOffset = this.currentPosition;
  }
  
  static boolean isInLimits(long paramLong1, long paramLong2, long paramLong3)
  {
    return (paramLong2 >= 0L) && (paramLong3 >= 0L) && (paramLong2 + paramLong3 <= paramLong1);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.types.ClobInputStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */