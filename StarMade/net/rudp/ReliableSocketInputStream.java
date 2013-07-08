package net.rudp;

import java.io.IOException;
import java.io.InputStream;

class ReliableSocketInputStream
  extends InputStream
{
  protected ReliableSocket _sock;
  protected byte[] _buf;
  protected int _pos;
  protected int _count;
  
  public ReliableSocketInputStream(ReliableSocket paramReliableSocket)
    throws IOException
  {
    if (paramReliableSocket == null) {
      throw new NullPointerException("sock");
    }
    this._sock = paramReliableSocket;
    this._buf = new byte[this._sock.getReceiveBufferSize()];
    this._pos = (this._count = 0);
  }
  
  public synchronized int read()
    throws IOException
  {
    if (readImpl() < 0) {
      return -1;
    }
    return this._buf[(this._pos++)] & 0xFF;
  }
  
  public synchronized int read(byte[] paramArrayOfByte)
    throws IOException
  {
    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public synchronized int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (paramArrayOfByte == null) {
      throw new NullPointerException();
    }
    if ((paramInt1 < 0) || (paramInt2 < 0) || (paramInt1 + paramInt2 > paramArrayOfByte.length)) {
      throw new IndexOutOfBoundsException();
    }
    if (readImpl() < 0) {
      return -1;
    }
    int i = Math.min(available(), paramInt2);
    System.arraycopy(this._buf, this._pos, paramArrayOfByte, paramInt1, i);
    this._pos += i;
    return i;
  }
  
  public synchronized int available()
  {
    return this._count - this._pos;
  }
  
  public boolean markSupported()
  {
    return false;
  }
  
  public void close()
    throws IOException
  {
    this._sock.shutdownInput();
  }
  
  private int readImpl()
    throws IOException
  {
    if (available() == 0)
    {
      this._count = this._sock.read(this._buf, 0, this._buf.length);
      this._pos = 0;
    }
    return this._count;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     net.rudp.ReliableSocketInputStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */