package org.hsqldb.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

class OdbcPacketOutputStream
  extends java.io.DataOutputStream
{
  private ByteArrayOutputStream byteArrayOutputStream;
  private ByteArrayOutputStream stringWriterOS = new ByteArrayOutputStream();
  private java.io.DataOutputStream stringWriterDos = new java.io.DataOutputStream(this.stringWriterOS);
  private int packetStart = 0;
  
  public int getSize()
  {
    return this.written - this.packetStart;
  }
  
  synchronized void write(String paramString)
    throws IOException
  {
    write(paramString, true);
  }
  
  synchronized void write(String paramString, boolean paramBoolean)
    throws IOException
  {
    this.stringWriterDos.writeUTF(paramString);
    write(this.stringWriterOS.toByteArray(), 2, this.stringWriterOS.size() - 2);
    this.stringWriterOS.reset();
    if (paramBoolean) {
      writeByte(0);
    }
  }
  
  synchronized void writeSized(String paramString)
    throws IOException
  {
    this.stringWriterDos.writeUTF(paramString);
    byte[] arrayOfByte = this.stringWriterOS.toByteArray();
    this.stringWriterOS.reset();
    writeInt(arrayOfByte.length - 2);
    write(arrayOfByte, 2, arrayOfByte.length - 2);
  }
  
  synchronized void reset()
    throws IOException
  {
    this.byteArrayOutputStream.reset();
    this.packetStart = this.written;
    writeInt(-1);
  }
  
  static OdbcPacketOutputStream newOdbcPacketOutputStream()
    throws IOException
  {
    return new OdbcPacketOutputStream(new ByteArrayOutputStream());
  }
  
  protected OdbcPacketOutputStream(ByteArrayOutputStream paramByteArrayOutputStream)
    throws IOException
  {
    super(paramByteArrayOutputStream);
    this.byteArrayOutputStream = paramByteArrayOutputStream;
    reset();
  }
  
  synchronized int xmit(char paramChar, org.hsqldb.lib.DataOutputStream paramDataOutputStream)
    throws IOException
  {
    byte[] arrayOfByte = this.byteArrayOutputStream.toByteArray();
    arrayOfByte[0] = ((byte)(arrayOfByte.length >> 24));
    arrayOfByte[1] = ((byte)(arrayOfByte.length >> 16));
    arrayOfByte[2] = ((byte)(arrayOfByte.length >> 8));
    arrayOfByte[3] = ((byte)arrayOfByte.length);
    reset();
    paramDataOutputStream.writeByte(paramChar);
    paramDataOutputStream.write(arrayOfByte);
    paramDataOutputStream.flush();
    return arrayOfByte.length;
  }
  
  public synchronized void close()
    throws IOException
  {
    super.close();
    this.stringWriterDos.close();
  }
  
  public synchronized void writeByteChar(char paramChar)
    throws IOException
  {
    writeByte(paramChar);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.server.OdbcPacketOutputStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */