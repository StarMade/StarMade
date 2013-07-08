package org.hsqldb.types;

import java.io.Reader;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultLob;

public class ClobDataID
  implements ClobData
{
  long field_300;
  long length = -1L;
  
  public ClobDataID(long paramLong)
  {
    this.field_300 = paramLong;
  }
  
  public char[] getChars(SessionInterface paramSessionInterface, long paramLong, int paramInt)
  {
    ResultLob localResultLob = ResultLob.newLobGetCharsRequest(this.field_300, paramLong, paramInt);
    Result localResult = paramSessionInterface.execute(localResultLob);
    if (localResult.isError()) {
      throw localResult.getException();
    }
    return ((ResultLob)localResult).getCharArray();
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
  
  public String getSubString(SessionInterface paramSessionInterface, long paramLong, int paramInt)
  {
    char[] arrayOfChar = getChars(paramSessionInterface, paramLong, paramInt);
    return new String(arrayOfChar);
  }
  
  public ClobData duplicate(SessionInterface paramSessionInterface)
  {
    ResultLob localResultLob = ResultLob.newLobDuplicateRequest(this.field_300);
    Result localResult = paramSessionInterface.execute(localResultLob);
    if (localResult.isError()) {
      throw localResult.getException();
    }
    long l = ((ResultLob)localResult).getLobID();
    return new ClobDataID(l);
  }
  
  public ClobData getClob(SessionInterface paramSessionInterface, long paramLong1, long paramLong2)
  {
    ResultLob localResultLob = ResultLob.newLobGetRequest(this.field_300, paramLong1, paramLong2);
    Result localResult = paramSessionInterface.execute(localResultLob);
    if (localResult.isError()) {
      throw localResult.getException();
    }
    long l = ((ResultLob)localResult).getLobID();
    return new ClobDataID(l);
  }
  
  public void truncate(SessionInterface paramSessionInterface, long paramLong)
  {
    ResultLob localResultLob = ResultLob.newLobTruncateRequest(this.field_300, paramLong);
    Result localResult = paramSessionInterface.execute(localResultLob);
    if (localResult.isError()) {
      throw localResult.getException();
    }
    this.length = ((ResultLob)localResult).getBlockLength();
  }
  
  public Reader getCharacterStream(SessionInterface paramSessionInterface)
  {
    long l = length(paramSessionInterface);
    return new ClobInputStream(paramSessionInterface, this, 0L, l);
  }
  
  public void setCharacterStream(SessionInterface paramSessionInterface, long paramLong, Reader paramReader) {}
  
  public void setString(SessionInterface paramSessionInterface, long paramLong, String paramString)
  {
    ResultLob localResultLob = ResultLob.newLobSetCharsRequest(this.field_300, paramLong, paramString.toCharArray());
    Result localResult = paramSessionInterface.execute(localResultLob);
    if (localResult.isError()) {
      throw localResult.getException();
    }
    this.length = ((ResultLob)localResult).getBlockLength();
  }
  
  public void setClob(SessionInterface paramSessionInterface, long paramLong1, ClobData paramClobData, long paramLong2, long paramLong3) {}
  
  public void setChars(SessionInterface paramSessionInterface, long paramLong, char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    if ((paramInt1 != 0) || (paramInt2 != paramArrayOfChar.length))
    {
      if (!isInLimits(paramArrayOfChar.length, paramInt1, paramInt2)) {
        throw Error.error(3401);
      }
      if ((paramInt1 != 0) || (paramInt2 != paramArrayOfChar.length))
      {
        localObject = new char[paramInt2];
        System.arraycopy(paramArrayOfChar, paramInt1, localObject, 0, paramInt2);
        paramArrayOfChar = (char[])localObject;
      }
    }
    Object localObject = ResultLob.newLobSetCharsRequest(this.field_300, paramLong, paramArrayOfChar);
    Result localResult = paramSessionInterface.execute((Result)localObject);
    if (localResult.isError()) {
      throw localResult.getException();
    }
    this.length = ((ResultLob)localResult).getBlockLength();
  }
  
  public long position(SessionInterface paramSessionInterface, String paramString, long paramLong)
  {
    ResultLob localResultLob = ResultLob.newLobGetCharPatternPositionRequest(this.field_300, paramString.toCharArray(), paramLong);
    Result localResult = paramSessionInterface.execute(localResultLob);
    if (localResult.isError()) {
      throw localResult.getException();
    }
    return ((ResultLob)localResult).getOffset();
  }
  
  public long position(SessionInterface paramSessionInterface, ClobData paramClobData, long paramLong)
  {
    ResultLob localResultLob = ResultLob.newLobGetCharPatternPositionRequest(this.field_300, paramClobData.getId(), paramLong);
    Result localResult = paramSessionInterface.execute(localResultLob);
    if (localResult.isError()) {
      throw localResult.getException();
    }
    return ((ResultLob)localResult).getOffset();
  }
  
  public long nonSpaceLength(SessionInterface paramSessionInterface)
  {
    ResultLob localResultLob = ResultLob.newLobGetTruncateLength(this.field_300);
    Result localResult = paramSessionInterface.execute(localResultLob);
    if (localResult.isError()) {
      throw localResult.getException();
    }
    return ((ResultLob)localResult).getBlockLength();
  }
  
  public Reader getCharacterStream(SessionInterface paramSessionInterface, long paramLong1, long paramLong2)
  {
    return new ClobInputStream(paramSessionInterface, this, paramLong1, paramLong2);
  }
  
  public long getId()
  {
    return this.field_300;
  }
  
  public void setId(long paramLong)
  {
    this.field_300 = paramLong;
  }
  
  static boolean isInLimits(long paramLong1, long paramLong2, long paramLong3)
  {
    return (paramLong2 >= 0L) && (paramLong3 >= 0L) && (paramLong2 + paramLong3 <= paramLong1);
  }
  
  public void setSession(SessionInterface paramSessionInterface) {}
  
  public boolean isBinary()
  {
    return false;
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
 * Qualified Name:     org.hsqldb.types.ClobDataID
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */