package org.hsqldb.types;

import java.io.DataInput;
import java.io.IOException;
import java.io.InputStream;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;

public class BinaryData
  implements BlobData
{
  public static final BinaryData singleBitZero = new BinaryData(new byte[] { 0 }, 1L);
  public static final BinaryData singleBitOne = new BinaryData(new byte[] { -128 }, 1L);
  public static final byte[] zeroLengthBytes = new byte[0];
  public static final BinaryData zeroLengthBinary = new BinaryData(zeroLengthBytes, false);
  long id;
  protected byte[] data;
  private boolean isBits;
  private long bitLength;
  private int hashCode = 0;

  public static BinaryData getBitData(byte[] paramArrayOfByte, long paramLong)
  {
    if (paramLong == 1L)
      return paramArrayOfByte[0] == 0 ? singleBitZero : singleBitOne;
    return new BinaryData(paramArrayOfByte, paramLong);
  }

  public BinaryData(byte[] paramArrayOfByte, boolean paramBoolean)
  {
    if (paramBoolean)
      paramArrayOfByte = (byte[])ArrayUtil.duplicateArray(paramArrayOfByte);
    this.data = paramArrayOfByte;
    this.bitLength = (paramArrayOfByte.length * 8);
  }

  public BinaryData(SessionInterface paramSessionInterface, BlobData paramBlobData1, BlobData paramBlobData2)
  {
    long l = paramBlobData1.length(paramSessionInterface) + paramBlobData2.length(paramSessionInterface);
    if (l > 2147483647L)
      throw Error.error(3401);
    this.data = new byte[(int)l];
    System.arraycopy(paramBlobData1.getBytes(paramSessionInterface, 0L, (int)paramBlobData1.length(paramSessionInterface)), 0, this.data, 0, (int)paramBlobData1.length(paramSessionInterface));
    System.arraycopy(paramBlobData2.getBytes(paramSessionInterface, 0L, (int)paramBlobData2.length(paramSessionInterface)), 0, this.data, (int)paramBlobData1.length(paramSessionInterface), (int)paramBlobData2.length(paramSessionInterface));
    this.bitLength = ((int)l * 8);
  }

  public BinaryData(byte[] paramArrayOfByte, long paramLong)
  {
    this.data = paramArrayOfByte;
    this.bitLength = paramLong;
    this.isBits = true;
  }

  public BinaryData(long paramLong, DataInput paramDataInput)
  {
    this.data = new byte[(int)paramLong];
    this.bitLength = (this.data.length * 8);
    try
    {
      paramDataInput.readFully(this.data);
    }
    catch (IOException localIOException)
    {
      throw Error.error(467, localIOException);
    }
  }

  public byte[] getBytes()
  {
    return this.data;
  }

  public long length(SessionInterface paramSessionInterface)
  {
    return this.data.length;
  }

  public long bitLength(SessionInterface paramSessionInterface)
  {
    return this.bitLength;
  }

  public boolean isBits()
  {
    return this.isBits;
  }

  public byte[] getBytes(SessionInterface paramSessionInterface, long paramLong, int paramInt)
  {
    if (!isInLimits(this.data.length, paramLong, paramInt))
      throw new IndexOutOfBoundsException();
    byte[] arrayOfByte = new byte[paramInt];
    System.arraycopy(this.data, (int)paramLong, arrayOfByte, 0, paramInt);
    return arrayOfByte;
  }

  public BlobData getBlob(SessionInterface paramSessionInterface, long paramLong1, long paramLong2)
  {
    throw Error.runtimeError(201, "BinaryData");
  }

  public InputStream getBinaryStream(SessionInterface paramSessionInterface)
  {
    return new BlobInputStream(paramSessionInterface, this, 0L, length(paramSessionInterface));
  }

  public InputStream getBinaryStream(SessionInterface paramSessionInterface, long paramLong1, long paramLong2)
  {
    if (!isInLimits(this.data.length, paramLong1, paramLong2))
      throw new IndexOutOfBoundsException();
    return new BlobInputStream(paramSessionInterface, this, paramLong1, length(paramSessionInterface));
  }

  public void setBytes(SessionInterface paramSessionInterface, long paramLong, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (!isInLimits(this.data.length, paramLong, 0L))
      throw new IndexOutOfBoundsException();
    if (!isInLimits(this.data.length, paramLong, paramInt2))
      this.data = ((byte[])ArrayUtil.resizeArray(this.data, (int)paramLong + paramInt2));
    System.arraycopy(paramArrayOfByte, paramInt1, this.data, (int)paramLong, paramInt2);
    this.bitLength = (this.data.length * 8);
  }

  public void setBytes(SessionInterface paramSessionInterface, long paramLong, byte[] paramArrayOfByte)
  {
    setBytes(paramSessionInterface, paramLong, paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public void setBytes(SessionInterface paramSessionInterface, long paramLong1, BlobData paramBlobData, long paramLong2, long paramLong3)
  {
    if (paramLong3 > 2147483647L)
      throw new IndexOutOfBoundsException();
    byte[] arrayOfByte = paramBlobData.getBytes(paramSessionInterface, paramLong2, (int)paramLong3);
    setBytes(paramSessionInterface, paramLong1, arrayOfByte, 0, arrayOfByte.length);
  }

  public void setBinaryStream(SessionInterface paramSessionInterface, long paramLong, InputStream paramInputStream)
  {
  }

  public void truncate(SessionInterface paramSessionInterface, long paramLong)
  {
    if (this.data.length > paramLong)
    {
      this.data = ((byte[])ArrayUtil.resizeArray(this.data, (int)paramLong));
      this.bitLength = (this.data.length * 8);
    }
  }

  public BlobData duplicate(SessionInterface paramSessionInterface)
  {
    return new BinaryData(this.data, true);
  }

  public long position(SessionInterface paramSessionInterface, byte[] paramArrayOfByte, long paramLong)
  {
    if (paramArrayOfByte.length > this.data.length)
      return -1L;
    if (paramLong >= this.data.length)
      return -1L;
    return ArrayUtil.find(this.data, (int)paramLong, this.data.length, paramArrayOfByte);
  }

  public long position(SessionInterface paramSessionInterface, BlobData paramBlobData, long paramLong)
  {
    if (paramBlobData.length(paramSessionInterface) > this.data.length)
      return -1L;
    byte[] arrayOfByte = paramBlobData.getBytes(paramSessionInterface, 0L, (int)paramBlobData.length(paramSessionInterface));
    return position(paramSessionInterface, arrayOfByte, paramLong);
  }

  public long nonZeroLength(SessionInterface paramSessionInterface)
  {
    return this.data.length;
  }

  public long getId()
  {
    return this.id;
  }

  public void setId(long paramLong)
  {
    this.id = paramLong;
  }

  public int getStreamBlockSize()
  {
    return 524288;
  }

  public boolean isClosed()
  {
    return false;
  }

  public void free()
  {
  }

  public void setSession(SessionInterface paramSessionInterface)
  {
  }

  static boolean isInLimits(long paramLong1, long paramLong2, long paramLong3)
  {
    return (paramLong2 >= 0L) && (paramLong3 >= 0L) && (paramLong2 + paramLong3 <= paramLong1);
  }

  public boolean isBinary()
  {
    return true;
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof BinaryData))
      return Type.SQL_VARBINARY.compare(null, this, paramObject) == 0;
    return false;
  }

  public int hashCode()
  {
    if (this.hashCode == 0)
    {
      int i = 0;
      for (int j = 0; (j < this.data.length) && (j < 32); j++)
        i = i * 31 + (0xFF & this.data[j]);
      i += this.data.length;
      this.hashCode = i;
    }
    return this.hashCode;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.types.BinaryData
 * JD-Core Version:    0.6.2
 */