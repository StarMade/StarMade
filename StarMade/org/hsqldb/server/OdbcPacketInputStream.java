package org.hsqldb.server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.hsqldb.HsqlException;
import org.hsqldb.types.BinaryData;

class OdbcPacketInputStream
  extends DataInputStream
{
  char packetType;
  private InputStream bufferStream;
  
  static OdbcPacketInputStream newOdbcPacketInputStream(char paramChar, InputStream paramInputStream, int paramInt)
    throws IOException
  {
    return newOdbcPacketInputStream(paramChar, paramInputStream, new Integer(paramInt));
  }
  
  static OdbcPacketInputStream newOdbcPacketInputStream(char paramChar, InputStream paramInputStream)
    throws IOException
  {
    return newOdbcPacketInputStream(paramChar, paramInputStream, null);
  }
  
  private static OdbcPacketInputStream newOdbcPacketInputStream(char paramChar, InputStream paramInputStream, Integer paramInteger)
    throws IOException
  {
    int k = 0;
    int j;
    if (paramInteger == null)
    {
      arrayOfByte = new byte[4];
      i = 0;
      while ((j = paramInputStream.read(arrayOfByte, i, arrayOfByte.length - i)) > 0) {
        i += j;
      }
      if (i != arrayOfByte.length) {
        throw new EOFException("Failed to read size header int");
      }
      k = ((arrayOfByte[0] & 0xFF) << 24) + ((arrayOfByte[1] & 0xFF) << 16) + ((arrayOfByte[2] & 0xFF) << 8) + (arrayOfByte[3] & 0xFF) - 4;
    }
    else
    {
      k = paramInteger.intValue();
    }
    byte[] arrayOfByte = new byte[k];
    int i = 0;
    while ((j = paramInputStream.read(arrayOfByte, i, arrayOfByte.length - i)) > 0) {
      i += j;
    }
    if (i != arrayOfByte.length) {
      throw new EOFException("Failed to read packet contents from given stream");
    }
    return new OdbcPacketInputStream(paramChar, new ByteArrayInputStream(arrayOfByte));
  }
  
  private OdbcPacketInputStream(char paramChar, InputStream paramInputStream)
  {
    super(paramInputStream);
    this.packetType = paramChar;
  }
  
  Map readStringPairs()
    throws IOException
  {
    HashMap localHashMap = new HashMap();
    for (;;)
    {
      String str = readString();
      if (str.length() < 1) {
        break;
      }
      localHashMap.put(str, readString());
    }
    return localHashMap;
  }
  
  String readString()
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    localByteArrayOutputStream.write(88);
    localByteArrayOutputStream.write(88);
    int i;
    while ((i = readByte()) > 0) {
      localByteArrayOutputStream.write((byte)i);
    }
    byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
    localByteArrayOutputStream.close();
    int j = arrayOfByte.length - 2;
    arrayOfByte[0] = ((byte)(j >>> 8));
    arrayOfByte[1] = ((byte)j);
    DataInputStream localDataInputStream = new DataInputStream(new ByteArrayInputStream(arrayOfByte));
    String str = localDataInputStream.readUTF();
    localDataInputStream.close();
    return str;
  }
  
  BinaryData readSizedBinaryData()
    throws IOException
  {
    int i = readInt();
    try
    {
      return i < 0 ? null : new BinaryData(i, this);
    }
    catch (HsqlException localHsqlException)
    {
      throw new IOException(localHsqlException.getMessage());
    }
  }
  
  String readSizedString()
    throws IOException
  {
    int i = readInt();
    return i < 0 ? null : readString(i);
  }
  
  String readString(int paramInt)
    throws IOException
  {
    int i = 0;
    byte[] arrayOfByte = new byte[paramInt + 2];
    arrayOfByte[0] = ((byte)(paramInt >>> 8));
    arrayOfByte[1] = ((byte)paramInt);
    while (((j = read(arrayOfByte, 2 + i, paramInt - i)) > -1) && (i < paramInt)) {
      i += j;
    }
    if (i != paramInt) {
      throw new EOFException("Packet ran dry");
    }
    for (int j = 2; j < arrayOfByte.length - 1; j++) {
      if (arrayOfByte[j] == 0) {
        throw new RuntimeException("Null internal to String at offset " + (j - 2));
      }
    }
    DataInputStream localDataInputStream = new DataInputStream(new ByteArrayInputStream(arrayOfByte));
    String str = localDataInputStream.readUTF();
    localDataInputStream.close();
    return str;
  }
  
  public char readByteChar()
    throws IOException
  {
    return (char)readByte();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.server.OdbcPacketInputStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */