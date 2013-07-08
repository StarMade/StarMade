package net.rudp;

import java.io.IOException;
import java.io.OutputStream;

class ReliableSocketOutputStream
  extends OutputStream
{
  protected ReliableSocket _sock;
  protected byte[] _buf;
  protected int _count;
  
  public ReliableSocketOutputStream(ReliableSocket paramReliableSocket)
    throws IOException
  {
    if (paramReliableSocket == null) {
      throw new NullPointerException("sock");
    }
    this._sock = paramReliableSocket;
    this._buf = new byte[this._sock.getSendBufferSize()];
    this._count = 0;
  }
  
  public synchronized void write(int paramInt)
    throws IOException
  {
    if (this._count >= this._buf.length) {
      flush();
    }
    this._buf[(this._count++)] = ((byte)(paramInt & 0xFF));
  }
  
  public synchronized void write(byte[] paramArrayOfByte)
    throws IOException
  {
    write(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public synchronized void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (paramArrayOfByte == null) {
      throw new NullPointerException();
    }
    if ((paramInt1 < 0) || (paramInt2 < 0) || (paramInt1 + paramInt2 > paramArrayOfByte.length)) {
      throw new IndexOutOfBoundsException();
    }
    int j = 0;
    while (j < paramInt2)
    {
      int i = Math.min(this._buf.length, paramInt2 - j);
      if (i > this._buf.length - this._count) {
        flush();
      }
      System.arraycopy(paramArrayOfByte, paramInt1 + j, this._buf, this._count, i);
      this._count += i;
      j += i;
    }
  }
  
  public synchronized void flush()
    throws IOException
  {
    if (this._count > 0)
    {
      this._sock.write(this._buf, 0, this._count);
      this._count = 0;
    }
  }
  
  public synchronized void close()
    throws IOException
  {
    flush();
    this._sock.shutdownOutput();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     net.rudp.ReliableSocketOutputStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */