package org.hsqldb.types;

import java.io.InputStream;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultLob;

public class BlobDataID
  implements BlobData
{
  long field_300;
  long length = -1L;
  
  public BlobDataID(long paramLong)
  {
    this.field_300 = paramLong;
  }
  
  public BlobData duplicate(SessionInterface paramSessionInterface)
  {
    ResultLob localResultLob = ResultLob.newLobDuplicateRequest(this.field_300);
    Result localResult = paramSessionInterface.execute(localResultLob);
    if (localResult.isError()) {
      throw localResult.getException();
    }
    long l = ((ResultLob)localResult).getLobID();
    return new BlobDataID(l);
  }
  
  public void free() {}
  
  public InputStream getBinaryStream(SessionInterface paramSessionInterface)
  {
    long l = length(paramSessionInterface);
    return new BlobInputStream(paramSessionInterface, this, 0L, l);
  }
  
  public InputStream getBinaryStream(SessionInterface paramSessionInterface, long paramLong1, long paramLong2)
  {
    return new BlobInputStream(paramSessionInterface, this, paramLong1, paramLong2);
  }
  
  public byte[] getBytes()
  {
    return null;
  }
  
  public byte[] getBytes(SessionInterface paramSessionInterface, long paramLong, int paramInt)
  {
    ResultLob localResultLob = ResultLob.newLobGetBytesRequest(this.field_300, paramLong, paramInt);
    Result localResult = paramSessionInterface.execute(localResultLob);
    if (localResult.isError()) {
      throw Error.error(localResult);
    }
    return ((ResultLob)localResult).getByteArray();
  }
  
  public BlobData getBlob(SessionInterface paramSessionInterface, long paramLong1, long paramLong2)
  {
    ResultLob localResultLob = ResultLob.newLobGetRequest(this.field_300, paramLong1, paramLong2);
    Result localResult = paramSessionInterface.execute(localResultLob);
    if (localResult.isError()) {
      throw Error.error(localResult);
    }
    long l = ((ResultLob)localResult).getLobID();
    return new BlobDataID(l);
  }
  
  public long getId()
  {
    return this.field_300;
  }
  
  public void setId(long paramLong)
  {
    this.field_300 = paramLong;
  }
  
  public int getStreamBlockSize()
  {
    return 0;
  }
  
  public boolean isClosed()
  {
    return false;
  }
  
  public long length(SessionInterface paramSessionInterface)
  {
    if (this.length > -1L) {
      return this.length;
    }
    ResultLob localResultLob = ResultLob.newLobGetLengthRequest(this.field_300);
    Result localResult = paramSessionInterface.execute(localResultLob);
    if (localResult.isError()) {
      throw localResult.getException();
    }
    this.length = ((ResultLob)localResult).getBlockLength();
    return this.length;
  }
  
  public long bitLength(SessionInterface paramSessionInterface)
  {
    return length(paramSessionInterface) * 8L;
  }
  
  public boolean isBits()
  {
    return false;
  }
  
  public long position(SessionInterface paramSessionInterface, BlobData paramBlobData, long paramLong)
  {
    ResultLob localResultLob = ResultLob.newLobGetCharPatternPositionRequest(this.field_300, paramBlobData.getId(), paramLong);
    Result localResult = paramSessionInterface.execute(localResultLob);
    if (localResult.isError()) {
      throw localResult.getException();
    }
    return ((ResultLob)localResult).getOffset();
  }
  
  public long position(SessionInterface paramSessionInterface, byte[] paramArrayOfByte, long paramLong)
  {
    ResultLob localResultLob = ResultLob.newLobGetBytePatternPositionRequest(this.field_300, paramArrayOfByte, paramLong);
    Result localResult = paramSessionInterface.execute(localResultLob);
    if (localResult.isError()) {
      throw localResult.getException();
    }
    return ((ResultLob)localResult).getOffset();
  }
  
  public long nonZeroLength(SessionInterface paramSessionInterface)
  {
    ResultLob localResultLob = ResultLob.newLobGetTruncateLength(this.field_300);
    Result localResult = paramSessionInterface.execute(localResultLob);
    if (localResult.isError()) {
      throw localResult.getException();
    }
    return ((ResultLob)localResult).getBlockLength();
  }
  
  public void setBytes(SessionInterface paramSessionInterface, long paramLong, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if ((paramInt1 != 0) || (paramInt2 != paramArrayOfByte.length))
    {
      if (!BinaryData.isInLimits(paramArrayOfByte.length, paramInt1, paramInt2)) {
        throw new IndexOutOfBoundsException();
      }
      localObject = new byte[paramInt2];
      System.arraycopy(paramArrayOfByte, paramInt1, localObject, 0, paramInt2);
      paramArrayOfByte = (byte[])localObject;
    }
    Object localObject = ResultLob.newLobSetBytesRequest(this.field_300, paramLong, paramArrayOfByte);
    Result localResult = paramSessionInterface.execute((Result)localObject);
    if (localResult.isError()) {
      throw localResult.getException();
    }
    this.length = ((ResultLob)localResult).getBlockLength();
  }
  
  public void setBytes(SessionInterface paramSessionInterface, long paramLong, byte[] paramArrayOfByte)
  {
    setBytes(paramSessionInterface, paramLong, paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public void setBytes(SessionInterface paramSessionInterface, long paramLong1, BlobData paramBlobData, long paramLong2, long paramLong3)
  {
    if (paramLong3 > 2147483647L) {
      throw new IndexOutOfBoundsException();
    }
    byte[] arrayOfByte = paramBlobData.getBytes(paramSessionInterface, paramLong2, (int)paramLong3);
    setBytes(paramSessionInterface, paramLong1, arrayOfByte, 0, arrayOfByte.length);
  }
  
  public void setBinaryStream(SessionInterface paramSessionInterface, long paramLong, InputStream paramInputStream) {}
  
  public void setSession(SessionInterface paramSessionInterface) {}
  
  public void truncate(SessionInterface paramSessionInterface, long paramLong)
  {
    ResultLob localResultLob = ResultLob.newLobTruncateRequest(this.field_300, paramLong);
    Result localResult = paramSessionInterface.execute(localResultLob);
    if (localResult.isError()) {
      throw localResult.getException();
    }
  }
  
  public boolean isBinary()
  {
    return true;
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof BlobDataID)) {
      return this.field_300 == ((BlobDataID)paramObject).field_300;
    }
    return false;
  }
  
  public int hashCode()
  {
    return (int)this.field_300;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.types.BlobDataID
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */